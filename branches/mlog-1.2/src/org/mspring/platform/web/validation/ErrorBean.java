/**
 * 
 */
package org.mspring.platform.web.validation;

import java.io.Serializable;

import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-2
 * @Description
 * @TODO
 */
public class ErrorBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5426469222460454511L;
    private String field;
    private String message;

    /**
     * 
     */
    public ErrorBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param field
     * @param message
     */
    public ErrorBean(String field, String message) {
        super();
        this.field = field;
        this.message = message;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        if (StringUtils.isBlank(message)) {
            return "字段[" + field + "]未设置验证失败消息";
        }
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
