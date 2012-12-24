/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import org.mspring.mlog.Application;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
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
public class BottomWidget {
    @RequestMapping(value = "/bottom")
    public String execute(Model model) {
        model.addAttribute("app", Application.getInstance());
        return "/admin/frame/bottom";
    }
}
