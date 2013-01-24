/**
 * 
 */
package org.mspring.platform.exception;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
public class BusinessException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 2398740334943163701L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}