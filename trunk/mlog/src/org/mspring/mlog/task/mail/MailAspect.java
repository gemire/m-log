/**
 * 
 */
package org.mspring.mlog.task.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.mspring.mlog.entity.Comment;
import org.mspring.platform.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since Apr 28, 2012
 */
@Aspect
@Component
public class MailAspect {
    private static final Logger log = Logger.getLogger(MailAspect.class);
    
    private Task commentAuditPassTask;

    @Autowired
    public void setCommentAuditPassTask(Task commentAuditPassTask) {
        this.commentAuditPassTask = commentAuditPassTask;
    }
    
    @AfterReturning("execution(* org.mspring.mlog.service.CommentService.updateStatus(..))")
    public void commentAuditPassTask(JoinPoint jp){
        Integer status = (Integer) jp.getArgs()[0];
        Long[] ids = (Long[]) jp.getArgs()[1];
        if (Comment.STATUS_PASS.equals(status)) {
            Map<Object, Object> context = new HashMap<Object, Object>();
            context.put(CommentAuditPassTask.PARAM_COMMENT_ID, ids);
            context.put(CommentAuditPassTask.PARAM_COMMENT_STATUS, status);
            commentAuditPassTask.doAsyncTask(context);
        }
    }
}
