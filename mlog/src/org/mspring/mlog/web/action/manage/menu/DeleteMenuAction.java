/**
 * @since Jul 10, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.action.manage.menu;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * 
 * @author Gao Youbo
 */
public class DeleteMenuAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 2887144070414959321L;
    private Long[] menuItems;

    public Long[] getMenuItems() {
        return this.menuItems;
    }

    public void setMenuItems(Long[] menuItems) {
        this.menuItems = menuItems;
    }

    public String execute() {
        menuService.deleteMenu(menuItems);
        return SUCCESS;
    }
}
