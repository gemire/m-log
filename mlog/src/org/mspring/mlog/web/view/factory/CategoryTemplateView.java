/**
 * @since Jun 26, 2011
 * www.mspring.org
 * @author gaoyoubo
 */
package org.mspring.mlog.web.view.factory;

import org.mspring.mlog.service.CategoryService;
import org.mspring.mlog.web.view.AbstractTemplateView;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

public class CategoryTemplateView extends AbstractTemplateView {
	protected CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mspring.mlog.script.AbstractTemplateView#json()
	 */
	@Override
	public String json() {
		// TODO Auto-generated method stub
		return new Gson().toJson(categoryService.findAll());
	}
}
