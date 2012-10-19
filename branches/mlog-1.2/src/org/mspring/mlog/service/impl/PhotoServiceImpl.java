/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.utils.AlbumUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.ImageUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectory;

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
    private OptionService optionService;

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
        return createPhoto(request, album, AlbumUtils.DEFAULT_AUTO_ROTATE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoService#createPhoto(javax.servlet.http.
     * HttpServletRequest, java.lang.Long, boolean)
     */
    @Override
    public Photo createPhoto(HttpServletRequest request, Long album, boolean autoRotate) {
        // TODO Auto-generated method stub
        String path = request.getSession().getServletContext().getRealPath("") + File.separator;

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = ""; // 文件名
        String extendName = ""; // 文件后缀
        String imageAbstractUrl = ""; // 图片相对路径
        String previewImageAbstractUrl = ""; // 预览图相对路径
        String originalImagePath = ""; // 文件完整路径,包括名称
        String previewImagePath = ""; // 缩略图完整路径
        File originalImage;
        if (fileMap == null || fileMap.size() < 1) {
            return null;
        }
        MultipartFile mf = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            mf = entity.getValue();
            break;
        }
        fileName = mf.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        extendName = StringUtils.getFileExtend(fileName);
        imageAbstractUrl = AlbumUtils.getAbstractImageUrl(AlbumUtils.UPLOAD_FOLDER, extendName);
        previewImageAbstractUrl = AlbumUtils.getAbstractPreviewImageUrl(imageAbstractUrl, AlbumUtils.PREVIEW_FOLDER);
        originalImagePath = path + imageAbstractUrl;
        previewImagePath = path + previewImageAbstractUrl;

        originalImage = new File(originalImagePath);
        try {
            if (!originalImage.getParentFile().exists()) {
                originalImage.getParentFile().mkdirs();
            }
            FileCopyUtils.copy(mf.getBytes(), originalImage);
            log.debug("upload image " + originalImagePath + " success");

            BufferedImage sourceImage = ImageIO.read(originalImage);
            Photo photo = new Photo();
            // 限制图片最大大小
            boolean isLimit = "true".equals(optionService.getOption("photo_islimit_size"));
            if (isLimit) {
                String string_ori_width = optionService.getOption("photo_max_width");
                String string_ori_height = optionService.getOption("photo_max_height");
                int MAX_WIDTH = StringUtils.isBlank(string_ori_width) ? AlbumUtils.DEFAULT_MAX_WIDTH : Integer.parseInt(string_ori_width);
                int MAX_HEIGHT = StringUtils.isBlank(string_ori_height) ? AlbumUtils.DEFAULT_MAX_HEIGHT : Integer.parseInt(string_ori_height);

                // 图片大小超过限定范围，调整图片大小
                if (sourceImage.getWidth() > MAX_WIDTH && sourceImage.getHeight() > MAX_HEIGHT) {
                    // targetImage = ImageUtils.resize(sourceImage,
                    // MAX_WIDTH, MAX_HEIGHT);
                    ImageUtils.saveImage(originalImage, originalImage, MAX_WIDTH, MAX_HEIGHT);

                    // 重新读取图片
                    sourceImage = ImageIO.read(originalImage);
                }
            }
            photo.setWidth(sourceImage.getWidth());
            photo.setHeight(sourceImage.getHeight());
            photo.setAlbum(new Album(album));
            photo.setFileName(originalImage.getName());
            photo.setName(originalImage.getName().substring(0, originalImage.getName().lastIndexOf(".")));
            photo.setUrl(imageAbstractUrl);
            photo.setPreviewUrl(previewImageAbstractUrl);

            // 创建缩略图
            ImageUtils.saveImage(originalImage, new File(previewImagePath), AlbumUtils.PREVIEW_WIDTH, AlbumUtils.PREVIEW_HEIGHT);
            // 图片信息插入数据库
            return insertPhotoToDB(photo, originalImagePath, autoRotate);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * 将图片信息插入数据库
     * 
     * @param photo
     * @param originalImage
     * @param oldImage
     * @param autoRotate
     * @throws IOException
     */
    private Photo insertPhotoToDB(Photo photo, String originalImagePath, boolean autoRotate) throws IOException {
        String extendName = StringUtils.getFileExtend(originalImagePath);
        if (ImageUtils.isJPG(extendName)) {
            try {
                photo = fillExifInfo(originalImagePath, photo);
                int orient = photo.getOrientation();
                // 旋转图片
                if (autoRotate && orient > 0 && orient <= 8) {
                    ImageUtils.rotateImage(originalImagePath, orient);
                }
            }
            catch (Exception e) {
                // TODO: handle exception
                log.error("Exception occur when reading EXIF of " + originalImagePath, e);
            }
        }
        else if (ImageUtils.isBMP(extendName)) {
            String jpgName = ImageUtils.BMP_TO_JPG(originalImagePath);
            if (jpgName != null) {
                // 删除bmp文件
                if (new File(originalImagePath).delete()) {
                    originalImagePath = jpgName;
                }
            }
        }

        // 获取图片的基本信息,例如大小尺寸像素等
        File originalImage = new File(originalImagePath);
        BufferedImage oldImage = ImageIO.read(originalImage);

        photo.setColorBit(oldImage.getColorModel().getPixelSize());
        photo.setSize((int) originalImage.length());
        Long id = (Long) create(photo);
        return getPhotoById(id);
    }

    /**
     * 填充图片的EXIF信息
     * 
     * @param img_path
     *            图片文件保存的路径
     * @return 是否有EXIF信息
     */
    public static Photo fillExifInfo(String img_path, Photo photo) {
        // Reading EXIF
        try {
            Metadata metadata = JpegMetadataReader.readMetadata(new File(img_path));
            if (!metadata.containsDirectory(ExifDirectory.class)) return photo;
            Directory exif = metadata.getDirectory(ExifDirectory.class);
            if (exif != null) {
                if (exif.containsTag(ExifDirectory.TAG_ORIENTATION)) photo.setOrientation(exif.getInt(ExifDirectory.TAG_ORIENTATION));
                if (exif.containsTag(ExifDirectory.TAG_MAKE)) photo.setManufacturer(exif.getString(ExifDirectory.TAG_MAKE));
                if (exif.containsTag(ExifDirectory.TAG_MODEL)) photo.setModel(exif.getString(ExifDirectory.TAG_MODEL));
                if (exif.containsTag(ExifDirectory.TAG_APERTURE)) photo.setAperture(exif.getDescription(ExifDirectory.TAG_APERTURE));
                if (exif.containsTag(ExifDirectory.TAG_COLOR_SPACE)) photo.setColorSpace(exif.getDescription(ExifDirectory.TAG_COLOR_SPACE));
                if (exif.containsTag(ExifDirectory.TAG_EXPOSURE_BIAS)) photo.setExposureBias(exif.getDescription(ExifDirectory.TAG_EXPOSURE_BIAS));
                if (exif.containsTag(ExifDirectory.TAG_FOCAL_LENGTH)) photo.setFocalLength(exif.getDescription(ExifDirectory.TAG_FOCAL_LENGTH));
                if (exif.containsTag(ExifDirectory.TAG_ISO_EQUIVALENT)) photo.setISO(exif.getInt(ExifDirectory.TAG_ISO_EQUIVALENT));
                if (exif.containsTag(ExifDirectory.TAG_SHUTTER_SPEED)) photo.setShutter(exif.getDescription(ExifDirectory.TAG_SHUTTER_SPEED));
                if (exif.containsTag(ExifDirectory.TAG_EXPOSURE_TIME)) photo.setExposureTime(exif.getDescription(ExifDirectory.TAG_EXPOSURE_TIME));
                return photo;
            }
        }
        catch (Exception e) {
            log.error("Reading EXIF of " + img_path + " failed.", e);
        }
        return photo;
    }

}
