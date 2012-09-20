/**
 * 
 */
package org.mspring.platform.persistence.search;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.mspring.platform.persistence.support.Page;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Gao Youbo
 * @since 2012-9-20
 * @Description
 * @TODO
 */
public class BaseHibernateSearchDao extends HibernateDaoSupport {
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * Create a HibernateTemplate for the given SessionFactory. Only invoked if
     * populating the DAO with a SessionFactory reference! with different
     * configuration, or a custom HibernateTemplate subclass.
     * 
     * @param sessionFactory
     *            the Hibernate SessionFactory to create a HibernateTemplate for
     * @return the new DefaultHibernateTemplate instance
     * @see org.springframework.orm.hibernate3.support#createHibernateTemplate
     * @see org.springframework.orm.hibernate3.support#setSessionFactory
     */
    protected final HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        return new DefaultHibernateSearchTemplate(sessionFactory);
    }

    /**
     * Return the DefaultHibernateTemplate for this DAO, pre-initialized with
     * the SessionFactory or set explicitly.
     */
    public final DefaultHibernateSearchTemplate getDefaultHibernateSearchTemplate() {
        return (DefaultHibernateSearchTemplate) getHibernateTemplate();
    }

    public QueryBuilder getQueryBuilder(Class clazz) {
        return getDefaultHibernateSearchTemplate().getQueryBuilder(clazz);
    }

    public void updateIndex(Class clazz, Serializable id) {
        getDefaultHibernateSearchTemplate().updateIndex(clazz, id);
    }

    public void updateAllIndex(Class clazz) {
        getDefaultHibernateSearchTemplate().updateAllIndex(clazz);
    }

    public Page searchPage(Page page, Query query, Class... clazz) {
        return getDefaultHibernateSearchTemplate().searchPage(page, query, clazz);
    }
}
