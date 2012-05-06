/**
 * 
 */
package org.mspring.mlog.web.action.manage.link;

import java.util.List;

import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.entity.LinkType;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class EditLinkAction extends AbstractManageAction {
    private Long id;
    private Link link;
    private List<LinkType> linkTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public List<LinkType> getLinkTypes() {
        return linkTypes;
    }

    public void setLinkTypes(List<LinkType> linkTypes) {
        this.linkTypes = linkTypes;
    }

    public String toEditLink() {
        if (id == null) {
            addActionError("请选择要修改链接");
            return INPUT;
        }
        link = linkService.getLinkById(id);
        linkTypes = linkTypeService.findAllLinkType();
        return SUCCESS;
    }

    public String doEditLink() {
        try {
            linkService.updateLink(link);
        }
        catch (Exception e) {
            // TODO: handle exception
            addActionError("链接修改失败");
        }
        addActionMessage("链接修改成功");
        return SUCCESS;
    }
}
