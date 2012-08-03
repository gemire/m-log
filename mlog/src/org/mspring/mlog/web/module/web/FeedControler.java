/**
 * 
 */
package org.mspring.mlog.web.module.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.support.feed.atom.Category;
import org.mspring.platform.support.feed.atom.Entry;
import org.mspring.platform.support.feed.atom.Feed;
import org.mspring.platform.web.servlet.renderer.AtomRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String atom(HttpServletRequest request, HttpServletResponse response) {
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

        for (int i = 0; i < posts.size(); i++) {
            final Post post = posts.get(i);
            final Entry entry = new Entry();

            final String title = StringEscapeUtils.escapeXml(post.getTitle());
            entry.setTitle(title);
            final String summary = StringEscapeUtils.escapeXml(post.getSummary());
            entry.setSummary(summary);
            final Date updated = post.getCreateTime();
            entry.setUpdated(updated);

            final String link = blogurl + "/post/" + post.getId() + ".html";
            entry.setLink(link);
            entry.setId(link);

            final String authorName = StringEscapeUtils.escapeXml(post.getAuthor().getAlias());
            entry.setAuthor(authorName);

            Catalog catalog = post.getCatalog();
            Category cat = new Category();
            cat.setTerm(catalog.getName());
            entry.addCatetory(cat);
            feed.addEntry(entry);
        }
        response.setContentType("application/atom+xml");
        response.setCharacterEncoding("UTF-8");
        return feed.toString();
    }
}
