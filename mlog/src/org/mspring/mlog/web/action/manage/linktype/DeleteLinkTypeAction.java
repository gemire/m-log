/**
 * 
 */
package org.mspring.mlog.web.action.manage.linktype;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class DeleteLinkTypeAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 5438240888281919770L;
    private Long[] id;

    public Long[] getId() {
        return id;
    }

    public void setId(Long[] id) {
        this.id = id;
    }

    public String execute() {
        linkTypeService.deleteLinkType(id);
        return SUCCESS;
    }
}
