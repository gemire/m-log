/**
 * @since 2011-6-29
 * www.mspring.org
 * @author gaoyoubo
 */
package org.mspring.mlog.web.view.factory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.mspring.mlog.entity.Menu;
import org.mspring.mlog.web.view.AbstractTemplateView;

import com.google.gson.Gson;

/**
 * 导航
 */
public class MenuTemplateView extends AbstractTemplateView {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mspring.mlog.web.view.AbstractTemplateView#json()
	 */
	@SuppressWarnings("unchecked")
    @Override
	public String json() {
		// TODO Auto-generated method stub
		List<Menu> list = menuService.findAll();
		return new Gson().toJson(list);
	}
}
