/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Controller;

/**
 * @author Gao Youbo
 * @since 2012-7-13
 * @Description
 * @TODO
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Controller
public @interface Widget {
    String value() default "";
}