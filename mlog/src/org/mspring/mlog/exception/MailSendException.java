/**
 * 
 */
package org.mspring.mlog.exception;

import org.mspring.platform.exception.BusinessException;

/**
 * @author Gao Youbo
 * @since Apr 8, 2012
 */
public class MailSendException extends BusinessException {

    /**
     * 
     */
    private static final long serialVersionUID = -8005754880011510408L;

    public MailSendException(String message) {
        super(message);
    }

    public MailSendException(Throwable cause) {
        super(cause);
    }

    public MailSendException(String message, Throwable cause) {
        super(message, cause);
    }

}
