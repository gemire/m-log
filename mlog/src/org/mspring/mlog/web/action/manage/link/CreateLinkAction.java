/**
 * 
 */
package org.mspring.mlog.web.action.manage.link;

import java.util.List;

import org.mspring.mlog.entity.Link;
import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class CreateLinkAction extends AbstractManageAction {
    private List<LinkType> linkTypes;
    private Link link;

    public List<LinkType> getLinkTypes() {
        return linkTypes;
    }

    public void setLinkTypes(List<LinkType> linkTypes) {
        this.linkTypes = linkTypes;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String toCreateLink() {
        linkTypes = linkTypeService.findAllLinkType();
        return SUCCESS;
    }

    public String doCreateLink() {
        linkService.createLink(link);
        addRebuildTabScript("200003002");
        return SUCCESS;
    }
}
