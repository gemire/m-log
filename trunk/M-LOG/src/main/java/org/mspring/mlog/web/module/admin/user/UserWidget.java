/**
 * 
 */
package org.mspring.mlog.web.module.admin.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.entity.security.UserRole;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.mlog.web.module.admin.user.query.UserQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-1-14
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/user")
public class UserWidget extends AbstractAdminWidget {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    @Log
    public String list(@ModelAttribute Page<User> userPage, @ModelAttribute UserRole userRole, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (userPage == null) {
            userPage = new Page<User>();
        }
        // userPage.setSort(new Sort("id", Sort.DESC));

        userPage = userService.findUser(userPage, new UserQueryCriterion(queryParams));
        List<Role> roles = roleService.findEnabledRole();

        model.addAttribute("userPage", userPage);
        model.addAttribute("roles", roles);
        model.addAllAttributes(queryParams);
        return "/admin/user/listUser";
    }

    @RequestMapping("/create")
    public String create(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Role> roles = roleService.findEnabledRole();
        model.addAttribute("roles", roles);
        return "/admin/user/createUser";
    }

    @RequestMapping("/create/save")
    @Log
    public String create_save(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        user = userService.createUser(user);

        String userRoles = request.getParameter("selectRoles");
        if (StringUtils.isNotBlank(userRoles)) {
            String[] roles = userRoles.split(",");
            userService.setUserRoles(user.getId(), roles);
        }
        return "redirect:/admin/user/edit?id=" + user.getId();
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) Long id, @ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "UserWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请先选择要修改的角色");
        }

        user = userService.getUserById(id);
        List<Role> roles = roleService.findEnabledRole();
        List<Role> userRoles = userService.getUserRoles(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("userRoles", userRoles);

        setSessionAttribute(request, "UserWidget_edit_id", id);
        return "/admin/user/editUser";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        String newPassword = request.getParameter("newPassword");
        if (StringUtils.isNotBlank(newPassword)) {
            String oldPassword = request.getParameter("oldPassword");
            String MD5OldPassword = StringUtils.getMD5(oldPassword);
            if (MD5OldPassword.equals(user.getPassword())) {
                user.setPassword(StringUtils.getMD5(newPassword));
            } else {
                model.addAttribute("message", "密码修改失败，原始密码输入错误！");
            }
        }
        userService.updateUserInfo(user);

        String userRoles = request.getParameter("selectRoles");
        if (StringUtils.isNotBlank(userRoles)) {
            String[] roles = userRoles.split(",");
            userService.setUserRoles(user.getId(), roles);
        }
        return edit(user.getId(), user, request, response, model);
    }

    @RequestMapping("/delete")
    @Log
    public String delete(@RequestParam(required = false) Long id, @ModelAttribute Page<User> userPage, @ModelAttribute UserRole userRole, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请选择要删除的用户。");
        }
        userService.deleteUser(id);
        return list(userPage, userRole, queryParams, request, response, model);
    }

}
