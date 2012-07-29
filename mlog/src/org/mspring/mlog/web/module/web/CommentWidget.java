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
import org.mspring.mlog.web.Keys;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-29
 * @Description
 * @TODO
 */
@Widget("customCommentWidget")
@RequestMapping("/comment")
public class CommentWidget extends AbstractWebWidget {

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

        return "skin:/comment.ftl";
    }

    @RequestMapping("/post")
    public String postComment(HttpServletRequest request, HttpServletResponse response, Model model) {
        String postId = request.getParameter("postId");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String email = request.getParameter("email");
        String url = request.getParameter("url");

        String ip = request.getRemoteAddr();
        String agent = request.getHeader("user-agent");

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setEmail(email);
        comment.setUrl(url);
        comment.setPostIp(ip);
        comment.setAgent(agent);
        comment.setCreateTime(new Date());
        comment.setPost(new Post(new Long(postId.trim())));

        // 判断评论审核功能是否开启
        String is_comment_audit = optionService.getOption("comment_audit");
        comment.setStatus("true".equals(is_comment_audit) ? Comment.Status.WAIT_FOR_APPROVE : Comment.Status.APPROVED);

        comment = commentService.createComment(comment);

        // 将评论作者的信息保存到cookie中
        CookieUtils.setCookie(response, Keys.COMMENT_AUTHOR_COOKIE, author, 365);
        CookieUtils.setCookie(response, Keys.COMMENT_EMAIL_COOKIE, email, 365);
        CookieUtils.setCookie(response, Keys.COMMENT_URL_COOKIE, url, 365);
        model.addAttribute("comment", comment);
        return String.format("redirect:/%s.html", postId);
    }
}
