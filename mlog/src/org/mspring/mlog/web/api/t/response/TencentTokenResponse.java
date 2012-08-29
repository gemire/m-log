/**
 * 
 */
package org.mspring.mlog.web.api.t.response;

import org.apache.amber.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.amber.oauth2.common.OAuth;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.token.BasicOAuthToken;
import org.apache.amber.oauth2.common.token.OAuthToken;
import org.apache.amber.oauth2.common.utils.OAuthUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public class TencentTokenResponse extends OAuthAccessTokenResponse {

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.amber.oauth2.client.response.OAuthAccessTokenResponse#
     * getAccessToken()
     */
    @Override
    public String getAccessToken() {
        // TODO Auto-generated method stub
        return getParam(OAuth.OAUTH_ACCESS_TOKEN);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.amber.oauth2.client.response.OAuthAccessTokenResponse#getExpiresIn
     * ()
     */
    @Override
    public Long getExpiresIn() {
        // TODO Auto-generated method stub
        String value = getParam(OAuth.OAUTH_EXPIRES_IN);
        return value == null ? null : Long.valueOf(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.amber.oauth2.client.response.OAuthAccessTokenResponse#
     * getRefreshToken()
     */
    @Override
    public String getRefreshToken() {
        // TODO Auto-generated method stub
        return getParam(OAuth.OAUTH_EXPIRES_IN);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.amber.oauth2.client.response.OAuthAccessTokenResponse#getScope
     * ()
     */
    @Override
    public String getScope() {
        // TODO Auto-generated method stub
        return getParam(OAuth.OAUTH_SCOPE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.amber.oauth2.client.response.OAuthAccessTokenResponse#
     * getOAuthToken()
     */
    @Override
    public OAuthToken getOAuthToken() {
        // TODO Auto-generated method stub
        return new BasicOAuthToken(getAccessToken(), getExpiresIn(), getRefreshToken(), getScope());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.amber.oauth2.client.response.OAuthClientResponse#setBody(java
     * .lang.String)
     */
    @Override
    protected void setBody(String body) throws OAuthProblemException {
        // TODO Auto-generated method stub
        this.body = body;
        parameters = OAuthUtils.decodeForm(body);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.amber.oauth2.client.response.OAuthClientResponse#setContentType
     * (java.lang.String)
     */
    @Override
    protected void setContentType(String contentTypr) {
        // TODO Auto-generated method stub
        this.contentType = contentType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.amber.oauth2.client.response.OAuthClientResponse#setResponseCode
     * (int)
     */
    @Override
    protected void setResponseCode(int responseCode) {
        // TODO Auto-generated method stub
        this.responseCode = responseCode;
    }

}
