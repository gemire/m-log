/**
 * 
 */
package org.mspring.platform.event;

import java.util.EventListener;
import java.util.List;

/**
 * Dispatch the event for specified listeners
 * 
 * @author Gao Youbo
 * @since May 7, 2012
 */
public interface EventDispatcher {
    /**
     * Return the interested event class of this EventDispatcher
     */
    public Class getHandledEventClass();

    /**
     * Dispatch the event to listeners(globle)
     * 
     * @param event
     *            the event for dispatch
     * @param listeners
     *            the listeners to dispatch
     */
    public void dispatch(Event event, List<EventListener> listeners);
}
