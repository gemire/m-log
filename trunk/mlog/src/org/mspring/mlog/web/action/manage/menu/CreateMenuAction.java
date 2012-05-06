/**
 * @since Jul 10, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.action.manage.menu;

import java.util.Date;
import java.util.List;

import org.mspring.mlog.common.MenuType;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.entity.Menu;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * 
 * @author Gao Youbo
 */
public class CreateMenuAction extends AbstractManageAction {
	private Menu menu;
	private List parentmenus;
	private List categories;
	
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
	
	public String toCreateMenu() {
		parentmenus = menuService.findWithoutSystem();
		categories = categoryService.findAll();
		return SUCCESS;
	}
	
	public String doCreateMenu() {
		menu.setCreateTime(new Date());
		if (menu.getType() == MenuType.CATEGORY) { //如果是分类菜单
			Category cat = categoryService.findCategoryById(menu.getCategory());
			menu.setName(cat.getName());
			//menu.setDescription(cat.getIntro());
		}
		else if(menu.getType() == MenuType.URL) {
			
		}
		menuService.createMenu(menu);
		return SUCCESS;
	}
}
