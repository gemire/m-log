/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import java.util.List;

import org.mspring.mlog.entity.TreeItem;
import org.mspring.mlog.service.TreeItemService;
import org.mspring.platform.web.widget.stereotype.Widget;
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
    public String execute(Model model) {
        List<TreeItem> items = treeItemService.findAllItems();
        model.addAttribute("items", items);
        return "/admin/frame/leftMenu";
    }
}
