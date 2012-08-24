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
     * 获取OAuth2
     * 
     * @param redirectUri
     * @return
     */
    public static OAuthV2 getOAuthV2(String redirectUri) {
        OAuthV2 oAuth = new OAuthV2();
        oAuth.setClientId("801115505");
        oAuth.setClientSecret("be1dd1410434a9f7d5a2586bab7a6829");
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

    private static final Map<String, String> getMacroBlogSetting() {
        return PropertiesUtils.getPropertyMap(MACRO_BLOG_SETTING_FILE);
    }
}
