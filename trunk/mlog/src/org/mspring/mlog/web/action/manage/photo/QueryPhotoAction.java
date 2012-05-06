/**
 * 
 */
package org.mspring.mlog.web.action.manage.photo;

import java.util.Map;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.photo.query.PhotoQueryCriterion;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.mspring.platform.dao.support.Sort;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * @author Gao Youbo
 * @since Jan 8, 2012
 */
public class QueryPhotoAction extends AbstractManageAction implements QueryParameterAware {

    private Map queryParameters;
    private QueryCriterion queryCriterion;

    public String getEncodedQueryParams() {
        if (queryCriterion != null) {
            return queryCriterion.getQueryParamsAsString();
        }
        return null;
    }

    public void setQueryParameters(Map queryParameters) {
        this.queryParameters = queryParameters;
    }

    private Album album;
    private Photo photo;
    private Page<Photo> photoPage = new Page<Photo>();

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Page<Photo> getPhotoPage() {
        return photoPage;
    }

    public void setPhotoPage(Page<Photo> photoPage) {
        this.photoPage = photoPage;
    }

    public String execute() {
        if (photo == null || photo.getAlbum() == null || photo.getAlbum().getId() == 0) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "请先选择一个相册");
            return PROMPT;
        }
        if (photoPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            photoPage.setSort(sort);
        }
        photoPage.setPageSize(50);
        
        queryCriterion = new PhotoQueryCriterion(queryParameters);
        photoPage = photoService.queryPhoto(photoPage, queryCriterion);
        
        album = albumService.findAlbumById(photo.getAlbum().getId());
        return SUCCESS;
    }
}
