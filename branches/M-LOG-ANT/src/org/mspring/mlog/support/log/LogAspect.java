/**
 * 
 */
package org.mspring.mlog.support.log;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.mspring.mlog.utils.WebUtils;
import org.mspring.mlog.web.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2013-3-11
 * @description
 * @TODO
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogTask logTask;

    @AfterReturning("within(org.mspring.mlog..*) && @annotation(logAnno)")
    public void log(final JoinPoint jp, final Log logAnno) {
        try {
            Map<Object, Object> context = new HashMap<Object, Object>();
            context.put("request", WebUtils.getRequest());
            context.put("joinPoint", jp);
            context.put("logAnno", logAnno);
            context.put("user", SecurityUtils.getCurrentUser());
            logTask.doAsyncTask(context);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
