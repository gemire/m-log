/**
 * 
 */
package org.mspring.mlog.web.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.mspring.mlog.schedule.SchedulerService;
import org.mspring.mlog.schedule.job.UpdateStatInfoJob;
import org.mspring.platform.core.ContextManager;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO
 */
public class StandardStartupListener extends AbstractStartupListener {

    private static final Logger log = Logger.getLogger(StandardStartupListener.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.listener.AbstractStartupListener#startuped(javax
     * .servlet.ServletContextEvent)
     */
    @Override
    public void startuped(ServletContextEvent event) {
        // TODO Auto-generated method stub
        log.info("container has started.");

        SchedulerService schedulerService = ContextManager.getApplicationContext().getBean(SchedulerService.class);
        schedulerService.schedule("UpdateStatInfoJob", UpdateStatInfoJob.class, 60*1000);
        log.info("UpdateStatInfoJob register success!");
    }

}
