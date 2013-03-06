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

    public static String CONTEXT_COMMENT = "comment";

    @Autowired
    private CommentNoticeTask commentNoticeTask;

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
            context.put(CONTEXT_COMMENT, comment);

            // 评论回复邮件通知
            if (comment != null && comment.getParent() != null) {
                commentReplyNoticeTask.doAsyncTask(context);
            }
            // 文章评论通知
            if (comment != null) {
                commentNoticeTask.doAsyncTask(context);
            }
        } catch (Exception e) {
            // TODO: handle exception
            log.debug("send comment reply notice mail failure.");
        }
    }
}
