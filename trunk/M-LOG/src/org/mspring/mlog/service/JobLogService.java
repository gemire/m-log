/**
 * 
 */
package org.mspring.mlog.service;

import java.util.Date;

import org.mspring.mlog.entity.JobLog;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Administrator
 * @since Gao Youbo
 * @description 
 * @TODO
 */
public interface JobLogService {
    /**
     * 插入一条JOB日志
     * @param log
     */
    void createJobLog(JobLog log);
    
    /**
     * 根据JOB编号获取该JOB最后执行时间
     * @param jobId
     * @return
     */
    JobLog getLastestExecute(Long jobId);
    
    /**
     * 分页查询JOB
     * @param jobLogPage
     * @param queryCriterion
     * @return
     */
    Page<JobLog> findJobLog(Page<JobLog> jobLogPage, QueryCriterion queryCriterion);
    
    /**
     * 删除JOB日志
     * @param days 删除多少天以前的日志，如果days=0， 那么清空所有日志
     */
    void removeJobLog(int days);
}
