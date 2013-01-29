/**
 * 
 */
package org.mspring.mlog.web.module.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.mlog.service.security.RoleTreeItemService;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.mlog.web.security.annotation.Premission;
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
    private RoleTreeItemService roleTreeItemService;

    @RequestMapping("/list")
    @Premission(item = "310005")
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
    @Premission(item = "310005")
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
    @Premission(item = "310010")
    public String create(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/role/createRole";
    }

    @RequestMapping("/doCreate")
    @Premission(item = "310010")
    public String doCreate(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response, Model model) {
        roleService.createRole(role);
        return "redirect:/admin/role/edit?id=" + role.getId();
    }

    @RequestMapping("/edit")
    @Premission(item = "310015")
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

    @RequestMapping("/doEdit")
    @Premission(item = "310015")
    public String doEdit(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response, Model model) {
        roleService.updateRole(role);
        return "redirect:/admin/role/edit?id=" + role.getId();
    }

    @RequestMapping("/authorize")
    @Premission(item = "310020")
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
        List<TreeItem> authorized = roleTreeItemService.getPremissions(id);
        model.addAttribute("id", id);
        model.addAttribute("treeItems", treeItems);
        model.addAttribute("authorized", authorized);
        return "/admin/role/authorize";
    }

    @RequestMapping("/saveAuthorize")
    @Premission(item = "310020")
    public String saveAuthorize(@RequestParam(required = false) Long id, @RequestParam(required = false) String checkedItems,@RequestParam(required = false) String notCheckedItems, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return prompt(model, "请先选择要修改的角色");
        }
        String[] ids = StringUtils.split(checkedItems, ",");
        roleTreeItemService.setPremission(id, ids);
        
        String[] notIds = StringUtils.split(notCheckedItems, ",");
        roleTreeItemService.removePremission(id, notIds);
        return "redirect:/admin/role/authorize";
    }
}
