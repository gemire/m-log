/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.entity.Size;
import org.mspring.mlog.service.FileService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.service.PhotoUploadService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gao Youbo
 * @since 2012-10-17
 * @Description
 * @TODO
 */
@Service
@Transactional
public class PhotoServiceImpl extends AbstractServiceSupport implements PhotoService {

    private static final Logger log = Logger.getLogger(PhotoServiceImpl.class);

    @Autowired
    private PhotoUploadService photoUploadService;
    @Autowired
    private FileService fileService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#createPhoto(javax.servlet.http.
     * HttpServletRequest, java.lang.Long)
     */
    @Override
    public Photo createPhoto(HttpServletRequest request, Long album) {
        // TODO Auto-generated method stub
        MultipartFile mf = photoUploadService.getPhotoMultipartFile(request);
        BufferedImage image = photoUploadService.getBufferedImage(mf);
        if (photoUploadService.isLimitSize(image.getWidth(), image.getHeight())) {
            Size maxSize = photoUploadService.getMaxSize();
            Size size = photoUploadService.getZoomSize(image.getWidth(), image.getHeight(), (int) maxSize.getWidth(), (int) maxSize.getHeight());
            image = ImageUtils.resize(image, (int) size.getWidth(), (int) size.getHeight());
        }
        String photoSaveName = photoUploadService.getPhotoSaveName(mf);
        String photoSavePath = photoUploadService.getPhotoSavePath(photoSaveName);
        String url = "";
        try {
            url = photoUploadService.uploadPhoto(image, photoSavePath);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        String photoPreviewSavenPath = photoUploadService.getPhotoPreviewSavePath(photoSavePath);
        Size previewSize = photoUploadService.getZoomSize(image.getWidth(), image.getHeight(), photoUploadService.DEFAULT_MAX_PREVIEW_WIDTH, photoUploadService.DEFAULT_MAX_PREVIEW_HEIGHT);
        BufferedImage previewImage = photoUploadService.resize(image, (int) previewSize.getWidth(), (int) previewSize.getHeight());
        String previewUrl = "";
        try {
            previewUrl = photoUploadService.uploadPhoto(previewImage, photoPreviewSavenPath);
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        Photo photo = photoUploadService.getPhotoEntity(image, url, album, mf, photoSaveName, photoSavePath, previewUrl, photoPreviewSavenPath);
        Serializable s = create(photo);
        return getPhotoById((Long) s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoService#getPhotoById(java.lang.Long)
     */
    @Override
    public Photo getPhotoById(Long photoId) {
        // TODO Auto-generated method stub
        return (Photo) getById(Photo.class, photoId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoService#deletePhoto(java.lang.Long[])
     */
    @Override
    public void deletePhoto(Long... id) {
        // TODO Auto-generated method stub
        if (id == null) {
            return;
        }
        Photo photo = null;
        for (Long i : id) {
            photo = getPhotoById(i);
            try {
                fileService.deleteFile(photo.getFileName());
                fileService.deleteFile(photo.getPreviewFileName());
            }
            catch (Exception e) {
                // TODO: handle exception
                log.warn("remove file from bcs error, filename = " + photo.getFileName(), e);
                continue;
            }
            // 如果有相册使用该图片为封面，那么取消相册封面
            executeUpdate("update Album album set album.cover.id = null where album.cover.id = ?", i);
            remove(photo);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#findPhoto(org.mspring.platform.
     * persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<Photo> findPhoto(Page<Photo> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#findPhoto(org.mspring.platform.
     * persistence.support.Page, java.lang.String)
     */
    @Override
    public Page<Photo> findPhoto(Page<Photo> page, String queryString) {
        // TODO Auto-generated method stub
        return findPage(queryString, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#findPhoto(org.mspring.platform.
     * persistence.support.Page, java.lang.String, java.lang.Object[])
     */
    @Override
    public Page<Photo> findPhoto(Page<Photo> page, String queryString, Object... params) {
        // TODO Auto-generated method stub
        return findPage(queryString, page, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#updatePhoto(org.mspring.mlog.entity
     * .Photo)
     */
    @Override
    public void updatePhoto(Photo photo) {
        // TODO Auto-generated method stub
        photo.setModifyTime(new Date());
        update(photo);
    }

}
