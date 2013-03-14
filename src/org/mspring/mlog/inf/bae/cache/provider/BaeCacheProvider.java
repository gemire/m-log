/**
 * 
 */
package org.mspring.mlog.inf.bae.cache.provider;

import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.Timestamper;
import org.mspring.mlog.inf.bae.cache.CacheManager;
import org.mspring.mlog.inf.bae.cache.support.CacheBean;
import org.mspring.mlog.service.CacheService;
import org.mspring.platform.utils.ClassLoaderUtils;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description 基于bae平台自定义的CacheProvider
 * @TODO
 */
@SuppressWarnings("deprecation")
public class BaeCacheProvider implements CacheProvider {

    private static final Logger log = Logger.getLogger(BaeCacheProvider.class);

    private CacheManager cacheManager;

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    // 当前cache的name
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#buildCache(java.lang.String,
     * java.util.Properties)
     */
    @Override
    public Cache buildCache(String name, Properties properties) throws CacheException {
        // TODO Auto-generated method stub
        this.name = name;

        CacheBean cacheBean = cacheManager.getCache(name);
        long expiry = CacheService.DEFAULT_EXPIRY;
        if (cacheBean != null) {
            expiry = cacheBean.getTimeToIdleSeconds();
            log.info("get cacheNamed : " + cacheBean.getName());
        }
        log.info("build cache....... name=[" + name + "], expiry=[" + expiry + "]");
        return new BaeCache(name, expiry);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#start(java.util.Properties)
     */
    @Override
    public void start(Properties properties) throws CacheException {
        // TODO Auto-generated method stub
        if (this.cacheManager != null) {
            log.info("Attempt to restart an already started BaeCacheProvider.");
            return;
        }

        String configLocation = new Config(properties).getConfigLocation();
        if (StringUtils.isBlank(configLocation)) {
            log.warn("configLocation is blank.");
            this.cacheManager = new CacheManager();
        }
        else {
            URL url = ClassLoaderUtils.getClasspathResource(configLocation);
            this.cacheManager = new CacheManager(url);
        }
        log.info("started bae cache! name=" + this.name + ", configLoation=" + configLocation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.cache.CacheProvider#stop()
     */
    @Override
    public void stop() {
        // TODO Auto-generated method stub
        log.info("stoped bae cache, name = [" + this.name + "]");
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
     * @see org.hibernate.cache.CacheProvider#isMinimalPutsEnabledByDefault()
     */
    @Override
    public boolean isMinimalPutsEnabledByDefault() {
        // TODO Auto-generated method stub
        return false;
    }

}
