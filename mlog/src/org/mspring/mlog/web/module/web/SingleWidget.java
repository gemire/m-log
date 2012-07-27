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
@RequestMapping({ "/", "" })
public class SingleWidget extends AbstractWebWidget {

    @RequestMapping("/single")
    private String single(HttpServletRequest request, HttpServletResponse response, Model model) {
        String idStr = request.getParameter("id");
        if (!StringUtils.isBlank(idStr) && ValidatorUtils.isNumber(idStr)) {
            Post post = postService.getPostById(new Long(idStr));
            model.addAttribute("post", post);

            List<Comment> comments = commentService.findCommentsByPost(post.getId());
            model.addAttribute("comments", comments);
        }
        return "skin:/single";
    }

    @RequestMapping("/comment/post")
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
        
        comment = commentService.createComment(comment);
        model.addAttribute("comment", comment);
        return String.format("redirect:/%s.html", postId);
    }
}
