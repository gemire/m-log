/**
 * 
 */
package org.mspring.msns.web.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.mspring.msns.core.ServiceFactory;
import org.mspring.platform.web.WebContext;
import org.mspring.platform.web.listener.AbstractStartupListener;

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
     * org.mspring.msns.web.listener.AbstractStartupListener#startuped(javax
     * .servlet.ServletContextEvent)
     */
    @Override
    public void startuped(ServletContextEvent event) {
        // TODO Auto-generated method stub
        WebContext.getInstance().setServletContext(event.getServletContext());
        ServiceFactory.getJobService().loadJobServer();
        log.info("container has started.");
    }

}
