/**
 * 
 */
package org.mspring.platform.persistence.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.mspring.platform.common.Assert;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Gao Youbo
 * @since Jun 10, 2012
 */
public class DefaultHibernateTemplate extends HibernateTemplate {

    private static final Logger log = Logger.getLogger(DefaultHibernateTemplate.class);

    /**
     * 
     */
    public DefaultHibernateTemplate(SessionFactory sessionFactory) {
        // TODO Auto-generated constructor stub
        super(sessionFactory);
    }

    /**
     * 执行更新
     * 
     * @param queryString
     * @return
     */
    public int executeUpdate(final String queryString) {
        return execute(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                return queryObject.executeUpdate();
            }
        });
    }

    /**
     * 执行更新
     * 
     * @param queryString
     * @param values
     * @return
     */
    public int executeUpdate(final String queryString, final Object[] values) {
        return execute(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        queryObject.setParameter(i, values[i]);
                    }
                }
                return queryObject.executeUpdate();
            }
        });
    }

    /**
     * 执行更新
     * 
     * @param queryString
     * @param paramNames
     * @param values
     * @return
     * @throws DataAccessException
     */
    public int executeUpdate(final String queryString, final String[] paramNames, final Object[] values) throws DataAccessException {
        Assert.isTrue(paramNames.length == values.length, "Length of paramNames array must match length of values array");
        return execute(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException {
                Query queryObject = session.createQuery(queryString);
                prepareQuery(queryObject);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        queryObject.setParameter(paramNames[i], values[i]);
                    }
                }
                return queryObject.executeUpdate();
            }
        });
    }

    /**
     * 根据ID加载
     * 
     * @param entityClass
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Object loadById(Class entityClass, Serializable id) throws DataAccessException {
        return id != null ? super.load(entityClass, id) : null;
    }

    /**
     * 加载
     * 
     * @param entityClass
     * @param ids
     * @return
     * @throws DataAccessException
     */
    public List loadById(final Class entityClass, final Serializable[] ids) throws DataAccessException {
        if (ids == null || ids.length == 0) {
            return Collections.emptyList();
        }
        return execute(new HibernateCallback<List>() {
            @SuppressWarnings("unchecked")
            public List doInHibernate(Session session) throws HibernateException {
                return session.createCriteria(entityClass).add(Restrictions.in(session.getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName(), ids)).list();
            }
        });
    }

    /**
     * 加载
     * 
     * @param entityClass
     * @param ids
     * @return
     * @throws DataAccessException
     */
    public List loadById(final Class entityClass, final List ids) throws DataAccessException {
        if (ids == null || ids.size() == 0) {
            return Collections.emptyList();
        }
        return execute(new HibernateCallback<List>() {
            @SuppressWarnings("unchecked")
            public List doInHibernate(Session session) throws HibernateException {
                return session.createCriteria(entityClass).add(Restrictions.in(session.getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName(), ids)).list();
            }
        });
    }

    /**
     * 删除
     * 
     * @param entityClass
     * @param id
     * @throws DataAccessException
     */
    public void remove(Class entityClass, Serializable id) throws DataAccessException {
        if (id == null) {
            return;
        }
        Object entity = loadById(entityClass, id);
        if (null != entity) {
            super.delete(entity);
        }
    }

    /**
     * 删除
     * 
     * @param entityClass
     * @param ids
     * @throws DataAccessException
     */
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
}
