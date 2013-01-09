/**
 * 
 */
package org.mspring.mlog.inf.sae.cache;

import java.util.Map;

import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.Timestamper;

/**
 * @author Gao Youbo
 * @since 2013-1-6
 * @Description 
 * @TODO 
 */
public class SaeCache implements Cache {
    
    /**
     * 缓存时间
     */
    private long expiry;
    private String regionName;
    
    /**
     * 
     */
    public SaeCache(String regionName, long expiry) {
        // TODO Auto-generated constructor stub
        this.regionName = regionName;
        this.expiry = expiry;
    }
    

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#clear()
     */
    @Override
    public void clear() throws CacheException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#destroy()
     */
    @Override
    public void destroy() throws CacheException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#get(java.lang.Object)
     */
    @Override
    public Object get(Object key) throws CacheException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#getElementCountInMemory()
     */
    @Override
    public long getElementCountInMemory() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#getElementCountOnDisk()
     */
    @Override
    public long getElementCountOnDisk() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#getRegionName()
     */
    @Override
    public String getRegionName() {
        // TODO Auto-generated method stub
        return regionName;
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#getSizeInMemory()
     */
    @Override
    public long getSizeInMemory() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#getTimeout()
     */
    @Override
    public int getTimeout() {
        // TODO Auto-generated method stub
        return new Long(expiry).intValue();
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#lock(java.lang.Object)
     */
    @Override
    public void lock(Object key) throws CacheException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#nextTimestamp()
     */
    @Override
    public long nextTimestamp() {
        // TODO Auto-generated method stub
        return Timestamper.next();
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(Object key, Object value) throws CacheException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#read(java.lang.Object)
     */
    @Override
    public Object read(Object key) throws CacheException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#remove(java.lang.Object)
     */
    @Override
    public void remove(Object key) throws CacheException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#toMap()
     */
    @Override
    public Map toMap() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#unlock(java.lang.Object)
     */
    @Override
    public void unlock(Object key) throws CacheException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.hibernate.cache.Cache#update(java.lang.Object, java.lang.Object)
     */
    @Override
    public void update(Object key, Object value) throws CacheException {
        // TODO Auto-generated method stub

    }

}
