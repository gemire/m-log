/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.List;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.web.widget.AbstractWidget;
import org.mspring.mlog.web.widget.annotations.Widget;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 */
@Widget(name = "CategoryListWidget")
public class CategoryListWidget extends AbstractWidget {
    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.widget.AbstractWidget#render(java.util.List)
     */
    @Override
    public String render(List args) {
        // TODO Auto-generated method stub
        StringBuilder result = new StringBuilder();
        List<Category> categories = ServiceFactory.getCategoryService().findAll();
        for (Category category : categories) {
            result.append("<li><a href='" + getRequest().getContextPath() + "/category/" + category.getId() + "'>" + category.getName() + "</a></li>\n");
        }
        result.append("<li><a href='" + getRequest().getContextPath() + "/category/0'>未分类</a></li>\n");
        return result.toString();
    }

}
