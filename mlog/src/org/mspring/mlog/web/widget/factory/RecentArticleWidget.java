/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.List;

import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.web.widget.AbstractWidget;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 * @TODO 最近发布文章
 */
public class RecentArticleWidget extends AbstractWidget {

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.widget.WidgetInterface#render(java.util.List)
     */
    @Override
    public String render(List args) {
        // TODO Auto-generated method stub
        StringBuilder result = new StringBuilder();
        int numOfPosts = 20;
        if (args.size() > 0) {
            numOfPosts = new Integer(args.get(0).toString());
        }
        List<Article> recentEntries = ServiceFactory.getArticleService().getRecentArticles(numOfPosts);
        String path = getRequest().getContextPath();
        String rebuildFolder = optionService.getOption(ConfigTokens.mspring_config_global_rebuildfolder);
        for (Article article : recentEntries) {
            result.append("<li><a href='" + path + "/" + rebuildFolder + "/" + article.getId() + ".html'>" + article.getTitle() + "</a></li>\n");
        }
        return result.toString();
    }

}
