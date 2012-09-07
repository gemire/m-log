/**
 * 
 */
package org.mspring.mlog.web.module.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.web.widget.stereotype.Widget;
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

    @RequestMapping("sitemap_baidu.xml")
    public String sitemap_baidu(HttpServletRequest request, HttpServletResponse response, Model model) {
        String blogurl = optionService.getOption("blogurl");
        
//        List<Post> posts = 
        
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        return "/common/sitemap_baidu";
    }

    @RequestMapping("sitemap.xml")
    public String sitemap(HttpServletRequest request, HttpServletResponse response, Model model) {

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        return "";
    }
}
