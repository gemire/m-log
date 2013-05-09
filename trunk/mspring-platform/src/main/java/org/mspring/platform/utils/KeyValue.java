/**
 * 
 */
package org.mspring.platform.utils;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since Apr 14, 2012
 */
public class KeyValue implements Serializable {
    private String key;
    private Object value;

    /**
     * 
     */
    public KeyValue() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param key
     * @param value
     */
    public KeyValue(String key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
