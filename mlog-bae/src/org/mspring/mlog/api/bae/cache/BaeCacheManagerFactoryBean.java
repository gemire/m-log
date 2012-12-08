/**
 * 
 */
package org.mspring.mlog.api.bae.cache;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description
 * @TODO
 */
public class BaeCacheManagerFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {

    private static final Logger log = Logger.getLogger(BaeCacheManagerFactoryBean.class);

    private Resource configLocation;
    private String cacheManagerName;

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    public void setCacheManagerName(String cacheManagerName) {
        this.cacheManagerName = cacheManagerName;
    }

    private CacheManager cacheManager;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        log.info("Shutting down BaeCache CacheManager");
        cacheManager.caches.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        log.info("Initializing BaeCache CacheManager");
        if (this.configLocation != null) {
            InputStream is = this.configLocation.getInputStream();
            try {
                this.cacheManager = CacheManager.create(is);
            }
            finally {
                is.close();
            }
        }
        if (this.cacheManagerName != null) this.cacheManager.setName(this.cacheManagerName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.FactoryBean#getObject()
     */
    @Override
    public CacheManager getObject() throws Exception {
        // TODO Auto-generated method stub
        return this.cacheManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.FactoryBean#getObjectType()
     */
    @Override
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return this.cacheManager != null ? this.cacheManager.getClass() : CacheManager.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    @Override
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return true;
    }

}
