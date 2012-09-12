/**
 * 
 */
package org.mspring.mlog.service.search;

import org.mspring.mlog.entity.Post;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-7-30
 * @Description
 * @TODO
 */
public interface PostSearchService {
    Page<Post> search(Page<Post> page, String queryString);

    void rebuildPostIndex(Long postId);

    void rebuildAllPostIndex();
}
