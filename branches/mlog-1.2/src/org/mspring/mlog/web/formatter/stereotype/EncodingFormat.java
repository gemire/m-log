/**
 * 
 */
package org.mspring.mlog.web.formatter.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gao Youbo
 * @since 2012-8-1
 * @Description
 * @TODO
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncodingFormat {
    String encoding() default "UTF-8";
}
