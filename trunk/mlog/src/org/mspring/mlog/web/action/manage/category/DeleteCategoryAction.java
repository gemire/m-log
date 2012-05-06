package org.mspring.mlog.web.action.manage.category;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 11, 2011 org.mspring.mlog.web.action.manage.category
 */
public class DeleteCategoryAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = -2966016063151159263L;

    private Long[] cbCategory;

    public Long[] getCbCategory() {
        return cbCategory;
    }

    public void setCbCategory(Long[] cbCategory) {
        this.cbCategory = cbCategory;
    }

    public String execute() {
        categoryService.deleteCategory(cbCategory);
        return SUCCESS;
    }

}
