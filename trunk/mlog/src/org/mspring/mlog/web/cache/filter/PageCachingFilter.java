/**
 * 
 */
package org.mspring.mlog.web.cache.filter;

import java.io.Serializable;
import java.util.logging.Logger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.CachingFilter;

import org.mspring.mlog.web.cache.CacheTokens;

/**
 * @author Gao Youbo
 * @since Jan 17, 2012 http://www.mspring.org
 */
public abstract class PageCachingFilter extends CachingFilter implements Serializable {

    private static final Logger log = Logger.getLogger(ArticlePageCachingFilter.class.getName());

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
