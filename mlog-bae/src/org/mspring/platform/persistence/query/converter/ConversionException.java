/**
 * 
 */
package org.mspring.platform.persistence.query.converter;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class ConversionException extends RuntimeException {
    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(Throwable cause) {
        super(cause);
    }
}
