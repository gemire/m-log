/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.utils.GlobalUtils;
import org.mspring.mlog.utils.PermaLinkUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.PostQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/post")
public class PostWidget {
    private static final Logger log = Logger.getLogger(PostWidget.class);

    private PostService postService;
    private CatalogService catalogService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping({ "/list", "/", "" })
    public String listPost(@ModelAttribute Page<Post> postPage, @ModelAttribute Post post, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (postPage == null) {
            postPage = new Page<Post>();
        }
        //postPage.setSort(new Sort("id", Sort.DESC));
        postPage.setSort(new Sort("isTop desc, id desc", ""));

        // 默认查看已发布的文章
        if (post == null) {
            post = new Post();
        }
        if (queryParams.get("status") == null || StringUtils.isBlank(queryParams.get("status").toString())) {
            post.setStatus(Post.Status.PUBLISH);
            queryParams.put("status", Post.Status.PUBLISH);
        }

        postPage = postService.findPost(postPage, new PostQueryCriterion(queryParams));
        model.addAttribute("postPage", postPage);
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
        
        Map<String, String> isTop = new HashMap<String, String>();
        isTop.put("true", "是");
        isTop.put("false", "否");
        model.addAttribute("isTop", isTop);
        return "/admin/post/createPost";
    }

    @RequestMapping("/doCreate")
    public String doCreatePost(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = GlobalUtils.getCurrentUser(request);
        if (post.getAuthor() == null) {
            post.setAuthor(user);
        }
        post.setPostIp(request.getRemoteAddr());
        postService.createPost(post);
        return "redirect:/admin/post/list";
    }

    /**
     * 将文章移入回收站
     * 
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/trash")
    public String trashPost(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Post> postPage, @ModelAttribute Post post, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            postService.discardPost(id);
        }
        // return "redirect:/admin/post/list";
        return listPost(postPage, post, queryParams, request, response, model);
    }

    /**
     * 从回收站还原文章
     * 
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/trash2Publish")
    public String trash2Publish(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Post> postPage, @ModelAttribute Post post, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            postService.trash2Publish(id);
        }
        // return "redirect:/admin/post/list";
        return listPost(postPage, post, queryParams, request, response, model);
    }

    /**
     * 彻底删除文章
     * 
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String deletePost(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Post> postPage, @ModelAttribute Post post, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            postService.deletePost(id);
        }
        return listPost(postPage, post, queryParams, request, response, model);
    }

    /**
     * 清空回收站
     * 
     * @return
     */
    @RequestMapping("clearTrash")
    public String clearTrash(@ModelAttribute Page<Post> postPage, @ModelAttribute Post post, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        postService.clearTrash();
        return listPost(postPage, post, queryParams, request, response, model);
    }

    @RequestMapping("/edit")
    public String editPostView(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        post = postService.getPostById(post.getId());
        
        Map<String, String> isTop = new HashMap<String, String>();
        isTop.put("true", "是");
        isTop.put("false", "否");
        model.addAttribute("isTop", isTop);
        return getEditPostView(post, model);
    }

    @RequestMapping("/doEdit")
    public String doEditPost(@ModelAttribute Post post, HttpServletRequest request, HttpServletResponse response, Model model) {
        postService.updatePost(post);
        return "redirect:/admin/post/list";
    }

    /**
     * 更新文章索引
     * 
     * @return
     */
    @RequestMapping("/updateIndex")
    @ResponseBody
    public String updateLuceneIndex() {
        try {
            // postSearchService.rebuildAllPostIndex();
        }
        catch (Exception e) {
            // TODO: handle exception
            log.debug("update post index failure!", e);
            return "false";
        }
        return "true";
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

    /**
     * 判断文章标题是否为空
     * 
     * @param title
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("postTitleExists")
    @ResponseBody
    public String postTitleExists(@RequestParam(required = false) String title, @RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(title)) {
            return "true";
        }
        boolean flag = postService.titleExists(title, id);
        return flag ? "true" : "false";
    }

    /**
     * 判断链接的合法性
     * 
     * @param url
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("postUrlIllegal")
    @ResponseBody
    public String postUrlIllegal(@RequestParam(required = false) String url, HttpServletRequest request, HttpServletResponse response) {
        if ("".equals(url) || url == null) {
            return "true";
        }
        // 验证链接是否含有非法字符串，含有非法字符串返回false，验证不通过
        if (PermaLinkUtils.hasIllegalCharacter(url)) {
            return "false";
        }
        if (PermaLinkUtils.invalidParamLink(url)) {
            return "false";
        }
        return "true";
    }

    /**
     * 判断文章链接是否存在
     * 
     * @param url
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("postUrlExists")
    @ResponseBody
    public String postUrlExists(@RequestParam(required = false) String url, @RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if ("".equals(url) || url == null) {
            return "false";
        }
        boolean flag = postService.urlExists(url, id);
        return flag ? "true" : "false";
    }
}
