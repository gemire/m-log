/**
 * 
 */
package org.mspring.mlog.web.cache.filter;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.web.cache.CacheTokens;

/**
 * @author Gao Youbo
 * @since Jan 22, 2012
 */
public class IndexPageCachingFilter extends PageCachingFilter {

    /* (non-Javadoc)
     * @see net.sf.ehcache.constructs.web.filter.CachingFilter#calculateKey(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected String calculateKey(HttpServletRequest arg0) {
        // TODO Auto-generated method stub
        return CacheTokens.INDEX_CACHE_KEY;
    }

}
