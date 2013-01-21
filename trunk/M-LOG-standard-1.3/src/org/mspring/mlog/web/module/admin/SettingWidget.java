/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Skin;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.SkinService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.security.annotation.Premission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/setting")
public class SettingWidget extends AbstractAdminWidget {
    private OptionService optionService;
    private SkinService skinService;

    /**
     * @param optionService
     *            the optionService to set
     */
    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    /**
     * @param skinService
     *            the skinService to set
     */
    @Autowired
    public void setSkinService(SkinService skinService) {
        this.skinService = skinService;
    }

    /**
     * 显示"博客信息"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/bloginfo")
    @Premission(item = "220005")
    public String infoSettingView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = optionService.getOptions();
        model.addAllAttributes(options);
        return "/admin/setting/bloginfo";
    }

    /**
     * 保存"博客信息"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/saveBloginfo")
    @Premission(item = "220005")
    public String saveBloginfo(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/setting/bloginfo";
    }

    /**
     * 显示"皮肤设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/skin")
    @Premission(item = "230005")
    public String skinSettingView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Skin> skins = skinService.scrnSkin();
        model.addAttribute("skins", skins);
        return "/admin/setting/skin";
    }

    /**
     * 保存"皮肤设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/saveSkin")
    @Premission(item = "230005")
    public String saveSkin(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/setting/skin";
    }

    /**
     * 显示"SEO设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/seo")
    @Premission(item = "240005")
    public String seoSettingView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = optionService.getOptions();
        model.addAllAttributes(options);
        return "/admin/setting/seo";
    }

    /**
     * 保存"SEO设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/saveSeo")
    @Premission(item = "240005")
    public String saveSeo(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/setting/seo";
    }

    /**
     * 显示"BAE设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/bae")
    @Premission(item = "240005")
    public String baeSettingView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        options = optionService.getOptions();
        model.addAllAttributes(options);
        return "/admin/setting/bae";
    }

    /**
     * 保存"BAE设置"
     * 
     * @param options
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/saveBae")
    public String saveBae(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/setting/bae";
    }
}
