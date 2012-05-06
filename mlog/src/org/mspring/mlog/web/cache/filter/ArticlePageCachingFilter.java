/**
 * 
 */
package org.mspring.mlog.web.cache.filter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.PageInfo;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;

import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.web.cache.CacheTokens;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Jan 17, 2012
 * @see 页面缓存
 */
public class ArticlePageCachingFilter extends PageCachingFilter {

    private static final Logger log = Logger.getLogger(ArticlePageCachingFilter.class.getName());

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.ehcache.constructs.web.filter.CachingFilter#calculateKey(javax
     *      .servlet.http.HttpServletRequest)
     */
    @Override
    protected String calculateKey(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String articleId = request.getParameter("id");
        if (!StringUtils.isBlank(articleId)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(CacheTokens.ARTICLE_CACHE_PREFIX).append(articleId);
            return stringBuffer.toString();
        }
        return null;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws AlreadyGzippedException, AlreadyCommittedException, FilterNonReentrantException, LockTimeoutException, Exception {
        // TODO Auto-generated method stub
        String enablePageCache = ServiceFactory.getOptionService().getOption(ConfigTokens.mspring_cache_enable_page_cache);
        // 如果启用页面缓存
        if ("1".equals(enablePageCache)) {
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
        } else {
            chain.doFilter(request, response);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.ehcache.constructs.web.filter.CachingFilter#buildPageInfo(javax
     *      .servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
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
            if ((element == null) || (element.getObjectValue() == null))
                try {
                    pageInfo = buildPage(request, response, chain);
                    if (pageInfo.isOk()) {
                        if (log.isLoggable(Level.FINEST)) {
                            log.finest("PageInfo ok. Adding to cache " + this.blockingCache.getName() + " with key " + key);
                        }
                        this.blockingCache.put(new Element(key, pageInfo));
                    } else {
                        if (log.isLoggable(Level.WARNING)) {
                            log.warning("PageInfo was not ok(200). Putting null into cache " + this.blockingCache.getName() + " with key " + key);
                        }
                        this.blockingCache.put(new Element(key, null));
                    }
                } catch (Throwable throwable) {
                    this.blockingCache.put(new Element(key, null));
                    throw new Exception(throwable);
                }
            else {
                pageInfo = (PageInfo) element.getObjectValue();
            }
        } catch (LockTimeoutException e) {
            throw e;
        } finally {
            Thread.currentThread().setName(originalThreadName);
        }
        return pageInfo;
    }

}
