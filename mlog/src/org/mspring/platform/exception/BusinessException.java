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