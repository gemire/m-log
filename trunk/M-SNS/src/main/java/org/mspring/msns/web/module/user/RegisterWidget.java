/**
 * 
 */
package org.mspring.msns.web.module.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.msns.entity.security.User;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-5-9
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/user/reg")
public class RegisterWidget extends AbstractUserWidget {
    @RequestMapping({ "", "/" })
    public String reg(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "skin:/user/reg";
    }

    @RequestMapping("/save")
    public String reg_save(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "";
    }
}