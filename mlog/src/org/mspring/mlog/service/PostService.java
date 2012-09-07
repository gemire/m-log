/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;
import java.util.Map;

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
     * 根据编号获取
     * 
     * @param postId
     * @return
     */
    Post getPostById(Long postId);

    /**
     * 根据标题获取
     * 
     * @param title
     * @return
     */
    Post getPostByTitle(String title);

    /**
     * 根据URL获取
     * 
     * @param url
     * @return
     */
    Post getPostByUrl(String url);

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
     * 最高点击率文章
     * 
     * @param nums
     * @return
     */
    List<Post> getMostViewPost(int nums);

    /**
     * 验证文章标题是否存在
     * 
     * @param title
     * @param postId
     * @return
     */
    boolean titleExists(String title, Long postId);

    /**
     * 验证URL是否存在
     * 
     * @param url
     * @return
     */
    boolean urlExists(String url);

    /**
     * 验证URL是否存在
     * 
     * @param url
     * @param postId
     * @return
     */
    boolean urlExists(String url, Long postId);

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
     * @return
     */
    List<Post> findAll();
}
