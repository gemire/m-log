/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.utils.GlobalUtils;
import org.mspring.mlog.web.module.admin.query.PostQueryCriterion;
import org.mspring.mlog.web.resolver.QueryParam;
import org.mspring.mlog.web.validator.PostValidator;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.web.validation.Errors;
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
    private PostValidator postValidator;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Autowired
    public void setPostValidator(PostValidator postValidator) {
        this.postValidator = postValidator;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping({ "/list", "/", "" })
    public String listPost(@ModelAttribute Page<Post> postPage, @ModelAttribute Post post, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        postPage.setSort(new Sort("id", Sort.DESC));

        postPage = postService.findPost(postPage, new PostQueryCriterion(queryParams));

        List<Field> columnfields = new ArrayList<Field>();
        columnfields.add(new ColumnField("id", "编号"));
        columnfields.add(new ColumnField("title", "标题"));
        columnfields.add(new ColumnField("catalogs", "分类"));
        columnfields.add(new ColumnField("url", "链接"));
        columnfields.add(new ColumnField("createTime", "创建时间"));
        columnfields.add(new ColumnField("modifyTime", "修改时间"));
        columnfields.add(new ColumnField("author.alias", "作者"));
        columnfields.add(new ColumnField("status", "状态"));

        model.addAttribute("postPage", postPage);
        model.addAttribute("columnfields", columnfields);
        model.addAttribute("status", Post.Status.getStatusMap());

        return "/admin/post/listPost";
    }

    @RequestMapping("/create")
    public String createPostView(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 文章分类
        List<Catalog> catalogs = catalogService.findAllCatalog();
        model.addAttribute("catalogs", catalogs);

        // 是否开启评论
        Map<String, String> commentStatus = new HashMap<String, String>();
        commentStatus.put(Post.CommentStatus.OPEN, "开启");
        commentStatus.put(Post.CommentStatus.CLOSE, "关闭");
        model.addAttribute("commentStatus", commentStatus);

        // model.addAttribute("errors", errors);
        return "/admin/post/createPost";
    }

    @RequestMapping("/doCreate")
    public String doCreatePost(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        Errors errors = postValidator.validate(post);
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return createPostView(post, request, response, model);
        }
        User user = GlobalUtils.getCurrentUser(request);
        if (post.getAuthor() == null) {
            post.setAuthor(user);
        }
        post.setPostIp(request.getRemoteAddr());
        postService.createPost(post);
        return "redirect:/admin/post/list";
    }

    @RequestMapping("/delete")
    public String deleteCatalog(@RequestParam(required = false) Long[] id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            postService.deletePost(id);
        }
        return "redirect:/admin/post/list";
    }

    @RequestMapping("/edit")
    public String editPostView(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        post = postService.getPostById(post.getId());
        return getEditPostView(post, model);
    }

    @RequestMapping("/doEdit")
    public String doEditPost(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        Errors errors = postValidator.validate(post);
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return getEditPostView(post, model);
        }
        postService.updatePost(post);
        return "redirect:/admin/post/list";
    }

    /**
     * 获取文章编辑页面
     * 
     * @param post
     * @param model
     * @return
     */
    private String getEditPostView(Post post, Model model) {
        // 文章
        model.addAttribute("post", post);
        // 文章分类
        model.addAttribute("catalogs", catalogService.findAllCatalog());
        // 是否开启评论
        model.addAttribute("commentStatus", Post.CommentStatus.getCommentStatusMap());
        return "/admin/post/editPost";
    }
}
