/**
 * 
 */
package org.mspring.mlog.schedule.job;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO 更新系统统计信息
 */
public class UpdateStatInfoJob extends QuartzJobBean {

    private static final Logger log = Logger.getLogger(UpdateStatInfoJob.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org
     * .quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        log.debug("begin update post count...");
        ServiceFactory.getStatService().updatePostCount();
        log.debug("begin update comment count...");
        ServiceFactory.getStatService().updateCommentCount();
    }

}
