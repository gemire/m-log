/**
 * 
 */
package org.mspring.mlog.web.cache;

import java.io.Serializable;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.CachingFilter;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since 2012-8-17
 * @Description
 * @TODO
 */
public abstract class PageCachingFilter extends CachingFilter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 219822490023617357L;

    private static final Logger log = Logger.getLogger(PageCachingFilter.class);

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.ehcache.constructs.web.filter.CachingFilter#getCacheManager()
     */
    @Override
    protected CacheManager getCacheManager() {
        // TODO Auto-generated method stub
        return CacheManager.getInstance();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.ehcache.constructs.web.filter.CachingFilter#getCacheName()
     */
    @Override
    protected String getCacheName() {
        // TODO Auto-generated method stub
        return CacheTokens.PAGE_CACHE_NAME;
    }

}
