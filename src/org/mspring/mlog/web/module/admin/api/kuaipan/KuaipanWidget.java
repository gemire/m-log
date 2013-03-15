/**
 * 
 */
package org.mspring.mlog.web.module.admin.api.kuaipan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.api.kuaipan.KuaipanUtils;
import org.mspring.mlog.api.kuaipan.MKuaipanAPI;
import org.mspring.mlog.api.kuaipan.client.KuaipanAPI;
import org.mspring.mlog.api.kuaipan.client.exception.KuaipanAuthExpiredException;
import org.mspring.mlog.api.kuaipan.client.exception.KuaipanIOException;
import org.mspring.mlog.api.kuaipan.client.exception.KuaipanServerException;
import org.mspring.mlog.api.kuaipan.client.session.OauthSession;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-1-6
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/api/kuaipan")
public class KuaipanWidget extends AbstractAdminWidget {
    @Autowired
    private OptionService optionService;

    @RequestMapping("/setting")
    public String setting(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = optionService.getOptions();
        model.addAllAttributes(options);
        return "/admin/api/kuaipan/setting";
    }

    @RequestMapping("/saveSetting")
    public String saveSetting(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/api/kuaipan/setting";
    }

    private KuaipanAPI api;

    /**
     * 获取未授权的临时 token
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws KuaipanIOException
     * @throws KuaipanServerException
     * @throws KuaipanAuthExpiredException
     */
    @RequestMapping("/requestToken")
    public String requestToken(HttpServletRequest request, HttpServletResponse response, Model model) throws KuaipanIOException, KuaipanServerException, KuaipanAuthExpiredException {
        String consumer_key = optionService.getOption("api_kuaipan_key");
        String consumer_secret = optionService.getOption("api_kuaipan_secret");
        OauthSession session = new OauthSession(consumer_key, consumer_secret, OauthSession.Root.APP_FOLDER);
        api = new MKuaipanAPI(session);
        String url = api.requestToken();
        return "redirect:" + url;
    }

    /**
     * 获取 access_token
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws KuaipanIOException
     * @throws KuaipanServerException
     * @throws KuaipanAuthExpiredException
     */
    @RequestMapping("/accessToken")
    public String accessToken(HttpServletRequest request, HttpServletResponse response, Model model) throws KuaipanIOException, KuaipanServerException, KuaipanAuthExpiredException {
        if (api == null || api.getSession() == null) {
            return "/admin/api/kuaipan/accessTokenFail";
        }
        api.accessToken();
        if (api.getSession().isAuth()) {
            KuaipanUtils.saveAuthFile(api.getSession().token);
        }
        // 设置快盘状态为已开通
        optionService.setOption("api_kuaipan_on", "true");
        return "/admin/api/kuaipan/accessTokenSuccess";
    }

    /**
     * 关闭快盘接口
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("close")
    public String closeKuaipan(HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOption("api_kuaipan_on", "false");
        return "redirect:/admin/api/kuaipan/setting";
    }

}
