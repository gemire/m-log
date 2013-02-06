/**
 * 
 */
package org.mspring.platform.cache.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gao Youbo
 * @since 2013-2-5
 * @description
 * @TODO
 */
public class HttpDateFormatter {
    private static final Logger LOG = LoggerFactory.getLogger(HttpDateFormatter.class);

    private final SimpleDateFormat httpDateFormat;

    /**
     * Constructs a new formatter.
     * <p/>
     * Note that this class is not thread-safe for use by multiple threads, as
     * SimpleDateFormat is not. Each thread should create their own instance of
     * this class.
     */
    public HttpDateFormatter() {
        httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * 
     * @param date
     * @return A date formatted in accordance with Section 3.3.1 of RFC 2616
     */
    public synchronized String formatHttpDate(Date date) {
        return httpDateFormat.format(date);
    }

    /**
     * Parses dates supplied in accordance with Section 3.3.1 of RFC 2616
     * 
     * @param date
     *            a date formatted in accordance with Section 3.3.1 of RFC 2616
     * @return the parsed Date. If the date cannot be parsed, the start of POSIX
     *         time, 1/1/1970 is returned, which will have the effect of
     *         expiring the content.
     */
    public synchronized Date parseDateFromHttpDate(String date) {
        try {
            return httpDateFormat.parse(date);
        } catch (ParseException e) {
            LOG.debug("ParseException on date {}. 1/1/1970 will be returned", date);
            return new Date(0);
        }
    }
}
