/**
 * 
 */
package org.mspring.mlog.web.api.t.utils;

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
        return getProperty(TConfigKeys.REDIRECT_URI_KEY);
    }

    public static final String getClientId(String app) {
        return getProperty(app + "." + TConfigKeys.CLIENT_ID_KEY);
    }

    public static final String getClientSecret(String app) {
        return getProperty(app + "." + TConfigKeys.CLIENT_SECRET_KEY);
    }
    
    public static final String getAuthzEndpoint(String app){
        return getProperty(app + "." + TConfigKeys.AUTHZ_ENDPOINT_KEY);
    }
    
    public static final String getTokenEndpoint(String app){
        return getProperty(app + "." + TConfigKeys.TOKEN_ENDPOINT_KEY);
    }
    
    public static final String getScope(String app){
        return getProperty(app + "." + TConfigKeys.SCOPE_KEY);
    }
    
    public static final void setScope(String app, String value){
        setProperty(app + "." + TConfigKeys.SCOPE_KEY, value);
    }
    
    private static String getProperty(String key) {
        return PropertiesUtils.getProperty(key, T_PROPERTIES);
    }

    private static void setProperty(String key, String value) {
        PropertiesUtils.setProperties(key, value, T_PROPERTIES);
    }
}
