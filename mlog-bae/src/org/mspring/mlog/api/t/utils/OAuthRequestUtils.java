/**
 * 
 */
package org.mspring.mlog.api.t.utils;

import org.mspring.mlog.api.t.model.OAuthV2RequestParams;

/**
 * @author Gao Youbo
 * @since 2012-8-30
 * @Description
 * @TODO
 */
public class OAuthRequestUtils {
    /**
     * 获取请求参数
     * 
     * @param app
     * @return
     */
    public static final OAuthV2RequestParams getOAuthV2RequesetParams(String app) {
        OAuthV2RequestParams params = new OAuthV2RequestParams();
        params.setClientid(TConfigUtils.getClientId(app));
        params.setClientSecret(TConfigUtils.getClientSecret(app));
        params.setAccessToken(TConfigUtils.getAccessToken(app));
        params.setOpenid(TConfigUtils.getOpenid(app));
        params.setOpenkey(TConfigUtils.getOpenkey(app));
        return params;
    }
}
