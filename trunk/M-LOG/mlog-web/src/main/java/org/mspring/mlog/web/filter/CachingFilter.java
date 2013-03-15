/**
 * 
 */
package org.mspring.mlog.web.filter;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;

import org.mspring.mlog.service.cache.CacheService;
import org.mspring.platform.cache.filter.CacheConfig;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2013-2-6
 * @description
 * @TODO
 */
public class CachingFilter extends org.mspring.platform.cache.filter.CachingFilter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.cache.filter.CachingFilter#getCacheConfig(javax.
     * servlet.http.HttpServletRequest)
     */
    @Override
    public CacheConfig getCacheConfig(HttpServletRequest request) {
        // TODO Auto-generated method stub
        CacheConfig cacheConfig = new CacheConfig();
        String requestURI = request.getRequestURI();
        if (StringUtils.isNotBlank(requestURI)) {
            if (requestURI.startsWith("/post")) {
                cacheConfig.setCacheName(CacheService.CacheName.POST_PAGE_CACHE_NAME);
                cacheConfig.setCacheKey(request.getParameter("id"));
            }
        }
        cacheConfig.setExpiry(CacheService.ONE_MINUTE);
        cacheConfig.setCacheManager(CacheManager.getInstance());
        cacheConfig.setRefresh(false);
        return cacheConfig;
    }

}
