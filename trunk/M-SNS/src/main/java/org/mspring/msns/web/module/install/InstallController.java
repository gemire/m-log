/**
 * 
 */
package org.mspring.msns.web.module.install;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.msns.service.InstallService;
import org.mspring.msns.service.OptionService;
import org.mspring.msns.web.module.AbstractWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-9-9
 * @Description
 * @TODO
 */
@Controller
@RequestMapping("/install")
public class InstallController extends AbstractWidget {
    @Autowired
    private OptionService optionService;
    
    @Autowired
    private InstallService installService;

    /**
     * 安装第一步,显示授权信息
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({ "", "/", "/setup1" })
    public String setup1(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (installService.hasInstall()) {
            return prompt(model, "系统消息", "系统已经安装，不能重复运行安装引导", optionService.getOption("siteurl"));
        }
        return "/install/setup1";
    }

    /**
     * 安装第二步，检测系统当前环境
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/setup2")
    public String setup2(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (installService.hasInstall()) {
            return prompt(model, "系统消息", "系统已经安装，不能重复运行安装引导", optionService.getOption("siteurl"));
        }
        return "redirect:/install/setup3";
    }

    /**
     * 安装第三步,填写相关信息
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/setup3")
    public String setup3_view(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (installService.hasInstall()) {
            return prompt(model, "系统消息", "系统已经安装，不能重复运行安装引导", optionService.getOption("siteurl"));
        }
        StringBuffer url = request.getRequestURL();
        String siteurl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
        siteurl = siteurl + request.getContextPath();
        model.addAttribute("siteurl", siteurl);
        return "/install/setup3";
    }

    /**
     * 安装第四步，初始化数据
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/setup4")
    public String setup4(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (installService.hasInstall()) {
            return prompt(model, "系统消息", "系统已经安装，不能重复运行安装引导", optionService.getOption("siteurl"));
        }
        try {
            String blogname = request.getParameter("blogname");
            String siteurl = request.getParameter("siteurl");
            String username = request.getParameter("username");
            String alias = request.getParameter("alias");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            installService.initBlogInfo(blogname, siteurl, username, alias, password, email);
            installService.initTreeItems();
            installService.initPosts();
            installService.initLinks();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "/install/failure";
        }
        installService.setHasInstalled();
        String siteurl = optionService.getOption("siteurl");
        model.addAttribute("siteurl", siteurl);
        return "/install/success";
    }
}
