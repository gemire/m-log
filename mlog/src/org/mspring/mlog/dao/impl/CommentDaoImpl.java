/**
 * 
 */
package org.mspring.mlog.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.mspring.mlog.dao.CommentDao;
import org.mspring.mlog.entity.Comment;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class CommentDaoImpl extends AbstractHibernateDao<Comment> implements CommentDao {

    /**
     * @param entityClass
     */
    protected CommentDaoImpl() {
        super(Comment.class);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.dao.CommentDao#getRecentComments(int)
     */
    @Override
    public List<Comment> getRecentComments(int count) {
        // TODO Auto-generated method stub
        Criteria c = super.getSession().createCriteria(Comment.class);
        c.addOrder(Order.desc("createTime"));
        c.setMaxResults(count);
        return c.list();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.dao.CommentDao#findCommentsByArticle(java.lang.Integer)
     */
    @Override
    public List<Comment> findCommentsByArticle(Long articleId) {
        // TODO Auto-generated method stub
        String queryString = "select c from Comment c where c.article.id = ?";
        return find(queryString, articleId);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.dao.CommentDao#findAuditPassCommentsByArticle(java.lang.Long)
     */
    @Override
    public List<Comment> findAuditPassCommentsByArticle(Long articleId) {
        // TODO Auto-generated method stub
        String queryString = "select c from Comment c where c.article.id = ? and c.status = ?";
        return find(queryString, new Object[]{articleId, Comment.STATUS_PASS});
    }

}
