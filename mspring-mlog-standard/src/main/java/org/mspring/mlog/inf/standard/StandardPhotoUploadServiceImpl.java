/**
 * 
 */
package org.mspring.mlog.inf.standard;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.entity.Size;
import org.mspring.mlog.service.PhotoUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gao Youbo
 * @since 2012-12-19
 * @Description 
 * @TODO 
 */
@Service
public class StandardPhotoUploadServiceImpl implements PhotoUploadService {

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getPhotoMultipartFile(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public MultipartFile getPhotoMultipartFile(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getBufferedImage(org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public BufferedImage getBufferedImage(MultipartFile mf) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getPhotoSaveName(org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public String getPhotoSaveName(MultipartFile mf) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getPhotoSavePath(java.lang.String)
     */
    @Override
    public String getPhotoSavePath(String photoSaveName) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getPhotoPreviewSavePath(java.lang.String)
     */
    @Override
    public String getPhotoPreviewSavePath(String photoSavePath) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getContentType(org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public String getContentType(MultipartFile mf) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getContentLength(org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public long getContentLength(MultipartFile mf) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getZoomSize(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Size getZoomSize(Integer sourceW, Integer sourceH, Integer targetW, Integer targetH) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#isLimitSize(int, int)
     */
    @Override
    public boolean isLimitSize(int sourceWidth, int sourceHeight) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getMaxSize()
     */
    @Override
    public Size getMaxSize() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#resize(java.awt.image.BufferedImage, int, int)
     */
    @Override
    public BufferedImage resize(BufferedImage source, int targetW, int targetH) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#uploadPhoto(java.awt.image.BufferedImage, java.lang.String)
     */
    @Override
    public String uploadPhoto(BufferedImage image, String fileName) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#getPhotoEntity(java.awt.image.BufferedImage, java.lang.String, long, org.springframework.web.multipart.MultipartFile, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Photo getPhotoEntity(BufferedImage image, String url, long album, MultipartFile mf, String photoSaveName, String photoSavePath, String previewUrl, String previewSavePath) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.PhotoUploadService#fillExifInfo(org.springframework.web.multipart.MultipartFile, org.mspring.mlog.entity.Photo)
     */
    @Override
    public Photo fillExifInfo(MultipartFile mf, Photo photo) {
        // TODO Auto-generated method stub
        return null;
    }

}
