/**
 * 
 */
package org.mspring.mlog.web.action.manage.link;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class DeleteLinkAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 4537965287694711137L;
    public Long[] id;

    public Long[] getId() {
        return id;
    }

    public void setId(Long[] id) {
        this.id = id;
    }

    public String execute() {
        linkService.deleteLink(id);
        return SUCCESS;
    }
}
