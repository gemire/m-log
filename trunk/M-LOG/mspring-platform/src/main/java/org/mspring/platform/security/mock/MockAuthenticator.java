package org.mspring.platform.security.mock;

import javax.servlet.http.HttpServletRequest;

import org.mspring.platform.security.auth.AbstractAuthenticator;

public class MockAuthenticator extends AbstractAuthenticator {
    protected boolean authenticate(Object user, HttpServletRequest request) {
        return true;
    }

    protected Object getUser(HttpServletRequest request) {
        return new User();
    }
}