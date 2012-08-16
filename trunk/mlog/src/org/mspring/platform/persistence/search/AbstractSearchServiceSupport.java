package org.mspring.platform.persistence.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.support.Page;

public class AbstractSearchServiceSupport extends AbstractServiceSupport {

    protected FullTextSession getFullTextSession() {
        return Search.getFullTextSession(getHibernateTemplate().getSessionFactory().openSession());
    }

    protected SearchFactory getSearchFactory() {
        return getFullTextSession().getSearchFactory();
    }

    protected QueryBuilder getQueryBuilder(Class clazz) {
        return getFullTextSession().getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
    }

    public Integer count(Query query, Class... clazz) {
        // TODO Auto-generated method stub
        FullTextQuery fullTextQuery = getFullTextSession().createFullTextQuery(query, clazz);
        return Integer.valueOf(fullTextQuery.getResultSize());
    }

    public void index(Object paramT) {
        // TODO Auto-generated method stub
        FullTextSession fullTextSession = getFullTextSession();
        fullTextSession.index(paramT);
    }

    public Page searchPage(Page page, Query query, Class... clazz) {
        // TODO Auto-generated method stub
        FullTextQuery fullTextQuery = getFullTextSession().createFullTextQuery(query, clazz);

        if (page.isAutoCount()) {
            page.setTotalCount(fullTextQuery.getResultSize());
            page.setAutoCount(false);
        }

        fullTextQuery.setFirstResult(page.getFirst() - 1);
        fullTextQuery.setMaxResults(page.getPageSize());
        return page.setResult(fullTextQuery.list());
    }
}