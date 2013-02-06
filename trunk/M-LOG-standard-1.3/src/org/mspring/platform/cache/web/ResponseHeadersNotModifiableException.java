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
public class ResponseHeadersNotModifiableException extends CacheException {
    /**
     * 
     */
    private static final long serialVersionUID = 1472700125634574378L;

    /**
     * Constructor for the exception
     */
    public ResponseHeadersNotModifiableException() {
        super();
    }

    /**
     * Constructs an exception with the message given
     * 
     * @param message
     *            the message
     */
    public ResponseHeadersNotModifiableException(String message) {
        super(message);
    }
}
