/**
 * 
 */
package org.mspring.mlog.api.kuaipan;

import org.mspring.mlog.api.kuaipan.client.exception.KuaipanException;
import org.mspring.mlog.api.kuaipan.client.model.AccessToken;
import org.mspring.mlog.api.kuaipan.client.model.TokenPair;
import org.mspring.mlog.api.kuaipan.client.session.OauthSession;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2013-1-6
 * @Description
 * @TODO
 */
public class KuaipanUtils {
    private static MKuaipanAPI api = null;

    /**
     * 获取快盘操作的API
     * @param consumer_key
     * @param consumer_secret
     * @return
     */
    public static synchronized MKuaipanAPI getApi() {
//        String consumer_key = ServiceFactory.getOptionService().getOption("consumer_key");
//        String consumer_secret = ServiceFactory.getOptionService().getOption("consumer_secret");
        String consumer_key = "xcJs0tWpPdBhGgl0";
        String consumer_secret = "OuY94uwGhtKD5OVU";
        if (api == null) {
            OauthSession session = new OauthSession(consumer_key, consumer_secret, OauthSession.Root.APP_FOLDER);
            api = new MKuaipanAPI(session);

            AccessToken t = loadAuthFile();
            if (t != null) {
                api.getSession().setAuthToken(t.key, t.secret);
                try {
                    api.accountInfo();
                    return api;
                }
                catch (KuaipanException e) {
                    api.getSession().unAuth();
                }
            }
        }
        return api;
    }
    
    
//    public static void main(String[] args) {
//        OauthSession session = new OauthSession("xcJs0tWpPdBhGgl0", "xcJs0tWpPdBhGgl0", OauthSession.Root.APP_FOLDER);
//        MKuaipanAPI api = new MKuaipanAPI(session);
//        AccessToken t = new AccessToken("428882095", "");
//        if (t != null) {
//            api.getSession().setAuthToken(t.key, t.secret);
//            try {
//                api.accountInfo();
//                return api;
//            }
//            catch (KuaipanException e) {
//                api.getSession().unAuth();
//            }
//        }
//    }
    
//    /**
//     * 获取未授权的临时 token
//     * @return
//     * @throws KuaipanAuthExpiredException 
//     * @throws KuaipanServerException 
//     * @throws KuaipanIOException 
//     */
//    public static String requestToken() throws KuaipanIOException, KuaipanServerException, KuaipanAuthExpiredException{
//        String consumer_key = ServiceFactory.getOptionService().getOption("api_kuaipan_key");
//        String consumer_secret = ServiceFactory.getOptionService().getOption("api_kuaipan_secret");
//        OauthSession session = new OauthSession(consumer_key, consumer_secret, OauthSession.Root.APP_FOLDER);
//        MKuaipanAPI api = new MKuaipanAPI(session);
//        return api.requestToken();
//    }
    
    
    /**
     * 加载授权信息
     * @return
     */
    public static AccessToken loadAuthFile() {
//        String authKey = ServiceFactory.getOptionService().getOption("api_kuaipan_access_key");
//        String authSecret = ServiceFactory.getOptionService().getOption("api_kuaipan_access_secret");
        
        String authKey = "004bfb83134399d57ee3fcb7";
        String authSecret = "6e850cc9e5084cad9518e18b38d1f4e9";

        if (StringUtils.isBlank(authKey) || StringUtils.isBlank(authSecret)) {
            return null;
        }
        return new AccessToken(authKey, authSecret);
    }

    /**
     * 保存授权信息
     * @param token
     * @return
     */
    public static boolean saveAuthFile(TokenPair token) {
        ServiceFactory.getOptionService().setOption("api_kuaipan_access_key", token.key);
        ServiceFactory.getOptionService().setOption("api_kuaipan_access_secret", token.secret);
        return true;
    }
}
