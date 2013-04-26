/**
 * 
 */
package org.mspring.mlog.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.BlockingCache;

import org.apache.log4j.Logger;
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.platform.cache.CacheUtils;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2013-2-6
 * @description
 * @TODO
 */
public class CacheTag extends BodyTagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -7602820360123796597L;

    private static final Logger log = Logger.getLogger(CacheTag.class);

    private final static String CACHE_TAG_COUNTER_KEY = "__cache_tag_counter";
    private final static Integer blockingTimeoutMillis = 1000 * 10;

    private CacheManager cacheManager = CacheManager.getInstance();

    private String cacheName;
    private String cacheKey;
    private long expiry;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    protected BlockingCache blockingCache;

    @Override
    public int doStartTag() throws JspException {
        // TODO Auto-generated method stub
        // We can only skip the body if the cache has the data
        doInit();

        int returnCode = EVAL_BODY_BUFFERED;

        if (blockingCache != null && StringUtils.isNotBlank(cacheKey)) {
            Object content = CacheUtils.getObjectValue(blockingCache, cacheKey);
            if (content != null) {
                try {
                    pageContext.getOut().write(content.toString());
                    returnCode = SKIP_BODY;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    throw new JspTagException("IO Exception: " + e.getMessage());
                }
            }
        }

        if (returnCode == EVAL_BODY_BUFFERED) {
            log.debug("<cache>: Cached content not used: New cache entry, cache stale or scope flushed : " + cacheKey);
        }
        return returnCode;
    }

    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        return EVAL_PAGE;
    }

    @Override
    public int doAfterBody() throws JspException {
        // TODO Auto-generated method stub
        String body = null;
        try {
            if (bodyContent != null && (body = bodyContent.getString()) != null) {
                log.debug("<cache>: Updating cache entry with new content : " + cacheKey);
                if (blockingCache != null && StringUtils.isNotBlank(cacheKey)) {
                    CacheUtils.updateValue(blockingCache, cacheKey, body, new Long(expiry).intValue());
                }

            } else {
                log.info("<cache>: Missing cached content : " + cacheKey);
                body = "Missing cached content";
            }
            bodyContent.clearBody();
            bodyContent.write(body);
            bodyContent.writeOut(bodyContent.getEnclosingWriter());
        } catch (Exception e) {
            // TODO: handle exception
            throw new JspTagException("IO Error: " + e.getMessage());
        }
        return SKIP_BODY;
    }

    protected void doInit() {
        if (StringUtils.isBlank(cacheName)) {
            this.cacheName = CacheService.CacheName.WIDGET_CACHE_NAME;
        }
        if (StringUtils.isBlank(cacheKey)) {
            // cacheKey = String.valueOf(new Date().getTime());
            String suffix = null;
            synchronized (pageContext.getRequest()) {
                Object o = pageContext.getRequest().getAttribute(CACHE_TAG_COUNTER_KEY);
                if (o == null) {
                    suffix = "1";
                } else {
                    suffix = Integer.toString(Integer.parseInt((String) o) + 1);
                }
            }
            pageContext.getRequest().setAttribute(CACHE_TAG_COUNTER_KEY, suffix);
            this.cacheKey = generateEntryKey((HttpServletRequest) pageContext.getRequest(), suffix);
        }
        if (expiry == 0) {
            this.expiry = CacheService.ONE_MINUTE;
        }

        synchronized (this.getClass()) {
            if (blockingCache == null) {
                final String localCacheName = getCacheName();
                Ehcache cache = cacheManager.getEhcache(localCacheName);
                if (cache == null) {
                    throw new CacheException("cache '" + localCacheName + "' not found in configuration");
                }
                if (!(cache instanceof BlockingCache)) {
                    // decorate and substitute
                    BlockingCache newBlockingCache = new BlockingCache(cache);
                    cacheManager.replaceCacheWithDecoratedCache(cache, newBlockingCache);
                }
                blockingCache = (BlockingCache) cacheManager.getEhcache(localCacheName);
                blockingCache.setTimeoutMillis(blockingTimeoutMillis);
            }
        }
    }

    protected String generateEntryKey(HttpServletRequest request, String suffix) {
        StringBuffer cBuffer = new StringBuffer();
        String generatedKey = request.getRequestURI();
        cBuffer.append(generatedKey);
        cBuffer.append("_").append(request.getMethod()).append("_");
        generatedKey = getSortedQueryString(request);
        if (generatedKey != null) {
            try {
                java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                byte[] b = digest.digest(generatedKey.getBytes());
                cBuffer.append('_');
                cBuffer.append(toBase64(b).replace('/', '_'));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if ((suffix != null) && (suffix.length() > 0)) {
            cBuffer.append(suffix);
        }
        return cBuffer.toString();
    }

    protected String getSortedQueryString(HttpServletRequest request) {
        Map paramMap = request.getParameterMap();

        if (paramMap.isEmpty()) {
            return null;
        }

        Set paramSet = new TreeMap(paramMap).entrySet();

        StringBuffer buf = new StringBuffer();

        boolean first = true;

        for (Iterator it = paramSet.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            String[] values = (String[]) entry.getValue();

            for (int i = 0; i < values.length; i++) {
                String key = (String) entry.getKey();

                if ((key.length() != 10) || !"jsessionid".equals(key)) {
                    if (first) {
                        first = false;
                    } else {
                        buf.append('&');
                    }

                    buf.append(key).append('=').append(values[i]);
                }
            }
        }

        // We get a 0 length buffer if the only parameter was a jsessionid
        if (buf.length() == 0) {
            return null;
        } else {
            return buf.toString();
        }
    }

    private static final String m_strBase64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private static String toBase64(byte[] aValue) {
        int byte1;
        int byte2;
        int byte3;
        int iByteLen = aValue.length;
        StringBuffer tt = new StringBuffer();

        for (int i = 0; i < iByteLen; i += 3) {
            boolean bByte2 = (i + 1) < iByteLen;
            boolean bByte3 = (i + 2) < iByteLen;
            byte1 = aValue[i] & 0xFF;
            byte2 = (bByte2) ? (aValue[i + 1] & 0xFF) : 0;
            byte3 = (bByte3) ? (aValue[i + 2] & 0xFF) : 0;

            tt.append(m_strBase64Chars.charAt(byte1 / 4));
            tt.append(m_strBase64Chars.charAt((byte2 / 16) + ((byte1 & 0x3) * 16)));
            tt.append(((bByte2) ? m_strBase64Chars.charAt((byte3 / 64) + ((byte2 & 0xF) * 4)) : '='));
            tt.append(((bByte3) ? m_strBase64Chars.charAt(byte3 & 0x3F) : '='));
        }

        return tt.toString();
    }
}
