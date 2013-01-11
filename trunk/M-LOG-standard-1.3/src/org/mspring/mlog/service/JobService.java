/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.Job;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO
 */
public interface JobService {
    Page<Job> findJob(Page<Job> page, String queryString);
    /**
     * 设置是否启用JOB
     * @param enabled
     * @param id
     */
    void setEnabled(boolean enabled, Long... id);
    /**
     * 设置JOB名称，ids和expressions是一一对应的
     * @param ids
     * @param expressions
     */
    void setExpressions(Long[] ids, String[] expressions);
    
    /**
     * 设置JOB执行方式，ids和execTypes是一一对应的
     */
    void setExecTypes(Long[] ids, String[] execTypes);
    
    /**
     * 重新加载JOB
     * @param id
     */
    void reloadJobServer(Long id);
    
    /**
     * 重新加载JOB
     * @param job
     */
    void reloadJobServer(Job job);
    
    /**
     * 加载所有JOB
     */
    void loadJobServer();
}
