/**
 * 
 */
package org.mspring.mlog.web.module.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.common.Keys;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.AbstractWidget;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.mspring.platform.web.servlet.renderer.JSONRenderer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-7-29
 * @Description
 * @TODO
 */
@Widget("customCommentWidget")
@RequestMapping("/comment")
public class CommentWidget extends AbstractWidget {

    /**
     * 评论信息
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({ "/", "" })
    public String postform(HttpServletRequest request, HttpServletResponse response, Model model) {
        String postId = request.getParameter("post");
        List<Comment> comments = commentService.findCommentsByPost(new Long(postId));
        String author = CookieUtils.getCookie(request, Keys.COMMENT_AUTHOR_COOKIE);
        String email = CookieUtils.getCookie(request, Keys.COMMENT_EMAIL_COOKIE);
        String url = CookieUtils.getCookie(request, Keys.COMMENT_URL_COOKIE);
        model.addAttribute("author", author);
        model.addAttribute("email", email);
        model.addAttribute("url", url);
        model.addAttribute("comments", comments);
        model.addAttribute("postId", postId);
        return "skin:/comment";
    }

    /**
     * 发表评论
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/post")
    public String postComment(HttpServletRequest request, HttpServletResponse response, Model model) {
        String postId = request.getParameter("postId");
        if (!Post.CommentStatus.OPEN.equals(postService.getPostById(new Long(postId)).getCommentStatus())) {
            return prompt(model, "文章评论已关闭，无法发表评论");
        }

        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String email = request.getParameter("email");
        String url = request.getParameter("url");
        Long parentId = StringUtils.isBlank(request.getParameter("parentId")) ? null : new Long(request.getParameter("parentId"));
        String ip = StringUtils.getIpAddr(request);
        String agent = StringUtils.getUserAgent(request);

        /**
         * 验证评论发布人
         */
        if (StringUtils.isBlank(author)) {
            return prompt(model, "评论发表失败，发布人不能为空");
        }

        /**
         * 验证评论内容不能为空
         */
        if (StringUtils.isBlank(content)) {
            return prompt(model, "评论发表失败，评论内容不能为空");
        }

        /**
         * 验证email地址， email地址为非必填项，如果填写，那么验证email格式是否正确
         */
        if (StringUtils.isNotBlank(email) && !ValidatorUtils.isEmailAddress(email)) {
            return prompt(model, "评论发表失败，email格式填写不正确");
        }

        /**
         * 验证URL， URL地址为非必填项，如果填写，那么验证URL格式是否正确
         * 
         */
        if (StringUtils.isNotBlank(url) && !ValidatorUtils.isUrl(url)) {
            return prompt(model, "评论发表失败，主页地址格式填写不正确");
        }

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setEmail(email);
        comment.setUrl(url);
        comment.setPostIp(ip);
        comment.setAgent(agent);
        comment.setCreateTime(new Date());
        comment.setPost(new Post(new Long(postId.trim())));
        if (parentId != null) {
            comment.setParent(new Comment(parentId));
        }

        // 判断评论审核功能是否开启
        String is_comment_audit = optionService.getOption("comment_audit");
        if ("true".equals(is_comment_audit)) { // 如果开启评论审核
            comment.setStatus(Comment.Status.WAIT_FOR_APPROVE);
        }
        else {
            comment.setStatus(Comment.Status.APPROVED);
        }
        comment = commentService.createComment(comment);

        if (!("true".equals(is_comment_audit))) { // 如果没有开启评论审核
            // 更新文章评论数量
            postService.updatePostCommentCount(new Long(postId.trim()));
        }

        // 将评论作者的信息保存到cookie中
        CookieUtils.setCookie(response, Keys.COMMENT_AUTHOR_COOKIE, author, 365);
        CookieUtils.setCookie(response, Keys.COMMENT_EMAIL_COOKIE, email, 365);
        CookieUtils.setCookie(response, Keys.COMMENT_URL_COOKIE, url, 365);
        model.addAttribute(FreemarkerVariableNames.COMMENT, comment);
        String postUrl = comment.getPost().getUrl();
        if (StringUtils.isBlank(postUrl)) {
            postUrl = postService.getPostUrlByCommentId(comment.getId());
        }
        return String.format("redirect:%s", postUrl);
    }

    /**
     * 引用
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("quote")
    @ResponseBody
    public void quote(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            return;
        }
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            JSONRenderer renderer = new JSONRenderer(comment, true);
            renderer.render(response);
        }
    }

}
