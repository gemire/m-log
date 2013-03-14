/**
 * 
 */
package org.mspring.mlog.inf.sae;

import java.util.List;

import org.mspring.mlog.service.CacheService;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-12-19
 * @Description
 * @TODO
 */
@Service
public class SaeCacheServiceImpl implements CacheService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#getAppCachePrefix()
     */
    @Override
    public String getAppCachePrefix() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#getKey(java.lang.String)
     */
    @Override
    public String getKey(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#updateCacheValue(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public void updateCacheValue(String key, Object value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#updateCacheValue(java.lang.String,
     * java.lang.Object, long)
     */
    @Override
    public void updateCacheValue(String key, Object value, long expiry) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#getCacheValue(java.lang.String)
     */
    @Override
    public Object getCacheValue(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#deleteCache(java.lang.String)
     */
    @Override
    public void deleteCache(String key) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#getCacheKeys()
     */
    @Override
    public List<String> getCacheKeys() {
        // TODO Auto-generated method stub
        return null;
    }

}
