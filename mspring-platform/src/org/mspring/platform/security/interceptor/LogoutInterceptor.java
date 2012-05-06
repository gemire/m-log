package org.mspring.platform.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface LogoutInterceptor extends Interceptor {
    public abstract void beforeLogout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

    public abstract void afterLogout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}