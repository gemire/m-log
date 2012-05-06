package org.mspring.platform.dao.support;

import javax.annotation.Resource;

import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;

public abstract class AbstractSearchDao<T> {
    private Class<T> indexedClass;

    protected AbstractSearchDao(Class<T> indexedClass) {
        this.indexedClass = indexedClass;
    }

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected FullTextSession getFullTextSession() {
        return Search.getFullTextSession(sessionFactory.openSession());
    }

    protected SearchFactory getSearchFactory() {
        return getFullTextSession().getSearchFactory();
    }

    protected QueryBuilder getQueryBuilder() {
        return getFullTextSession().getSearchFactory().buildQueryBuilder().forEntity(this.indexedClass).get();
    }

    protected Page<T> searchPage(Page<T> page, Query query) {
        // long start = System.currentTimeMillis();
        // getFullTextSession().createIndexer(indexedClass).start();
        // long end = System.currentTimeMillis();
        // System.out.println(end - start);

        FullTextQuery fullTextQuery = getFullTextSession().createFullTextQuery(query, new Class[] { this.indexedClass });

        if (!page.isAutoCount()) {
            page.setTotalCount(fullTextQuery.getResultSize());
            page.setAutoCount(false);
        }

        fullTextQuery.setFirstResult(page.getFirst() - 1);
        fullTextQuery.setMaxResults(page.getPageSize());
        return page.setResult(fullTextQuery.list());
    }

    protected Integer count(Query query) {
        FullTextQuery fullTextQuery = getFullTextSession().createFullTextQuery(query, new Class[] { this.indexedClass });
        return Integer.valueOf(fullTextQuery.getResultSize());
    }
}