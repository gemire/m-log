/**
 * 
 */
package org.mspring.mlog.service.cache.impl;

import java.util.List;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.platform.cache.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-12-19
 * @Description
 * @TODO
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    protected OptionService optionService;

    @Autowired
    protected CacheManager cacheManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#updateCacheValue(java.lang.String,
     * java.lang.String, java.lang.Object)
     */
    @Override
    public void updateCacheValue(String cacheName, String key, Object value) {
        // TODO Auto-generated method stub
        updateCacheValue(cacheName, key, value, DEFAULT_EXPIRY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#updateCacheValue(java.lang.String,
     * java.lang.String, java.lang.Object, long)
     */
    @Override
    public void updateCacheValue(String cacheName, String key, Object value, long expiry) {
        // TODO Auto-generated method stub
        CacheUtils.updateValue(cacheManager, cacheName, key, value, (int) expiry);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#getCacheValue(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Object getCacheValue(String cacheName, String key) {
        // TODO Auto-generated method stub
        return CacheUtils.getObjectValue(cacheManager, cacheName, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#deleteCacheValue(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void deleteCacheValue(String cacheName, String key) {
        // TODO Auto-generated method stub
        CacheUtils.invalidateValue(cacheManager, cacheName, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#clearCache(java.lang.String)
     */
    @Override
    public void clearCache(String cacheName) {
        // TODO Auto-generated method stub
        Ehcache cache = CacheUtils.getCache(cacheManager, cacheName);
        if (cache != null) {
            cache.removeAll();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#clearAllCache()
     */
    @Override
    public void clearAllCache() {
        // TODO Auto-generated method stub
        cacheManager.clearAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.cache.CacheService#getCacheNames()
     */
    @Override
    public String[] getCacheNames() {
        // TODO Auto-generated method stub
        return cacheManager.getCacheNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.cache.CacheService#getCache(java.lang.String)
     */
    @Override
    public Ehcache getCache(String cacheName) {
        // TODO Auto-generated method stub
        return CacheUtils.getCache(cacheManager, cacheName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.cache.CacheService#getCacheKeys(java.lang.String
     * )
     */
    @Override
    public List getCacheKeys(String cacheName) {
        // TODO Auto-generated method stub
        Ehcache cache = getCache(cacheName);
        if (cache != null) {
            return cache.getKeys();
        }
        return null;
    }

}
