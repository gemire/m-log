/**
 * @since Jun 26, 2011
 * www.mspring.org
 * @author gaoyoubo
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Comment;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

public interface CommentService {
    /**
     * 根据编号获取评论
     * @param commentId
     * @return
     */
    Comment getCommentById(Long commentId);
    
    /**
     * 获取指定数量的最新评论
     * @param count
     * @return
     */
    List<Comment> getRecentComments(int count);

    /**
     * 创建评论
     * @param comment
     * @return
     */
    Comment createComment(Comment comment);

    /**
     * 根据文章，查找该文章下的评论
     * @param articleId
     * @return
     */
    List<Comment> findCommentsByArticle(Long articleId);
    
    /**
     * 根据文章，查找该文章下审核通过的评论
     * @param articleId
     * @return
     */
    List<Comment> findAuditPassCommentsByArticle(Long articleId);

    /**
     * 删除评论
     * @param ids
     */
    void deleteComment(Long... ids);

    /**
     * 查询评论
     * @param page
     * @param queryCriterion
     * @return
     */
    Page<Comment> queryComment(Page<Comment> page, QueryCriterion queryCriterion);
    
    /**
     * 更细评论状态
     * @param status
     * @param ids
     */
    void updateStatus(Integer status, Long... ids);
}
