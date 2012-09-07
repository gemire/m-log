/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.utils.PermaLinkUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.StringUtils;
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
            post.setCommentStatus(Post.CommentStatus.OPEN);
        }
        if (StringUtils.isBlank(post.getStatus())) {
            post.setStatus(Post.Status.PUBLISH);
        }
        if (post.getCommentCount() == null) {
            post.setCommentCount(new Long(0));
        }
        if (StringUtils.isBlank(post.getUrl())) {
            String url = PermaLinkUtils.getDefaultPostURL();
            post.setUrl(url);
        }
        post.setCommentCount(new Long(0));
        post.setViewCount(new Long(0));
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
        if (post.getCreateTime() == null) {
            post.setCreateTime(new Date());
        }
        if (StringUtils.isBlank(post.getCommentStatus())) {
            post.setCommentStatus(Post.CommentStatus.OPEN);
        }
        if (StringUtils.isBlank(post.getStatus())) {
            post.setStatus(Post.Status.PUBLISH);
        }
        if (post.getCommentCount() == null) {
            post.setCommentCount(new Long(0));
        }
        if (StringUtils.isBlank(post.getUrl())) {
            String url = PermaLinkUtils.getDefaultPostURL();
            post.setUrl(url);
        }
        if (post.getCommentCount() == null) {
            post.setCommentCount(new Long(0));
        }
        if (post.getViewCount() == null) {
            post.setViewCount(new Long(0));
        }
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
                Query query = session.createQuery("select post from Post post where post.status = ? order by post.createTime desc");
                query.setParameter(0, Post.Status.PUBLISH);
                query.setMaxResults(nums);
                return query.list();
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#getMostViewPost(int)
     */
    @Override
    public List<Post> getMostViewPost(final int nums) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Post>>() {

            @Override
            public List<Post> doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query query = session.createQuery("select post from Post post where post.status = ? order by post.viewCount desc");
                query.setParameter(0, Post.Status.PUBLISH);
                query.setMaxResults(nums);
                return query.list();
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#getPostByTitle(java.lang.String)
     */
    @Override
    public Post getPostByTitle(String title) {
        // TODO Auto-generated method stub
        String queryString = "select post from Post post where post.title = ?";
        return (Post) findUnique(queryString, title);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#getPostByUrl(java.lang.String)
     */
    @Override
    public Post getPostByUrl(String url) {
        // TODO Auto-generated method stub
        String queryString = "select post from Post post where post.url = ?";
        return (Post) findUnique(queryString, url);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#titleExists(java.lang.String ,
     * java.lang.Long)
     */
    @Override
    public boolean titleExists(String title, Long postId) {
        // TODO Auto-generated method stub
        Long count = null;
        if (postId == null) {
            String queryString = "select count(*) from Post post where post.title = ?";
            count = count(queryString, title);
        }
        else {
            String queryString = "select count(*) from Post post where post.title = ? and post.id <> ?";
            count = count(queryString, new Object[] { title, postId });
        }
        if (count > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#urlExists(java.lang.String)
     */
    @Override
    public boolean urlExists(String url) {
        // TODO Auto-generated method stub
        return urlExists(url, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#urlExists(java.lang.String,
     * java.lang.Long)
     */
    @Override
    public boolean urlExists(String url, Long postId) {
        // TODO Auto-generated method stub
        Long count = null;
        if (postId == null) {
            String queryString = "select count(*) from Post post where post.url = ?";
            count = count(queryString, url);
        }
        else {
            String queryString = "select count(*) from Post post where post.url = ? and post.id <> ?";
            count = count(queryString, new Object[] { url, postId });
        }
        if (count > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#updatePostCommentCount(java.lang
     * .Long)
     */
    @Override
    public void updatePostCommentCount(Long postId) {
        // TODO Auto-generated method stub
        if (postId != null) {
            executeUpdate("update Post set commentCount = (select count(*) from Comment comment where comment.status = ? and comment.post.id = ?) where id = ?", new Object[] { Comment.Status.APPROVED, postId, postId });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#updatePostViewCount(java.lang.Long)
     */
    @Override
    public void updatePostViewCount(Long postId) {
        // TODO Auto-generated method stub
        if (postId != null) {
            executeUpdate("update Post post set post.viewCount = (post.viewCount + 1) where post.id = ?", postId);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#getRandomPost(int)
     */
    @Override
    public List<Post> getRandomPost(int nums) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#getRelatedPost(org.mspring.mlog.
     * entity.Post, int)
     */
    @Override
    public List<Post> getRelatedPost(Post post, int nums) {
        // TODO Auto-generated method stub
        Set<Tag> tags = post.getTags();

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#findAll()
     */
    @Override
    public List<Post> findAll() {
        // TODO Auto-generated method stub
        return super.findAll(Post.class);
    }
}
