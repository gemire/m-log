/**
 * 
 */
package org.mspring.platform.cache.web;

import net.sf.ehcache.CacheException;

/**
 * @author Gao Youbo
 * @since 2013-2-5
 * @description
 * @TODO
 */
public class AlreadyGzippedException extends CacheException {
    /**
     * 
     */
    private static final long serialVersionUID = -2965818874621057838L;

    /**
     * Constructor for the exception
     */
    public AlreadyGzippedException() {
        super();
    }

    /**
     * Constructs an exception with the message given
     * 
     * @param message
     *            the message
     */
    public AlreadyGzippedException(String message) {
        super(message);
    }
}
