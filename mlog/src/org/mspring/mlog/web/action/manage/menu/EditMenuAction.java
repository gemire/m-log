/**
 * @since Jul 11, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.action.manage.menu;

import java.util.List;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.common.MenuType;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.entity.Menu;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * 
 * @author Gao Youbo
 */
public class EditMenuAction extends AbstractManageAction {
    private Long menuId;
    private Menu menu;
    private List parentmenus;
    private List categories;
    private Integer category;

    public Long getMenuId() {
        return this.menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List getParentmenus() {
        return this.parentmenus;
    }

    public void setParentmenus(List parentmenus) {
        this.parentmenus = parentmenus;
    }

    public List getCategories() {
        return this.categories;
    }

    public void setCategories(List categories) {
        this.categories = categories;
    }

    public Integer getCategory() {
        return this.category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String toEditMenu() {
        if (menuId == null) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "请选择要一条菜单对象.");
            return PROMPT;
        }
        parentmenus = menuService.findWithoutSystem();
        categories = categoryService.findAll();
        menu = menuService.findMenuById(menuId);
        return SUCCESS;
    }

    public String doEditMenu() {
        if (menu.getType() == MenuType.CATEGORY) { //如果是分类菜单
            Category cat = categoryService.findCategoryById(menu.getCategory());
            menu.setName(cat.getName());
            //menu.setDescription(cat.getIntro());
        }
        menuService.mergeMenu(menu);
        return SUCCESS;
    }
}
