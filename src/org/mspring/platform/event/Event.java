/**
 * 
 */
package org.mspring.platform.event;

import java.util.EventObject;

/**
 * @author Gao Youbo
 * @since May 7, 2012
 */
public class Event extends EventObject {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final long timestamp;

    /**
     * @param source
     *            the component that published the event
     */
    public Event(Object source) {
        super(source);
        // TODO Auto-generated constructor stub
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Return the system time in milliseconds when the event happened.
     */
    public long getTimestamp() {
        return timestamp;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return source == null ? other.source == null : source.equals(other.source);
    }

    public int hashCode() {
        return source == null ? 0 : source.hashCode();
    }

}
