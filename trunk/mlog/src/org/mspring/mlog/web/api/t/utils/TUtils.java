/**
 * 
 */
package org.mspring.mlog.web.api.t.utils;

import org.mspring.mlog.web.api.t.MacroBlogException;
import org.mspring.mlog.web.api.t.model.OAuthParams;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public final class TUtils {
    private TUtils() {
    }

    public static final String REG_TYPE_PULL = "pull";
    public static final String REG_TYPE_PUSH = "push";


    /**
     * 验证授权信息参数
     * 
     * @param oauthParams
     * @throws Exception
     */
    public static void validateAuthorizationParams(OAuthParams oauthParams) throws MacroBlogException {
        String authzEndpoint = oauthParams.getAuthzEndpoint();
        String tokenEndpoint = oauthParams.getTokenEndpoint();
        String clientId = oauthParams.getClientId();
        String clientSecret = oauthParams.getClientSecret();
        String redirectUri = oauthParams.getRedirectUri();

        StringBuffer sb = new StringBuffer();

        if (StringUtils.isBlank(authzEndpoint)) {
            sb.append("Authorization Endpoint ");
        }

        if (StringUtils.isBlank(tokenEndpoint)) {
            sb.append("Token Endpoint ");
        }

        if (StringUtils.isBlank(clientId)) {
            sb.append("Client ID ");
        }

        if (StringUtils.isBlank(clientSecret)) {
            sb.append("Client Secret ");
        }

        if (!TConfigUtils.getRedirectURI().equals(redirectUri)) {
            sb.append("Redirect URI");
        }

        String incorrectParams = sb.toString();
        if ("".equals(incorrectParams)) {
            return;
        }
        throw new MacroBlogException("Incorrect parameters: " + incorrectParams);
    }
    
    public static void validateTokenParams(OAuthParams oauthParams) throws MacroBlogException {

        String authzEndpoint = oauthParams.getAuthzEndpoint();
        String tokenEndpoint = oauthParams.getTokenEndpoint();
        String clientId = oauthParams.getClientId();
        String clientSecret = oauthParams.getClientSecret();
        String redirectUri = oauthParams.getRedirectUri();
        String authzCode = oauthParams.getAuthzCode();

        StringBuffer sb = new StringBuffer();

        if (StringUtils.isBlank(authzCode)) {
            sb.append("Authorization Code ");
        }

        if (StringUtils.isBlank(authzEndpoint)) {
            sb.append("Authorization Endpoint ");
        }

        if (StringUtils.isBlank(tokenEndpoint)) {
            sb.append("Token Endpoint ");
        }

        if (StringUtils.isBlank(clientId)) {
            sb.append("Client ID ");
        }

        if (StringUtils.isBlank(clientSecret)) {
            sb.append("Client Secret ");
        }

        if (!TConfigUtils.getRedirectURI().equals(redirectUri)) {
            sb.append("Redirect URI");
        }

        String incorrectParams = sb.toString();
        if ("".equals(incorrectParams)) {
            return;
        }
        throw new MacroBlogException("Incorrect parameters: " + incorrectParams);

    }
    
    public static String isIssued(String value) {
        if (StringUtils.isBlank(value)) {
            return "(Not issued)";
        }
        return value;
    }
}
