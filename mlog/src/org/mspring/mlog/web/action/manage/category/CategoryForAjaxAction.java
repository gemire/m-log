/**
 * 
 */
package org.mspring.mlog.web.action.manage.category;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.web.action.CommonActionSupport;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.servlet.renderer.JSONRenderer;
import org.mspring.platform.web.servlet.renderer.TextRenderer;

/**
 * @author Gao Youbo
 * @since Apr 19, 2012
 */
public class CategoryForAjaxAction extends CommonActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -5464947062760496136L;

    private String name;
    private List<Category> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    /**
     * 查找所有分类
     * @return
     */
    public String findAllCategoriesByAjax(){
        categories = categoryService.findAll();
        JSONRenderer render = new JSONRenderer(categories, true);
        render.render(ServletActionContext.getResponse());
        return NONE;
    }

    /**
     * 检测分类的名称是否重复
     * 
     * @return
     */
    public String categoryWhetherRepeat() {
        Category category = categoryService.findCategoryByName(name);
        String flag = "true";
        if (category != null) {
            flag = "false";
        }
        TextRenderer renderer = new TextRenderer(flag);
        renderer.render(ServletActionContext.getResponse());
        return NONE;
    }

    /**
     * 创建分类
     * 
     * @return
     * @throws IOException
     */
    public String createCategoryByAjax() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        if (!StringUtils.isBlank(name)) {
            Category category = new Category();
            category.setCreateTime(new Date());
            category.setName(name);
            category = categoryService.createCategory(category);
            JSONRenderer render = new JSONRenderer(category);
            render.render(response);
        }
        return NONE;
    }
}
