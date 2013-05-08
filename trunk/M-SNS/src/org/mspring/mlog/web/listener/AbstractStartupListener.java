/**
 * 
 */
package org.mspring.mlog.web.listener;

import javax.servlet.ServletContextEvent;

import org.mspring.platform.core.ContextManager;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description 
 * @TODO 
 */
public abstract class AbstractStartupListener extends ContextLoaderListener {

    /* (non-Javadoc)
     * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // TODO Auto-generated method stub
        if (ContextManager.getApplicationContext() == null) {
            super.contextInitialized(event);
        }
        startuped(event);
    }
    
    /**
     * 容器启动之后执行
     * @param event
     */
    public abstract void startuped(ServletContextEvent event);

}
