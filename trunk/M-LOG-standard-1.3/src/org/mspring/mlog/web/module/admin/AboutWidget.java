/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.Application;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class AboutWidget extends AbstractAdminWidget {

    /**
     * 关于页面
     * 
     * @return
     */
    @RequestMapping("/about")
    public String about(HttpServletRequest request, HttpServletResponse response, Model model) {
        Application app = Application.getInstance();
        model.addAttribute("app", app);
        return "/admin/about";
    }
}
