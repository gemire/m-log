/**
 * 
 */
package org.mspring.platform.cache.filter;

import net.sf.ehcache.CacheManager;

/**
 * @author Gao Youbo
 * @since 2013-2-6
 * @description
 * @TODO
 */
public class CacheConfig {
    private CacheManager cacheManager; 
    private String cacheName;
    private String cacheKey;
    private Long expiry; //缓存时间，单位毫秒
    private boolean refresh; //是否刷新缓存，为true时将重新装载该cache值

    /**
     * 
     */
    public CacheConfig() {
        // TODO Auto-generated constructor stub
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

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

    public Long getExpiry() {
        return expiry;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
