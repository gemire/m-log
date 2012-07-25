/**
 * 
 */
package org.mspring.mlog.web.module.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.Application;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/common")
public class AboutWidget {
    /**
     * 关于我
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/about")
    public String about(HttpServletRequest request, HttpServletResponse response, Model model) {
        Application app = Application.getInstance();
        model.addAttribute("app", app);
        return "/admin/common/about";
    }
    
    /**
     * 联系方式
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/contact")
    public String contact(HttpServletRequest request, HttpServletResponse response, Model model) {
        Application app = Application.getInstance();
        model.addAttribute("app", app);
        return "/admin/contact";
    }
}
