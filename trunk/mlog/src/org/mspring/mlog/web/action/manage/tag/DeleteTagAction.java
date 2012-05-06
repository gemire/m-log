/**
 * 
 */
package org.mspring.mlog.web.action.manage.tag;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Jan 22, 2012
 */
public class DeleteTagAction extends AbstractManageAction {
    private Long[] tagItems;

    public Long[] getTagItems() {
        return tagItems;
    }

    public void setTagItems(Long[] tagItems) {
        this.tagItems = tagItems;
    }
    
    public String execute(){
        try {
            tagService.deleteTag(tagItems);
        } catch (Exception e) {
            // TODO: handle exception
            addActionMessage(getText("message.failure"));
        }
        addActionMessage(getText("message.success"));
        return SUCCESS;
    }
}
