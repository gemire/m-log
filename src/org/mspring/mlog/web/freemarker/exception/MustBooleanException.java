/**
 * 
 */
package org.mspring.mlog.web.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description 非布尔参数异常
 * @TODO
 */
public class MustBooleanException extends TemplateModelException {
    /**
     * 
     */
    private static final long serialVersionUID = 8454485679060427177L;

    public MustBooleanException(String paramName) {
        super("The \"" + paramName + "\" parameter must be a boolean.");
    }
}
