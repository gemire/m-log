package org.mspring.platform.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface LoginInterceptor extends Interceptor {
    public abstract void beforeLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

    public abstract void afterLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString);
}