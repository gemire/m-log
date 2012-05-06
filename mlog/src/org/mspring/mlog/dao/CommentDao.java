/**
 * 
 */
package org.mspring.mlog.dao;

import java.util.List;

import org.mspring.mlog.entity.Comment;
import org.mspring.platform.dao.BaseDao;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
public abstract interface CommentDao extends BaseDao<Comment> {
    /**
     * 获取指定数量的最新评论
     * @param count
     * @return
     */
    List<Comment> getRecentComments(int count);

    /**
     * 获取文章下所有的评论
     * @param articleId
     * @return
     */
    List<Comment> findCommentsByArticle(Long articleId);
    
    /**
     * 获取文章下通过审核的评论
     * @param articleId
     * @return
     */
    List<Comment> findAuditPassCommentsByArticle(Long articleId);
}
