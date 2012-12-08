package org.mspring.platform.security.logout;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mspring.platform.security.auth.AuthenticationException;
import org.mspring.platform.security.auth.Authenticator;
import org.mspring.platform.security.config.SecurityConfig;

public class LogoutServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(LogoutServlet.class);

    private SecurityConfig securityConfig = null;

    public void init(ServletConfig servletConfig) throws ServletException {
        log.info("Initializing servlet '" + servletConfig.getServletName() + "'");

        super.init(servletConfig);
        this.securityConfig = ((SecurityConfig) servletConfig.getServletContext().getAttribute("__sentry_config__"));

        log.info("Servlet '" + servletConfig.getServletName() + "' configured successfully");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Authenticator authenticator = this.securityConfig.getAuthenticator();
            authenticator.logout(request, response);
        } catch (AuthenticationException e) {
            throw new ServletException("Sentry authenticator couldn't log out", e);
        }

        log.debug("sendRedirect to: " + this.securityConfig.getLogoutURL());
        if (isRelativeRedirect())
            response.sendRedirect(request.getContextPath() + this.securityConfig.getLogoutURL());
        else
            response.sendRedirect(this.securityConfig.getLogoutURL());
    }

    private boolean isRelativeRedirect() {
        return this.securityConfig.getLogoutURL().indexOf("://") == -1;
    }
}