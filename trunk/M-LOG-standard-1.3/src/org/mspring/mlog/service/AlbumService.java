/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Album;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-10-15
 * @Description
 * @TODO
 */
public interface AlbumService {
    public Album createAlbum(Album album);

    public void deleteAlbum(Long... idArray);

    public Album getAlbumById(Long id);

    public void updateAlbum(Album album);

    /**
     * 获取相册中的图片数量
     * 
     * @param id
     * @return
     */
    public int getAlbumPhotoCount(Long id);

    public List<Album> findAllAlbum();

    public Page<Album> findAlbum(Page<Album> page, String queryString);

    public Page<Album> findAlbum(Page<Album> page, String queryString, Object... params);

    public Page<Album> findAlbum(Page<Album> page, QueryCriterion criterion);

    /**
     * 验证相册访问权限
     * 
     * @param albumId
     * @param password
     * @return
     */
    public boolean hasPermission(Long albumId, String password);
    
    /**
     * 设置相册封面
     * @param albumId
     * @param photoId
     */
    public void setCover(Long albumId, Long photoId);
}
