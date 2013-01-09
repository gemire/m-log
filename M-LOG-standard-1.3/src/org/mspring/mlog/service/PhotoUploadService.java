/**
 * 
 */
package org.mspring.mlog.service;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.entity.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gao Youbo
 * @since 2012-12-11
 * @Description
 * @TODO
 */
public interface PhotoUploadService {
    public static final int DEFAULT_MAX_WIDTH = 1440;
    public static final int DEFAULT_MAX_HEIGHT = 900;
    public static final int DEFAULT_MAX_PREVIEW_WIDTH = 250;
    public static final int DEFAULT_MAX_PREVIEW_HEIGHT = 250;

    /**
     * 从request中获取MultipartFile
     * 
     * @param request
     * @return
     */
    public MultipartFile getPhotoMultipartFile(HttpServletRequest request);

    /**
     * 从MultipartFile中读取BufferedImage
     * 
     * @param mf
     * @return
     */
    public BufferedImage getBufferedImage(MultipartFile mf);

    /**
     * 图片保存的名称
     * 
     * @param mf
     * @return
     */
    public String getPhotoSaveName(MultipartFile mf);

    /**
     * 图片保存的路径
     * 
     * @param photoSaveName
     * @return
     */
    public String getPhotoSavePath(String photoSaveName);

    /**
     * 获取缩略图保存的路径
     * 
     * @param photoSavePath
     * @return
     */
    public String getPhotoPreviewSavePath(String photoSavePath);

    /**
     * 获取文件的contentType
     * 
     * @param mf
     * @return
     */
    public String getContentType(MultipartFile mf);

    /**
     * 获取文件的contentType
     * 
     * @param mf
     * @return
     */
    public long getContentLength(MultipartFile mf);

    /**
     * 按照特定比例缩放
     * 
     * @param sourceW
     *            原图宽度
     * @param sourceH
     *            原图高度
     * @param targetW
     *            目标高度
     * @param targetH
     *            目标宽度
     */
    public Size getZoomSize(Integer sourceW, Integer sourceH, Integer targetW, Integer targetH);

    /**
     * 是否限定图片大小
     * 
     * @param sourceWidth
     *            原图片宽度
     * @param sourceHeight
     *            原图片高度
     * @return
     */
    public boolean isLimitSize(int sourceWidth, int sourceHeight);

    /**
     * 获取最大size
     * 
     * @return
     */
    public Size getMaxSize();

    /**
     * 按规定比例缩放图片
     * 
     * @param source
     * @param targetW
     * @param targetH
     * @return
     */
    public BufferedImage resize(BufferedImage source, int targetW, int targetH);

    /**
     * 上传Photo
     * 
     * @param image
     * @return
     * @throws IOException
     */
    public String uploadPhoto(BufferedImage image, String fileName) throws IOException;

    /**
     * 获取图片实体对象
     * 
     * @param image
     * @param url
     * @param album
     * @param mf
     * @param photoSaveName
     * @param photoSavePath
     * @return
     */
    public Photo getPhotoEntity(BufferedImage image, String url, long album, MultipartFile mf, String photoSaveName, String photoSavePath, String previewUrl, String previewSavePath);

    /**
     * 填充Exif信息
     * 
     * @param mf
     * @param photo
     * @return
     */
    public Photo fillExifInfo(MultipartFile mf, Photo photo);
}
