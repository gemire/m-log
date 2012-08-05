/**
 * 
 */
package org.mspring.mlog.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author Gao Youbo
 * @since 2012-8-5
 * @Description 
 * @TODO 定时更新文章评论数量
 */
public class UpdatePostCommentNumJob implements StatefulJob {
    private static final Logger log = Logger.getLogger(UpdatePostCommentNumJob.class);

    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        log.debug("execute job UpdatePostCommentNumJob");
        try {
            
        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }

}
