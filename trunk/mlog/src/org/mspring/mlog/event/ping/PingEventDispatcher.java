/**
 * 
 */
package org.mspring.mlog.event.ping;

import java.util.EventListener;
import java.util.Iterator;
import java.util.List;

import org.mspring.platform.common.Assert;
import org.mspring.platform.event.Event;
import org.mspring.platform.event.EventDispatcher;

/**
 * @author Gao Youbo
 * @since May 7, 2012
 */
public class PingEventDispatcher implements EventDispatcher {

    /* (non-Javadoc)
     * @see org.mspring.platform.event.EventDispatcher#dispatch(org.mspring.platform.event.Event, java.util.List)
     */
    @Override
    public void dispatch(Event event, List<EventListener> listeners) {
        // TODO Auto-generated method stub
        Assert.isInstanceOf(PingEvent.class, event);
        PingEvent pingEvent = (PingEvent) event;
        PingEventType eventType = pingEvent.getType();
        
        for (Iterator<EventListener> it = listeners.iterator(); it.hasNext();) {
            EventListener listener = (EventListener) it.next();
            if (listener instanceof PingEventListener) {
                PingEventListener pingEventListener = (PingEventListener) listener;
                if (eventType.equals(PingEventType.PING)) {
                    pingEventListener.ping(pingEvent);
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see org.mspring.platform.event.EventDispatcher#getHandledEventClass()
     */
    @Override
    public Class getHandledEventClass() {
        // TODO Auto-generated method stub
        return PingEvent.class;
    }

}
