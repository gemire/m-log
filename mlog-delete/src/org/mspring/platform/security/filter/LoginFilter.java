package org.mspring.platform.security.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mspring.platform.security.auth.AuthenticationContext;
import org.mspring.platform.security.auth.AuthenticationException;
import org.mspring.platform.security.config.SecurityConfig;
import org.mspring.platform.security.config.SecurityConfigFactory;
import org.mspring.platform.security.interceptor.LoginInterceptor;
import org.mspring.platform.utils.StringUtils;

public class LoginFilter implements Filter {
    protected static final Log log = LogFactory.getLog(LoginFilter.class);
    public static final String ALREADY_FILTERED = "loginfilter.already.filtered";
    public static final String LOGIN_SUCCESS = "success";
    public static final String LOGIN_FAILED = "failed";
    public static final String LOGIN_NOATTEMPT = null;
    public static final String SENTRY_AUTHSTATUS_KEY = "sentry_authstatus";
    protected FilterConfig config;
    protected SecurityConfig securityConfig;

    public void init(FilterConfig config) throws ServletException {
        log.info("Initializing filter '" + config.getFilterName() + "'");
        this.config = config;

        this.securityConfig = SecurityConfigFactory.getConfig();
        config.getServletContext().setAttribute("__sentry_config__", this.securityConfig);

        log.info("Filter '" + config.getFilterName() + "' configured successfully");
    }

    public void destroy() {
        this.securityConfig.destroy();
        this.securityConfig = null;
        this.config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 如果请求已经验证、或者关闭验证，直接跳过
        if ((request.getAttribute("loginfilter.already.filtered") != null) || (!this.securityConfig.getController().isSecurityEnabled())) {
            chain.doFilter(request, response);
            return;
        }

        request.setAttribute("loginfilter.already.filtered", Boolean.TRUE);
        request.setAttribute("sentry_authstatus", LOGIN_NOATTEMPT);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            authenticate(req, res, chain);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
        }
    }

    protected void authenticate(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if ((isLoginUrl(req)) || (hasAuthenticated(req)) || (this.securityConfig.isPathExcluded(extractRequestPath(req), req.getRemoteAddr()))) {
            chain.doFilter(req, res);
        }
        else try {
            doAuthenticate(req, res, chain);
        }
        catch (Exception e) {
            sendRedirect(req, res, this.securityConfig.getLoginURL());
        }
    }

    protected void doAuthenticate(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List interceptors = this.securityConfig.getInterceptors(LoginInterceptor.class);

        for (Iterator it = interceptors.iterator(); it.hasNext();) {
            LoginInterceptor loginInterceptor = (LoginInterceptor) it.next();
            loginInterceptor.beforeLogin(req, res);
        }

        if (!this.securityConfig.useExternalAuthenticator()) {
            try {
                this.securityConfig.getAuthenticator().login(req, res);
                log.debug("Login was successful - setting attribute to \"Success\"");
                req.setAttribute("sentry_authstatus", "success");
            }
            catch (AuthenticationException e) {
                log.debug("Login was not successful - setting attribute to \"Failed\"");
                req.setAttribute("sentry_authstatus", "failed");
                log.warn("Exception was thrown while logging in: " + e.getMessage());

                sendRedirect(req, res, this.securityConfig.getLoginURL());
                return;
            }

        }

        AuthenticationContext.setUser(req.getSession().getAttribute(this.securityConfig.getLoggedInKey()));
        try {
            Iterator it = interceptors.iterator();
            while (it.hasNext()) {
                LoginInterceptor loginInterceptor = (LoginInterceptor) it.next();
                loginInterceptor.afterLogin(req, res, (String) req.getAttribute("sentry_authstatus"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("call loginInterceptor.afterLogin() error.", e);
        }

        String originalURL = (String) req.getSession().getAttribute(this.securityConfig.getOriginalURLKey());
        if (originalURL != null) {
            if (log.isDebugEnabled()) {
                log.debug("Logged In - Redirecting to Original URL: " + req.getContextPath() + originalURL);
            }
            req.getSession().setAttribute(this.securityConfig.getOriginalURLKey(), null);
            sendRedirect(req, res, originalURL);
            return;
        }
        if (req.getParameter("sentry_destination") != null) {
            if (log.isDebugEnabled()) {
                log.debug("Logged In - redirecting to sentry_destination: " + req.getParameter("sentry_destination"));
            }
            sendRedirect(req, res, req.getParameter("sentry_destination"));
            return;
        }
        chain.doFilter(req, res);
    }

    protected boolean hasAuthenticated(HttpServletRequest req) {
        boolean result = false;
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object user = session.getAttribute(this.securityConfig.getLoggedInKey());
            if (user != null) {
                result = true;
            }
            AuthenticationContext.setUser(user);
        }
        return result;
    }

    /**
     * 重定向
     * 
     * @param req
     * @param res
     * @param location
     * @throws IOException
     */
    protected void sendRedirect(HttpServletRequest req, HttpServletResponse res, String location) throws IOException {
        // 如果链接中存在"://" 或是 连接中已经添加了 contextPath
        if ((isExternalUrlLink(location)) || (isStartWithContextPath(req, location))) {
            res.sendRedirect(location);
        }
        else {
            res.sendRedirect(req.getContextPath() + location);
        }
    }

    private boolean isLoginUrl(HttpServletRequest req) {
        //return (req.getServletPath() != null) && (req.getServletPath().equals(this.securityConfig.getLoginURL()));
        String pathInfo = req.getPathInfo();
        return (!StringUtils.isBlank(pathInfo)) && pathInfo.equals(this.securityConfig.getLoginURL());
    }

    private boolean isExternalUrlLink(String url) {
        return url.indexOf("://") != -1;
    }

    private boolean isStartWithContextPath(HttpServletRequest req, String url) {
        String contextPath = req.getContextPath();
        return url.startsWith(contextPath);
    }

    private String extractRequestPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String query = request.getQueryString();
        return (servletPath == null ? "" : servletPath) + (pathInfo == null ? "" : pathInfo) + (query == null ? "" : new StringBuffer().append("?").append(query).toString());
    }
}