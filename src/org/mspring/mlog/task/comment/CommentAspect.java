/**
 * 
 */
package org.mspring.mlog.task.comment;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.mspring.mlog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2012-9-28
 * @Description
 * @TODO
 */
@Aspect
@Component
public class CommentAspect {

    @Autowired
    private CommentReplyNoticeTask commentReplyNoticeTask;

    /**
     * 评论回复通知
     * 
     * @param jp
     */
    @AfterReturning(value = "execution(* org.mspring.mlog.service.CommentService.createComment(..))", returning = "comment")
    public void commentReplyNoticeTask(JoinPoint jp, Comment comment) {
        if (comment != null && comment.getParent() != null && comment.getParent().getId() != null) {
            Map<Object, Object> context = new HashMap<Object, Object>();
            context.put("commentId", comment.getParent().getId());
            commentReplyNoticeTask.doAsyncTask(context);
        }
    }
}
