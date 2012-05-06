/**
 * Jan 7, 2011 11:12:23 AM
 * @author gaoyb
 */
package org.mspring.mlog.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gaoyb
 * 
 */
public class LoginFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LoginFilter.class);

    protected FilterConfig filterConfig = null;
    private String loginURL = null;
    private List<String> notCheckURLList = new ArrayList<String>();

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
        notCheckURLList.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // String requestURI = request.getServletPath() + (request.getPathInfo()
        // == null ? "" : request.getPathInfo());
        // List<String> loginUrls = new ArrayList<String>();
        // loginUrls.add(loginURL);

        // 如果当前请求的URL不在不进行登录验证的页面结合中, 并且当前用户没有登录, 那么就进行登录过滤
        if ((!checkRequestURIInNotFilterList(request)) && session.getAttribute(Const.SESSION_LOGINUSER) == null) {
            // 从Cookie中获取用户信息
            String userName = CookieUtils.getCookie(request, Const.MSPRING_COOKIE_USERNAME);
            String password = CookieUtils.getCookie(request, Const.MSPRING_COOKIE_PASSWORD);
            if (!StringUtils.isBlank(userName) && !StringUtils.isBlank(password)) {
                User currentUser = userService.validateUser(userName, password, true);
                session.setAttribute(Const.SESSION_LOGINUSER, currentUser);
            }

            // 如果Cookie中也未保存用户信息,那么用户登录失败.
            if (session.getAttribute(Const.SESSION_LOGINUSER) == null) {
                logger.warn("#########################请先登录#########################");
                response.sendRedirect(request.getContextPath() + loginURL);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        this.filterConfig = filterConfig;
        loginURL = filterConfig.getInitParameter("loginURL");
        String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");

        if (!StringUtils.isBlank(notCheckURLListStr)) {
            StringTokenizer st = new StringTokenizer(notCheckURLListStr, "|");
            notCheckURLList.clear();
            while (st.hasMoreTokens()) {
                notCheckURLList.add(st.nextToken());
            }
        }
    }

    /**
     * 检测当前访问的URL是否在不过滤的范围内.
     * 
     * @param request
     * @return
     */
    private boolean checkRequestURIInNotFilterList(HttpServletRequest request) {
        String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
        return contains(uri);
    }

    public boolean contains(String uri) {
        for (String str : notCheckURLList) {
            if (str.endsWith("*")) {
                if (uri.startsWith(str.substring(0, str.indexOf("*")))) {
                    return true;
                }
            } else {
                if (str.equals(uri)) {
                    return true;
                }
            }
        }
        return false;
    }
}
