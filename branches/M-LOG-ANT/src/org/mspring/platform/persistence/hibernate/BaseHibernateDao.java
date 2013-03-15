/**
 * 
 */
package org.mspring.platform.persistence.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Gao Youbo
 * @since 2012-9-11
 * @Description
 * @TODO
 */
public class BaseHibernateDao extends HibernateDaoSupport {
    private static final Logger log = Logger.getLogger(BaseHibernateDao.class);
    
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
        return new DefaultHibernateTemplate(sessionFactory);
    }

    /**
     * Return the DefaultHibernateTemplate for this DAO, pre-initialized with
     * the SessionFactory or set explicitly.
     */
    public final DefaultHibernateTemplate getDefaultHibernateTemplate() {
        return (DefaultHibernateTemplate) getHibernateTemplate();
    }

    // -------------------------------------------------------------------------
    // Convenience methods for executeUpdate
    // -------------------------------------------------------------------------

    /**
     * Execute the update or delete statement
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @return The number of entities updated or deleted
     * @see org.hibernate.Query#executeUpdate
     */
    public int executeUpdate(String queryString) {
        return getDefaultHibernateTemplate().executeUpdate(queryString);
    }

    /**
     * Execute the update or delete statement
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param values
     *            the values of the parameter
     * @return The number of entities updated or deleted
     * @see org.hibernate.Query#executeUpdate
     */
    public int executeUpdate(String queryString, Object... values) {
        return getDefaultHibernateTemplate().executeUpdate(queryString, values);
    }

    /**
     * Execute the update or delete statement
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramName
     *            the name of the parameter
     * @param value
     *            the value of the parameter
     * @return The number of entities updated or deleted
     * @see org.hibernate.Query#executeUpdate
     */
    public int executeUpdate(String queryString, String paramName, Object value) {
        return executeUpdate(queryString, new String[] { paramName }, new Object[] { value });
    }

    /**
     * Execute the update or delete statement
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramNames
     *            the names of the parameters
     * @param values
     *            the values of the parameter
     * @return The number of entities updated or deleted
     * @see org.hibernate.Query#executeUpdate
     */
    public int executeUpdate(String queryString, String[] paramNames, Object[] values) {
        return getDefaultHibernateTemplate().executeUpdate(queryString, paramNames, values);
    }

    // -------------------------------------------------------------------------
    // Convenience methods for create/merge/update/remove/load Object
    // -------------------------------------------------------------------------

    /**
     * Persist the given transient instance.
     * 
     * @param entity
     *            the transient instance to persist
     * @return the generated identifier
     * @see org.hibernate.Session#save(Object)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#save(Object)
     */
    public Serializable create(Object entity) {
        return getHibernateTemplate().save(entity);
    }

    /**
     * Persist the given transient instance. Follows JSR-220 semantics.
     * <p>
     * Similar to <code>save</code>, associating the given object with the
     * current Hibernate Session.
     * 
     * @param entity
     *            the persistent instance to persist
     * @see org.hibernate.Session#persist(Object)
     */
    public void persist(Object entity) {
        getHibernateTemplate().persist(entity);
    }

    /**
     * Copy the state of the given object onto the persistent object with the
     * same identifier. Follows JSR-220 semantics.
     * 
     * @param entity
     *            the object to merge with the corresponding persistence
     *            instance
     * @return the updated, registered persistent instance
     * @see org.hibernate.Session#merge(Object)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#merge(java.lang.Object)
     * @see org.springframework.orm.hibernate3.support.IdTransferringMergeEventListener
     */
    public Object merge(Object entity) {
        return getHibernateTemplate().merge(entity);
    }

    /**
     * Update the given persistent instance, associating it with the current
     * Hibernate Session.
     * 
     * @param entity
     *            the persistent instance to update
     * @see org.hibernate.Session#saveOrUpdate(Object)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#saveOrUpdate(Object)
     */
    public void update(Object entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    public void updateSingleProperties(Object entity) {
        getDefaultHibernateTemplate().updateSingleProperties(entity);
    }

    public void updateSingleProperties(Object entity, String[] ignoreProperties) {
        // TODO
        getDefaultHibernateTemplate().updateSingleProperties(entity);
    }

    /**
     * Remove the given persistent instance.
     * 
     * @param entity
     *            the persistent instance to delete
     * @see org.hibernate.Session#delete(Object)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#delete(Object)
     */
    public void remove(Object entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * Remove the given persistent instance.
     * 
     * @param entityClass
     *            a persistent class
     * @param id
     *            an identifier of the persistent instance
     * @see org.hibernate.Session#delete(Object)
     */
    public void remove(Class entityClass, Serializable id) {
        getDefaultHibernateTemplate().remove(entityClass, id);
    }

    /**
     * Remove the given persistent instance.
     * 
     * @param entityClass
     *            a persistent class
     * @param ids
     *            identifiers of the persistent class
     */
    public void remove(Class entityClass, Serializable[] ids) {
        getDefaultHibernateTemplate().remove(entityClass, ids);
    }

    /**
     * Remove all given persistent instances.
     * 
     * @param entities
     *            the persistent instances to delete
     * @see org.hibernate.Session#delete(Object)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#deleteAll(Object)
     */
    public void removeAll(Collection entities) {
        getHibernateTemplate().deleteAll(entities);
    }

    /**
     * Return the persistent instance of the given entity class with the given
     * identifier, throwing an exception if not found.
     * 
     * @param entityClass
     *            a persistent class
     * @param id
     *            an identifier of the persistent instance
     * @return the persistent instance
     * @see org.hibernate.Session#load(Class, java.io.Serializable)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#load(Class,
     *      java.io.Serializable)
     */
    public Object loadById(Class entityClass, Serializable id) {
        return getDefaultHibernateTemplate().loadById(entityClass, id);
    }

    /**
     * Return all persistent instances of the given entity class with the given
     * identifiers, throwing an exception if not found.
     * 
     * @param entityClass
     *            a persistent class
     * @param ids
     *            identifiers of the persistent class
     * @return a List containing 0 or more persistent instances
     */
    public List loadById(Class entityClass, Serializable[] ids) {
        return getDefaultHibernateTemplate().loadById(entityClass, ids);
    }

    /**
     * Return all persistent instances of the given entity class with the given
     * identifiers, throwing an exception if not found.
     * 
     * @param entityClass
     *            a persistent class
     * @param ids
     *            identifiers of the persistent class
     * @return a List containing 0 or more persistent instances
     */
    public List loadById(Class entityClass, List ids) {
        return getDefaultHibernateTemplate().loadById(entityClass, ids);
    }

    /**
     * Return the persistent instance of the given entity class with the given
     * identifier, or null if not found.
     * 
     * @param entityClass
     *            a persistent class
     * @param id
     *            an identifier of the persistent instance
     * @return the persistent instance, or null if not found
     * @see org.hibernate.Session#get(Class, java.io.Serializable)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#get(Class,
     *      java.io.Serializable)
     */
    public Object getById(Class entityClass, Serializable id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    public boolean exist(Class entityClass, Serializable id) {
        return getById(entityClass, id) != null;
    }

    // -------------------------------------------------------------------------
    // Convenience finder methods for persistent Class
    // -------------------------------------------------------------------------

    /**
     * Execute a query for all persistent instances of the given persistent
     * class.
     * 
     * @param entityClass
     *            a persistent class
     * @return all instances of the given persistent class
     */
    public List findAll(Class entityClass) {
        return getHibernateTemplate().loadAll(entityClass);
    }

    /**
     * Execute a query for all persistent instances of the given persistent
     * class.
     * 
     * @param entityClass
     *            a persistent class
     * @param sortField
     *            the field name to be sorted, by default the field will be sort
     *            ascending
     * @return all instances of the given persistent class
     */
    public List findAllSorted(Class entityClass, String sortField) {
        return getDefaultHibernateTemplate().findAllSorted(entityClass, sortField);
    }

    public List findAllSorted(Class entityClass, Sort sort) {
        return getDefaultHibernateTemplate().findAllSorted(entityClass, sort);
    }

    /**
     * 
     * @param entityClass
     * @param page
     * @return
     */
    public Page findPage(Class entityClass, Page page) {
        return getDefaultHibernateTemplate().findPage(entityClass, page);
    }

    // -------------------------------------------------------------------------
    // Convenience finder methods for HQL strings
    // -------------------------------------------------------------------------

    public Page findPage(String queryString, Page page) {
        return getDefaultHibernateTemplate().findPage(queryString, page);
    }

    public Page findPage(String queryString, Page page, Object... values) {
        return getDefaultHibernateTemplate().findPage(queryString, page, values);
    }
    
    public Page findPage(QueryCriterion queryCriterion, Page page){
        return getDefaultHibernateTemplate().findPage(queryCriterion, page);
    }

    /**
     * Execute a query for persistent instances,binding a number of values to
     * ":" named parameters in the query string. The queryString will be
     * retrieve from the queryCriterion Object, the
     * QueryCriterion#getCountString will not be called
     * 
     * @param queryCriterion
     *            the queryCriterion
     * @return a List containing 0 or more persistent instances
     * @see QueryCriterion
     */
    public List find(QueryCriterion queryCriterion) {
        return getDefaultHibernateTemplate().find(queryCriterion);
    }

    /**
     * Execute a query for persistent instances.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @return a List containing 0 or more persistent instances
     * @see org.springframework.orm.hibernate3.HibernateTemplate#find(java.lang.String)
     */
    public List find(String queryString) {
        return getHibernateTemplate().find(queryString);
    }

    /**
     * Execute a query for persistent instances.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param value
     *            the value of the parameter
     * @return a List containing 0 or more persistent instances
     * @see org.springframework.orm.hibernate3.HibernateTemplate#find(java.lang.String,
     *      java.lang.Object)
     */
    public List find(String queryString, Object... value) {
        return getHibernateTemplate().find(queryString, value);
    }
    
    public Object findUnique(String queryString){
        return getDefaultHibernateTemplate().findUnique(queryString);
    }
    
    public Object findUnique(final String queryString, final Object... values){
        return getDefaultHibernateTemplate().findUnique(queryString, values);
    }

    /**
     * Execute a query for persistent instances, binding one value to a ":"
     * named parameter in the query string.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramName
     *            the name of parameter
     * @param value
     *            the value of the parameter
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedParam(java.lang.String,
     *      java.lang.String, java.lang.Object)
     */
    public List findByNamedParam(String queryString, String paramName, Object value) {
        return getHibernateTemplate().findByNamedParam(queryString, paramName, value);
    }

    /**
     * Execute a query for persistent instances, binding a number of values to
     * ":" named parameters in the query string.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramNames
     *            the names of the parameters
     * @param values
     *            the values of the parameters
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     * @see org.springframework.orm.hibernate3.HibernateTemplate#findByNamedParam(java.lang.String,
     *      java.lang.String[], java.lang.Object[])
     */
    public List findByNamedParam(String queryString, String[] paramNames, Object[] values) {
        return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
    }

    /**
     * Execute a query for persistent instances, binding a number of values to
     * ":" named parameters in the query string. The result will be pageable.
     * The page's totalCount must be set explicitly. Call
     * <code>BaseHibernateDao#countByNamedParam(String, String, Object)</code>
     * before this method.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramName
     *            the name of the parameter
     * @param value
     *            the value of the parameter
     * @param page
     *            the Page Object
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     * @see BaseHibernateDao#countByNamedParam(String, String, Object)
     */
    public List findByNamedParam(String queryString, String paramName, Object value, Page page) {
        return findByNamedParam(queryString, new String[] { paramName }, new Object[] { value }, page);
    }

    /**
     * Execute a query for persistent instances, binding a number of values to
     * ":" named parameters in the query string. The result will be pageable.
     * The page's totalCount must be set explicitly. Call
     * <code>BaseHibernateDao#countByNamedParam(String, String[], Object[])</code>
     * before this method.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramNames
     *            the names of the parameters
     * @param values
     *            the values of the parameters
     * @param page
     *            the Page Object
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     * @see BaseHibernateDao#countByNamedParam(String, String[], Object[])
     */
    public List findByNamedParam(String queryString, String[] paramNames, Object[] values, Page page) {
        return getDefaultHibernateTemplate().findByNamedParam(queryString, paramNames, values, page);
    }

    // -------------------------------------------------------------------------
    // Convenience finder methods for named queries
    // -------------------------------------------------------------------------

    /**
     * Execute a named query for persistent instances. A named query is defined
     * in a Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     */
    public List findByNamedQuery(String queryName) {
        return getHibernateTemplate().findByNamedQuery(queryName);
    }

    /**
     * Execute a named query for persistent instances, binding one value to a
     * "?" parameter in the query string. A named query is defined in a
     * Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     */
    public List findByNamedQuery(String queryName, Object value) {
        return getHibernateTemplate().findByNamedQuery(queryName, value);
    }

    /**
     * Execute a named query for persistent instances, binding a number of
     * values to "?" parameters in the query string. A named query is defined in
     * a Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param values
     *            the values of the parameters
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     */
    public List findByNamedQuery(String queryName, Object[] values) {
        return getHibernateTemplate().findByNamedQuery(queryName, values);
    }

    /**
     * Execute a named query for persistent instances. A named query is defined
     * in a Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param page
     *            the Page Object
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     */
    public List findByNamedQuery(String queryName, Page page) {
        return findByNamedQuery(queryName, (Object[]) null, page);
    }

    /**
     * Execute a named query for persistent instances, binding one value to a
     * "?" parameter in the query string. A named query is defined in a
     * Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param page
     *            the Page Object
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     */
    public List findByNamedQuery(String queryName, Object value, Page page) {
        return findByNamedQuery(queryName, new Object[] { value }, page);
    }

    /**
     * Execute a named query for persistent instances, binding a number of
     * values to "?" parameters in the query string. A named query is defined in
     * a Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param values
     *            the values of the parameters
     * @param page
     *            the Page Object
     * @return a List containing 0 or more persistent instances
     * @see org.hibernate.Session#getNamedQuery(String)
     */
    public List findByNamedQuery(String queryName, Object[] values, Page page) {
        return getDefaultHibernateTemplate().findByNamedQuery(queryName, values, page);
    }

    // -------------------------------------------------------------------------
    // Convenience query methods for iteratation
    // -------------------------------------------------------------------------

    /**
     * Execute a query for persistent instances.
     * <p>
     * Returns the results as Iterator. Entities returned are initialized on
     * demand. See Hibernate docs for details.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @return a List containing 0 or more persistent instances
     */
    public Iterator iterate(String queryString) {
        return getHibernateTemplate().iterate(queryString);
    }

    /**
     * Execute a query for persistent instances, binding one value to a "?"
     * parameter in the query string.
     * <p>
     * Returns the results as Iterator. Entities returned are initialized on
     * demand. See Hibernate docs for details.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param value
     *            the value of the parameter
     * @return a List containing 0 or more persistent instances
     */
    public Iterator iterate(String queryString, Object value) {
        return getHibernateTemplate().iterate(queryString, value);
    }

    /**
     * Execute a query for persistent instances, binding a number of values to
     * "?" parameters in the query string.
     * <p>
     * Returns the results as Iterator. Entities returned are initialized on
     * demand. See Hibernate docs for details.
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param values
     *            the values of the parameters
     * @return a List containing 0 or more persistent instances
     */
    public Iterator iterate(String queryString, Object[] values) {
        return getHibernateTemplate().iterate(queryString, values);
    }

    /**
     * Execute a query for persistent instances.
     * <p>
     * Returns the results as Iterator. Entities returned are initialized on
     * demand. See Hibernate docs for details. A named query is defined in a
     * Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     */
    public Iterator iterateByNamedQuery(String queryName) {
        return iterateByNamedQuery(queryName, (Object[]) null);
    }

    /**
     * Execute a query for persistent instances.
     * <p>
     * Returns the results as Iterator. Entities returned are initialized on
     * demand. See Hibernate docs for details. A named query is defined in a
     * Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param value
     *            the value of the parameter
     */
    public Iterator iterateByNamedQuery(String queryName, Object value) {
        return iterateByNamedQuery(queryName, new Object[] { value });
    }

    /**
     * Execute a query for persistent instances.
     * <p>
     * Returns the results as Iterator. Entities returned are initialized on
     * demand. See Hibernate docs for details. A named query is defined in a
     * Hibernate mapping file.
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param values
     *            the values of the parameters
     */
    public Iterator iterateByNamedQuery(String queryName, Object[] values) {
        return getDefaultHibernateTemplate().iterateByNamedQuery(queryName, values);
    }

    // -------------------------------------------------------------------------
    // Convenience counter methods for HQL strings
    // -------------------------------------------------------------------------

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramName
     *            the name of the parameter
     * @param value
     *            the value of the parameter
     * @return The number of entities satisfy the query
     */
    public int countByNamedParam(String queryString, String paramName, Object value) {
        return countByNamedParam(queryString, new String[] { paramName }, new Object[] { value });
    }

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param paramNames
     *            the names of the parameters
     * @param values
     *            the values of the parameters
     * @return The number of entities satisfy the query
     */
    public int countByNamedParam(String queryString, String[] paramNames, Object[] values) {
        return getDefaultHibernateTemplate().countByNamedParam(queryString, paramNames, values);
    }

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @return The number of entities satisfy the query
     */
    public int countByNamedQuery(String queryName) {
        return countByNamedQuery(queryName, (Object[]) null);
    }

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param value
     *            the value of the parameter
     * @return The number of entities satisfy the query
     */
    public int countByNamedQuery(String queryName, Object value) {
        return countByNamedQuery(queryName, new Object[] { value });
    }

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryName
     *            the name of a Hibernate query in a mapping file
     * @param values
     *            the values of the parameters
     * @return The number of entities satisfy the query
     */
    public int countByNamedQuery(String queryName, Object[] values) {
        return getDefaultHibernateTemplate().countByNamedQuery(queryName, values);
    }

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @return The number of entities satisfy the query
     */
    public int count(String queryString) {
        return getDefaultHibernateTemplate().count(queryString);
    }

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param value
     *            the value of the parameter
     * @return The number of entities satisfy the query
     */
    public int count(String queryString, Object value) {
        return count(queryString, new Object[] { value });
    }

    /**
     * Execute a query for the number of entities satisfy the query condition
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param values
     *            the values of the parameters
     * @return The number of entities satisfy the query
     */
    public int count(String queryString, Object[] values) {
        return getDefaultHibernateTemplate().count(queryString, values);
    }

    public int count(QueryCriterion queryCriterion) {
        return getDefaultHibernateTemplate().count(queryCriterion);
    }

    // -------------------------------------------------------------------------
    // miscellaneous methods
    // -------------------------------------------------------------------------

    /**
     * If <code>results</code> is not null and has one element then the element
     * will be return, otherwise null will be returned.
     * 
     * @param results
     * @return this first Object in <code>results</code>
     */
    public Object findSingleObject(List results) {
        if (results != null && results.size() == 1) {
            return results.get(0);
        }
        if (results != null && results.size() > 1) {
            log.error("Found more than one object when single object requested: " + results);
        }
        return null;
    }

    /**
     * Return the first Object in the list if
     * <code>results<code> has any elements
     * or null if the <code>results<code> is null or its size is 0
     * 
     * @param results
     * @return the first Object
     */
    public Object findFirstObject(List results) {
        if (results != null && results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    /**
     * Return the first Object in the Iterator if
     * <code>it<code> has any elements
     * or null if the <code>Iterator<code> doesn't has next value.
     * 
     * @param it
     * @return
     */
    public Object findFirstObject(Iterator it) {
        return it.hasNext() ? it.next() : null;
    }
}
