/**
 * 
 */
package org.mspring.mlog.support.resolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gao Youbo
 * @since 2013-3-7
 * @description
 * @TODO
 */
@Target(value = ElementType.PARAMETER)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface PathParam {
    boolean required() default true;

    String defaultValue() default "";
}
