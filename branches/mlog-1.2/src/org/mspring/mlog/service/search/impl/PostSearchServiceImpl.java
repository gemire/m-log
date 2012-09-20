/**
 * 
 */
package org.mspring.mlog.service.search.impl;

import org.apache.lucene.search.Query;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.search.PostSearchService;
import org.mspring.platform.persistence.search.BaseHibernateSearchDao;
import org.mspring.platform.persistence.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-7-30
 * @Description
 * @TODO
 */
@Service
public class PostSearchServiceImpl extends BaseHibernateSearchDao implements PostSearchService {

    @Autowired
    private PostService postService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.PostSearchService#search(org.mspring.
     * platform.persistence.support.Page, java.lang.String)
     */
    @Override
    public Page<Post> search(Page<Post> page, String queryString) {
        // TODO Auto-generated method stub
        String[] fields = new String[] { "title", "summary", "content" };
        Query query = getQueryBuilder(Post.class).keyword().onFields(fields).matching(queryString).createQuery();
        return searchPage(page, query, new Class[] { Post.class });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.PostSearchService#rebuildPostIndex(java
     * .lang.Long)
     */
    @Override
    public void rebuildPostIndex(Long postId) {
        // TODO Auto-generated method stub
        updateIndex(Post.class, postId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.PostSearchService#rebuildAllPostIndex()
     */
    @Override
    public void rebuildAllPostIndex() {
        // TODO Auto-generated method stub
        updateAllIndex(Post.class);
    }

}
