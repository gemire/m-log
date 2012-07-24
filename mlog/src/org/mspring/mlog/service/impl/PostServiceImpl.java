/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Service
@Transactional
public class PostServiceImpl extends AbstractServiceSupport implements PostService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#createPost(org.mspring.mlog.entity
     * .Post)
     */
    @Override
    public Post createPost(Post post) {
        // TODO Auto-generated method stub
        if (post.getCreateTime() == null) {
            post.setCreateTime(new Date());
        }
        if (StringUtils.isBlank(post.getCommentStatus())) {
            post.setCommentStatus(Post.COMMENT_STATUS_OPEN);
        }
        if (StringUtils.isBlank(post.getStatus())) {
            post.setStatus(Post.POST_STATUS_PUBLISH);
        }
        Long id = (Long) super.save(post);
        return getPostById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#deletePost(java.lang.Long[])
     */
    @Override
    public void deletePost(Long... idArray) {
        // TODO Auto-generated method stub
        super.delete(Post.class, idArray);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#findPost(org.mspring.platform.
     * persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<Post> findPost(Page<Post> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return super.findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#findPost(org.mspring.platform.
     * persistence.support.Page, java.lang.String)
     */
    @Override
    public Page<Post> findPost(Page<Post> page, String queryString) {
        // TODO Auto-generated method stub
        return super.findPage(page, queryString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#findPost(org.mspring.platform.
     * persistence.support.Page, java.lang.String, java.lang.Object[])
     */
    @Override
    public Page<Post> findPost(Page<Post> page, String queryString, Object... queryParams) {
        // TODO Auto-generated method stub
        return super.findPage(page, queryString, queryParams);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#getPostById(java.lang.Long)
     */
    @Override
    public Post getPostById(Long postId) {
        // TODO Auto-generated method stub
        return (Post) super.get(Post.class, postId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#updatePost(org.mspring.mlog.entity
     * .Post)
     */
    @Override
    public void updatePost(Post post) {
        // TODO Auto-generated method stub
        post.setModifyTime(new Date());
        super.update(post);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#getRecentPost(int)
     */
    @Override
    public List<Post> getRecentPost(final int nums) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Post>>() {

            @Override
            public List<Post> doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query query = session.createQuery("select post from Post post order by post.createTime desc");
                query.setMaxResults(nums);
                return query.list();
            }
        });
    }

}
