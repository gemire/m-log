/**
 * 
 */
package org.mspring.mlog.task.stat;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.mspring.mlog.task.cache.CacheAspect;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since May 5, 2012
 */
@Aspect
@Component
public class StatAspect {
    private static final Logger log = Logger.getLogger(CacheAspect.class);
}
