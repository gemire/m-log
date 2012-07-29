/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.CommentService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
@Service
@Transactional
public class CommentServiceImpl extends AbstractServiceSupport implements CommentService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#createComment(org.mspring.mlog
     * .entity.Comment)
     */
    @Override
    public Comment createComment(Comment comment) {
        // TODO Auto-generated method stub
        Serializable s = super.save(comment);
        return (Comment) super.get(Comment.class, s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#deleteComment(java.lang.Long[])
     */
    @Override
    public void deleteComment(Long... idArray) {
        // TODO Auto-generated method stub
        super.delete(Comment.class, idArray);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#findCommentsByPost(java.lang.
     * Long)
     */
    @Override
    public List<Comment> findCommentsByPost(Long postId) {
        // TODO Auto-generated method stub
        String queryString = "select comment from Comment comment where comment.post.id = ? and comment.status = ?";
        return find(queryString, new Object[]{postId, Comment.Status.APPROVED});
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#getCommentById(java.lang.Long)
     */
    @Override
    public Comment getCommentById(Long id) {
        // TODO Auto-generated method stub
        return (Comment) get(Comment.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#findComment(java.lang.String,
     * java.lang.Object[])
     */
    @Override
    public Page<Comment> findComment(Page<Comment> page, String queryString, Object... params) {
        // TODO Auto-generated method stub
        return findPage(page, queryString, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#getRecentComment(int)
     */
    @Override
    public List<Comment> getRecentComment(final int num) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Comment>>() {

            @Override
            public List<Comment> doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query query = session.createQuery("select comment from Comment comment order by comment.createTime desc");
                query.setMaxResults(num);
                return query.list();
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#findComment(org.mspring.platform
     * .persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<Comment> findComment(Page<Comment> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#approved(java.lang.Long[])
     */
    @Override
    public void approved(Long... ids) {
        // TODO Auto-generated method stub
        for (Long id : ids) {
            updateCommentStatus(id, Comment.Status.APPROVED);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#spam(java.lang.Long[])
     */
    @Override
    public void spam(Long... ids) {
        // TODO Auto-generated method stub
        for (Long id : ids) {
            updateCommentStatus(id, Comment.Status.SPAM);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#recycle(java.lang.Long[])
     */
    @Override
    public void recycle(Long... ids) {
        // TODO Auto-generated method stub
        for (Long id : ids) {
            updateCommentStatus(id, Comment.Status.RECYCLE);
        }
    }

    /**
     * 更新评论状态
     * 
     * @param status
     */
    private void updateCommentStatus(Long id, String status) {
        String queryString = "update Comment comment set comment.status = ? where comment.id = ?";
        executeUpdate(queryString, new Object[] { status, id });
    }

}
