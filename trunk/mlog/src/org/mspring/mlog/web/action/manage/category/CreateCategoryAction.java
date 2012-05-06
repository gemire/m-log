package org.mspring.mlog.web.action.manage.category;

import org.mspring.mlog.entity.Category;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.platform.utils.DateUtils;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 11, 2011 org.mspring.mlog.web.action.manage.category
 */
public class CreateCategoryAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 815796881572200623L;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toCreateCategory() {
        return SUCCESS;
    }

    public String doCreateCategory() {
        category.setCreateTime(DateUtils.parse(DateUtils.YYYY_MM_DD_HH_MM_SS));
        category = categoryService.createCategory(category);
        if (category != null) {
            addActionMessage(getText("message.success"));
        }
        return SUCCESS;
    }

}
