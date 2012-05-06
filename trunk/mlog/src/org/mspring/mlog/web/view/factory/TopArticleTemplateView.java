/**
 * @since Jun 26, 2011
 * www.mspring.org
 * @author gaoyoubo
 */
package org.mspring.mlog.web.view.factory;

import java.util.List;

import org.mspring.mlog.entity.Article;
import org.mspring.mlog.web.view.AbstractTemplateView;

import com.google.gson.Gson;

public class TopArticleTemplateView extends AbstractTemplateView {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mspring.mlog.web.view.AbstractTemplateView#json()
	 */
	@Override
	public String json() {
		// TODO Auto-generated method stub
		List<Article> list = articleService.getRecentArticles(10);
		return new Gson().toJson(list);
	}
}
