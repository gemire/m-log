/**
 * 
 */
package org.mspring.mlog.task.comment;

import java.util.Map;

import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.CommentService;
import org.mspring.platform.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2013-1-9
 * @Description
 * @TODO
 */
@Component
public class CommentNoticeTask extends AbstractTask {
    @Autowired
    private CommentService commentService;

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.task.AbstractTask#doTask(java.util.Map)
     */
    @Override
    protected void doTask(Map<Object, Object> paramMap) {
        // TODO Auto-generated method stub
        if (paramMap != null) {
            Object comment = paramMap.get(CommentAspect.CONTEXT_COMMENT);
            if (comment == null) {
                return;
            }
            commentService.commentNotice((Comment) comment);
        }
    }
}
