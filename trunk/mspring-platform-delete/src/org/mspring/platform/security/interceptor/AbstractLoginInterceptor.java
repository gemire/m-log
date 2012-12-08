package org.mspring.platform.security.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.security.config.SecurityConfig;

public abstract class AbstractLoginInterceptor implements LoginInterceptor {
    private SecurityConfig securityConfig;

    public void init(Map params, SecurityConfig config) {
        this.securityConfig = config;
    }

    public void destroy() {
    }

    public void beforeLogin(HttpServletRequest request, HttpServletResponse response) {
    }

    public void afterLogin(HttpServletRequest request, HttpServletResponse response, String loginStatus) {
    }

    protected SecurityConfig getSecurityConfig() {
        return this.securityConfig;
    }
}