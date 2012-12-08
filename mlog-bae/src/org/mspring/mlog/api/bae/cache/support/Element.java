/**
 * 
 */
package org.mspring.mlog.api.bae.cache.support;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since 2012-10-11
 * @Description
 * @TODO
 */
public class Element implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8608843462575892493L;

    private Object cacheObject = null;

    /**
     * 
     */
    public Element() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cacheObject
     */
    public Element(Object cacheObject) {
        super();
        this.cacheObject = cacheObject;
    }

    public void put(Object object) {
        cacheObject = object;
    }

    public Object get() {
        return cacheObject;
    }
}
