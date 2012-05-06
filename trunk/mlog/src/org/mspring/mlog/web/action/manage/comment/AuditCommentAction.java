/**
 * 
 */
package org.mspring.mlog.web.action.manage.comment;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Apr 13, 2012
 */
public class AuditCommentAction extends AbstractManageAction {

    /**
     * 
     */
    private static final long serialVersionUID = 3897831691208811465L;
    private Long[] commentItems;

    public Long[] getCommentItems() {
        return this.commentItems;
    }

    public void setCommentItems(Long[] commentItems) {
        this.commentItems = commentItems;
    }

    /**
     * 审核通过
     * @return
     */
    public String auditPass(){
        if (commentItems != null && commentItems.length > 0) {
            Comment comment = null;
            for (Long commentId : commentItems) {
                comment = commentService.getCommentById(commentId);
                //如果comment不为空,并且评论转台不为已审核通过, 那么标记该comment为审核通过
                if (comment != null && !(Comment.STATUS_PASS.equals(comment.getStatus()))) {
                    commentService.updateStatus(Comment.STATUS_PASS, commentId);
                }
            }
        }
        setRequestAttribute(Const.REQUEST_PROMPT_DISPATCHER, "queryComment.action");
        setRequestAttribute(Const.REQUEST_PROMPT_INFO, "审核成功");
        return PROMPT;
    }
    
    /**
     * 驳回
     * @return
     */
    public String auditReject(){
        if (commentItems != null && commentItems.length > 0) {
            Comment comment = null;
            for (Long commentId : commentItems) {
                comment = commentService.getCommentById(commentId);
                //如果comment不为空,并且评论转台不为驳回, 那么标记该comment为驳回
                if (comment != null && !(Comment.STATUS_REJECT.equals(comment.getStatus()))) {
                    commentService.updateStatus(Comment.STATUS_REJECT, commentId);
                }
            }
        }
        setRequestAttribute(Const.REQUEST_PROMPT_DISPATCHER, "queryComment.action");
        setRequestAttribute(Const.REQUEST_PROMPT_INFO, "审核成功");
        return PROMPT;
    }
    
    /**
     * 垃圾评论
     * @return
     */
    public String auditDust(){
        if (commentItems != null && commentItems.length > 0) {
            Comment comment = null;
            for (Long commentId : commentItems) {
                comment = commentService.getCommentById(commentId);
                //如果comment不为空,并且评论转台不为垃圾评论, 那么标记该comment为垃圾评论
                if (comment != null && !(Comment.STATUS_DUST.equals(comment.getStatus()))) {
                    commentService.updateStatus(Comment.STATUS_DUST, commentId);
                }
            }
        }
        setRequestAttribute(Const.REQUEST_PROMPT_DISPATCHER, "queryComment.action");
        setRequestAttribute(Const.REQUEST_PROMPT_INFO, "审核成功");
        return PROMPT;
    }

}
