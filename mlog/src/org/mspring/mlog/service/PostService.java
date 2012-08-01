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
    Post createPost(Post post);

    void deletePost(Long... idArray);

    Page<Post> findPost(Page<Post> page, QueryCriterion queryCriterion);

    Page<Post> findPost(Page<Post> page, String queryString);

    Page<Post> findPost(Page<Post> page, String queryString, Object... queryParams);

    Post getPostById(Long postId);

    Post getPostByTitle(String title);

    void updatePost(Post post);

    /**
     * 最新发表文章
     * 
     * @param nums
     * @return
     */
    List<Post> getRecentPost(int nums);
}
