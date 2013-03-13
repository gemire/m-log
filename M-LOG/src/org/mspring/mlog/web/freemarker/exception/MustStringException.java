/**
 * 
 */
package org.mspring.mlog.web.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description 非字符串参数异常
 * @TODO
 */
public class MustStringException extends TemplateModelException {
    /**
     * 
     */
    private static final long serialVersionUID = 4815987566821602266L;

    public MustStringException(String paramName) {
        super("The \"" + paramName + "\" parameter must be a string.");
    }
}
