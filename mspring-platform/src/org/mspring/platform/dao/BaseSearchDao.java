/**
 * 
 */
package org.mspring.platform.dao;

import org.apache.lucene.search.Query;
import org.mspring.platform.dao.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-5-6
 */
public abstract interface BaseSearchDao<T> {
    public abstract Page<T> searchPage(Page<T> page, Query query);
    public Integer count(Query query);
    public void index(T paramT);
}
