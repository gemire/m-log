/**
 * 
 */
package org.mspring.mlog.service.cache.impl;

import java.util.List;

import net.sf.ehcache.Ehcache;

import org.mspring.mlog.service.cache.CacheService;
import org.mspring.mlog.service.cache.OptionCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2013-1-30
 * @description
 * @TODO
 */
@Service
public class OptionCacheServiceImpl implements OptionCacheService {

    @Autowired
    private CacheService cacheService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.cache.OptionCacheService#updateOptionCacheValue
     * (java.lang.String, java.lang.Object)
     */
    @Override
    public void updateOptionCacheValue(String key, Object value) {
        // TODO Auto-generated method stub
        cacheService.updateCacheValue(CACHE_NAME, key, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.cache.OptionCacheService#updateOptionCacheValue
     * (java.lang.String, java.lang.Object, long)
     */
    @Override
    public void updateOptionCacheValue(String key, Object value, long expiry) {
        // TODO Auto-generated method stub
        cacheService.updateCacheValue(CACHE_NAME, key, value, expiry);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.cache.OptionCacheService#getOptionCacheValue
     * (java.lang.String)
     */
    @Override
    public Object getOptionCacheValue(String key) {
        // TODO Auto-generated method stub
        return cacheService.getCacheValue(CACHE_NAME, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.cache.OptionCacheService#deleteOptionCacheValue
     * (java.lang.String)
     */
    @Override
    public void deleteOptionCacheValue(String key) {
        // TODO Auto-generated method stub
        cacheService.deleteCacheValue(CACHE_NAME, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.cache.OptionCacheService#clearOptionCache()
     */
    @Override
    public void clearOptionCache() {
        // TODO Auto-generated method stub
        cacheService.clearCache(CACHE_NAME);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.cache.OptionCacheService#getCache()
     */
    @Override
    public Ehcache getCache() {
        // TODO Auto-generated method stub
        return cacheService.getCache(CACHE_NAME);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.cache.OptionCacheService#getCacheKeys()
     */
    @Override
    public List getCacheKeys() {
        // TODO Auto-generated method stub
        return cacheService.getCacheKeys(CACHE_NAME);
    }

}
