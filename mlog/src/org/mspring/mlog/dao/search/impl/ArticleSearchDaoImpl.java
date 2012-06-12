/**
 * 
 */
package org.mspring.mlog.dao.search.impl;

import org.apache.lucene.search.Query;
import org.mspring.mlog.dao.search.ArticleSearchDao;
import org.mspring.mlog.entity.Article;
import org.mspring.platform.persistence.search.AbstractSearchDao;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since Apr 2, 2012
 */
@Repository
public class ArticleSearchDaoImpl extends AbstractSearchDao<Article> implements ArticleSearchDao {
    /**
     * @param indexedClass
     */
    protected ArticleSearchDaoImpl() {
        super(Article.class);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.dao.search.ArticleSearchDao#search(org.mspring.platform.dao.support.Page,
     *      java.lang.String)
     */
    @Override
    public Page<Article> search(Page<Article> page, String queryString) {
        // TODO Auto-generated method stub
        String[] fields = new String[] { "title", "intro", "content", "categories.name", "tags.name"};
        Query query = getQueryBuilder().keyword().onFields(fields).matching(queryString).createQuery();
        return searchPage(page, query);
    }
}
