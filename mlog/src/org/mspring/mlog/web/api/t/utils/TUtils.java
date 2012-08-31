/**
 * 
 */
package org.mspring.mlog.web.api.t.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
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
        String code = oauthParams.getCode();

        StringBuffer sb = new StringBuffer();

        if (StringUtils.isBlank(code)) {
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
    
    /**
     * 生成URL queryString
     * @param queryParamsList
     * @return
     */
    public static String getQueryString(List<NameValuePair> queryParamsList){
        StringBuilder queryString=new StringBuilder();
        for(NameValuePair param:queryParamsList){
                queryString.append('&');
            queryString.append(param.getName());
            queryString.append('=');
            queryString.append(paramEncode(param.getValue()));
        }
        //去掉第一个&号
        return queryString.toString().substring(1);
    }
    
    /**
     * 对参数进行UTF-8编码，并替换特殊字符
     * 
     * @param paramDecString 待编码的参数字符串
     * @return 完成编码转换的字符串
     */
    public static String paramEncode(String paramDecString) {
        if (StringUtils.isBlank(paramDecString)) {
            return "";
        }
        try {
            return URLEncoder.encode(paramDecString, "UTF-8").replace("+", "%20")
                    .replace("*", "%2A").replace("%7E", "~")
                    .replace("#", "%23");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
