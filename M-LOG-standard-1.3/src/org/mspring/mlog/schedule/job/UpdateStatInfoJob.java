/**
 * 
 */
package org.mspring.mlog.schedule.job;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.quartz.JobExecutionContext;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO 更新系统统计信息
 */
public class UpdateStatInfoJob extends BaseJob {

    private static final Logger log = Logger.getLogger(UpdateStatInfoJob.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.schedule.job.AbstractJob#nativeExecuteInternal(org.quartz
     * .JobExecutionContext)
     */
    @Override
    public void nativeExecuteInternal(JobExecutionContext arg0) {
        // TODO Auto-generated method stub
        try {
            log.debug("begin update post count...");
            ServiceFactory.getStatService().updatePostCount();
            log.debug("begin update comment count...");
            ServiceFactory.getStatService().updateCommentCount();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
