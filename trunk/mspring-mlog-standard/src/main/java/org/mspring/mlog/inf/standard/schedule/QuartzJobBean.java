/**
 * 
 */
package org.mspring.mlog.inf.standard.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description 
 * @TODO 
 */
public class QuartzJobBean extends org.springframework.scheduling.quartz.QuartzJobBean {

    /* (non-Javadoc)
     * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        System.out.println("执行JOB...");
    }

}
