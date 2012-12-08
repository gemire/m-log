/**
 * 
 */
package org.mspring.mlog.api.t.utils;

import org.mspring.mlog.api.t.common.TConfigTokens;
import org.mspring.platform.utils.PropertiesUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public final class TConfigUtils {
    private static final String T_PROPERTIES = "t.properties";

    /**
     * 获取redirect uri
     * 
     * @return
     */
    public static final String getRedirectURI() {
        return getProperty(TConfigTokens.REDIRECT_URI_KEY);
    }

    public static final String getClientId(String app) {
        return getProperty(app + "." + TConfigTokens.CLIENT_ID_KEY);
    }

    public static final String getClientSecret(String app) {
        return getProperty(app + "." + TConfigTokens.CLIENT_SECRET_KEY);
    }

    public static final String getAuthzEndpoint(String app) {
        return getProperty(app + "." + TConfigTokens.AUTHZ_ENDPOINT_KEY);
    }

    public static final String getTokenEndpoint(String app) {
        return getProperty(app + "." + TConfigTokens.TOKEN_ENDPOINT_KEY);
    }

    public static final String getScope(String app) {
        return getProperty(app + "." + TConfigTokens.SCOPE_KEY);
    }

    public static final void setScope(String app, String value) {
        setProperty(app + "." + TConfigTokens.SCOPE_KEY, value);
    }

    public static final String getAccessToken(String app) {
        return getProperty(app + "." + TConfigTokens.ACCESS_TOKEN_KEY);
    }

    public static final void setAccessToken(String app, String value) {
        setProperty(app + "." + TConfigTokens.ACCESS_TOKEN_KEY, value);
    }

    public static final String getOpenid(String app) {
        return getProperty(app + "." + TConfigTokens.OPEN_ID);
    }

    public static final void setOpenid(String app, String value) {
        setProperty(app + "." + TConfigTokens.OPEN_ID, value);
    }

    public static final String getOpenkey(String app) {
        return getProperty(app + "." + TConfigTokens.OPEN_KEY);
    }

    public static final void setOpenkey(String app, String value) {
        setProperty(app + "." + TConfigTokens.OPEN_KEY, value);
    }

    private static String getProperty(String key) {
        return PropertiesUtils.getProperty(key, T_PROPERTIES);
    }

    private static void setProperty(String key, String value) {
        PropertiesUtils.setProperties(key, value, T_PROPERTIES);
    }
}
