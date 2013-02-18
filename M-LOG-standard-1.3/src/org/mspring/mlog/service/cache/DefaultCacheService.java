/**
 * 
 */
package org.mspring.mlog.service.cache;

import java.util.List;

import net.sf.ehcache.Ehcache;

/**
 * @author Gao Youbo
 * @since 2013-1-30
 * @description
 * @TODO
 */
public interface DefaultCacheService {
    static final String CACHE_NAME = CacheService.CacheName.DEFAULT_CACHE_NAME;

    public void updateDefaultCacheValue(String key, Object value);

    public void updateDefaultCacheValue(String key, Object value, long expiry);

    public Object getDefaultCacheValue(String key);

    public void deleteDefaultCacheValue(String key);

    public void clearDefaultCache();
    
    public Ehcache getCache();
    
    public List getCacheKeys();
}
