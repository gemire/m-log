package org.mspring.platform.security.auth;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mspring.platform.security.config.SecurityConfig;
import org.mspring.platform.security.interceptor.LogoutInterceptor;

public abstract class AbstractAuthenticator implements Authenticator {
    private static Log log = LogFactory.getLog(AbstractAuthenticator.class);
    private Map params;
    private SecurityConfig securityConfig;

    public void init(Map params, SecurityConfig config) {
        this.params = params;
        this.securityConfig = config;
    }

    public void destroy() {
    }

    public Map getParams() {
        return this.params;
    }

    public SecurityConfig getSecurityConfig() {
        return this.securityConfig;
    }

    public boolean isUserInRole(HttpServletRequest request, String role) {
        return false;
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Object user = getUser(request);
        if (log.isDebugEnabled()) {
            log.debug("principal=" + user);
        }

        if (user == null) {
            log.info("Cannot login user '" + user + "' as they do not exist.");
            throw new AuthenticationException("Cound't find user " + user + ", login failed");
        }
        boolean authenticated = authenticate(user, request);
        if (authenticated) {
            if (log.isDebugEnabled()) {
                log.debug("set user " + user + " in session");
            }

            request.getSession().setAttribute(this.securityConfig.getLoggedInKey(), user);
            request.getSession().setAttribute(this.securityConfig.getLoggedOutKey(), null);

            if (log.isInfoEnabled()) {
                log.info("principal login sucessfully: " + user);
            }
            return;
        }
        log.info("Cannot login user '" + user + "' as they used an incorrect password");
        throw new AuthenticationException("authenticate user " + user + ", failed");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (log.isInfoEnabled()) {
            log.info("Logout principal: " + request.getSession().getAttribute(this.securityConfig.getLoggedInKey()));
        }

        List interceptors = getLogoutInterceptors();

        for (Iterator it = interceptors.iterator(); it.hasNext();) {
            LogoutInterceptor interceptor = (LogoutInterceptor) it.next();
            interceptor.beforeLogout(request, response);
        }

        request.getSession().setAttribute(this.securityConfig.getLoggedInKey(), null);
        request.getSession().setAttribute(this.securityConfig.getLoggedOutKey(), Boolean.TRUE);
        request.getSession().invalidate();

        for (Iterator it = interceptors.iterator(); it.hasNext();) {
            LogoutInterceptor interceptor = (LogoutInterceptor) it.next();
            interceptor.afterLogout(request, response);
        }
    }

    protected List getLogoutInterceptors() {
        return getSecurityConfig().getInterceptors(LogoutInterceptor.class);
    }

    protected abstract Object getUser(HttpServletRequest paramHttpServletRequest);

    protected abstract boolean authenticate(Object paramObject, HttpServletRequest paramHttpServletRequest);
}