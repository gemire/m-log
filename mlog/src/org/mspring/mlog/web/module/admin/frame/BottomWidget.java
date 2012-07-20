/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import org.mspring.mlog.Application;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description 底部信息页面
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class BottomWidget {
    @RequestMapping(value = "/bottom", method = RequestMethod.GET)
    public String execute(Model model) {
        model.addAttribute("app", Application.getInstance());
        return "/admin/frame/bottom";
    }
}
