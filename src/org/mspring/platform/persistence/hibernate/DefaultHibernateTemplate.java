/**
 * 
 */
package org.mspring.platform.persistence.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.Assert;
import org.mspring.platform.utils.BeanUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Gao Youbo
 * @since 2012-9-11
 * @Description
 * @TODO
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultHibernateTemplate extends HibernateTemplate {
    private static final Logger log = Logger.getLogger(DefaultHibernateTemplate.class);

    public DefaultHibernateTemplate(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // -------------------------------------------------------------------------
    // Convenience methods for executeUpdate
    // -------------------------------------------------------------------------

    public int executeUpdate(final String queryString) throws DataAccessException {
        return ((Integer) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                return new Integer(queryObject.executeUpdate());
            }
        })).intValue();
    }

    public int executeUpdate(final String queryString, final Object... values) throws DataAccessException {
        return ((Integer) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                setParametersToQuery(queryObject, values);
                return new Integer(queryObject.executeUpdate());
            }
        })).intValue();
    }

    public int executeUpdate(final String queryString, final String[] paramNames, final Object[] values) throws DataAccessException {
        Assert.isTrue(paramNames.length == values.length, "Length of paramNames array must match length of values array");
        return ((Integer) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        queryObject.setParameter(paramNames[i], values[i]);
                    }
                }
                return new Integer(queryObject.executeUpdate());
            }
        })).intValue();
    }

    // -------------------------------------------------------------------------
    // Convenience methods for remove/load Object
    // -------------------------------------------------------------------------

    public Object loadById(Class entityClass, Serializable id) throws DataAccessException {
        return id != null ? super.load(entityClass, id) : null;
    }

    public List loadById(final Class entityClass, final Serializable[] ids) throws DataAccessException {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createCriteria(entityClass).add(Restrictions.in(session.getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName(), ids)).list();
            }
        });
    }

    public List loadById(final Class entityClass, final List ids) throws DataAccessException {
        if (ids == null || ids.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createCriteria(entityClass).add(Restrictions.in(session.getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName(), ids)).list();
            }
        });
    }

    public void remove(Class entityClass, Serializable id) throws DataAccessException {
        if (id == null) {
            return;
        }
        Object entity = loadById(entityClass, id);
        if (null != entity) {
            super.delete(entity);
        }
    }

    public void remove(final Class entityClass, final Serializable[] ids) throws DataAccessException {
        execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                // TODO switch to Bulk DELETE
                if (ids != null && ids.length > 0) {
                    for (int i = 0; i < ids.length; i++) {
                        remove(entityClass, ids[i]);
                    }
                }
                return null;
            }
        });
    }

    public void updateSingleProperties(final Object entity) {
        // TODO
        execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Class entityClass = entity.getClass();
                ClassMetadata classMeta = session.getSessionFactory().getClassMetadata(entityClass);
                Serializable id = (Serializable) BeanUtils.getSimpleProperty(entity, classMeta.getIdentifierPropertyName());
                Object persist = loadById(entityClass, id);
                BeanUtils.copySingleProperties(persist, entity);
                return null;
            }
        });
    }

    // -------------------------------------------------------------------------
    // Convenience finder methods for persistent Class
    // -------------------------------------------------------------------------

    public List findAllSorted(final Class entityClass, final String sortField) throws DataAccessException {
        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(entityClass);
                prepareCriteria(criteria);
                criteria.addOrder(Order.asc(sortField));
                return criteria.list();
            }
        });
    }

    public List findAllSorted(final Class entityClass, final Sort sort) throws DataAccessException {
        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(entityClass);
                prepareCriteria(criteria);
                if (sort != null) {
                    criteria.addOrder(Sort.DESC.equals(sort.getOrder()) ? Order.desc(sort.getField()) : Order.asc(sort.getField()));
                }
                return criteria.list();
            }
        });
    }

    public Page findPage(final Class entityClass, final Page page) throws DataAccessException {
        return (Page) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String shortClassName = StringUtils.uncapitalize(ClassUtils.getShortClassName(entityClass));
                String queryString = applyPaginationSorter(page, "from " + entityClass.getName() + " " + shortClassName);

                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                if (page.isAutoCount()) {
                    applyPagination(page, queryObject, count(applyCountPrefix(queryString), null));
                    page.setAutoCount(false);
                }
                else {
                    applyPagination(page, queryObject);
                }
                page.setResult(queryObject.list());
                return page;
            }
        });
    }

    // -------------------------------------------------------------------------
    // Convenience finder methods for HQL strings
    // -------------------------------------------------------------------------
    public Page findPage(final String queryString, final Page page) throws DataAccessException {
//        return (Page) execute(new HibernateCallback() {
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                String queryStringValue = applyPaginationSorter(page, queryString);
//                Query queryObject = session.createQuery(queryStringValue);
//                prepareQuery(queryObject);
//
//                if (page.isAutoCount()) {
//                    applyPagination(page, queryObject, count(applyCountPrefix(queryString), null));
//                    page.setAutoCount(false);
//                }
//                else {
//                    applyPagination(page, queryObject);
//                }
//                page.setResult(queryObject.list());
//                return page;
//            }
//        });
        return findPage(queryString, page, null);
    }

    public Page findPage(final String queryString, final Page page, final Object... values) throws DataAccessException {
        return (Page) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String queryStringValue = applyPaginationSorter(page, queryString);

                Query queryObject = session.createQuery(queryStringValue);
                prepareQuery(queryObject);
                
                setParametersToQuery(queryObject, values);
                
                if (page.isAutoCount()) {
                    applyPagination(page, queryObject, count(applyCountPrefix(queryString), values));
                    page.setAutoCount(false);
                }
                else {
                    applyPagination(page, queryObject);
                }
                page.setResult(queryObject.list());
                return page;
            }
        });
    }

    public Page findPage(final QueryCriterion queryCriterion, final Page page) throws DataAccessException {
        Assert.notNull(queryCriterion);
        return (Page) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query queryObject = session.createQuery(applyPaginationSorter(page, queryCriterion.getQueryString()));
                Query countObject = session.createQuery(queryCriterion.getCountString());
                applyQueryCriteriaToQuery(queryObject, queryCriterion);
                applyQueryCriteriaToQuery(countObject, queryCriterion);

                // applyPagination(ps, queryObject, ((Integer)
                // countObject.iterate().next()).intValue());
                // change from Integer to Long
                
                if (page.isAutoCount()) {
                    applyPagination(page, queryObject, ((Long) countObject.iterate().next()).intValue());
                    page.setAutoCount(false);
                }
                else {
                    applyPagination(page, queryObject);
                }
                page.setResult(queryObject.list());
                return page;
            }
        });
    }

    public List find(final QueryCriterion queryCriterion) throws DataAccessException {
        Assert.notNull(queryCriterion);
        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query queryObject = session.createQuery(queryCriterion.getQueryString());
                applyQueryCriteriaToQuery(queryObject, queryCriterion);
                return queryObject.list();
            }
        });
    }
    
    public Object findUnique(String queryString) {
        // TODO Auto-generated method stub
        return findUnique(queryString, null);
    }

    public Object findUnique(final String queryString, final Object... values) {
        // TODO Auto-generated method stub
        List list = find(queryString, values);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values, final Page page) throws DataAccessException {
        Assert.isTrue(paramNames.length == values.length, "Length of paramNames array must match length of values array");
        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStringValue = applyPaginationSorter(page, queryString);
                Query queryObject = session.createQuery(queryStringValue);
                prepareQuery(queryObject);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
                    }
                }

                applyPagination(page, queryObject);
                return queryObject.list();
            }
        });
    }

    // -------------------------------------------------------------------------
    // Convenience finder methods for named queries
    // -------------------------------------------------------------------------

    public List findByNamedQuery(final String queryName, final Object[] values, final Page page) {
        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryString = applyPaginationSorter(page, session.getNamedQuery(queryName).getQueryString());
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);

                setParametersToQuery(queryObject, values);
                applyPagination(page, queryObject);
                return queryObject.list();
            }
        });
    }

    // -------------------------------------------------------------------------
    // Convenience query methods for iteration
    // -------------------------------------------------------------------------

    public Iterator iterateByNamedQuery(final String queryName, final Object[] values) throws DataAccessException {
        return (Iterator) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.getNamedQuery(queryName);
                prepareQuery(queryObject);
                setParametersToQuery(queryObject, values);
                return queryObject.iterate();
            }
        });
    }

    // -------------------------------------------------------------------------
    // Convenience counter methods for HQL strings
    // -------------------------------------------------------------------------

    public int countByNamedParam(final String queryString, final String[] paramNames, final Object[] values) throws DataAccessException {
        Assert.isTrue(paramNames.length == values.length, "Length of paramNames array must match length of values array");

        // change from Integer to Long
        return ((Long) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
                    }
                }
                return (Long) queryObject.iterate().next();
            }
        })).intValue();
    }

    public int countByNamedQuery(final String queryName, final Object[] values) throws DataAccessException {
        // change from Integer to Long
        return ((Long) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.getNamedQuery(queryName);
                prepareQuery(queryObject);
                setParametersToQuery(queryObject, values);
                return (Long) queryObject.iterate().next();
            }
        })).intValue();
    }

    public int count(final String queryString) throws DataAccessException {
        // change from Integer to Long
        return ((Long) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                return (Long) queryObject.iterate().next();
            }
        })).intValue();
    }

    public int count(final String queryString, final Object[] values) throws DataAccessException {
        // change from Integer to Long
        return ((Long) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                setParametersToQuery(queryObject, values);
                return (Long) queryObject.iterate().next();
            }
        })).intValue();
    }

    public int count(final QueryCriterion queryCriterion) throws DataAccessException {
        Assert.notNull(queryCriterion);
        // change from Integer to Long
        return ((Long) execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query queryObject = session.createQuery(queryCriterion.getCountString());
                applyQueryCriteriaToQuery(queryObject, queryCriterion);
                return (Long) queryObject.iterate().next();
            }
        })).intValue();
    }

    // -------------------------------------------------------------------------
    // miscellaneous methods
    // -------------------------------------------------------------------------

    protected String applySorter(String queryString, Sort sort) {
        if (null == sort || StringUtils.isBlank(sort.getField())) {
            return queryString;
        }
        if (queryString.toLowerCase().indexOf("order by") > -1) {
            // TODO queryString 中含有 order by using regular pattern, just return
            return queryString;
        }
        else {
            return new StringBuffer(queryString).append(" order by ").append(sort.getField()).append(" ").append(sort.getOrder()).toString();
        }
    }

    protected String applyPaginationSorter(Page page, String queryString) {
        if (null != page && page.isSortEnable()) {
            Sort sort = page.getSort();
            queryString = applySorter(queryString, sort);
        }

        if (log.isDebugEnabled()) {
            log.debug("after applySorter queryString=" + queryString);
        }
        return queryString;
    }
    
    protected void setParametersToQuery(Query query, Object... values) {
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
    }

    private String applyCountPrefix(String queryString) {
        int start = queryString.indexOf("from");
        return "select count(*) " + queryString.substring(start, queryString.length());
    }

    private void applyPagination(Page page, Query queryObject) {
        applyPagination(page, queryObject, -1);
    }

    private void applyPagination(Page page, Query queryObject, int totalCount) {
        if (page == null) return;
        Assert.notNull(queryObject);

        if (log.isDebugEnabled()) {
            log.debug(page);
        }

        if (totalCount > -1) {
            // if totalCount = -1, totalCount will be set explicitly
            page.setTotalCount(totalCount);
        }
        queryObject.setFirstResult(page.getFirst() - 1);
        queryObject.setMaxResults(page.getPageSize());
    }

    private void applyQueryCriteriaToQuery(Query queryObject, QueryCriterion queryCriterion) {
        if (log.isDebugEnabled()) {
            log.debug(queryCriterion);
        }
        prepareQuery(queryObject);

        // using namedParam query
        Map nameValuePairs = queryCriterion.getQueryParams();
        if (log.isDebugEnabled()) {
            log.debug("nameValuePairs=" + nameValuePairs);
        }

        if (null != nameValuePairs && !nameValuePairs.isEmpty()) {
            for (Iterator it = nameValuePairs.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                applyNamedParameterToQuery(queryObject, key, nameValuePairs.get(key));
            }
        }
    }
}
