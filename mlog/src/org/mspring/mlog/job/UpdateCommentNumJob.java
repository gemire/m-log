/**
 * 
 */
package org.mspring.mlog.job;

import org.apache.log4j.Logger;
import org.mspring.mlog.job.command.UpdateCommentNumCommand;
import org.mspring.platform.job.HibernateAwareJob;
import org.mspring.platform.job.command.Command;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author Gao Youbo
 * @since May 5, 2012
 */
public class UpdateCommentNumJob extends HibernateAwareJob implements StatefulJob {
    private static final Logger log = Logger.getLogger(UpdateCommentNumJob.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.job.HibernateAwareJob#doExecute(org.quartz.JobExecutionContext)
     */
    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        log.debug("############################################ execute job UpdateCommentNumJob");
        try {
            Command command = new UpdateCommentNumCommand();
            command.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.warn("execute job UpdateCommentNumJob failure!", e);
        }
    }

}
