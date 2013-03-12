/**
 * 
 */
package org.mspring.mlog.web.module.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.mlog.service.security.TreeItemSecurityService;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
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
@RequestMapping("/admin/role")
public class RoleWidget extends AbstractAdminWidget {
    @Autowired
    private RoleService roleService;
    @Autowired
    private TreeItemService treeItemService;
    @Autowired
    private TreeItemSecurityService treeItemSecurityService;

    @RequestMapping("/list")
    @Log
    public String list(@ModelAttribute Page<Role> rolePage, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (rolePage == null) {
            rolePage = new Page<Role>();
        }
        rolePage.setSort(new Sort("id", Sort.DESC));

        rolePage = roleService.findRole("select role from Role role", rolePage);
        model.addAttribute("rolePage", rolePage);
        return "/admin/role/listRole";
    }

    @RequestMapping("/delete")
    @Log
    public String delete(@RequestParam(required = false) Long id, @ModelAttribute Page<Role> rolePage, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "删除失败，请选择要删除的角色");
        }
        boolean hasUserInRole = roleService.hasUserInRole(id);
        if (hasUserInRole) {
            return prompt(model, "删除失败，该角色下存在用户");
        }
        roleService.deleteRole(id);
        return list(rolePage, request, response, model);
    }

    @RequestMapping("/create")
    public String create(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/role/createRole";
    }

    @RequestMapping("/create/save")
    @Log
    public String create_save(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response, Model model) {
        role = roleService.createRole(role);
        // roleResourceService.createRoleResource(role.getId(), new Long(1));
        return "redirect:/admin/role/edit?id=" + role.getId();
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "RoleWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请先选择要修改的角色");
        }

        Role role = roleService.getRoleById(id);
        if (role == null) {
            return prompt(model, "请先选择要修改的角色");
        }
        model.addAttribute("role", role);
        setSessionAttribute(request, "RoleWidget_edit_id", id);
        return "/admin/role/editRole";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response, Model model) {
        roleService.updateRole(role);
        return "redirect:/admin/role/edit?id=" + role.getId();
    }

    @RequestMapping("/authorize")
    public String authorize(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "RoleWidget_authorize_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请先选择要修改的角色");
        }
        setSessionAttribute(request, "RoleWidget_authorize_id", id);

        List<TreeItem> treeItems = treeItemService.findAllTreeItems();
        List<TreeItem> authorized = treeItemSecurityService.getPremissions(id);
        model.addAttribute("id", id);
        model.addAttribute("treeItems", treeItems);
        model.addAttribute("authorized", authorized);
        return "/admin/role/authorize";
    }

    @RequestMapping("/authorize/save")
    @Log
    public String saveAuthorize(@RequestParam(required = false) Long id, @RequestParam(required = false) String checkedItems, @RequestParam(required = false) String notCheckedItems, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请先选择要修改的角色");
        }
        String[] ids = StringUtils.split(checkedItems, ",");
        treeItemSecurityService.setPremission(id, ids);
        String[] notIds = StringUtils.split(notCheckedItems, ",");
        treeItemSecurityService.removePremission(id, notIds);

        User user = SecurityUtils.getCurrentUser(request);
        SecurityUtils.reloadUserDetails(user.getName(), request);
        return "redirect:/admin/role/authorize";
    }
}
