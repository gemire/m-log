/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import javax.servlet.http.HttpSession;

import org.mspring.mlog.web.Keys;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description 顶部页面
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class TopWidget {
    @RequestMapping(value = { "/top" }, method = RequestMethod.GET)
    public String execute(Model model, HttpSession session) {
        model.addAttribute("currentUser", session.getAttribute(Keys.CURRENT_USER));
        return "/admin/frame/top";
    }
}
