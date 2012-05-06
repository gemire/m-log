/**
 * 
 */
package org.mspring.mlog.web.action.manage.linktype;

import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class CreateLinkTypeAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 6273907809332679634L;
    private LinkType linkType;

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public String toCreateLinkType() {
        return SUCCESS;
    }

    public String doCreateLinkType() {
        linkTypeService.createLinkType(linkType);
        return SUCCESS;
    }
}
