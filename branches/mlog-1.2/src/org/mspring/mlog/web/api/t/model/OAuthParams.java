/**
 * 
 */
package org.mspring.mlog.web.api.t.model;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO OAuth授权相关参数
 */
public class OAuthParams {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String authzEndpoint;
    private String tokenEndpoint;
    private String accessToken;
    private long expiresIn;
    private String refreshToken;
    private String scope;
    private String resourceUrl;
    private String resource;
    private String application;
    private String code;
    private String openId;
    private String openKey;

    private String errorMessage;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getAuthzEndpoint() {
        return authzEndpoint;
    }

    public void setAuthzEndpoint(String authzEndpoint) {
        this.authzEndpoint = authzEndpoint;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = Long.parseLong(expiresIn);
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     *            the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return the openKey
     */
    public String getOpenKey() {
        return openKey;
    }

    /**
     * @param openKey
     *            the openKey to set
     */
    public void setOpenKey(String openKey) {
        this.openKey = openKey;
    }

}
