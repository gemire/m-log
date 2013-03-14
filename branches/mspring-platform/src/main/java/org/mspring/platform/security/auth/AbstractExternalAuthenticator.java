package org.mspring.platform.security.auth;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractExternalAuthenticator extends AbstractAuthenticator {
    protected boolean authenticate(Object user, HttpServletRequest request) {
        return true;
    }
}