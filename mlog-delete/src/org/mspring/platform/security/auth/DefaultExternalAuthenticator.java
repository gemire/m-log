package org.mspring.platform.security.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultExternalAuthenticator extends AbstractExternalAuthenticator {
    private static Log log = LogFactory.getLog(DefaultExternalAuthenticator.class);

    protected Object getUser(HttpServletRequest request) {
        Object principal = request.getSession().getAttribute(getSecurityConfig().getLoggedInKey());

        if (log.isDebugEnabled()) {
            log.debug("Get principal from external Authenticator: " + principal);
        }
        if (log.isInfoEnabled()) {
            log.info("login principal: " + principal);
        }

        return principal;
    }
}