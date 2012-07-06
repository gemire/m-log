/**
 * 
 */
package org.mspring.mlog.web.action.manage.photo;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Jan 8, 2012
 */
public class QueryPhotoAction extends AbstractManageAction {
    private Long albumId;
    private Album album;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String execute() {
        if (albumId == null) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "请先选择一个相册");
            return PROMPT;
        }
        album = albumService.findAlbumById(albumId);
        return SUCCESS;
    }
}
