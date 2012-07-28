/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.web.module.admin.query.CommentQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.web.query.QueryParameterAware;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/comment")
public class CommentWidget implements QueryParameterAware {

    private Map queryParams;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.query.QueryParameterAware#setQueryParameters
     * (java.util.Map)
     */
    @Override
    public void setQueryParameters(Map queryParameters) {
        // TODO Auto-generated method stub
        this.queryParams = queryParameters;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.query.QueryParameterAware#getEncodedQueryParams
     * ()
     */
    @Override
    public String getEncodedQueryParams() {
        // TODO Auto-generated method stub
        return null;
    }

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/list")
    public String listComment(@ModelAttribute Page<Comment> commentPage, HttpServletRequest request, HttpServletResponse response, Model model) {
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

        return "/admin/comment/listComment";
    }

}
