/**
 * 
 */
package org.mspring.mlog.task.comment;

import java.util.Map;

import org.mspring.mlog.service.CommentService;
import org.mspring.platform.task.AbstractTask;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2012-9-28
 * @Description
 * @TODO 有新评论时,邮件通知作者
 */
@Component
public class CommentReplyNoticeTask extends AbstractTask {

    @Autowired
    private CommentService commentService;

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.task.AbstractTask#doTask(java.util.Map)
     */
    @Override
    protected void doTask(Map<Object, Object> paramMap) throws Exception {
        // TODO Auto-generated method stub
        if (paramMap != null) {
            Object commentId = paramMap.get("commentId");
            if (commentId == null || StringUtils.isBlank(commentId.toString())) {
                return;
            }
            commentService.commentReplyNotice(new Long(commentId.toString()));
        }
    }
}
