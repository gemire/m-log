/**
 * 
 */
package org.mspring.mlog.schedule.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Job;
import org.mspring.mlog.entity.JobLog;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Gao Youbo
 * @since 2013-1-28
 * @description
 * @TODO
 */
public abstract class BaseJob extends AbstractJob {

    private static final Logger log = Logger.getLogger(BaseJob.class);

    private JobLog jobLog = new JobLog();
    private Long start = null;
    private Long end = null;
    private boolean success = true;
    private String message = "";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        preExecuteInternal(context);
        try {
            nativeExecuteInternal(context);
        } catch (Exception e) {
            // TODO: handle exception
            success = false;
            message = e.getMessage();
            log.error(e.getMessage());
        }
        afterExecuteInternal(context);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.schedule.job.AbstractJob#preExecuteInternal(org.quartz
     * .JobExecutionContext)
     */
    @Override
    protected void preExecuteInternal(JobExecutionContext context) {
        // TODO Auto-generated method stub
        start = System.currentTimeMillis();
        jobLog.setTime(new Date());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.schedule.job.AbstractJob#afterExecuteInternal(org.quartz
     * .JobExecutionContext)
     */
    @Override
    protected void afterExecuteInternal(JobExecutionContext context) {
        // TODO Auto-generated method stub
        end = System.currentTimeMillis();

        JobDetail jobDetail = context.getJobDetail();
        jobLog.setJob(new Job(new Long(jobDetail.getName())));
        jobLog.setName(jobDetail.getJobClass().getName());

        jobLog.setUseTime(end - start);
        jobLog.setSuccess(success);
        jobLog.setMessage(message);
        ServiceFactory.getJobLogService().createJobLog(jobLog);
    }

}
