/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import javax.servlet.http.HttpSession;

import org.mspring.mlog.common.Keys;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description 顶部页面
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class TopWidget {
    @RequestMapping(value = { "/top" })
    public String execute(Model model, HttpSession session) {
        model.addAttribute("currentUser", session.getAttribute(Keys.CURRENT_USER));
        return "/admin/frame/top";
    }
}
