/**
 * 
 */
package org.mspring.mlog.event.ping;

import java.util.EventListener;

/**
 * @author Gao Youbo
 * @since May 7, 2012
 */
public interface PingEventListener extends EventListener {
    public void ping(PingEvent event); 
}
