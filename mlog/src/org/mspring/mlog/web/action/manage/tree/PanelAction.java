/**
 * 
 */
package org.mspring.mlog.web.action.manage.tree;

import java.util.List;

import org.mspring.mlog.entity.security.Tree;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Feb 9, 2012
 */
public class PanelAction extends AbstractManageAction {
    private List<Tree> panelList;

    public List<Tree> getPanelList() {
        return panelList;
    }

    public void setPanelList(List<Tree> panelList) {
        this.panelList = panelList;
    }

    public String execute() {
        panelList = treeService.findAllTreePanel(getCurrentUser());
        return SUCCESS;
    }
}
