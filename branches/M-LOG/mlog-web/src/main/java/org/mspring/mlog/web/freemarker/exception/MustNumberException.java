/**
 * 
 */
package org.mspring.mlog.web.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description 非数字参数异常
 * @TODO
 */
public class MustNumberException extends TemplateModelException {
    /**
     * 
     */
    private static final long serialVersionUID = 8721429140212978225L;

    public MustNumberException(String paramName) {
        super("The \"" + paramName + "\" parameter must be a number.");
    }
}
