/**
 * 
 */
package org.mspring.mlog.web.action.manage.photo;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Jan 10, 2012
 */
public class DeletePhotoAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long albumId;
    private Long[] photoItems;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long[] getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(Long[] photoItems) {
        this.photoItems = photoItems;
    }

    public String execute() {
        String basePath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("");
        photoService.deletePhoto(basePath, photoItems);
        return SUCCESS;
    }
}
