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

    public void deleteComment(Long... idArray);

    public List<Comment> findCommentsByPost(Long postId);

    public Comment getCommentById(Long id);

    public Page<Comment> findComment(Page<Comment> page, String queryString, Object... params);
    
    public Page<Comment> findComment(Page<Comment> page, QueryCriterion queryCriterion);

    public List<Comment> getRecentComment(int num);
}
