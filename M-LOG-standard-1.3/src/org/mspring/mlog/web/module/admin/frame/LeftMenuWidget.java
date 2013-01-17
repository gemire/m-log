/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.mlog.utils.GlobalUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description 左侧菜单页面
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class LeftMenuWidget {

    private TreeItemService treeItemService;

    @Autowired
    public void setTreeItemService(TreeItemService treeItemService) {
        this.treeItemService = treeItemService;
    }

    @RequestMapping(value = "/leftMenu")
    public String execute(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = GlobalUtils.getCurrentUser(request);
        List<TreeItem> items = treeItemService.findTreeItemByUser(user.getId());
        model.addAttribute("items", items);
        return "/admin/frame/leftMenu";
    }
}
