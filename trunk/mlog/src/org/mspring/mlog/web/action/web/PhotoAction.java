/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.util.Map;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.web.action.web.query.PhotoQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * @author Gao Youbo
 * @since May 26, 2012
 */
public class PhotoAction extends CommonWebActionSupport implements QueryParameterAware {

    /**
     * 
     */
    private static final long serialVersionUID = 2948982936593091787L;

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

    private Long albumId;
    private Album album;
    private Page<Photo> photoPage = new Page<Photo>();

    
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

    public Page<Photo> getPhotoPage() {
        return photoPage;
    }

    public void setPhotoPage(Page<Photo> photoPage) {
        this.photoPage = photoPage;
    }

    public String execute() {
        if (albumId == null || albumId == new Long(0)) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "请先选择相册");
            return PROMPT;
        }
        queryCriterion = new PhotoQueryCriterion(queryParameters);
        photoPage = photoService.queryPhoto(photoPage, queryCriterion);
        return SUCCESS;
    }

}
