/**
 * 
 */
package org.mspring.mlog.web.cache;

import javax.persistence.LockTimeoutException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.PageInfo;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;

import org.apache.log4j.Logger;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-17
 * @Description
 * @TODO
 */
public class PostPageCachingFilter extends PageCachingFilter {

    /**
     * 
     */
    private static final long serialVersionUID = -3820828851391364331L;
    
    private static final Logger log = Logger.getLogger(PostPageCachingFilter.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.sf.ehcache.constructs.web.filter.CachingFilter#calculateKey(javax
     * .servlet.http.HttpServletRequest)
     */
    @Override
    protected String calculateKey(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String postId = request.getParameter("id");
        if (!StringUtils.isBlank(postId)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(CacheTokens.POST_CACHE_PREFIX).append(postId);
            return stringBuffer.toString();
        }
        return null;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws AlreadyGzippedException, AlreadyCommittedException, FilterNonReentrantException, LockTimeoutException, Exception {
        // TODO Auto-generated method stub
        if (response.isCommitted()) {
            throw new AlreadyCommittedException("Response already committed before doing buildPage.");
        }
        logRequestHeaders(request);
        PageInfo pageInfo = buildPageInfo(request, response, chain);

        if (pageInfo.isOk()) {
            if (response.isCommitted()) {
                throw new AlreadyCommittedException("Response already committed after doing buildPage but before writing response from PageInfo.");
            }
            writeResponse(request, response, pageInfo);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.sf.ehcache.constructs.web.filter.CachingFilter#buildPageInfo(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    protected PageInfo buildPageInfo(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws Exception {
        String key = calculateKey(request);
        PageInfo pageInfo = null;
        String originalThreadName = Thread.currentThread().getName();
        try {
            // checkNoReentry(request);
            Element element = null;
            if (!StringUtils.isBlank(key)) {
                element = this.blockingCache.get(key);
            }
            if ((element == null) || (element.getObjectValue() == null)) try {
                pageInfo = buildPage(request, response, chain);
                if (pageInfo.isOk()) {
                    log.debug("PageInfo ok. Adding to cache " + this.blockingCache.getName() + " with key " + key);
                    this.blockingCache.put(new Element(key, pageInfo));
                }
                else {
                    log.warn("PageInfo was not ok(200). Putting null into cache " + this.blockingCache.getName() + " with key " + key);
                    this.blockingCache.put(new Element(key, null));
                }
            }
            catch (Throwable throwable) {
                this.blockingCache.put(new Element(key, null));
                throw new Exception(throwable);
            }
            else {
                pageInfo = (PageInfo) element.getObjectValue();
            }
        }
        catch (LockTimeoutException e) {
            throw e;
        }
        finally {
            Thread.currentThread().setName(originalThreadName);
        }
        return pageInfo;
    }

}
