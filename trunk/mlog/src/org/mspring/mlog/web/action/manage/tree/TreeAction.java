/**
 * 
 */
package org.mspring.mlog.web.action.manage.tree;

import java.util.List;

import org.mspring.mlog.entity.security.Tree;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Jan 14, 2012
 */
public class TreeAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String panel;
    private String parent;
    private boolean isTreeItem = false;
    private List<Tree> treeList;

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public boolean isTreeItem() {
        return isTreeItem;
    }

    public void setTreeItem(boolean isTreeItem) {
        this.isTreeItem = isTreeItem;
    }

    public List<Tree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

    public String execute() {
        User user = getCurrentUser();
        if (!StringUtils.isBlank(parent)) {
            treeList = treeService.findTreeItemByParent(parent, user);
        } else {
            treeList = treeService.findRootTreeItem(panel, user);
        }
        return SUCCESS;
    }
}
