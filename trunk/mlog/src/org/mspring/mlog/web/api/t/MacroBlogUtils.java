/**
 * 
 */
package org.mspring.mlog.web.api.t;

import java.util.Map;

import org.mspring.mlog.web.api.t.tencent.oauthv2.OAuthV2;
import org.mspring.platform.utils.PropertiesUtils;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-24
 * @Description
 * @TODO
 */
public class MacroBlogUtils {
    public static final String MACRO_BLOG_SETTING_FILE = "t.properties";

    public static final String APP_KEY_SUFFIX = "app_key";
    public static final String APP_SECRET_SUFFIX = "app_secret";
    
    /**
     * 腾讯微博配置前缀
     */
    public static final String TENCENT_APP_KEY_PREFIX = "tencent.";

    /**
     * 获取OAuth2
     * 
     * @param redirectUri
     * @return
     */
    public static OAuthV2 getOAuthV2(String prefix, String redirectUri) {
        OAuthV2 oAuth = new OAuthV2();
        oAuth.setClientId(getAppKey(prefix));
        oAuth.setClientSecret(getAppSecret(prefix));
        oAuth.setRedirectUri(redirectUri);
        return oAuth;
    }

    /**
     * 获取app_key
     * 
     * @param prefix
     *            appkey配置的前缀
     * @return
     */
    public static final String getAppKey(String prefix) {
        String appkey = getMacroBlogSetting().get(prefix + APP_KEY_SUFFIX);
        if (StringUtils.isNotBlank(appkey)) {
            return appkey;
        }
        return "";
    }

    /**
     * 获取app_secret
     * 
     * @param prefix
     * @return
     */
    public static final String getAppSecret(String prefix) {
        String appSecret = getMacroBlogSetting().get(prefix + APP_SECRET_SUFFIX);
        if (StringUtils.isNotBlank(appSecret)) {
            return appSecret;
        }
        return "";
    }
    
    /**
     * 写入应用授权信息
     */
    public static final void setAuthorizeInfo(String prefix, String code, String openid, String openkey){
        Map<String, String> map = getMacroBlogSetting();
        map.put(prefix + "code", code);
        map.put(prefix + "openid", openid);
        map.put(prefix + "openkey", openkey);
        
        PropertiesUtils.setPropertyMap(MACRO_BLOG_SETTING_FILE, map);
    }

    private static final Map<String, String> getMacroBlogSetting() {
        return PropertiesUtils.getPropertyMap(MACRO_BLOG_SETTING_FILE);
    }
}
