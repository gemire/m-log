/**
 * 
 */
package org.mspring.mlog.web.module.t;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.api.t.common.TConfigTokens;
import org.mspring.mlog.web.api.t.model.OAuthParams;
import org.mspring.mlog.web.api.t.utils.TConfigUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
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
public class MainController {
    private static final Logger log = Logger.getLogger(MainController.class);

    @RequestMapping("/{app}")
    public String authorize(@PathVariable("app") String app, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean selected = false;
        OAuthParams oauthParams = new OAuthParams();
        // OAuthRegParams oauthRegParams = null;
        if (TConfigTokens.APP_TENCENT.equals(app)) {
            selected = true;
            oauthParams.setAuthzEndpoint(TConfigUtils.getAuthzEndpoint(app));
            oauthParams.setTokenEndpoint(TConfigUtils.getTokenEndpoint(app));
        }
        if (selected) {
            oauthParams.setApplication(app);
            oauthParams.setRedirectUri(TConfigUtils.getRedirectURI());
            model.addAttribute("oauthParams", oauthParams);
            return "/t/get_authz";
        }
        model.addAttribute("oauthRegParams", null);
        return "/t/get_authz_failure";
    }
}
