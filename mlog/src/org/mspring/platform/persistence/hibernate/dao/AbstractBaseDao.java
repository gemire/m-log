/**
 * 
 */
package org.mspring.platform.persistence.hibernate.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.mspring.platform.persistence.hibernate.DefaultHibernateTemplate;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
public abstract class AbstractBaseDao<T> extends HibernateDaoSupport implements BaseDao<T> {
    private static final Logger log = Logger.getLogger(AbstractBaseDao.class);
    private Class<T> entityClass;

    protected AbstractBaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    @Override
    protected final HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        // TODO Auto-generated method stub
        return new DefaultHibernateTemplate(sessionFactory);
    }

    public final DefaultHibernateTemplate getDefaultHibernateTemplate() {
        return (DefaultHibernateTemplate) getHibernateTemplate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#get(java.io.Serializable)
     */
    @Override
    public <PK extends Serializable> T get(PK paramPK) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().get(this.entityClass, paramPK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#save(java.lang.Object)
     */
    @Override
    public Serializable save(T paramT) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().save(paramT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#update(java.lang.Object)
     */
    @Override
    public void update(T paramT) {
        // TODO Auto-generated method stub
        this.getDefaultHibernateTemplate().update(paramT);
    }

    @Override
    public void executeUpdate(String queryString) {
        // TODO Auto-generated method stub
        executeUpdate(queryString, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#executeUpdate(java.lang.Object,
     *      java.lang.Object[])
     */
    @Override
    public void executeUpdate(String queryString, Object... value) {
        // TODO Auto-generated method stub
        Query query = getSession().createQuery(queryString);
        setParametersToQuery(query, value);
        query.executeUpdate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#delete(java.lang.Object)
     */
    @Override
    public void delete(T paramT) {
        // TODO Auto-generated method stub
        this.getDefaultHibernateTemplate().delete(paramT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#delete(PK[])
     */
    @Override
    public <PK extends Serializable> void delete(PK... paramPK) {
        // TODO Auto-generated method stub
        if (paramPK != null && paramPK.length > 0) {
            for (int i = 0; i < paramPK.length; i++) {
                T entity = get(paramPK[i]);
                this.getDefaultHibernateTemplate().delete(entity);
            }
        }
    }

    @Override
    public T findUnique(String queryString) {
        // TODO Auto-generated method stub
        return findUnique(queryString, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#findUnique(java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public T findUnique(String queryString, Object value) {
        // TODO Auto-generated method stub
        return findUnique(queryString, new Object[] { value });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#findUnique(java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public T findUnique(String queryString, Object[] values) {
        // TODO Auto-generated method stub
        Query query = this.getSession().createQuery(queryString);
        if (values != null && values.length > 0) {
            setParametersToQuery(query, values);
        }
        T t = null;
        try {
            List<T> list = query.list();
            if (list != null && list.size() > 0)
                t = list.get(0);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
        }
        return t;
    }

    @Override
    public Page<T> findPage(Page<T> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        if (page.isAutoCount()) {
            page.setTotalCount(count(queryCriterion));
            page.setAutoCount(false);
        }
        String queryString = queryCriterion.getQueryString();
        if (page.isSortEnable() && page.getSort() != null) {
            queryString = applySort(queryString, page.getSort());
        }
        Query queryObject = this.getSession().createQuery(queryString);
        applyQueryCriteriaToQuery(queryObject, queryCriterion);
        applyPage(queryObject, page);
        return page.setResult(queryObject.list());
    }

    @Override
    public Page<T> findPage(Page<T> page, String queryString) {
        // TODO Auto-generated method stub
        return findPage(page, queryString, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#findPage(org.mspring.platform.dao.support
     *      .Page, java.lang.String, java.lang.Object[])
     */
    @Override
    public Page<T> findPage(Page<T> page, String queryString, Object[] values) {
        // TODO Auto-generated method stub
        if (page.isAutoCount()) {
            Long totalCount = count(queryString, values);
            page.setTotalCount(totalCount.longValue());
            page.setAutoCount(false);
        }

        if (page.isSortEnable() && page.getSort() != null) {
            queryString = applySort(queryString, page.getSort());
        }

        Query query = this.getSession().createQuery(queryString);
        if (values != null && values.length > 0) {
            setParametersToQuery(query, values);
        }
        applyPage(query, page);
        return page.setResult(query.list());
    }

    @Override
    public List<T> find(QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        String queryString = queryCriterion.getQueryString();
        Query queryObject = this.getSession().createQuery(queryString);
        applyQueryCriteriaToQuery(queryObject, queryCriterion);
        return queryObject.list();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#find(java.lang.String)
     */
    @Override
    public List<T> find(String queryString) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().find(queryString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#find(java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public List<T> find(String queryString, Object values) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().find(queryString, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#find(java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public List<T> find(String queryString, Object[] values) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().find(queryString, values);
    }

    @Override
    public long count(QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        String countString = queryCriterion.getCountString();
        countString = applyCount(countString);
        Query countQuery = this.getSession().createQuery(countString);
        applyQueryCriteriaToQuery(countQuery, queryCriterion);
        return new Long(countQuery.uniqueResult().toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#count(java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public Long count(String queryString, Object... values) {
        // TODO Auto-generated method stub
        Query query = getCountQuery(queryString, values);
        List list = query.list();
        if (list != null && list.size() > 0) {
            return (Long) list.get(0);
        }
        return new Long(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#findAll()
     */
    @Override
    public List<T> findAll() {
        // TODO Auto-generated method stub
        String entityName = this.entityClass.getSimpleName();
        String entityAlias = StringUtils.uncapitalize(entityName);
        String queryString = "from " + entityName + " as " + entityAlias;
        return this.find(queryString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.BaseDao#merge(java.lang.Object)
     */
    @Override
    public T merge(T object) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().merge(object);
    }

    @Override
    public void saveOrUpdate(T object) {
        // TODO Auto-generated method stub
        this.getDefaultHibernateTemplate().saveOrUpdate(object);
    }

    protected void setParametersToQuery(Query query, Object[] values) {
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
    }

    private Query getCountQuery(String queryString, Object[] values) {
        String countQueryStr = applyCount(queryString);
        Query query = this.getSession().createQuery(countQueryStr);
        if (values != null && values.length > 0) {
            setParametersToQuery(query, values);
        }
        return query;
    }

    private String applyCount(String queryString) {
        int start = queryString.indexOf("from");
        return "select count(*) " + queryString.substring(start, queryString.length());
    }

    private void applyPage(Query query, Page<T> page) {
        query.setFirstResult(page.getFirst() - 1);
        query.setMaxResults(page.getPageSize());
    }

    private String applySort(String queryString, Sort sort) {
        if (null == sort || StringUtils.isBlank(sort.getField())) {
            return queryString;
        }
        if (queryString.toLowerCase().indexOf("order by") > -1) {
            // TODO queryString 中含有 order by using regular pattern, just return
            return queryString;
        } else {
            return new StringBuffer(queryString).append(" order by ").append(sort.getField()).append(" ").append(sort.getOrder()).toString();
        }
    }

    private void applyQueryCriteriaToQuery(Query queryObject, QueryCriterion queryCriterion) {
        if (log.isDebugEnabled()) {
            log.debug(queryCriterion);
        }

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

    protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value) {
        if (value instanceof Collection) {
            queryObject.setParameterList(paramName, (Collection) value);
        } else if (value instanceof Object[]) {
            queryObject.setParameterList(paramName, (Object[]) (Object[]) value);
        } else {
            queryObject.setParameter(paramName, value);
        }
    }
}
