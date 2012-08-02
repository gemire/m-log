/**
 * 
 */
package org.mspring.platform.web.resolver.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gao Youbo
 * @since 2012-8-2
 * @Description
 * @TODO
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UrlVariable {

}
