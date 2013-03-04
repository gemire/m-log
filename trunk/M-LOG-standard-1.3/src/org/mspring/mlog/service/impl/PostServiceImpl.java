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
import org.hibernate.Transaction;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description
 * @TODO
 */
@Service
@Transactional
public class PostServiceImpl extends AbstractServiceSupport implements PostService {

    @Autowired
    private CatalogService catalogService;

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
        if (post.getIsTop() == null) {
            post.setIsTop(false);
        }
        post.setCommentCount(new Long(0));
        post.setViewCount(new Long(0));
        Long id = (Long) super.create(post);

        return getPostById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#discardPost(java.lang.Long[])
     */
    @Override
    public void discardPost(Long... idArray) {
        // TODO Auto-generated method stub
        for (Long id : idArray) {
            String hql = "update Post post set post.status = ? where post.id = ?";
            executeUpdate(hql, new Object[] { Post.Status.TRASH, id });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#trash2Publish(java.lang.Long[])
     */
    @Override
    public void trash2Publish(Long... idArray) {
        // TODO Auto-generated method stub
        for (Long id : idArray) {
            String hql = "update Post post set post.status = ? where post.id = ? and post.status = ?";
            executeUpdate(hql, new Object[] { Post.Status.PUBLISH, id, Post.Status.TRASH });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#deletePost(java.lang.Long[])
     */
    @Override
    public void deletePost(Long... idArray) {
        // TODO Auto-generated method stub
        super.remove(Post.class, idArray);
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
        return super.findPage(queryCriterion, page);
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
        return super.findPage(queryString, page);
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
        return super.findPage(queryString, page, queryParams);
    }

    @Override
    public Page<Post> findPostByTag(Page<Post> page, Long tag) {
        // TODO Auto-generated method stub
        if (page == null) {
            page = new Page<Post>();
        }
        if (page.getSort() == null) {
            page.setSort(new Sort("postTag.PK.post.id", Sort.DESC));
        }
        if (tag != null) {
            findPost(page, "select postTag.PK.post from PostTag postTag where postTag.PK.tag.id = ? and postTag.PK.post.status = ? order by postTag.PK.post.isTop desc, postTag.PK.post.id desc", new Object[] { tag, Post.Status.PUBLISH });
        }
        return page;
    }

    @Override
    public Page<Post> findPostByCatalog(Page<Post> page, Long catalog) {
        // TODO Auto-generated method stub
        if (page == null) {
            page = new Page<Post>();
        }
        if (page.getSort() == null) {
            page.setSort(new Sort("postCatalog.PK.post.id", Sort.DESC));
        }
        if (catalog != null) {
            List<Catalog> catalogs = catalogService.findAllChildCatalogs(catalog);
            StringBuffer in = new StringBuffer();
            for (int i = 0; i < catalogs.size(); i++) {
                in.append(catalogs.get(i).getId());
                if (i != (catalogs.size() - 1)) {
                    in.append(",");
                }
            }
            findPost(page, "select postCatalog.PK.post from PostCatalog postCatalog where postCatalog.PK.catalog.id in (" + in + ") and postCatalog.PK.post.status = ? order by postCatalog.PK.post.isTop desc, postCatalog.PK.post.id desc", Post.Status.PUBLISH);
        }
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#getPostById(java.lang.Long)
     */
    @Override
    public Post getPostById(Long postId) {
        // TODO Auto-generated method stub
        return (Post) getById(Post.class, postId);
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
        if (post.getIsTop() == null) {
            post.setIsTop(false);
        }
        update(post);
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

    @Override
    public List<Post> getRecentCatalogPost(final Long catalog, final int nums) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Post>>() {

            @Override
            public List<Post> doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                if (catalog != null) {
                    List<Catalog> catalogs = catalogService.findAllChildCatalogs(catalog);
                    StringBuffer in = new StringBuffer();
                    for (int i = 0; i < catalogs.size(); i++) {
                        in.append(catalogs.get(i).getId());
                        if (i != (catalogs.size() - 1)) {
                            in.append(",");
                        }
                    }
                    Query query = session.createQuery("select postCatalog.PK.post from PostCatalog postCatalog where postCatalog.PK.catalog.id in (" + in + ") and postCatalog.PK.post.status = ? order by postCatalog.PK.post.createTime desc");
                    query.setParameter(0, Post.Status.PUBLISH);
                    query.setMaxResults(nums);
                    return query.list();
                }
                return null;
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

    @Override
    public List<Post> getMostViewCatalogPost(final Long catalog, final int nums) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Post>>() {

            @Override
            public List<Post> doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                if (catalog != null) {
                    List<Catalog> catalogs = catalogService.findAllChildCatalogs(catalog);
                    StringBuffer in = new StringBuffer();
                    for (int i = 0; i < catalogs.size(); i++) {
                        in.append(catalogs.get(i).getId());
                        if (i != (catalogs.size() - 1)) {
                            in.append(",");
                        }
                    }
                    Query query = session.createQuery("select postCatalog.PK.post from PostCatalog postCatalog where postCatalog.PK.catalog.id in (" + in + ") and postCatalog.PK.post.status = ? order by postCatalog.PK.post.viewCount desc");
                    query.setParameter(0, Post.Status.PUBLISH);
                    query.setMaxResults(nums);
                    return query.list();
                }
                return null;
            }
        });
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#getPostUrlByCommentId(java.lang.
     * Long)
     */
    @Override
    public String getPostUrlByCommentId(Long commentId) {
        // TODO Auto-generated method stub
        String queryString = "select post.url from Post post, Comment comment where comment.post.id = post.id and comment.id = ?";
        Object url = findUnique(queryString, commentId);
        return url == null ? "" : url.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostService#getPostByComment(java.lang.Long)
     */
    @Override
    public Post getPostByComment(Long commentId) {
        // TODO Auto-generated method stub
        String queryString = "select post from Post post, Comment comment where comment.post.id = post.id and comment.id = ?";
        Object post = findUnique(queryString, commentId);
        return post == null ? null : ((Post) post);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#clearTrash()
     */
    @Override
    public void clearTrash() {
        // TODO Auto-generated method stub
        String hql = "delete Post post where post.status = ?";
        executeUpdate(hql, Post.Status.TRASH);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#getTopPosts(int)
     */
    @Override
    public List<Post> getTopPosts(final int nums) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Post>>() {

            @Override
            public List<Post> doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query query = session.createQuery("select post from Post post where post.status = ? and post.isTop = true");
                query.setParameter(0, Post.Status.PUBLISH);
                if (nums != 0) {
                    query.setMaxResults(nums);
                }
                return query.list();
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostService#hasPermisstion(java.lang.Long,
     * java.lang.String)
     */
    @Override
    public boolean hasPermisstion(Long postId, String password) {
        // TODO Auto-generated method stub
        Post post = getPostById(postId);
        if (StringUtils.isBlank(post.getPassword())) {
            return true;
        } else if (post.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void movePostCatalog(Long fromCatalog, Long toCatalog) {
        // TODO Auto-generated method stub
        Session session = getSession();
        try {
//            //查出已经存在的主键,防止主键冲突
            Query query1 = session.createQuery("select pc1.PK.post.id from PostCatalog pc1 where pc1.PK.catalog.id = ?");
            query1.setParameter(0, toCatalog);
            List list = query1.list();
            String ids = "";
            for (int i = 0; i < list.size(); i++) {
                ids += list.get(i).toString();
                if (i < list.size() - 1) {
                    ids += ",";
                }
            }
            
            Query query = session.createQuery("update PostCatalog pc set pc.PK.catalog.id = ? where pc.PK.catalog.id = ? and pc.PK.post.id not in (" + ids + ")");
            
            //Query query = session.createQuery("update PostCatalog pc set pc.PK.catalog.id = ? where pc.PK.catalog.id = ?"); //这种方式会照成主键冲突
            //Query query = session.createQuery("update PostCatalog pc set pc.PK.catalog.id = ? where pc.PK.catalog.id = ? and pc.PK.post.id not in (select pc1.PK.post.id from PostCatalog pc1 where pc1.PK.catalog.id = ?)"); //mysql不支持这样的sql,报错:You can't specify target table 'table_name' for update in FROM clause
            query.setParameter(0, toCatalog);
            query.setParameter(1, fromCatalog);
            query.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
