package org.mspring.platform.security.config;

import java.util.List;

import org.mspring.platform.security.auth.Authenticator;
import org.mspring.platform.security.controller.SecurityController;

public abstract interface SecurityConfig {
    public static final String SECURITY_CONFIG_FILE = "security-config.xml";
    public static final String STORAGE_KEY = "__sentry_config__";

    public abstract String getLoginURL();

    public abstract String getLogoutURL();

    public abstract String getOriginalURLKey();

    public abstract String getLoggedInKey();

    public abstract String getLoggedOutKey();

    public abstract String getLoginformUsernameKey();

    public abstract String getLoginformPasswordKey();

    public abstract Authenticator getAuthenticator();

    public abstract List getServices();

    public abstract SecurityController getController();

    public abstract List getInterceptors(Class paramClass);

    public abstract boolean isPathExcluded(String paramString1, String paramString2);

    public abstract boolean useExternalAuthenticator();

    public abstract void destroy();
}