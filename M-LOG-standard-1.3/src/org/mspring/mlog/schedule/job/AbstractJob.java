/**
 * 
 */
package org.mspring.mlog.schedule.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Administrator
 * @since Gao Youbo
 * @description
 * @TODO
 */
public abstract class AbstractJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        preExecuteInternal(context);
        nativeExecuteInternal(context);
        afterExecuteInternal(context);
    }

    /**
     * JOB执行之前
     * 
     * @param context
     */
    protected abstract void preExecuteInternal(JobExecutionContext context);

    /**
     * JOB执行
     * 
     * @param context
     */
    protected abstract void nativeExecuteInternal(JobExecutionContext context);

    /**
     * JOB执行之后
     * 
     * @param context
     */
    protected abstract void afterExecuteInternal(JobExecutionContext context);
}
