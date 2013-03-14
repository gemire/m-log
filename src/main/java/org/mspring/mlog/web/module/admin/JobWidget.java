/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO 定时任务管理
 */
@Widget
@RequestMapping("/admin/job")
public class JobWidget {
    @Autowired
    private OptionService optionService;

    @RequestMapping("/setting")
    public String setting(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAllAttributes(optionService.getOptions());
        return "/admin/job/setting";
    }
    
    @RequestMapping("/saveSetting")
    public String saveSetting(HttpServletRequest request, HttpServletResponse response, Model model){
        return "redirect:/admin/job/setting";
    }
    
    @RequestMapping("/exec")
    public String exec(HttpServletRequest request, HttpServletResponse response, Model model){
        return "redirect:/admin/job/setting";
    }
}
