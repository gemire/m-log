package org.mspring.mlog.web.action.manage.category;

import java.util.ArrayList;

import org.mspring.mlog.entity.Category;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.model.field.ColumnField;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 6, 2011 org.mspring.mlog.web.action.manage
 */
public class QueryCategoryAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 163199351913040173L;

    private Page<Category> categoryPage = new Page<Category>();

    public Page<Category> getCategoryPage() {
        return categoryPage;
    }

    public void setCategoryPage(Page<Category> categoryPage) {
        this.categoryPage = categoryPage;
    }

    @SuppressWarnings("unchecked")
    public String execute() {
        fieldColumns = new ArrayList<ColumnField>();
        fieldColumns.add(new ColumnField("id", getText("category.field.id")));
        fieldColumns.add(new ColumnField("name", getText("category.field.name")));
        fieldColumns.add(new ColumnField("order", getText("category.field.order")));
        fieldColumns.add(new ColumnField("intro", getText("category.field.description")));

        if (categoryPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            categoryPage.setSort(sort);
        }
        categoryPage = categoryService.queryCategory(categoryPage, "select category from Category category");

        return SUCCESS;
    }
}
