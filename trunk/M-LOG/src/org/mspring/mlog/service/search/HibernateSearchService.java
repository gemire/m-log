/**
 * 
 */
package org.mspring.mlog.service.search;

import java.io.Serializable;

import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-1-9
 * @Description 
 * @TODO 
 */
public interface HibernateSearchService {
    /**
     * 获取querybuilder
     * @param clazz
     * @return
     */
    QueryBuilder getQueryBuilder(Class clazz);
    
    /**
     * 更新索引
     * @param clazz
     * @param id
     */
    void updateIndex(Class clazz, Serializable id);
    
    /**
     * 更新所有索引
     * @param clazz
     */
    void updateAllIndex(Class clazz);
    
    /**
     * Count
     * @param query
     * @param clazz
     * @return
     */
    Long count(Query query, Class... clazz);
    
    /**
     * 查询结果，并将结果封装到Page对象中
     * @param page
     * @param query
     * @param clazz
     * @return
     */
    Page searchPage(Page page, Query query, Class... clazz);
}
