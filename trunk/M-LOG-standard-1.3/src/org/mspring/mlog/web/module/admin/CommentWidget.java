/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.CommentQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/comment")
public class CommentWidget extends AbstractAdminWidget {

    private static final Logger log = Logger.getLogger(CommentWidget.class);

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("/list")
    @Log
    public String listComment(@ModelAttribute Page<Comment> commentPage, @ModelAttribute Comment comment, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (comment == null) {
            comment = new Comment();
        }
        if (commentPage == null) {
            commentPage = new Page<Comment>();
        }
        commentPage.setSort(new Sort("id", Sort.DESC));

        if (comment == null) {
            comment = new Comment();
        }
        if (queryParams.get("status") == null || StringUtils.isBlank(queryParams.get("status").toString())) {
            comment.setStatus(Comment.Status.APPROVED);
            queryParams.put("status", Comment.Status.APPROVED);
        }

        QueryCriterion queryCriterion = new CommentQueryCriterion(queryParams);
        commentPage = commentService.findComment(commentPage, queryCriterion);

        model.addAttribute("commentPage", commentPage);
        model.addAttribute("commentStatus", Comment.Status.getCommentStatusMap());
        return "/admin/comment/listComment";
    }

    /**
     * 删除
     * 
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    @Log
    public String deleteComment(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Comment> commentPage, @ModelAttribute Comment comment, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            commentService.deleteComment(id);
        }
        return listComment(commentPage, comment, queryParams, request, response, model);
    }

    /**
     * 显示评论
     * 
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/view")
    public String viewComment(@RequestParam(required = true) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "/admin/comment/viewComment";
    }

    /**
     * 审核链接
     * 
     * @param id
     * @param commentPage
     * @param comment
     * @param queryParams
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/audit")
    @Log
    public String auditComment(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Comment> commentPage, @ModelAttribute Comment comment, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        String status = request.getParameter("type");
        if (id != null && id.length > 0 && StringUtils.isNotBlank(status)) {
            if (Comment.Status.APPROVED.equals(status)) {
                commentService.approved(id);
            } else if (Comment.Status.SPAM.equals(status)) {
                commentService.spam(id);
            } else if (Comment.Status.RECYCLE.equals(status)) {
                commentService.recycle(id);
            } else {
                log.warn("update comment status failure, status [" + status + "] is illegal");
            }
        }
        return listComment(commentPage, comment, queryParams, request, response, model);
    }

}
