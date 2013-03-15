/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

/**
 * @author Gao Youbo
 * @since 2012-12-31
 * @Description 
 * @TODO 
 */
public interface HQLExecuteService {
    /**
     * HQL查询
     * @param hql HQL语句
     * @return
     */
    public List query(String hql);
    
    /**
     * HQL查询
     * @param hql　HQL语句
     * @param firstResult 开始记录位置
     * @param maxResults 结束记录位置
     * @return
     */
    public List query(String hql, Integer firstResult, Integer maxResults);
}
