/**
 * 
 */
package org.mspring.mlog.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.utils.PermaLinkUtils;
import org.mspring.mlog.web.rulrewrite.PostRewriteRule;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-3
 * @Description
 * @TODO
 */
public class PermaLinkFilter implements Filter {

    private static final Logger log = Logger.getLogger(PermaLinkUtils.class);

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = StringUtils.encoding(request.getPathInfo(), "ISO-8859-1", "UTF-8");

        log.debug("Request URI [" + requestURI + "]");

        if (PermaLinkUtils.invalidParamLink(requestURI)) {
            log.debug("Skip filter request [URI=" + requestURI + "]");
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        Post post = ServiceFactory.getPostService().getPostByUrl(requestURI);
        if (post == null) {
            log.debug("Not found post with permalink [" + requestURI + "]");
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        dispatchToPostPage(request, response, post);
    }

    private void dispatchToPostPage(HttpServletRequest request, HttpServletResponse response, final Post post) throws ServletException, IOException {
        if (post != null) {
            // request.setAttribute("post", post);
            // request.setAttribute("requestURI", "/post");
            // request.setAttribute("method", "GET");
            // request.getRequestDispatcher("/post").forward(request, response);

            // 自定义链接功能抛弃， 现在将自定义链接转到原本连接上
            String url = PostRewriteRule.getPostUrl(post);
            response.sendRedirect(url);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}