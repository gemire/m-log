/**
 * 
 */
package org.mspring.mlog.job;

import org.apache.log4j.Logger;
import org.mspring.mlog.job.command.UpdateCommentNumCommand;
import org.mspring.mlog.job.command.UpdateTagCountCommand;
import org.mspring.platform.job.HibernateAwareJob;
import org.mspring.platform.job.command.Command;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author Gao Youbo
 * @since 2012-5-6
 */
public class UpdateTagCountJob extends HibernateAwareJob implements StatefulJob {
    
    private static final Logger log = Logger.getLogger(UpdateTagCountJob.class);

    /* (non-Javadoc)
     * @see org.mspring.platform.job.HibernateAwareJob#doExecute(org.quartz.JobExecutionContext)
     */
    @Override
    protected void doExecute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
        // TODO Auto-generated method stub
        log.debug("############################################ execute job UpdateTagCountJob");
        try {
            Command command = new UpdateTagCountCommand();
            command.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.warn("execute job UpdateTagCountJob failure!", e);
        }
    }

}
