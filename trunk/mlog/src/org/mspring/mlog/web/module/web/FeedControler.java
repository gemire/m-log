/**
 * 
 */
package org.mspring.mlog.web.module.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.support.feed.atom.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-8-3
 * @Description
 * @TODO
 */
@Controller
@RequestMapping("/")
public class FeedControler {
    private OptionService optionService;
    private PostService postService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("rss.xml")
    public String rss() {
        return "";
    }

    @RequestMapping("atom.xml")
    public String atom() {
        final Feed feed = new Feed();
        String blogname = optionService.getOption("blogname");
        String blogSubtitle = optionService.getOption("blogsubname");
        String blogurl = optionService.getOption("blogurl");

        feed.setTitle(StringEscapeUtils.escapeXml(blogname));
        feed.setSubtitle(StringEscapeUtils.escapeXml(blogSubtitle));
        feed.setUpdated(new Date());
        feed.setAuthor(StringEscapeUtils.escapeXml(blogname));
        feed.setLink(blogurl + "/atom.xml");
        feed.setId(blogurl);

        List<Post> posts = postService.getRecentPost(20);
        
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

        return "";
    }
}
