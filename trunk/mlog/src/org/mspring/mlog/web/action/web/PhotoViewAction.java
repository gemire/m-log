/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.util.List;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.Photo;

/**
 * @author Gao Youbo
 * @since May 28, 2012
 */
public class PhotoViewAction extends CommonWebActionSupport {
    private Long albumId;
    private Long photoId;
    private Photo photo;
    private List<Photo> photos;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String execute() {
        if (albumId == null) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "照片所在的相册已被删除");
            return PROMPT;
        }
        if (photoId == null) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "该照片不存在或已删除");
            return PROMPT;
        }
        photo = photoService.findPhotoById(photoId);
        //photos = photoService.findPhotosByAlbum(albumId);
        photos = photoService.findNearPhotos(albumId, photoId, 5);
        return SUCCESS;
    }
}
