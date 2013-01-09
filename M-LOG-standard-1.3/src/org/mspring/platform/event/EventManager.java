/**
 * 
 */
package org.mspring.platform.event;

import java.util.EventListener;

/**
 * @author Gao Youbo
 * @since May 7, 2012
 */
public interface EventManager {
    public void registerListener(EventListener listener);

    public void unregisterListener(EventListener listener);

    public void publishEvent(Event event);
}
