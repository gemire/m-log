/**
 * @since 2011-7-21
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.action.manage.comment;

import java.util.ArrayList;
import java.util.Map;

import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.comment.query.CommentQueryCriterion;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.mspring.platform.dao.support.Sort;
import org.mspring.platform.model.field.ColumnField;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * 
 * @author Gao Youbo
 */
public class QueryCommentAction extends AbstractManageAction implements QueryParameterAware {
    /**
     * 
     */
    private static final long serialVersionUID = -8255795043852742938L;

    private Map queryParameters;
    private QueryCriterion queryCriterion;

    public String getEncodedQueryParams() {
        if (queryCriterion != null) {
            return queryCriterion.getQueryParamsAsString();
        }
        return null;
    }

    public void setQueryParameters(Map queryParameters) {
        this.queryParameters = queryParameters;
    }

    private Page<Comment> commentPage = new Page<Comment>();
    private Comment comment;

    public Page<Comment> getCommentPage() {
        return commentPage;
    }

    public void setCommentPage(Page<Comment> commentPage) {
        this.commentPage = commentPage;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String execute() {
        fieldColumns = new ArrayList<ColumnField>();
        fieldColumns.add(new ColumnField("id", getText("comment.field.id")));
        fieldColumns.add(new ColumnField("author", getText("comment.field.author")));
        fieldColumns.add(new ColumnField("createTime", getText("comment.field.createtime")));
        fieldColumns.add(new ColumnField("ip", getText("comment.field.ip")));
        fieldColumns.add(new ColumnField("content", getText("comment.field.content")));
        fieldColumns.add(new ColumnField("status", getText("comment.field.status")));

        if (commentPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            commentPage.setSort(sort);
        }
        queryCriterion = new CommentQueryCriterion(queryParameters);
        commentPage = commentService.queryComment(commentPage, queryCriterion);
        return SUCCESS;
    }
}
