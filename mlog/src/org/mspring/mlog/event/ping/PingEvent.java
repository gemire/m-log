/**
 * 
 */
package org.mspring.mlog.event.ping;

import org.mspring.platform.event.Event;

/**
 * @author Gao Youbo
 * @since May 7, 2012
 */
public class PingEvent extends Event {

    protected PingEventType type;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // protected

    /**
     * @param source
     */
    public PingEvent(Object source, PingEventType type) {
        super(source);
        // TODO Auto-generated constructor stub
        this.type = type;
    }

    public PingEventType getType() {
        return type;
    }

}
