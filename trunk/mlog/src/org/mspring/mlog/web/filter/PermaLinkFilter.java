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

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.utils.PermaLinkUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-3
 * @Description
 * @TODO
 */
public class PermaLinkFilter implements Filter {

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

        String requestURI = request.getPathInfo();

        if (PermaLinkUtils.invalidParamLink(requestURI)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        Post post = ServiceFactory.getPostService().getPostByUrl(requestURI);
        if (post == null) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        dispatchToPostPage(request, response, post);
    }
    
    private void dispatchToPostPage(HttpServletRequest request, HttpServletResponse response, Post post) throws ServletException, IOException{
        if (post != null) {
            request.setAttribute("post", post);
            request.setAttribute("requestURI", "/post");
            request.setAttribute("method", "GET");
            request.getRequestDispatcher("/post").forward(request, response);
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
