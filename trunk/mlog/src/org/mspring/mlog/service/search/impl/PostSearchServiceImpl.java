/**
 * 
 */
package org.mspring.mlog.service.search.impl;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.search.PostSearchService;
import org.mspring.platform.persistence.search.AbstractSearchServiceSupport;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-30
 * @Description
 * @TODO
 */
@Service
@Transactional
public class PostSearchServiceImpl extends AbstractSearchServiceSupport implements PostSearchService {

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
        return searchPage(page, query, new Class[] { Post.class, Catalog.class, User.class });
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
    }

}
