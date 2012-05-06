package org.mspring.mlog.web.view;

import org.mspring.mlog.web.view.factory.ArchiveTemplateView;
import org.mspring.mlog.web.view.factory.CategoryTemplateView;
import org.mspring.mlog.web.view.factory.MenuTemplateView;
import org.mspring.mlog.web.view.factory.TopArticleTemplateView;
import org.mspring.mlog.web.view.factory.TopCommentTemplateView;

public class TemplateViewFactory {
	public static AbstractTemplateView createTemplateView(String viewType){
		AbstractTemplateView templateView = null;
		
		if (viewType.equals(ViewType.CATEGORY)) 
			templateView = new CategoryTemplateView();
		else if(viewType.equals(ViewType.ARCHIVE))
			templateView = new ArchiveTemplateView();
		else if(viewType.equals(ViewType.MENU))
			templateView = new MenuTemplateView();
		else if(viewType.equals(ViewType.TOP))
			templateView = new TopArticleTemplateView();
		else if(viewType.equals(ViewType.TOPCOMMENT))
			templateView = new TopCommentTemplateView();
		
		return templateView;
	}
}
