/**
 * 
 */
package org.mspring.mlog.web.module.t;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.amber.oauth2.client.response.OAuthAuthzResponse;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.mspring.mlog.web.api.t.common.TConfigTokens;
import org.mspring.mlog.web.api.t.model.OAuthParams;
import org.mspring.mlog.web.api.t.utils.TConfigUtils;
import org.mspring.mlog.web.api.t.utils.TUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO 授权成功后的回调方法
 */
@Widget
@RequestMapping("/t")
public class CallbackController {
    @RequestMapping(value = "/{app}/callback", method = RequestMethod.GET)
    public String callback(@ModelAttribute("oauthParams") OAuthParams oauthParams, @PathVariable String app, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {

            // Create the response wrapper
            OAuthAuthzResponse oar = null;
            oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
            
            if (TConfigTokens.APP_TENCENT.equals(app)) {
                String openid = oar.getParam("openid");
                String openkey = oar.getParam("openkey");
                TConfigUtils.setOpenid(app, openid);
                TConfigUtils.setOpenkey(app, openkey);
            }
            

            // Get Authorization Code
            String code = oar.getCode();

            // Get OAuth Info
            String clientId = TConfigUtils.getClientId(app);
            String clientSecret = TConfigUtils.getClientSecret(app);
            String authzEndpoint = TConfigUtils.getAuthzEndpoint(app);
            String tokenEndpoint = TConfigUtils.getTokenEndpoint(app);
            String redirectUri = TConfigUtils.getRedirectURI();
            String scope = TConfigUtils.getScope(app);

            oauthParams.setCode(code);
            oauthParams.setClientId(clientId);
            oauthParams.setClientSecret(clientSecret);
            oauthParams.setAuthzEndpoint(authzEndpoint);
            oauthParams.setTokenEndpoint(tokenEndpoint);
            oauthParams.setRedirectUri(redirectUri);
            oauthParams.setScope(TUtils.isIssued(scope));
            oauthParams.setApplication(app);
        }
        catch (OAuthProblemException e) {
            StringBuffer sb = new StringBuffer();
            sb.append("</br>");
            sb.append("Error code: ").append(e.getError()).append("</br>");
            sb.append("Error description: ").append(e.getDescription()).append("</br>");
            sb.append("Error uri: ").append(e.getUri()).append("</br>");
            sb.append("State: ").append(e.getState()).append("</br>");
            oauthParams.setErrorMessage(sb.toString());
            return "redirect:/t";
        }
        model.addAttribute("oauthParams", oauthParams);
        return "/t/request_token";
    }
}
