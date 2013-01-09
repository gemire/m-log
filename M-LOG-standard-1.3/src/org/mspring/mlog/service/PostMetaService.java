/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.PostMeta;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO
 */
public interface PostMetaService {
    public void insertPostMeta(Long postId, String key, String value);

    public List<PostMeta> getPostMeta(Long postId, String key);
}
