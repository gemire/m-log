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
public class MustSplitNumberException extends TemplateModelException {
    /**
     * 
     */
    private static final long serialVersionUID = -848292095207126182L;

    public MustSplitNumberException(String paramName) {
        super("The \"" + paramName + "\" parameter must be a number split by ','");
    }

    public MustSplitNumberException(String paramName, Exception cause) {
        super("The \"" + paramName + "\" parameter must be a number split by ','", cause);
    }
}
