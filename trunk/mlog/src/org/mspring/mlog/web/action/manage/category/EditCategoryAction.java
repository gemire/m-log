package org.mspring.mlog.web.action.manage.category;

import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.entity.Category;

public class EditCategoryAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1201702852997189080L;

    private Category category;
    private Long id;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String execute() {
        category = categoryService.findCategoryById(id);
        return SUCCESS;
    }

    public String edit() {
        categoryService.updateCategory(category);
        return SUCCESS;
    }
}
