/**
 * 
 */
package org.mspring.mlog.web.filter;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.zip.DataFormatException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.platform.cache.web.AlreadyCommittedException;
import org.mspring.platform.cache.web.AlreadyGzippedException;
import org.mspring.platform.cache.web.CacheResponseWrapper;
import org.mspring.platform.cache.web.Header;
import org.mspring.platform.cache.web.PageInfo;
import org.mspring.platform.cache.web.ResponseHeadersNotModifiableException;
import org.mspring.platform.cache.web.ResponseUtil;
import org.mspring.platform.cache.web.SerializableCookie;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2013-2-5
 * @description
 * @TODO
 */
public class CachingFilter extends org.mspring.platform.cache.web.filter.Filter {

    private static final Logger log = Logger.getLogger(CachingFilter.class);

    private CacheManager cacheManager = CacheManager.getInstance();
    private CacheService cacheService = ServiceFactory.getCacheService();

    public static final Long expiry = CacheService.ONE_HOUR;

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.cache.web.filter.Filter#doInit(javax.servlet.
     * FilterConfig)
     */
    @Override
    protected void doInit(FilterConfig filterConfig) throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.cache.web.filter.Filter#doFilter(javax.servlet.http
     * .HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws Throwable {
        // TODO Auto-generated method stub
        String cacheName = null;
        String cacheKey = null;
        String requestURI = request.getRequestURI();
        if (StringUtils.isNotBlank(requestURI)) {
            if (requestURI.startsWith("/post")) {
                cacheName = CacheService.CacheName.POST_PAGE_CACHE_NAME;
                cacheKey = request.getParameter("id");
            }
        }

        if (StringUtils.isBlank(cacheName) || StringUtils.isBlank(cacheKey)) {
            chain.doFilter(request, response);
            return;
        }

        if (response.isCommitted()) {
            // throw new
            // AlreadyCommittedException("Response already committed before doing buildPage.");
            log.debug("Response already committed before doing buildPage.");
        }
        logRequestHeaders(request);
        PageInfo pageInfo = buildPageInfo(cacheName, cacheKey, request, response, chain);
        if (pageInfo.isOk()) {
            if (response.isCommitted()) {
                throw new AlreadyCommittedException("Response already committed after doing buildPage" + " but before writing response from PageInfo.");
            }
            writeResponse(request, response, pageInfo);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.cache.web.filter.Filter#doDestroy()
     */
    @Override
    protected void doDestroy() {
        // TODO Auto-generated method stub

    }

    protected PageInfo buildPageInfo(final String cacheName, final String cacheKey, final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws Exception {
        // Look up the cached page
        PageInfo pageInfo = null;
        Object pageObject = cacheService.getCacheValue(cacheName, cacheKey);
        if (pageObject == null) {
            try {
                pageInfo = buildPage(request, response, chain);
                if (pageInfo.isOk()) {
                    if (log.isDebugEnabled()) {
                        log.debug("PageInfo ok. Adding to cache " + cacheName + " with key " + cacheKey);
                    }
                    cacheService.updateCacheValue(cacheName, cacheKey, pageInfo, expiry);
                } else {
                    if (log.isDebugEnabled()) {
                        log.debug("PageInfo was not ok(200). Putting null into cache " + cacheName + " with key " + cacheKey);
                    }
                    cacheService.updateCacheValue(cacheName, cacheKey, null);
                }
            } catch (final Throwable throwable) {
                cacheService.updateCacheValue(cacheName, cacheKey, null);
                log.debug(throwable.getMessage());
            }
        } else {
            pageInfo = (PageInfo) pageObject;
        }
        return pageInfo;
    }

    protected PageInfo buildPage(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws AlreadyGzippedException, Exception {
        // Invoke the next entity in the chain
        final ByteArrayOutputStream outstr = new ByteArrayOutputStream();
        final CacheResponseWrapper wrapper = new CacheResponseWrapper(response, outstr);
        chain.doFilter(request, wrapper);
        wrapper.flush();

        long timeToLiveSeconds = expiry;

        // Return the page info
        return new PageInfo(wrapper.getStatus(), wrapper.getContentType(), wrapper.getCookies(), outstr.toByteArray(), false, timeToLiveSeconds, wrapper.getAllHeaders());
    }

    protected void writeResponse(final HttpServletRequest request, final HttpServletResponse response, final PageInfo pageInfo) throws IOException, DataFormatException, ResponseHeadersNotModifiableException {
        boolean requestAcceptsGzipEncoding = acceptsGzipEncoding(request);

        setStatus(response, pageInfo);
        setContentType(response, pageInfo);
        setCookies(pageInfo, response);
        // do headers last so that users can override with their own header sets
        setHeaders(pageInfo, requestAcceptsGzipEncoding, response);
        writeContent(request, response, pageInfo);
    }

    protected void setContentType(final HttpServletResponse response, final PageInfo pageInfo) {
        String contentType = pageInfo.getContentType();
        if (contentType != null && contentType.length() > 0) {
            response.setContentType(contentType);
        }
    }

    protected void setCookies(final PageInfo pageInfo, final HttpServletResponse response) {
        final Collection cookies = pageInfo.getSerializableCookies();
        for (Iterator iterator = cookies.iterator(); iterator.hasNext();) {
            final Cookie cookie = ((SerializableCookie) iterator.next()).toCookie();
            response.addCookie(cookie);
        }
    }

    protected void setStatus(final HttpServletResponse response, final PageInfo pageInfo) {
        response.setStatus(pageInfo.getStatusCode());
    }

    protected void setHeaders(final PageInfo pageInfo, boolean requestAcceptsGzipEncoding, final HttpServletResponse response) {
        final Collection<Header<? extends Serializable>> headers = pageInfo.getHeaders();

        final TreeSet<String> setHeaders = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

        for (final Header<? extends Serializable> header : headers) {
            final String name = header.getName();

            switch (header.getType()) {
            case STRING:
                if (setHeaders.contains(name)) {
                    response.addHeader(name, (String) header.getValue());
                } else {
                    setHeaders.add(name);
                    response.setHeader(name, (String) header.getValue());
                }
                break;
            case DATE:
                if (setHeaders.contains(name)) {
                    response.addDateHeader(name, (Long) header.getValue());
                } else {
                    setHeaders.add(name);
                    response.setDateHeader(name, (Long) header.getValue());
                }
                break;
            case INT:
                if (setHeaders.contains(name)) {
                    response.addIntHeader(name, (Integer) header.getValue());
                } else {
                    setHeaders.add(name);
                    response.setIntHeader(name, (Integer) header.getValue());
                }
                break;
            default:
                throw new IllegalArgumentException("No mapping for Header: " + header);
            }
        }
    }

    protected void writeContent(final HttpServletRequest request, final HttpServletResponse response, final PageInfo pageInfo) throws IOException, ResponseHeadersNotModifiableException {
        byte[] body;

        // boolean shouldBodyBeZero = ResponseUtil.shouldBodyBeZero(request,
        // pageInfo.getStatusCode());
        // if (shouldBodyBeZero) {
        // body = new byte[0];
        // } else if (acceptsGzipEncoding(request)) {
        // body = pageInfo.getGzippedBody();
        // if (ResponseUtil.shouldGzippedBodyBeZero(body, request)) {
        // body = new byte[0];
        // } else {
        // ResponseUtil.addGzipHeader(response);
        // }
        //
        // } else {
        // body = pageInfo.getUngzippedBody();
        // }

        body = pageInfo.getUngzippedBody();
        if (body == null || body.length == 0) {
            body = pageInfo.getGzippedBody();
            ResponseUtil.addGzipHeader(response);
        }

        response.setContentLength(body.length);
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(body);
        out.flush();
    }

}
