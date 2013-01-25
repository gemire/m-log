/**
 * 
 */
package org.mspring.mlog.web.module.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/")
public class SitemapController {

    @Autowired
    private PostService postService;
    @Autowired
    private OptionService optionService;

    //@RequestMapping("sitemap_baidu.xml")
    public String sitemap_baidu(HttpServletRequest request, HttpServletResponse response, Model model) {
        String blogurl = optionService.getOption("blogurl");
        List<Post> posts = postService.findAll();
        List<Map<String, String>> urlset = new ArrayList<Map<String, String>>();
        for (Post post : posts) {
            String loc = blogurl + post.getUrl();
            String lastmod = post.getModifyTime() == null ? DateUtils.format(post.getCreateTime(), DateUtils.YYYY_MM_DD_HH_MM_SS) : DateUtils.format(post.getModifyTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            String changefreq = "daily";
            String priority = "1";

            Map<String, String> map = new HashMap<String, String>();
            map.put("loc", loc);
            map.put("lastmod", lastmod);
            map.put("changefreq", changefreq);
            map.put("priority", priority);
            urlset.add(map);
        }
        model.addAttribute("urlset", urlset);

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        return "/common/sitemap_baidu";
    }

    //@RequestMapping("sitemap.xml")
    public String sitemap(HttpServletRequest request, HttpServletResponse response, Model model) {
        return this.sitemap_baidu(request, response, model);
    }
}
