/**
 * 
 */
package org.mspring.mlog.web.module.t;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.amber.oauth2.client.OAuthClient;
import org.apache.amber.oauth2.client.URLConnectionClient;
import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.amber.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.types.GrantType;
import org.mspring.mlog.web.api.t.MacroBlogException;
import org.mspring.mlog.web.api.t.model.OAuthParams;
import org.mspring.mlog.web.api.t.response.TencentTokenResponse;
import org.mspring.mlog.web.api.t.utils.TConfigKeys;
import org.mspring.mlog.web.api.t.utils.TUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/t")
public class TokenController {
    @RequestMapping("/{app}/get_token")
    public String authorize(@ModelAttribute("oauthParams") OAuthParams oauthParams, @PathVariable String app, HttpServletRequest req, Model model) throws OAuthSystemException, IOException {
        try {

            TUtils.validateTokenParams(oauthParams);

            OAuthClientRequest request = OAuthClientRequest.tokenLocation(oauthParams.getTokenEndpoint()).setClientId(oauthParams.getClientId()).setClientSecret(oauthParams.getClientSecret()).setRedirectURI(oauthParams.getRedirectUri()).setCode(oauthParams.getAuthzCode()).setGrantType(GrantType.AUTHORIZATION_CODE).buildQueryMessage();

            OAuthClient client = new OAuthClient(new URLConnectionClient());

            OAuthAccessTokenResponse oauthResponse = null;
            Class<? extends OAuthAccessTokenResponse> cl = OAuthJSONAccessTokenResponse.class;

            if (TConfigKeys.APP_TENCENT.equals(app)) {
                cl = TencentTokenResponse.class;
            }

            oauthResponse = client.accessToken(request, cl);

            oauthParams.setAccessToken(oauthResponse.getAccessToken());
            oauthParams.setExpiresIn(oauthResponse.getExpiresIn());
            oauthParams.setRefreshToken(TUtils.isIssued(oauthResponse.getRefreshToken()));

            model.addAttribute("oauthParams", oauthParams);
            return "/t/get_resource";

        }
        catch (MacroBlogException e) {
            oauthParams.setErrorMessage(e.getMessage());
            model.addAttribute("oauthParams", oauthParams);
            return "/t/request_token";
        }
        catch (OAuthProblemException e) {
            StringBuffer sb = new StringBuffer();
            sb.append("</br>");
            sb.append("Error code: ").append(e.getError()).append("</br>");
            sb.append("Error description: ").append(e.getDescription()).append("</br>");
            sb.append("Error uri: ").append(e.getUri()).append("</br>");
            sb.append("State: ").append(e.getState()).append("</br>");
            oauthParams.setErrorMessage(sb.toString());
            model.addAttribute("oauthParams", oauthParams);
            return "/t/get_authz";
        }
    }
}
