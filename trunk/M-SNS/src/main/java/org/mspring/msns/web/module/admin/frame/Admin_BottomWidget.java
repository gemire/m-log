/**
 * 
 */
package org.mspring.msns.web.module.admin.frame;

import org.mspring.msns.Application;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description 底部信息页面
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class Admin_BottomWidget {
    @RequestMapping(value = "/bottom")
    public String execute(Model model) {
        model.addAttribute("app", Application.getInstance());
        return "/admin/frame/bottom";
    }
}
