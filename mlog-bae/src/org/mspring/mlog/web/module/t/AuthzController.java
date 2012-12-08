/**
 * 
 */
package org.mspring.mlog.web.module.t;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.types.ResponseType;
import org.mspring.mlog.api.t.MacroBlogException;
import org.mspring.mlog.api.t.model.OAuthParams;
import org.mspring.mlog.api.t.utils.TUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
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
public class AuthzController {
    @RequestMapping("/{app}/authorize")
    public String authorize(@ModelAttribute("oauthParams") OAuthParams oauthParams, @PathVariable String app, HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException, MacroBlogException {
        try {
            TUtils.validateAuthorizationParams(oauthParams);
            OAuthClientRequest clientRequest = OAuthClientRequest.authorizationLocation(oauthParams.getAuthzEndpoint()).setClientId(oauthParams.getClientId()).setRedirectURI(oauthParams.getRedirectUri()).setResponseType(ResponseType.CODE.toString()).setScope(oauthParams.getScope()).buildQueryMessage();
            return "redirect:" + clientRequest.getLocationUri();
        }
        catch (Exception e) {
            // TODO: handle exception
            oauthParams.setErrorMessage(e.getMessage());
            return "/t/get_authz";
        }
    }
}
