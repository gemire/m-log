/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.web.action.CommonActionSupport;
import org.mspring.platform.web.feed.atom.Category;
import org.mspring.platform.web.feed.atom.Entry;
import org.mspring.platform.web.feed.atom.Feed;
import org.mspring.platform.web.servlet.renderer.AtomRenderer;

/**
 * @author Gao Youbo
 * @since May 12, 2012
 */
public class FeedAction extends CommonActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1310748341925275347L;
    private static final Logger log = Logger.getLogger(FeedAction.class);

    public String articleAtom() {
        final Feed feed = new Feed();
        try {

            final String blogTitle = getConfig().get(ConfigTokens.mspring_config_base_title);
            final String blogSubtitle = getConfig().get(ConfigTokens.mspring_config_base_secondtitle);
            final String blogHost = getConfig().get(ConfigTokens.mspring_config_base_blogurl);

            feed.setTitle(StringEscapeUtils.escapeXml(blogTitle));
            feed.setSubtitle(StringEscapeUtils.escapeXml(blogSubtitle));
            feed.setUpdated(new Date());
            feed.setAuthor(StringEscapeUtils.escapeXml(blogTitle));
            feed.setLink(blogHost + "/articleAtom.action");
            feed.setId(blogHost + "/");

            List<Article> articles = articleService.findAll();

            for (int i = 0; i < articles.size(); i++) {
                final Article article = articles.get(i);
                final Entry entry = new Entry();

                final String title = StringEscapeUtils.escapeXml(article.getTitle());
                entry.setTitle(title);
                final String summary = StringEscapeUtils.escapeXml(article.getIntro());
                entry.setSummary(summary);
                final Date updated = article.getCreateTime();
                entry.setUpdated(updated);

                final String link = blogHost + "/post/" + article.getId() + ".html";
                entry.setLink(link);
                entry.setId(link);

                final String authorName = StringEscapeUtils.escapeXml(article.getAuthor());
                entry.setAuthor(authorName);

                final Set<org.mspring.mlog.entity.Category> categories = article.getCategories();
                for (org.mspring.mlog.entity.Category category : categories) {
                    final Category cat = new Category();
                    final String categoryString = category.getName();
                    cat.setTerm(categoryString);
                    entry.addCatetory(cat);

                }
                feed.addEntry(entry);
            }

            AtomRenderer renderer = new AtomRenderer(feed.toString());
            renderer.render(ServletActionContext.getResponse());
        } catch (Exception e) {
            // TODO: handle exception
            log.warn("article atom failed", e);
        }
        return NONE;
    }
}
