/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.UserService;
import org.mspring.mlog.utils.GlobalUtils;
import org.mspring.mlog.web.Keys;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-8-7
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/user")
public class UserWidget {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/userinfo")
    public String viewUserInfo(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        user = GlobalUtils.getCurrentUser(request);
        model.addAttribute("user", user);
        return "/admin/user/viewUserInfo";
    }

    @RequestMapping("/doEditUserInfo")
    public String doEditUserInfo(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isNotBlank(user.getPassword())) { // 如果密码框不为空，那么修改密码
            String password = StringUtils.getMD5(user.getPassword());

            user.setPassword(password);
            userService.updateUserInfo(user);
            // 重置用户登录信息
            request.getSession().invalidate();
            CookieUtils.setCookie(response, Keys.IS_REMEMBER_USER_COOKIE, "false", 365);
        }
        else {
            String password = GlobalUtils.getCurrentUser(request).getPassword();
            user.setPassword(password);
            userService.updateUserInfo(user);
            user = userService.getUserByName(user.getName());
            request.getSession().setAttribute(Keys.CURRENT_USER, user);
        }

        return "redirect:/admin/user/userinfo";
    }
}
