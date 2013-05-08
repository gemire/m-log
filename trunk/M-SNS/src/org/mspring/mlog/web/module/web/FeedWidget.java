/**
 * 
 */
package org.mspring.mlog.web.module.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mspring.mlog.Application;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-8-3
 * @Description
 * @TODO
 */
@Widget("webFeedWidget")
@RequestMapping("/")
public class FeedWidget extends AbstractWebWidget {
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
    public String rss(HttpServletRequest request, HttpServletResponse response, Model model) {
        putRssModelInfos(model);

        response.setContentType("application/rss+xml");
        response.setCharacterEncoding("UTF-8");
        return "/common/rss";
    }

    @RequestMapping("atom.xml")
    public String atom(HttpServletRequest request, HttpServletResponse response, Model model) {

        putAtomModelInfos(model);

        response.setContentType("application/atom+xml");
        response.setCharacterEncoding("UTF-8");
        return "/common/atom";
    }

    /**
     * 填充RSS模板信息
     * 
     * @param model
     */
    private void putRssModelInfos(Model model) {
        String title = optionService.getOption("blogname"); // 频道名称
        String link = optionService.getOption("blogurl") + "/rss.xml"; // 频道的URL
        String description = ""; // 频道的描述
        String lanugage = "zh_CN"; // 频道文章所用语言
        String copyright = optionService.getOption("copyright"); // 频道内容的版权说明
        String pubDate = DateFormatUtils.SMTP_DATETIME_FORMAT.format(new Date()); // 频道内容发布日期
        String lastBuildDate = DateFormatUtils.SMTP_DATETIME_FORMAT.format(new Date()); // 频道内容最后的修改日期
        String docs = ""; // 指向该RSS文件所用格式说明的URL
        String generator = Application.ALIAS_NAME + " " + Application.VERSION;// 生成该频道的程序名
        String managingEditor = "gaoyoubo@mspring.org"; // 责任编辑的email
        String webMaster = "gaoyoubo@mspring.org"; // 负责频道技术事务的网站管理员email
        List<Post> posts = postService.getRecentPost(20);

        model.addAttribute("title", StringEscapeUtils.escapeXml(title));
        model.addAttribute("link", StringEscapeUtils.escapeXml(link));
        model.addAttribute("description", StringEscapeUtils.escapeXml(description));
        model.addAttribute("lanugage", StringEscapeUtils.escapeXml(lanugage));
        model.addAttribute("copyright", StringEscapeUtils.escapeXml(copyright));
        model.addAttribute("pubDate", StringEscapeUtils.escapeXml(pubDate));
        model.addAttribute("lastBuildDate", StringEscapeUtils.escapeXml(lastBuildDate));
        model.addAttribute("docs", StringEscapeUtils.escapeXml(docs));
        model.addAttribute("generator", StringEscapeUtils.escapeXml(generator));
        model.addAttribute("managingEditor", StringEscapeUtils.escapeXml(managingEditor));
        model.addAttribute("webMaster", StringEscapeUtils.escapeXml(webMaster));
        model.addAttribute("posts", posts);
    }

    /**
     * 填充Atom模板信息
     * 
     * @param model
     */
    private void putAtomModelInfos(Model model) {
        String title = optionService.getOption("blogname"); // 频道名称
        String subtitle = optionService.getOption("blogsubname");
        String link = optionService.getOption("blogurl") + "/atom.xml"; // 频道的URL
        String updated = DateFormatUtils.SMTP_DATETIME_FORMAT.format(new Date());
        String rights = optionService.getOption("copyright");
        String generator = Application.ALIAS_NAME;
        String generator_uri = Application.HOME_PAGE;
        String generator_version = Application.VERSION;
        List<Post> posts = postService.getRecentPost(20);

        model.addAttribute("title", StringEscapeUtils.escapeXml(title));
        model.addAttribute("subtitle", StringEscapeUtils.escapeXml(subtitle));
        model.addAttribute("link", StringEscapeUtils.escapeXml(link));
        model.addAttribute("updated", StringEscapeUtils.escapeXml(updated));
        model.addAttribute("rights", StringEscapeUtils.escapeXml(rights));
        model.addAttribute("generator", StringEscapeUtils.escapeXml(generator));
        model.addAttribute("generator_uri", StringEscapeUtils.escapeXml(generator_uri));
        model.addAttribute("generator_version", StringEscapeUtils.escapeXml(generator_version));
        model.addAttribute("posts", posts);
    }
}
