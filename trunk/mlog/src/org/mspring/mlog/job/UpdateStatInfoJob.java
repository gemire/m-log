/**
 * 
 */
package org.mspring.mlog.job;

import org.apache.log4j.Logger;
import org.mspring.mlog.job.command.UpdateStatInfoCmd;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author Gao Youbo
 * @since 2012-8-5
 * @Description
 * @TODO 更新统计信息
 */
public class UpdateStatInfoJob implements StatefulJob {
    private static final Logger log = Logger.getLogger(UpdateStatInfoJob.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        log.debug("execute job UpdateStatInfoJob...");
        try {
            new UpdateStatInfoCmd().execute();
        }
        catch (Exception e) {
            // TODO: handle exception
            log.error("execute job UpdateStatInfoJob failure!", e);
        }
    }

}
