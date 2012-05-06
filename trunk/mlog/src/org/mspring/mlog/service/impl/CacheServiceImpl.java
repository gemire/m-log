/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.mspring.mlog.service.CacheService;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since Jan 19, 2012 http://www.mspring.org
 */
@Service
public class CacheServiceImpl implements CacheService {
    
    private static final Logger log = Logger.getLogger(CacheServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#removeCache(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void removeCache(String cacheName, String key) {
        // TODO Auto-generated method stub
        Ehcache cache = getEhcache(cacheName);
        if (cache == null) {
            log.debug("not found cache named " + cacheName);
            return;
        }
        cache.remove(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#removeCaches(java.lang.String,
     * java.util.List)
     */
    @Override
    public void removeCaches(String cacheName, List<String> keyList) {
        // TODO Auto-generated method stub
        for (String key : keyList) {
            removeCache(cacheName, key);
        }
    }
    
    /* (non-Javadoc)
     * @see org.mspring.mlog.service.CacheService#removeAllCache(java.lang.String)
     */
    @Override
    public void removeAllCache(String cacheName) {
        // TODO Auto-generated method stub
        Ehcache cache = getEhcache(cacheName);
        cache.removeAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#getCacheManager()
     */
    @Override
    public CacheManager getCacheManager() {
        // TODO Auto-generated method stub
        return CacheManager.getInstance();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CacheService#getEhcache(java.lang.String)
     */
    @Override
    public Ehcache getEhcache(String cacheName) {
        // TODO Auto-generated method stub
        CacheManager cacheManager = CacheManager.getInstance();
        return cacheManager.getEhcache(cacheName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#updateValue(net.sf.ehcache.Cache,
     * java.lang.String, java.lang.Object)
     */
    @Override
    public void updateValue(String cacheName, String key, Object value) {
        // TODO Auto-generated method stub
        Ehcache cache = getEhcache(cacheName);
        if (value != null)
            cache.put(new Element(key, value));
        else
            cache.remove(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CacheService#updateValue(net.sf.ehcache.Cache,
     * java.lang.String, java.lang.Object, int)
     */
    @Override
    public void updateValue(String cacheName, String key, Object value, int timeToLive) {
        // TODO Auto-generated method stub
        Ehcache cache = getEhcache(cacheName);
        if (value != null) {
            Element element = new Element(key, value);
            element.setTimeToLive(timeToLive);
            element.setTimeToIdle(timeToLive / 2);
            cache.put(element);
        } else {
            cache.remove(key);
        }
    }

}
