package org.mspring.platform.persistence.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

public abstract interface BaseDao<T> {
    public abstract <PK extends Serializable> T get(PK paramPK);

    public abstract Serializable save(T paramT);

    public abstract void update(T paramT);

    public abstract void executeUpdate(String queryString);
    
    public abstract void executeUpdate(String queryString, Object... value);

    public abstract void delete(T paramT);

    public abstract <PK extends Serializable> void delete(PK... paramPK);

    public abstract T findUnique(String queryString);

    public abstract T findUnique(String queryString, Object value);

    public abstract T findUnique(String queryString, Object[] values);

    public abstract Page<T> findPage(Page<T> paramPage, QueryCriterion queryCriterion);

    public abstract Page<T> findPage(Page<T> page, String queryString);
    
    public abstract Page<T> findPage(Page<T> page, String queryString, Object[] values);

    public abstract List<T> find(QueryCriterion queryCriterion);

    public abstract List<T> find(String queryString);

    public abstract List<T> find(String queryString, Object values);

    public abstract List<T> find(String queryString, Object[] values);

    public abstract long count(QueryCriterion queryCriterion);

    public abstract Long count(String queryString, Object... values);

    public abstract List<T> findAll();

    public abstract T merge(T object);
    
    public abstract void saveOrUpdate(T object);
}