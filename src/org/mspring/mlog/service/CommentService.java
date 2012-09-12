/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Comment;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
public interface CommentService {
    public Comment createComment(Comment comment);

    public List<Comment> findCommentsByPost(Long postId);

    public Comment getCommentById(Long id);

    public Page<Comment> findComment(Page<Comment> page, String queryString, Object... params);

    public Page<Comment> findComment(Page<Comment> page, QueryCriterion queryCriterion);

    public List<Comment> getRecentComment(int num);

    /**
     * 彻底删除
     * @param ids
     */
    public void deleteComment(Long... ids);

    /**
     * 审核通过
     * @param ids
     */
    public void approved(Long... ids);

    /**
     * 编辑为垃圾评论
     * @param ids
     */
    public void spam(Long... ids);

    /**
     * 移到回收站
     * @param ids
     */
    public void recycle(Long... ids);

}
