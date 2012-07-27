/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.web.GlobalUtils;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/post")
public class PostWidget {
    private PostService postService;
    private CatalogService catalogService;

    /**
     * @param postService
     *            the postService to set
     */
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * @param catalogService
     *            the catalogService to set
     */
    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RequestMapping({ "/list", "/", "" })
    public String listPost(@ModelAttribute Page<Post> postPage, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        postPage.setSort(new Sort("id", Sort.DESC));

        postPage = postService.findPost(postPage, "select post from Post post");

        List<Field> columnfields = new ArrayList<Field>();
        columnfields.add(new ColumnField("id", "编号"));
        columnfields.add(new ColumnField("title", "标题"));
        columnfields.add(new ColumnField("catalog.name", "分类"));
        columnfields.add(new ColumnField("createTime", "创建时间"));
        columnfields.add(new ColumnField("modifyTime", "修改时间"));
        columnfields.add(new ColumnField("author.alias", "作者"));

        model.addAttribute("postPage", postPage);
        model.addAttribute("columnfields", columnfields);

        return "/admin/post/listPost";
    }

    @RequestMapping("/create")
    public String createPostView(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Catalog> catalogs = catalogService.findAllCatalog();
        model.addAttribute("catalogs", catalogs);
        return "/admin/post/createPost";
    }

    @RequestMapping("/doCreate")
    public String doCreatePost(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = GlobalUtils.getCurrentUser(request);
        if (post.getAuthor() == null) {
            post.setAuthor(user);
        }
        postService.createPost(post);
        return "redirect:/admin/post/list";
    }

    @RequestMapping("/delete")
    public String deleteCatalog(@RequestParam Long[] id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            postService.deletePost(id);
        }
        return "redirect:/admin/post/list";
    }

    @RequestMapping("/edit")
    public String editPostView(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        String idString = request.getParameter("id");
        if (!StringUtils.isBlank(idString) && ValidatorUtils.isNumber(idString)) {
            post = postService.getPostById(new Long(idString));
            List<Catalog> catalogs = catalogService.findAllCatalog();

            model.addAttribute("post", post);
            model.addAttribute("catalogs", catalogs);
        }
        return "/admin/post/editPost";
    }

    @RequestMapping("/doEdit")
    public String doEditPost(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        postService.updatePost(post);
        return "redirect:/admin/post/list";
    }

}
