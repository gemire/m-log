/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.Date;

import org.mspring.mlog.entity.JobLog;
import org.mspring.mlog.service.JobLogService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.DateUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @since Gao Youbo
 * @description
 * @TODO
 */
@Service
@Transactional
public class JobLogServiceImpl extends AbstractServiceSupport implements JobLogService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.JobLogService#createJobLog(org.mspring.mlog.
     * entity.JobLog)
     */
    @Override
    public void createJobLog(JobLog log) {
        // TODO Auto-generated method stub
        if (log.getTime() == null) {
            log.setTime(new Date());
        }
        create(log);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.JobLogService#getLastestExecute(java.lang.Long)
     */
    @Override
    public JobLog getLastestExecute(Long jobId) {
        // TODO Auto-generated method stub
        Object obj = findUnique("select jobLog from JobLog jobLog where jobLog.job.id = ? and jobLog.success = true order by jobLog.time desc", jobId);
        return obj == null ? null : (JobLog) obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.JobLogService#findJobLog(org.mspring.platform
     * .persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<JobLog> findJobLog(Page<JobLog> jobLogPage, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, jobLogPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.JobLogService#removeJobLog(int)
     */
    @Override
    public void removeJobLog(int days) {
        // TODO Auto-generated method stub
        String hql = "delete JobLog jobLog ";
        if (days > 0) {
            Date date = DateUtils.addDays(new Date(), -days);
            String dateStr = DateUtils.format(date, DateUtils.YYYY_MM_DD_HH_MM_SS);
            hql += "where jobLog.time < '" + dateStr + "'";
        }
        executeUpdate(hql);
    }
}
