package org.mspring.platform.web.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Trace the HttpSession event, for debug.
 * 
 * @author gaoyb(www.mspring.org)
 * @since Mar 5, 2011 org.mspring.core.listener
 */
public class SessionTraceListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionBindingListener, HttpSessionActivationListener {

    private static final Logger logger = Logger.getLogger(SessionTraceListener.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
     * .HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        // TODO Auto-generated method stub
        logger.debug("sessionCreated, sessionid=" + sessionEvent.getSession().getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
     * .http.HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        // TODO Auto-generated method stub
        logger.debug("sessionDestroyed, sessionid=" + sessionEvent.getSession().getId());

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.
     * servlet.http.HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent bindingEvent) {
        // TODO Auto-generated method stub
        logger.debug("attributeAdded, bindingEvent[name=" + bindingEvent.getName() + ", value=" + bindingEvent.getValue() + "]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax
     * .servlet.http.HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent bindingEvent) {
        // TODO Auto-generated method stub
        logger.debug("attributeRemoved, bindingEvent[name=" + bindingEvent.getName() + ", value=" + bindingEvent.getValue() + "]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax
     * .servlet.http.HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent bindingEvent) {
        // TODO Auto-generated method stub
        logger.debug("attributeReplaced, bindingEvent[name=" + bindingEvent.getName() + ", value=" + bindingEvent.getValue() + "]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet
     * .http.HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent bindingEvent) {
        // TODO Auto-generated method stub
        logger.debug("valueBound, bindingEvent[name=" + bindingEvent.getName() + ", value=" + bindingEvent.getValue() + "]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet
     * .http.HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent bindingEvent) {
        // TODO Auto-generated method stub
        logger.debug("valueUnbound, bindingEvent[name=" + bindingEvent.getName() + ", value=" + bindingEvent.getValue() + "]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionActivationListener#sessionDidActivate(javax
     * .servlet.http.HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent sessionEvent) {
        // TODO Auto-generated method stub
        logger.debug("sessionDidActivate, sessionid=" + sessionEvent.getSession().getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpSessionActivationListener#sessionWillPassivate
     * (javax.servlet.http.HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent sessionEvent) {
        // TODO Auto-generated method stub
        logger.debug("sessionWillPassivate, sessionid=" + sessionEvent.getSession().getId());
    }

}
