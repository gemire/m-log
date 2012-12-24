/**
 * 
 */
package org.mspring.mlog.support.formater.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gao Youbo
 * @since 2012-8-6
 * @Description
 * @TODO
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CatalogFormat {

}
