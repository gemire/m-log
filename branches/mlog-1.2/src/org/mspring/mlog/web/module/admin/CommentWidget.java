/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.web.module.admin.query.CommentQueryCriterion;
import org.mspring.mlog.web.resolver.QueryParam;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
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
public class CommentWidget {

    private static final Logger log = Logger.getLogger(CommentWidget.class);

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("/list")
    public String listComment(@ModelAttribute Page<Comment> commentPage, @ModelAttribute Comment comment, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (comment == null) {
            comment = new Comment();
        }
        if (commentPage == null) {
            commentPage = new Page<Comment>();
        }
        commentPage.setSort(new Sort("id", Sort.DESC));

        QueryCriterion queryCriterion = new CommentQueryCriterion(queryParams);
        commentPage = commentService.findComment(commentPage, queryCriterion);

        List<Field> columnfields = new ArrayList<Field>();
        columnfields.add(new ColumnField("id", "编号"));
        columnfields.add(new ColumnField("author", "作者"));
        columnfields.add(new ColumnField("createTime", "发布时间"));
        columnfields.add(new ColumnField("content", "内容"));
        columnfields.add(new ColumnField("postIp", "IP"));

        model.addAttribute("commentPage", commentPage);
        model.addAttribute("columnfields", columnfields);
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
    public String deleteComment(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Comment> commentPage, @ModelAttribute Comment comment, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id != null && id.length > 0) {
            commentService.deleteComment(id);
        }
        //return "redirect:/admin/comment/list";
        return listComment(commentPage, comment, queryParams, request, response, model);
    }

    @RequestMapping("/audit")
    public String auditComment(@RequestParam(required = false) Long[] id, @ModelAttribute Page<Comment> commentPage, @ModelAttribute Comment comment, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        String status = request.getParameter("status");
        if (id != null && id.length > 0 && StringUtils.isNotBlank(status)) {
            if (Comment.Status.APPROVED.equals(status)) {
                commentService.approved(id);
            }
            else if (Comment.Status.SPAM.equals(status)) {
                commentService.spam(id);
            }
            else if (Comment.Status.RECYCLE.equals(status)) {
                commentService.recycle(id);
            }
            else {
                log.warn("update comment status failure, status [" + status + "] is illegal");
            }
        }
        //return "redirect:/admin/comment/list";
        return listComment(commentPage, comment, queryParams, request, response, model);
    }

}
