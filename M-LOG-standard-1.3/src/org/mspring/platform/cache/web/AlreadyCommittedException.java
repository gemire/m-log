/**
 * 
 */
package org.mspring.platform.cache.web;

/**
 * @author Gao Youbo
 * @since 2013-2-5
 * @description
 * @TODO
 */
public class AlreadyCommittedException extends ResponseHeadersNotModifiableException {
    /**
     * 
     */
    private static final long serialVersionUID = -6682797032062045695L;

    /**
     * Constructor for the exception
     */
    public AlreadyCommittedException() {
        super();
    }

    /**
     * Constructs an exception with the message given
     * 
     * @param message
     *            the message
     */
    public AlreadyCommittedException(String message) {
        super(message);
    }
}
