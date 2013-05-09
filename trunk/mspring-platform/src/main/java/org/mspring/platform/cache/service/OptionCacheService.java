/**
 * 
 */
package org.mspring.platform.cache.service;

import java.util.List;

import org.mspring.platform.cache.service.CacheService;

import net.sf.ehcache.Ehcache;

/**
 * @author Gao Youbo
 * @since 2013-1-30
 * @description
 * @TODO
 */
public interface OptionCacheService {
    static final String CACHE_NAME = CacheService.CacheName.OPTION_CACHE_NAME;

    public void updateOptionCacheValue(String key, Object value);

    public void updateOptionCacheValue(String key, Object value, long expiry);

    public Object getOptionCacheValue(String key);

    public void deleteOptionCacheValue(String key);

    public void clearOptionCache();

    public Ehcache getCache();

    public List getCacheKeys();
}
