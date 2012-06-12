/**
 * 
 */
package org.mspring.mlog.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.entity.Photo;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since Dec 29, 2011
 */
public interface PhotoService {
    public final static int PREVIEW_WIDTH = 200;
    public final static int PREVIEW_HEIGHT = 200;

    public final static int DEFAULT_MAX_WIDTH = 1440;
    public final static int DEFAULT_MAX_HEIGHT = 900;

    /**
     * 上传图片
     * 
     * @param request
     * @param path
     *            保存的完整路径
     * @param album
     *            相册编号
     * @param autoRotate
     *            是否自动旋转
     * @throws IOException
     */
    void createPhoto(HttpServletRequest request, String path, Long album, boolean autoRotate) throws IOException;

    /**
     * 上传图片
     * 
     * @param request
     * @param path
     * @param album
     *            相册编号
     * @throws IOException
     */
    void createPhoto(HttpServletRequest request, String path, Long album) throws IOException;

    /**
     * 删除照片
     * @param basePath
     * @param id
     */
    void deletePhoto(String basePath, Long id);

    /**
     * 删除照片
     * @param basePath
     * @param ids
     */
    void deletePhoto(String basePath, Long[] ids);

    /**
     * 根据编号获取照片
     * @param id
     * @return
     */
    Photo findPhotoById(Long id);

    /**
     * 查询照片
     * @param page
     * @param queryCriterion
     * @return
     */
    Page<Photo> queryPhoto(Page<Photo> page, QueryCriterion queryCriterion);
    
    /**
     * 相册中是否存在照片
     * @param albumId
     * @return
     */
    boolean hasPhotoInAlbum(Long albumId);
    
    
    /**
     * 查找相册中的照片
     * @param albumId
     * @return
     */
    List<Photo> findPhotosByAlbum(Long albumId);
    
    /**
     * 查找相册附近的照片
     * @param albumId
     * @param currentPhotoId
     * @param length
     * @return
     */
    List<Photo> findNearPhotos(Long albumId, Long currentPhotoId, int length);
}