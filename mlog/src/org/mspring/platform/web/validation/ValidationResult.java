/**
 * 
 */
package org.mspring.platform.web.validation;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since 2012-8-1
 * @Description
 * @TODO Ajax验证时返回前端的数据
 */
public class ValidationResult implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6526035995474959751L;

    private Boolean result;
    private String message;

    /**
     * 
     */
    public ValidationResult() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param result
     */
    public ValidationResult(Boolean result) {
        super();
        this.result = result;
    }

    /**
     * @param result
     * @param message
     */
    public ValidationResult(Boolean result, String message) {
        super();
        this.result = result;
        this.message = message;
    }

    /**
     * @return the result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(Boolean result) {
        this.result = result;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
