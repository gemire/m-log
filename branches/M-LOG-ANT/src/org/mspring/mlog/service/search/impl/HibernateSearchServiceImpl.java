/**
 * 
 */
package org.mspring.mlog.service.search.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.mspring.mlog.service.search.HibernateSearchService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-1-9
 * @Description
 * @TODO
 */
@Service
@Transactional
public class HibernateSearchServiceImpl extends AbstractServiceSupport implements HibernateSearchService {
    private static final Logger log = Logger.getLogger(HibernateSearchServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.HibernateSearchService#getQueryBuilder
     * (java.lang.Class)
     */
    @Override
    public QueryBuilder getQueryBuilder(Class clazz) {
        // TODO Auto-generated method stub
        return Search.getFullTextSession(getSession()).getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.HibernateSearchService#updateIndex(java
     * .lang .Class, java.io.Serializable)
     */
    @Override
    public void updateIndex(Class clazz, Serializable id) {
        // TODO Auto-generated method stub
        Session sessioin = getSession();
        Object object = getById(clazz, id);
        Search.getFullTextSession(getSession()).index(object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.HibernateSearchService#updateAllIndex
     * (java.lang.Class)
     */
    @Override
    public void updateAllIndex(Class clazz) {
        // TODO Auto-generated method stub
        MassIndexer indexer = Search.getFullTextSession(getSession()).createIndexer(clazz);
        indexer.batchSizeToLoadObjects(100);
        indexer.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.HibernateSearchService#count(org.apache
     * .lucene.search.Query, java.lang.Class[])
     */
    @Override
    public Long count(Query query, Class... clazz) {
        // TODO Auto-generated method stub
        int size = Search.getFullTextSession(getSession()).createFullTextQuery(query, clazz).getResultSize();
        return new Long(size);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.HibernateSearchService#searchPage(org
     * .mspring.platform.persistence.support.Page,
     * org.apache.lucene.search.Query, java.lang.Class[])
     */
    @Override
    public Page searchPage(Page page, Query query, Class... clazz) {
        // TODO Auto-generated method stub
        FullTextQuery fullTextQuery = Search.getFullTextSession(getSession()).createFullTextQuery(query, clazz);

        if (page.isAutoCount()) {
            page.setTotalCount(fullTextQuery.getResultSize());
            page.setAutoCount(false);
        }

        fullTextQuery.setFirstResult(page.getFirst() - 1);
        fullTextQuery.setMaxResults(page.getPageSize());
        List list = fullTextQuery.list();
        page.setResult(list);
        return page;
    }

}
