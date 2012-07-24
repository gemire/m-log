/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.OptionService;
import org.mspring.platform.web.widget.stereotype.Widget;
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
public class SettingWidget {
    private OptionService optionService;

    /**
     * @param optionService
     *            the optionService to set
     */
    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping({ "/", "" })
    public String settingView(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, String> options = optionService.getOptions();
        model.addAllAttributes(options);
        return "/admin/setting/setting-view";
    }

    @RequestMapping("/info")
    public String infoSettingView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/setting/info-setting";
    }

    @RequestMapping("/skin")
    public String skinSettingView(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/setting/skin-setting";
    }

    @RequestMapping("/saveSetting")
    public String saveSetting(@RequestParam Map<String, String> options, HttpServletRequest request, HttpServletResponse response, Model model) {
        optionService.setOptions(options);
        return "redirect:/admin/setting";
    }
}
