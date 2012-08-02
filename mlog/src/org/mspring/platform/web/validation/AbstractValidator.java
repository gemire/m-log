/**
 * 
 */
package org.mspring.platform.web.validation;

/**
 * @author Gao Youbo
 * @since 2012-8-2
 * @Description
 * @TODO
 */
public abstract class AbstractValidator implements Validator {
    protected Errors getErrorsInstance() {
        return new Errors();
    }
}
