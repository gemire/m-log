/**
 * 
 */
package org.mspring.mlog.api.bae.cache.provider;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.Timestamper;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.service.CacheService;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description
 * @TODO
 */
@SuppressWarnings("deprecation")
public class BaeCache implements Cache {

    private static final Logger log = Logger.getLogger(BaeCache.class);

    /**
     * 缓存时间
     */
    private long expiry;
    private String regionName;

    /**
     * 
     */
    public BaeCache(String regionName, long expiry) {
        // TODO Auto-generated constructor stub
        this.regionName = regionName;
        this.expiry = expiry;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#get(java.lang.Object)
     */
    @Override
    public Object get(Object key) throws CacheException {
        // TODO Auto-generated method stub
        String k = BaeCacheProviderUtils.getHibernateCacheKey(key.toString());
        if (StringUtils.isBlank(k)) {
            log.info("[" + regionName + "]Can't get object from cache, because cache key is blank.");
            return null;
        }
        Object obj = getCacheService().getCacheValue(k);
        log.info("[" + regionName + "]Get object from cache, key = [" + k + "], value = " + obj);
        return obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#read(java.lang.Object)
     */
    @Override
    public Object read(Object key) throws CacheException {
        // TODO Auto-generated method stub
        return get(BaeCacheProviderUtils.getHibernateCacheKey(key));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#update(java.lang.Object, java.lang.Object)
     */
    @Override
    public void update(Object key, Object value) throws CacheException {
        // TODO Auto-generated method stub
        put(key, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(Object key, Object value) throws CacheException {
        // TODO Auto-generated method stub
        String k = BaeCacheProviderUtils.getHibernateCacheKey(key);
        if (StringUtils.isBlank(k)) {
            log.warn("[" + regionName + "]can't update cacke, because key is blank");
            return;
        }
        getCacheService().updateCacheValue(k, value, expiry);
        log.info("[" + regionName + "]updated cache key = [" + k + "], expiry=[" + expiry + "], value = " + value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#remove(java.lang.Object)
     */
    @Override
    public void remove(Object key) throws CacheException {
        // TODO Auto-generated method stub
        String k = BaeCacheProviderUtils.getHibernateCacheKey(key);
        if (StringUtils.isBlank(k)) {
            log.warn("[" + regionName + "]can't remove cacke, because key is blank");
            return;
        }
        getCacheService().deleteCache(k);
        log.info("[" + regionName + "]removed cache key = [" + k + "]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#clear()
     */
    @Override
    public void clear() throws CacheException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException();
        List<String> keys = getCacheService().getCacheKeys();
        if (keys != null) {
            String prefix = getCacheService().getAppCachePrefix() + BaeCacheProviderUtils.HIBERNATE_CACHE_KEY_PREFIX;
            // String prefix = "MLOG_" +
            // BaeCacheProviderUtils.HIBERNATE_CACHE_KEY_PREFIX;
            for (String key : keys) {
                if (key.startsWith(prefix)) {
                    getCacheService().deleteCache(key);
                }
            }
        }
        log.info("[" + regionName + "]clear cache...");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#destroy()
     */
    @Override
    public void destroy() throws CacheException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#lock(java.lang.Object)
     */
    @Override
    public void lock(Object key) throws CacheException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#unlock(java.lang.Object)
     */
    @Override
    public void unlock(Object key) throws CacheException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#nextTimestamp()
     */
    @Override
    public long nextTimestamp() {
        // TODO Auto-generated method stub
        return Timestamper.next();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#getTimeout()
     */
    @Override
    public int getTimeout() {
        // TODO Auto-generated method stub
        return new Long(expiry).intValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#getElementCountInMemory()
     */
    @Override
    public long getElementCountInMemory() {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#getElementCountOnDisk()
     */
    @Override
    public long getElementCountOnDisk() {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#getRegionName()
     */
    @Override
    public String getRegionName() {
        // TODO Auto-generated method stub
        return regionName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#getSizeInMemory()
     */
    @Override
    public long getSizeInMemory() {
        // TODO Auto-generated method stub
        return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.Cache#toMap()
     */
    @Override
    public Map toMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    private CacheService getCacheService() {
        return ServiceFactory.getCacheService();
    }

}
