/**
 * 
 */
package org.mspring.platform.cache.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.platform.cache.CacheUtils;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2013-2-6
 * @description
 * @TODO
 */
public abstract class CachingFilter implements Filter {

    private static final Logger log = Logger.getLogger(CachingFilter.class);

    // Header
    public static final String HEADER_LAST_MODIFIED = "Last-Modified";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_EXPIRES = "Expires";
    public static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";

    // Fragment parameter
    public static final int FRAGMENT_AUTODETECT = -1;
    public static final int FRAGMENT_NO = 0;
    public static final int FRAGMENT_YES = 1;

    // No cache parameter
    public static final int NOCACHE_OFF = 0;
    public static final int NOCACHE_SESSION_ID_IN_URL = 1;

    // Last Modified parameter
    public static final long LAST_MODIFIED_OFF = 0;
    public static final long LAST_MODIFIED_ON = 1;
    public static final long LAST_MODIFIED_INITIAL = -1;

    // Expires parameter
    public static final long EXPIRES_OFF = 0;
    public static final long EXPIRES_ON = 1;
    public static final long EXPIRES_TIME = -1;

    // Cache Control
    public static final long MAX_AGE_NO_INIT = Long.MIN_VALUE;
    public static final long MAX_AGE_TIME = Long.MAX_VALUE;

    // filter variables
    private int nocache = NOCACHE_OFF;
    private int fragment = FRAGMENT_AUTODETECT;
    private long lastModified = LAST_MODIFIED_INITIAL;
    private long expires = EXPIRES_ON;
    private long cacheControlMaxAge = -60;

    private CacheConfig cacheConfig;

    public abstract CacheConfig getCacheConfig(HttpServletRequest request);

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        this.cacheConfig = getCacheConfig(request);
        if (cacheConfig == null) {
            chain.doFilter(request, response);
            log.debug("can't do cache filter, cacheConfig is null");
            return;
        }
        if (cacheConfig.getCacheManager() == null) {
            chain.doFilter(request, response);
            log.debug("can't do cache filter, cacheManager is null");
            return;
        }
        if (StringUtils.isBlank(cacheConfig.getCacheName())) {
            chain.doFilter(request, response);
            log.debug("can't do cache filter, cacheName is blank");
            return;
        }
        if (StringUtils.isBlank(cacheConfig.getCacheKey())) {
            chain.doFilter(request, response);
            log.debug("can't do cache filter, cacheKey is blank");
            return;
        }
        if (cacheConfig.getExpiry() == null || cacheConfig.getExpiry() == 0) {
            chain.doFilter(request, response);
            log.debug("can't do cache filter, expiry is null or 0");
            return;
        }

        boolean fragmentRequest = isFragment(request);
        if (cacheConfig.isRefresh()) {
            loadPage(fragmentRequest, request, response, chain);
        }
        else {
            try {
                ResponseContent respContent = (ResponseContent) CacheUtils.getObjectValue(cacheConfig.getCacheManager(), cacheConfig.getCacheName(), cacheConfig.getCacheKey());
                if (log.isInfoEnabled()) {
                    log.info("Using cached entry for " + cacheConfig.getCacheName() + "-----------" + cacheConfig.getCacheKey());
                }
                boolean acceptsGZip = false;
                if ((!fragmentRequest) && (lastModified != LAST_MODIFIED_OFF)) {
                    long clientLastModified = request.getDateHeader(HEADER_IF_MODIFIED_SINCE);

                    if ((clientLastModified != -1) && (clientLastModified >= respContent.getLastModified())) {
                        ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                        return;
                    }
                    acceptsGZip = respContent.isContentGZiped() && acceptsGZipEncoding(request);
                }
                respContent.writeTo(response, fragmentRequest, acceptsGZip);
            } catch (Exception ex) {
                loadPage(fragmentRequest, request, response, chain);
            }
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // TODO Auto-generated method stub
    }

    /**
     * 从Cache中加载
     * 
     * @param fragmentRequest
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void loadPage(boolean fragmentRequest, HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            if (log.isInfoEnabled()) {
                log.info("New cache entry, cache stale or cache scope flushed for " + cacheConfig.getCacheName() + "-----------" + cacheConfig.getCacheKey());
            }

            CacheHttpServletResponseWrapper cacheResponse = new CacheHttpServletResponseWrapper((HttpServletResponse) response, fragmentRequest, cacheConfig.getExpiry(), lastModified, expires, cacheControlMaxAge);
            chain.doFilter(request, cacheResponse);
            cacheResponse.flushBuffer();

            // Only cache if the response is cacheable
            if (isCacheableInternal(cacheResponse)) {
                // get the cache groups of the content
                CacheUtils.updateValue(cacheConfig.getCacheManager(), cacheConfig.getCacheName(), cacheConfig.getCacheKey(), cacheResponse.getContent());
                if (log.isInfoEnabled()) {
                    log.info("New entry added to the cache with key " + cacheConfig.getCacheName() + "-----------" + cacheConfig.getCacheKey());
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    private final boolean isCacheableInternal(ServletRequest request) {
        final boolean cacheable = isCacheable(request);
        return cacheable;
    }

    public boolean isCacheable(ServletRequest request) {
        boolean cacheable = request instanceof HttpServletRequest;
        if (cacheable) {
            HttpServletRequest requestHttp = (HttpServletRequest) request;
            if (nocache == NOCACHE_SESSION_ID_IN_URL) {
                cacheable = !requestHttp.isRequestedSessionIdFromURL();
            }
        }
        return cacheable;
    }

    private final boolean isCacheableInternal(CacheHttpServletResponseWrapper cacheResponse) {
        final boolean cacheable = isCacheable(cacheResponse);
        if (log.isDebugEnabled()) {
            log.debug("the response " + ((cacheable) ? "is" : "is not") + " cachable.");
        }
        return cacheable;
    }

    public boolean isCacheable(CacheHttpServletResponseWrapper cacheResponse) {
        return cacheResponse.getStatus() == HttpServletResponse.SC_OK;
    }

    public boolean isFragment(HttpServletRequest request) {
        if (fragment == FRAGMENT_AUTODETECT) {
            return request.getAttribute("javax.servlet.include.request_uri") != null;
        } else {
            return (fragment == FRAGMENT_NO) ? false : true;
        }
    }

    public boolean acceptsGZipEncoding(HttpServletRequest request) {
        String acceptEncoding = request.getHeader(HEADER_ACCEPT_ENCODING);
        return (acceptEncoding != null) && (acceptEncoding.indexOf("gzip") != -1);
    }
}
