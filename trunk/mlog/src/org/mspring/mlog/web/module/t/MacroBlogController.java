/**
 * 
 */
package org.mspring.mlog.web.module.t;

import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.web.api.t.MacroBlogUtils;
import org.mspring.mlog.web.api.t.tencent.oauthv2.OAuthV2;
import org.mspring.mlog.web.api.t.tencent.oauthv2.OAuthV2Client;
import org.mspring.mlog.web.api.t.tencent.utils.QHttpClient;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-8-24
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/t")
public class MacroBlogController {

    /**
     * 授权回调函数
     */
    @RequestMapping("/authorize")
    public String authorize(HttpServletRequest request, HttpServletResponse response, Model model) {
        OAuthV2 oAuth = MacroBlogUtils.getOAuthV2("http://localhost:8080/mlog/t/callback");
        //OAuthV2 oAuth = MacroBlogUtils.getOAuthV2("http://www.mspring.org/callback.php");
        String authorizationUrl = OAuthV2Client.generateAuthorizationURL(oAuth);
        return "redirect:" + authorizationUrl;
    }

    @RequestMapping("/callback")
    public String callback(@RequestParam(required = false) String code, @RequestParam(required = false) String openid, @RequestParam(required = false) String openkey, HttpServletRequest request, HttpServletResponse response, Model model) {
        String responseData = "code=" + code + "&openid=" + openid + "&openkey=" + openkey;
        OAuthV2 oAuth = MacroBlogUtils.getOAuthV2("http://localhost:8080/mlog/t/callback");
        QHttpClient qHttpClient=new QHttpClient(2, 2, 5000, 5000, null, null);
        OAuthV2Client.setQHttpClient(qHttpClient);
        
        //授权
        boolean flag = OAuthV2Client.parseAuthorization(responseData, oAuth);
        qHttpClient.shutdownConnection();
        
        if (flag) { //授权成功
            model.addAttribute("result", "true");
            
            //写入配置
        }
        else { //授权失败
            model.addAttribute("result", "false");
        }
        return "/t/authorize";
    }
}
