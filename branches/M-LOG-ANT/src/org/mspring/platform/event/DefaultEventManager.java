/**
 * 
 */
package org.mspring.platform.event;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Gao Youbo
 * @since May 7, 2012
 */
public class DefaultEventManager implements EventManager {

    private static final Logger log = Logger.getLogger(DefaultEventManager.class);

    // all listeners
    private List listeners = new ArrayList();

    // key: eventClass, value: EventDispatcher
    private Map eventDispatchers = new HashMap();

    public void setListeners(List listeners) {
        this.listeners = listeners;
    }

    public void setDispatchers(List dispatchers) {
        if (dispatchers == null)
            return;
        for (Iterator it = dispatchers.iterator(); it.hasNext();) {
            EventDispatcher eventDispatcher = (EventDispatcher) it.next();
            log.info("Register EventDispatcher: " + eventDispatcher.getClass());
            eventDispatchers.put(eventDispatcher.getHandledEventClass(), eventDispatcher);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.event.EventManager#publishEvent(org.mspring.platform.event.Event)
     */
    public void publishEvent(Event event) {
        // TODO Auto-generated method stub
        Class clazz = event.getClass();
        EventDispatcher dispatcher = (EventDispatcher) eventDispatchers.get(clazz);
        if (dispatcher == null) {
            return;
        }
        dispatcher.dispatch(event, listeners);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.event.EventManager#registerListener(java.util.EventListener)
     */
    public void registerListener(EventListener listener) {
        // TODO Auto-generated method stub
        if (listener == null) {
            return;
        }
        listeners.add(listener);
        log.info("Register listener: " + listener.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.event.EventManager#unregisterListener(java.util.EventListener)
     */
    public void unregisterListener(EventListener listener) {
        // TODO Auto-generated method stub
        listeners.remove(listener);
    }

}
