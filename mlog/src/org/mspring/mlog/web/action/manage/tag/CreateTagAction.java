package org.mspring.mlog.web.action.manage.tag;

import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.entity.Tag;

public class CreateTagAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = -2729296532608615647L;
    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String execute() {
        tagService.createTag(tag);
        return SUCCESS;
    }
}
