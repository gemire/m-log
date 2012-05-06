/**
 * 
 */
package org.mspring.mlog.exception.intercepter;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mspring.platform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since Mar 24, 2012
 */
@Aspect
@Component
public class ExceptionIntercepter {
    private static final Logger log = Logger.getLogger(ExceptionIntercepter.class);
    
    @Value("${exception.stacktrace}")
    private boolean exceptionStackTrace = false;

    //@Pointcut("execution(* org.mspring.mlog..*.*(..))")
    @Pointcut("execution(* org.mspring.mlog.service..*(..)) || execution(* org.mspring.mlog.web.security.service..*(..)) ")
    public void method1() {
    }

    // @AfterThrowing("execution(* org.mspring.mlog.service.*.*(..))")
    @AfterThrowing(pointcut = "method1()", throwing = "ex")
    public void doExceptionActions(JoinPoint jp, Throwable ex) {
        log.error("**************************************************************************");
        log.error("Error happened at: " + DateUtils.format(DateUtils.YYYY_MM_DD_HH_MM_SS));
        log.error("Error happened in class: " + jp.getTarget().getClass().getName());
        log.error("Error happened in method: " + jp.getSignature().getName());

        for (int i = 0; i < jp.getArgs().length; i++) {
            log.error("arg[" + i + "]: " + jp.getArgs()[i]);
        }

        //log.error("Exception class: " + ex.getClass().getName());
        //log.error("Exception message: " + ex.getMessage());
        if (exceptionStackTrace) {
            ex.printStackTrace();
        }
        log.error("**************************************************************************");
    }
}
