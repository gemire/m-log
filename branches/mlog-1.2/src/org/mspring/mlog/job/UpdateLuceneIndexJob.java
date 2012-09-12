/**
 * 
 */
package org.mspring.mlog.job;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO 更新索引
 */
public class UpdateLuceneIndexJob implements StatefulJob {
    private static final Logger log = Logger.getLogger(UpdateLuceneIndexJob.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        log.debug("execute UpdateLuceneIndexJob...");
        ServiceFactory.getPostSearchService().rebuildAllPostIndex();
    }

}
