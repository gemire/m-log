package org.mspring.platform.security.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.security.Initializable;

public abstract interface Authenticator extends Initializable {
    public abstract void destroy();

    public abstract boolean isUserInRole(HttpServletRequest paramHttpServletRequest, String paramString);

    public abstract void login(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws AuthenticationException;

    public abstract void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws AuthenticationException;
}