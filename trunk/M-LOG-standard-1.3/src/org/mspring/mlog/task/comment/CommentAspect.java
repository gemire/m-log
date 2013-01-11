/**
 * 
 */
package org.mspring.mlog.task.comment;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.mspring.mlog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2013-1-9
 * @Description
 * @TODO
 */
@Aspect
@Component
public class CommentAspect {
    private static final Logger log = Logger.getLogger(CommentAspect.class);

    @Autowired
    private CommentReplyNoticeTask commentReplyNoticeTask;

    /**
     * 评论Email通知(1.新评论通知管理员,2.评论有回复时通知被回复评论作者)
     * 
     * @param jp
     */
    @AfterReturning(value = "execution(* org.mspring.mlog.service.CommentService.createComment(..))", returning = "comment")
    public void noticeTask(JoinPoint jp, Comment comment) {
        try {
            Map<Object, Object> context = new HashMap<Object, Object>();
            // 评论邮件通知
            if (comment != null && comment.getReplyComment() != null) {
                context.put("comment", comment);
                commentReplyNoticeTask.doAsyncTask(context);
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            log.debug("send comment reply notice mail failure.", e);
        }
    }
}
