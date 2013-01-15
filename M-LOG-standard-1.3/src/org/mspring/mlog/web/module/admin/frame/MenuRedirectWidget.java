/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.Keys;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-1-10
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class MenuRedirectWidget extends AbstractAdminWidget {
    @Autowired
    private TreeItemService treeItemService;

    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response, Model model) {
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return prompt(model, "未找到该页面");
        }
        TreeItem item = treeItemService.getItemById(id);

        List<TreeItem> tabs = null;
        TreeItem openTab = null;
        String url = "";
        if (item.getType().equals(TreeItem.Type.TREE_ITEM)) {
            request.getSession().setAttribute(Keys.CURRENT_MODULE, item.getId());
            tabs = treeItemService.findTabItems(item.getId());
            if (tabs == null || tabs.size() == 0) {
                if (StringUtils.isBlank(url)) {
                    return prompt(model, item.getName() + " 未找到");
                }
            }
            if (tabs.size() == 0) {
                openTab = tabs.get(0);
            }
            else {
                openTab = treeItemService.getOpenTab(item.getId());
            }
            url = openTab.getCall();
        }
        else if (item.getType().equals(TreeItem.Type.TAB)) {
            request.getSession().setAttribute(Keys.CURRENT_ENTITY, item.getId());
            url = item.getCall();
            openTab = item;
            tabs = treeItemService.findTabItems(openTab.getParent());
        }

        if (StringUtils.isBlank(url)) {
            return prompt(model, item.getName() + " 未找到");
        }
        request.getSession().setAttribute("tabs", tabs);
        request.getSession().setAttribute("openTab", openTab);
        return "redirect:" + url;
    }
}
