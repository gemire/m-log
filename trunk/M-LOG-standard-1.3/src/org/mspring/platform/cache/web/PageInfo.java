/**
 * 
 */
package org.mspring.platform.cache.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since 2013-2-5
 * @description
 * @TODO
 */
public class PageInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1446667125448601048L;

    private static final Logger LOG = Logger.getLogger(PageInfo.class);

    private static final int FOUR_KB = 4196;
    private static final int GZIP_MAGIC_NUMBER_BYTE_1 = 31;
    private static final int GZIP_MAGIC_NUMBER_BYTE_2 = -117;
    private static final long ONE_YEAR_IN_SECONDS = 60 * 60 * 24 * 365;
    private final ArrayList<Header<? extends Serializable>> responseHeaders = new ArrayList<Header<? extends Serializable>>();
    private final ArrayList serializableCookies = new ArrayList();
    private String contentType;
    private byte[] gzippedBody;
    private byte[] ungzippedBody;
    private int statusCode;
    private boolean storeGzipped;
    private Date created;
    private long timeToLiveSeconds;
    private transient HttpDateFormatter httpDateFormatter;

    @Deprecated
    public PageInfo(final int statusCode, final String contentType, final Collection headers, final Collection cookies, final byte[] body, boolean storeGzipped, long timeToLiveSeconds) throws AlreadyGzippedException {

        // Convert the old
        final Collection<Header<? extends Serializable>> stringHeadersBuilder;
        if (headers == null) {
            stringHeadersBuilder = null;
        } else {
            stringHeadersBuilder = new ArrayList<Header<? extends Serializable>>(headers.size());
            for (final String[] header : (Collection<String[]>) headers) {
                stringHeadersBuilder.add(new Header<String>(header[0], header[1]));
            }
        }

        this.init(statusCode, contentType, stringHeadersBuilder, cookies, body, storeGzipped, timeToLiveSeconds);
    }

    public PageInfo(final int statusCode, final String contentType, final Collection cookies, final byte[] body, boolean storeGzipped, long timeToLiveSeconds, final Collection<Header<? extends Serializable>> headers) throws AlreadyGzippedException {
        this.init(statusCode, contentType, headers, cookies, body, storeGzipped, timeToLiveSeconds);
    }

    private void init(final int statusCode, final String contentType, final Collection<Header<? extends Serializable>> headers, final Collection cookies, final byte[] body, boolean storeGzipped, long timeToLiveSeconds) throws AlreadyGzippedException {
        if (headers != null) {
            this.responseHeaders.addAll(headers);
        }
        setTimeToLiveWithCheckForNeverExpires(timeToLiveSeconds);

        created = new Date();
        this.contentType = contentType;
        this.storeGzipped = storeGzipped;
        this.statusCode = statusCode;
        this.timeToLiveSeconds = timeToLiveSeconds;
        try {
            if (storeGzipped) {
                // gunzip on demand
                ungzippedBody = null;
                if (isBodyParameterGzipped()) {
                    gzippedBody = body;
                } else {
                    gzippedBody = gzip(body);
                }
            } else {
                if (isBodyParameterGzipped()) {
                    throw new IllegalArgumentException("Non gzip content has been gzipped.");
                } else {
                    ungzippedBody = body;
                }
            }
        } catch (IOException e) {
            LOG.error("Error ungzipping gzipped body", e);
        }

    }

    protected void setTimeToLiveWithCheckForNeverExpires(long timeToLiveSeconds) {
        // 0 means eternal
        if (timeToLiveSeconds == 0 || timeToLiveSeconds > ONE_YEAR_IN_SECONDS) {
            this.timeToLiveSeconds = ONE_YEAR_IN_SECONDS;
        } else {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }
    }

    private void extractCookies(Collection cookies) {
        if (cookies != null) {
            for (Iterator iterator = cookies.iterator(); iterator.hasNext();) {
                final Cookie cookie = (Cookie) iterator.next();
                serializableCookies.add(new SerializableCookie(cookie));
            }
        }
    }

    private byte[] gzip(byte[] ungzipped) throws IOException, AlreadyGzippedException {
        if (isGzipped(ungzipped)) {
            throw new AlreadyGzippedException("The byte[] is already gzipped. It should not be gzipped again.");
        }
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bytes);
        gzipOutputStream.write(ungzipped);
        gzipOutputStream.close();
        return bytes.toByteArray();
    }

    private boolean isBodyParameterGzipped() {
        for (final Header<? extends Serializable> header : this.responseHeaders) {
            if ("gzip".equals(header.getValue())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGzipped(byte[] candidate) {
        if (candidate == null || candidate.length < 2) {
            return false;
        } else {
            return (candidate[0] == GZIP_MAGIC_NUMBER_BYTE_1 && candidate[1] == GZIP_MAGIC_NUMBER_BYTE_2);
        }
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getGzippedBody() {
        if (storeGzipped) {
            return gzippedBody;
        } else {
            return null;
        }
    }

    private HttpDateFormatter getHttpDateFormatter() {
        if (this.httpDateFormatter == null) {
            this.httpDateFormatter = new HttpDateFormatter();
        }

        return this.httpDateFormatter;
    }

    @Deprecated
    public List getResponseHeaders() {
        final List<String[]> headers = new ArrayList<String[]>(this.responseHeaders.size());

        for (final Header<? extends Serializable> header : this.responseHeaders) {
            switch (header.getType()) {
            case STRING:
                headers.add(new String[] { header.getName(), (String) header.getValue() });
                break;
            case DATE:
                final HttpDateFormatter localHttpDateFormatter = this.getHttpDateFormatter();
                final String formattedValue = localHttpDateFormatter.formatHttpDate(new Date((Long) header.getValue()));
                headers.add(new String[] { header.getName(), formattedValue });
                break;
            case INT:
                headers.add(new String[] { header.getName(), ((Integer) header.getValue()).toString() });
                break;
            default:
                throw new IllegalArgumentException("No mapping for Header: " + header);
            }
        }

        return Collections.unmodifiableList(headers);
    }

    public List<Header<? extends Serializable>> getHeaders() {
        return this.responseHeaders;
    }

    public List getSerializableCookies() {
        return serializableCookies;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public byte[] getUngzippedBody() throws IOException {
        if (storeGzipped) {
            return ungzip(gzippedBody);
        } else {
            return ungzippedBody;
        }
    }

    private byte[] ungzip(final byte[] gzipped) throws IOException {
        final GZIPInputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(gzipped));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(gzipped.length);
        final byte[] buffer = new byte[FOUR_KB];
        int bytesRead = 0;
        while (bytesRead != -1) {
            bytesRead = inputStream.read(buffer, 0, FOUR_KB);
            if (bytesRead != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
        }
        byte[] ungzipped = byteArrayOutputStream.toByteArray();
        inputStream.close();
        byteArrayOutputStream.close();
        return ungzipped;
    }

    public boolean hasGzippedBody() {
        return (gzippedBody != null);
    }

    public boolean hasUngzippedBody() {
        return (ungzippedBody != null);
    }

    public boolean isOk() {
        return (statusCode == HttpServletResponse.SC_OK);
    }

    public Date getCreated() {
        return created;
    }

    public long getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }
}
