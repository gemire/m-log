/**
 * 
 */
package org.mspring.platform.persistence.hibernate.service;

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
 * @since Jun 10, 2012
 */
public class AbstractHibernateService extends HibernateDaoSupport {
    private static final Logger log = Logger.getLogger(AbstractHibernateService.class);

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

    public <PK extends Serializable> Object get(Class clazz, PK paramPK) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().get(clazz, paramPK);
    }

    public <PK extends Serializable> Object get(String entityName, PK paramPK) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().get(entityName, paramPK);
    }

    public Serializable save(Object object) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().save(object);
    }

    public void update(Object object) {
        // TODO Auto-generated method stub
        this.getDefaultHibernateTemplate().update(object);
    }

    public void executeUpdate(String queryString) {
        // TODO Auto-generated method stub
        executeUpdate(queryString, null);
    }

    public void executeUpdate(String queryString, Object... value) {
        // TODO Auto-generated method stub
        Query query = getSession().createQuery(queryString);
        setParametersToQuery(query, value);
        query.executeUpdate();
    }

    public void delete(Object object) {
        // TODO Auto-generated method stub
        this.getDefaultHibernateTemplate().delete(object);
    }

    public <PK extends Serializable> void delete(Class clazz, PK... paramPK) {
        // TODO Auto-generated method stub
        if (paramPK != null && paramPK.length > 0) {
            for (int i = 0; i < paramPK.length; i++) {
                Object entity = get(clazz, paramPK[i]);
                this.getDefaultHibernateTemplate().delete(entity);
            }
        }
    }

    public <PK extends Serializable> void delete(String entityName, PK... paramPK) {
        // TODO Auto-generated method stub
        if (paramPK != null && paramPK.length > 0) {
            for (int i = 0; i < paramPK.length; i++) {
                Object entity = get(entityName, paramPK[i]);
                this.getDefaultHibernateTemplate().delete(entity);
            }
        }
    }

    public Object findUnique(String queryString) {
        // TODO Auto-generated method stub
        return findUnique(queryString, null);
    }

    public Object findUnique(String queryString, Object value) {
        // TODO Auto-generated method stub
        return findUnique(queryString, new Object[] { value });
    }

    public Object findUnique(String queryString, Object[] values) {
        // TODO Auto-generated method stub
        Query query = this.getSession().createQuery(queryString);
        if (values != null && values.length > 0) {
            setParametersToQuery(query, values);
        }
        Object object = null;
        try {
            List list = query.list();
            if (list != null && list.size() > 0)
                object = list.get(0);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
        }
        return object;
    }

    public Page findPage(Page page, QueryCriterion queryCriterion) {
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

    public Page findPage(Page page, String queryString) {
        // TODO Auto-generated method stub
        return findPage(page, queryString, null);
    }

    public Page findPage(Page page, String queryString, Object[] values) {
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

    public List find(QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        String queryString = queryCriterion.getQueryString();
        Query queryObject = this.getSession().createQuery(queryString);
        applyQueryCriteriaToQuery(queryObject, queryCriterion);
        return queryObject.list();
    }

    public List find(String queryString) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().find(queryString);
    }

    public List find(String queryString, Object values) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().find(queryString, values);
    }

    public List find(String queryString, Object[] values) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().find(queryString, values);
    }

    public long count(QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        String countString = queryCriterion.getCountString();
        countString = applyCount(countString);
        Query countQuery = this.getSession().createQuery(countString);
        applyQueryCriteriaToQuery(countQuery, queryCriterion);
        return new Long(countQuery.uniqueResult().toString());
    }

    public Long count(String queryString, Object... values) {
        // TODO Auto-generated method stub
        Query query = getCountQuery(queryString, values);
        List list = query.list();
        if (list != null && list.size() > 0) {
            return (Long) list.get(0);
        }
        return new Long(0);
    }

    public List findAll(Class clazz) {
        // TODO Auto-generated method stub
        String entityName = clazz.getSimpleName();
        String entityAlias = StringUtils.uncapitalize(entityName);
        String queryString = "from " + entityName + " as " + entityAlias;
        return this.find(queryString);
    }

    public List findAll(String entityName) {
        // TODO Auto-generated method stub
        String entityAlias = StringUtils.uncapitalize(entityName);
        String queryString = "from " + entityName + " as " + entityAlias;
        return this.find(queryString);
    }

    public Object merge(Object object) {
        // TODO Auto-generated method stub
        return this.getDefaultHibernateTemplate().merge(object);
    }

    public void saveOrUpdate(Object object) {
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

    private void applyPage(Query query, Page page) {
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

    private void applyNamedParameterToQuery(Query queryObject, String paramName, Object value) {
        if (value instanceof Collection) {
            queryObject.setParameterList(paramName, (Collection) value);
        } else if (value instanceof Object[]) {
            queryObject.setParameterList(paramName, (Object[]) (Object[]) value);
        } else {
            queryObject.setParameter(paramName, value);
        }
    }
}
