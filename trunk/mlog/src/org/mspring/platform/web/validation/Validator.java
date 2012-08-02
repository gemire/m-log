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
public interface Validator {
    Errors validate(Object target);
}
