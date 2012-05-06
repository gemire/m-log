/**
 * @since 2011-7-23
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.action.manage.comment;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * 
 * @author Gao Youbo
 */
public class DeleteCommentAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 702783621594415600L;
    private Long[] commentItems;

    public Long[] getCommentItems() {
        return this.commentItems;
    }

    public void setCommentItems(Long[] commentItems) {
        this.commentItems = commentItems;
    }

    public String execute() {
        commentService.deleteComment(commentItems);
        return SUCCESS;
    }
}
