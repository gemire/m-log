/**
 * 
 */
package org.mspring.mlog.service.impl.standard;

import java.util.List;

import net.sf.ehcache.CacheManager;

import org.mspring.mlog.service.CacheService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.utils.CacheUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-12-19
 * @Description
 * @TODO
 */
@Service
public class StandardCacheServiceImpl implements CacheService {
    
    public static final String DEFAULT_CACHE_NAME = "DEFAULT_CACHE";

    @Autowired
    private OptionService optionService;

    @Autowired
    private CacheManager cacheManager;

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#getAppCachePrefix()
     */
    @Override
    public String getAppCachePrefix() {
        // TODO Auto-generated method stub
        String prefix = optionService.getPropertiesOption("cache_prefix");
        if (StringUtils.isBlank(prefix)) {
            prefix = "MLOG_";
        }
        return prefix;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#getKey(java.lang.String)
     */
    @Override
    public String getKey(String key) {
        // TODO Auto-generated method stub
        return getAppCachePrefix() + key;
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
        CacheUtils.updateValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key), value, new Long(DEFAULT_EXPIRY).intValue());
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
        if (expiry == 0) {
            expiry = DEFAULT_EXPIRY;
        }
        CacheUtils.updateValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key), value, new Long(expiry).intValue());
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
        return CacheUtils.getObjectValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#deleteCache(java.lang.String)
     */
    @Override
    public void deleteCache(String key) {
        // TODO Auto-generated method stub
        CacheUtils.invalidateValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key));
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
