/**
 * 
 */
package org.mspring.mlog.web.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description 非日期参数异常
 * @TODO
 */
public class MustDateException extends TemplateModelException {
    /**
     * 
     */
    private static final long serialVersionUID = -1227379348415814420L;

    public MustDateException(String paramName) {
        super("The \"" + paramName + "\" parameter must be a date.");
    }
}
