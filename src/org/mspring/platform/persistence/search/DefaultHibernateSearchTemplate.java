/**
 * 
 */
package org.mspring.platform.persistence.search;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.mspring.platform.persistence.hibernate.DefaultHibernateTemplate;
import org.mspring.platform.persistence.support.Page;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @author Gao Youbo
 * @since 2012-9-20
 * @Description
 * @TODO
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultHibernateSearchTemplate extends DefaultHibernateTemplate {
    public DefaultHibernateSearchTemplate(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public QueryBuilder getQueryBuilder(final Class clazz) {
        return execute(new HibernateCallback<QueryBuilder>() {
            @Override
            public QueryBuilder doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                return Search.getFullTextSession(session).getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
            }
        });
    }

    public void updateIndex(final Class clazz, final Serializable id) {
        execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Object object = session.get(clazz, id);
                Search.getFullTextSession(session).index(object);
                return null;
            }
        });
    }

    public void updateAllIndex(final Class clazz) {
        execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                FullTextSession fullTextSession = Search.getFullTextSession(session);
                List<Object> list = session.createQuery("from " + clazz.getName()).list();
                for (Object object : list) {
                    fullTextSession.index(object);
                }
                return null;
            }
        });
    }

    public Long count(final Query query, final Class... clazz) {
        return execute(new HibernateCallback<Long>() {

            @Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                int size = Search.getFullTextSession(session).createFullTextQuery(query, clazz).getResultSize();
                return new Long(size);
            }
        });
    }

    public Page searchPage(final Page page, final Query query, final Class... clazz) {
        return execute(new HibernateCallback<Page>() {

            @Override
            public Page doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                FullTextQuery fullTextQuery = Search.getFullTextSession(session).createFullTextQuery(query, clazz);

                if (page.isAutoCount()) {
                    page.setTotalCount(fullTextQuery.getResultSize());
                    page.setAutoCount(false);
                }

                fullTextQuery.setFirstResult(page.getFirst() - 1);
                fullTextQuery.setMaxResults(page.getPageSize());
                return page.setResult(fullTextQuery.list());
            }
        });
    }
}
