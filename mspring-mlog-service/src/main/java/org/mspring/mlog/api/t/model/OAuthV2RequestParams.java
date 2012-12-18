/**
 * 
 */
package org.mspring.mlog.api.t.model;

/**
 * @author Gao Youbo
 * @since 2012-8-30
 * @Description
 * @TODO 获取微博操作请求所需要的OauthV2授权信息
 */
public class OAuthV2RequestParams {
    public static final String OAUTH_V2_VERSION = "2.a";

    private String clientid;
    private String clientSecret;
    private String accessToken;
    private String openid;
    private String openkey;
    private String oauthVersion = OAUTH_V2_VERSION;
    /**
     * @return the clientid
     */
    public String getClientid() {
        return clientid;
    }
    /**
     * @param clientid the clientid to set
     */
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }
    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }
    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    /**
     * @return the openid
     */
    public String getOpenid() {
        return openid;
    }
    /**
     * @param openid the openid to set
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    /**
     * @return the openkey
     */
    public String getOpenkey() {
        return openkey;
    }
    /**
     * @param openkey the openkey to set
     */
    public void setOpenkey(String openkey) {
        this.openkey = openkey;
    }
    /**
     * @return the oauthVersion
     */
    public String getOauthVersion() {
        return oauthVersion;
    }
    /**
     * @param oauthVersion the oauthVersion to set
     */
    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

}
