/**
 * 
 */
package org.mspring.mlog.web.module.web.widget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.User;
import org.mspring.mlog.utils.GlobalUtils;
import org.mspring.mlog.web.common.Keys;
import org.mspring.mlog.web.module.web.AbstractWebWidget;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/widget")
public class SystemWidget extends AbstractWebWidget {

    /**
     * 分类列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("listCatalog")
    public String listCatalog(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Catalog> catalogs = catalogService.findAllCatalog();
        model.addAttribute("catalogs", catalogs);
        return "/widget/listCatalog";
    }

    /**
     * 最近发表文章
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("recentPost")
    public String recentPost(HttpServletRequest request, HttpServletResponse response, Model model) {
        int num = 20;
        String numStr = request.getParameter("num");
        if (!StringUtils.isBlank(numStr) && ValidatorUtils.isNumber(numStr)) {
            num = Integer.parseInt(numStr);
        }
        List<Post> posts = postService.getRecentPost(num);
        model.addAttribute("posts", posts);
        return "/widget/recentPost";
    }

    /**
     * 最高点击文章
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("mostViewPost")
    public String mostViewPost(HttpServletRequest request, HttpServletResponse response, Model model) {
        int num = 20;
        String numStr = request.getParameter("num");
        if (!StringUtils.isBlank(numStr) && ValidatorUtils.isNumber(numStr)) {
            num = Integer.parseInt(numStr);
        }
        List<Post> posts = postService.getMostViewPost(num);
        model.addAttribute("posts", posts);
        return "/widget/mostViewPost";
    }

    /**
     * 最近评论
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("recentComment")
    public String recentComment(HttpServletRequest request, HttpServletResponse response, Model model) {
        int num = 20;
        String numStr = request.getParameter("num");
        if (!StringUtils.isBlank(numStr) && ValidatorUtils.isNumber(numStr)) {
            num = Integer.parseInt(numStr);
        }
        List<Comment> comments = commentService.getRecentComment(num);
        model.addAttribute("comments", comments);
        return "/widget/recentComment";
    }

    /**
     * 链接
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("links")
    public String links(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Link> links = linkService.findVisableLinks();
        model.addAttribute("links", links);
        return "/widget/links";
    }

    /**
     * 菜单
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("menus")
    public String menus(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<String> menus = new ArrayList<String>();
        String url = optionService.getOption("blogurl");

        // 用户配置menu
        String menuString = optionService.getOption("menu");
        if (StringUtils.isNotBlank(menuString)) {
            StringReader sr = new StringReader(menuString);
            BufferedReader reader = new BufferedReader(sr);
            try {
                String menu = "";
                while (StringUtils.isNotBlank(menu = reader.readLine())) {
                    menu = menu.replaceAll("%base%", url);
                    menus.add(menu);
                }
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        model.addAttribute("menus", menus);
        return "/widget/menus";
    }

    /**
     * admin bar
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("adminbar")
    public String adminbar(HttpServletRequest request, HttpServletResponse response, Model model) {
        User loginUser = GlobalUtils.getCurrentUser(request);
        if (loginUser == null) {
            if ("true".equals(CookieUtils.getCookie(request, Keys.IS_REMEMBER_USER_COOKIE))) {
                String username = CookieUtils.getCookie(request, Keys.USERNAME_COOKIE);
                String password = CookieUtils.getCookie(request, Keys.PASSWORD_COOKIE);
                if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                    loginUser = userService.login(username, password);
                    request.getSession().setAttribute(Keys.CURRENT_USER, loginUser);
                }
            }
        }
        if (loginUser == null) {
            return null;
        }
        String notice = optionService.getOption("notice");
        model.addAttribute("notice", notice);
        model.addAttribute("user", loginUser);
        return "/widget/adminbar";
    }
}
