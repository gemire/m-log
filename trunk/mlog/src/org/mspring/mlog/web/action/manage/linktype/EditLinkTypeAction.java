/**
 * 
 */
package org.mspring.mlog.web.action.manage.linktype;

import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.entity.LinkType;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class EditLinkTypeAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 5179288778688576993L;
    private Long id;
    private LinkType linkType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public String toEditLinkType() {
        if (id != null) {
            linkType = linkTypeService.findLinkTypeById(id);
        }
        return SUCCESS;
    }

    public String doEditLinkType() {
        linkTypeService.updateLinkType(linkType);
        return SUCCESS;
    }
}
