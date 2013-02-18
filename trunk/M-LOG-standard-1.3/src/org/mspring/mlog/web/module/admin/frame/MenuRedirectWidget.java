/**
 * 
 */
package org.mspring.mlog.web.module.admin.frame;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.Keys;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.TreeItemSecurityService;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.mlog.web.security.SecurityUtils;
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
    
    @Autowired
    private TreeItemSecurityService treeItemSecurityService;

    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response, Model model) {
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return prompt(model, "未找到该页面");
        }
        TreeItem item = treeItemService.getItemById(id);

        User user = SecurityUtils.getCurrentUser(request);

        List<TreeItem> tabs = null;
        TreeItem openTab = null;
        String url = "";
        if (item.getType().equals(TreeItem.Type.TREE_ITEM)) {
            request.getSession().setAttribute(Keys.CURRENT_MODULE, item.getId());
            tabs = treeItemSecurityService.loadTabByUser(item.getId(), user.getId());
            if (tabs == null || tabs.size() == 0) {
                if (StringUtils.isBlank(url)) {
                    return prompt(model, "<font color=red>&lt;" + item.getName() + "&gt;</font> 未找到，或您无权访问该页面");
                }
            }
            if (tabs.size() == 0) {
                openTab = tabs.get(0);
            } else {
                openTab = treeItemSecurityService.getOpenTab(item.getId(), user.getId());
            }
            url = openTab.getCall();
        } else if (item.getType().equals(TreeItem.Type.TAB)) {
            request.getSession().setAttribute(Keys.CURRENT_ENTITY, item.getId());
            url = item.getCall();
            openTab = item;
            tabs = treeItemSecurityService.loadTabByUser(openTab.getParent(), user.getId());
        }

        if (StringUtils.isBlank(url)) {
            return prompt(model, "<font color=red>&lt;" + item.getName() + "&gt;</font> 未找到，或您无权访问该页面");
        }
        request.getSession().setAttribute("tabs", tabs);
        request.getSession().setAttribute("openTab", openTab);
        return "redirect:" + url;
    }
}
