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

import org.mspring.mlog.common.Const;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.security.User;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Jun 20, 2012
 */
public class RememberMeFilter implements Filter {

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        if (((HttpServletRequest) request).getSession().getAttribute(Const.SESSION_LOGINUSER) == null) {
            String remember = CookieUtils.getCookie((HttpServletRequest) request, Const.MSPRING_COOKIE_REMEMBER);
            if (!StringUtils.isBlank(remember) && "true".equals(remember)) {
                String username = CookieUtils.getCookie((HttpServletRequest) request, Const.MSPRING_COOKIE_USERNAME);
                String password = CookieUtils.getCookie((HttpServletRequest) request, Const.MSPRING_COOKIE_PASSWORD);
                
                if ((!StringUtils.isBlank(username)) && (!StringUtils.isBlank(password))) {
                    User user = ServiceFactory.getUserService().validateUser(username, password);
                    if (user != null) {
                        ((HttpServletRequest) request).getSession().setAttribute(Const.SESSION_LOGINUSER, user);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // TODO Auto-generated method stub

    }

}
