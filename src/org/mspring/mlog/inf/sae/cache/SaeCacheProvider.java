/**
 * 
 */
package org.mspring.mlog.inf.sae.cache;

import java.util.Properties;

import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.Timestamper;

/**
 * @author Gao Youbo
 * @since 2013-1-6
 * @Description
 * @TODO
 */
public class SaeCacheProvider implements CacheProvider {

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#buildCache(java.lang.String,
     * java.util.Properties)
     */
    @Override
    public Cache buildCache(String name, Properties properties) throws CacheException {
        // TODO Auto-generated method stub
        return new SaeCache(name, 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#isMinimalPutsEnabledByDefault()
     */
    @Override
    public boolean isMinimalPutsEnabledByDefault() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#nextTimestamp()
     */
    @Override
    public long nextTimestamp() {
        // TODO Auto-generated method stub
        return Timestamper.next();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#start(java.util.Properties)
     */
    @Override
    public void start(Properties properties) throws CacheException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#stop()
     */
    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

}
