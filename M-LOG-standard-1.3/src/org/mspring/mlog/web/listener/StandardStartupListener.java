/**
 * 
 */
package org.mspring.mlog.web.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;

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

        ServiceFactory.getJobService().loadJobServer();
    }

}
