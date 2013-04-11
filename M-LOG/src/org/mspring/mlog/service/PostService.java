/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Post;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
public interface PostService {
    /**
     * 创建文章
     * 
     * @param post
     * @return
     */
    Post createPost(Post post);

    /**
     * 废弃文章(将文章移入回收站)
     * 
     * @param idArray
     */
    void discardPost(Long... idArray);

    /**
     * 将文章从回收站移入已发布(从回收站恢复文章)
     * 
     * @param idArray
     */
    void trash2Publish(Long... idArray);

    /**
     * 清空回收站
     */
    void clearTrash();

    /**
     * 删除文章
     * 
     * @param idArray
     */
    void deletePost(Long... idArray);

    /**
     * 查找文章
     * 
     * @param page
     * @param queryCriterion
     * @return
     */
    Page<Post> findPost(Page<Post> page, QueryCriterion queryCriterion);

    /**
     * 查找文章
     * 
     * @param page
     * @param queryString
     * @return
     */
    Page<Post> findPost(Page<Post> page, String queryString);

    /**
     * 查找文章
     * 
     * @param page
     * @param queryString
     * @param queryParams
     * @return
     */
    Page<Post> findPost(Page<Post> page, String queryString, Object... queryParams);

    /**
     * 根据Tag分页查询Post
     * 
     * @param page
     * @param tag
     * @return
     */
    public Page<Post> findPostByTag(Page<Post> page, Long tag);

    /**
     * 根据Catalog分页查询Post
     * 
     * @param page
     * @param catalog
     * @return
     */
    public Page<Post> findPostByCatalog(Page<Post> page, Long catalog);

    /**
     * 根据编号获取
     * 
     * @param postId
     * @return
     */
    Post getPostById(Long postId);

    /**
     * 更新文章
     * 
     * @param post
     */
    void updatePost(Post post);

    /**
     * 最新发表文章
     * 
     * @param nums
     * @return
     */
    List<Post> getRecentPost(int nums);

    /**
     * 分类下最新发表的文章
     * 
     * @param nums
     * @return
     */
    List<Post> getRecentCatalogPost(Long catalog, int nums);

    /**
     * 最高点击率文章
     * 
     * @param nums
     * @return
     */
    List<Post> getMostViewPost(int nums);

    /**
     * 分类下最高点击率文章
     * 
     * @param catalog
     * @param nums
     * @return
     */
    List<Post> getMostViewCatalogPost(Long catalog, int nums);

    /**
     * 获取被置顶的文章
     * 
     * @param nums
     *            要获取的数量
     * @return
     */
    List<Post> getTopPosts(int nums);

    /**
     * 更新文章评论数量
     * 
     * @param postId
     */
    void updatePostCommentCount(Long postId);

    /**
     * 更新文章点击
     * 
     * @param postId
     */
    void updatePostViewCount(Long postId);

    /**
     * 获取随机文章
     * 
     * @param nums
     *            获取的文章个数
     * @return
     */
    List<Post> getRandomPost(int nums);

    /**
     * 获取相关文章
     * 
     * @param nums
     * @return
     */
    List<Post> getRelatedPost(Post post, int nums);

    /**
     * 查询所有
     * 
     * @return
     */
    List<Post> findAll();

    /**
     * 根据评论编号,获取该评论对应的文章的URL
     * 
     * @param commentId
     * @return
     */
    String getPostUrlByCommentId(Long commentId);

    /**
     * 根据评论编号获取该评论对应的文章
     * 
     * @param commentId
     * @return
     */
    Post getPostByComment(Long commentId);

    /**
     * 判断是否有文章的访问权限
     * 
     * @param postId
     * @param password
     * @return
     */
    boolean hasPermisstion(Long postId, String password);
    
    /**
     * 移动文章分类
     * @param fromCatalog
     * @param toCatalog
     */
    void movePostCatalog(Long fromCatalog, Long toCatalog);
}
