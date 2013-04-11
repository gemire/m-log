/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.entity.Size;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoUploadService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectory;

/**
 * @author Gao Youbo
 * @since 2012-12-11
 * @Description
 * @TODO
 */
public abstract class AbstractPhotoUploadService implements PhotoUploadService {

    private static final Logger log = Logger.getLogger(AbstractPhotoUploadService.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoUploadService#getPhotoMultipartFile(javax
     * .servlet.http.HttpServletRequest)
     */
    @Override
    public MultipartFile getPhotoMultipartFile(HttpServletRequest request) {
        // TODO Auto-generated method stub
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        if (fileMap == null || fileMap.size() < 1) {
            return null;
        }
        MultipartFile mf = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            mf = entity.getValue();
            break;
        }
        return mf;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoUploadService#getBufferedImage(org.
     * springframework.web.multipart.MultipartFile)
     */
    @Override
    public BufferedImage getBufferedImage(MultipartFile mf) {
        // TODO Auto-generated method stub
        try {
            return ImageIO.read(mf.getInputStream());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoUploadService#getPhotoSaveName(org.
     * springframework.web.multipart.MultipartFile)
     */
    @Override
    public String getPhotoSaveName(MultipartFile mf) {
        // TODO Auto-generated method stub
        return StringUtils.getFileName() + "." + StringUtils.getFileExtend(mf.getOriginalFilename());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoUploadService#getPhotoSavePath(java.lang
     * .String)
     */
    @Override
    public String getPhotoSavePath(String photoSaveName) {
        // TODO Auto-generated method stub
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return "/photos/" + year + "/" + month + "/" + day + "/" + photoSaveName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoUploadService#getPhotoPreviewSavePath(java
     * .lang.String)
     */
    @Override
    public String getPhotoPreviewSavePath(String photoSavePath) {
        // TODO Auto-generated method stub
        photoSavePath = photoSavePath.replace("\\", "/");
        String ext = StringUtils.getFileExtend(photoSavePath);
        String path = StringUtils.substringBeforeLast(photoSavePath, "/");
        String name = photoSavePath.substring(photoSavePath.lastIndexOf("/"), photoSavePath.lastIndexOf("."));
        return path + "/preview" + name + "_preview." + ext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoUploadService#getContentType(org.
     * springframework.web.multipart.MultipartFile)
     */
    @Override
    public String getContentType(MultipartFile mf) {
        // TODO Auto-generated method stub
        return mf.getContentType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoUploadService#getContentLength(org.
     * springframework.web.multipart.MultipartFile)
     */
    @Override
    public long getContentLength(MultipartFile mf) {
        // TODO Auto-generated method stub
        return mf.getSize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoUploadService#getZoomSize(java.lang.Integer
     * , java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Size getZoomSize(Integer sourceW, Integer sourceH, Integer targetW, Integer targetH) {
        // TODO Auto-generated method stub
        // Size size = getMaxSize();
        // double targetW = size.getWidth();
        // double targetH = size.getHeight();
        double sx = (double) targetW / sourceW;
        double sy = (double) targetH / sourceH;
        if (sx > sy) {
            sx = sy;
            targetW = (int) (sx * sourceW);
        }
        else {
            sy = sx;
            targetH = (int) (sy * sourceH);
        }
        return new Size(targetW, targetH);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoUploadService#isLimitSize(int, int)
     */
    @Override
    public boolean isLimitSize(int sourceWidth, int sourceHeight) {
        // TODO Auto-generated method stub
        OptionService optionService = ServiceFactory.getOptionService();
        boolean isLimit = "true".equals(optionService.getOption("photo_islimit_size"));
        if (!isLimit) {
            return false;
        }

        Size size = getMaxSize();
        // 原始图片的大小是否超出限定范围
        if (sourceWidth < size.getWidth() && sourceHeight < size.getHeight()) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoUploadService#getMaxSize()
     */
    @Override
    public Size getMaxSize() {
        // TODO Auto-generated method stub
        OptionService optionService = ServiceFactory.getOptionService();
        String string_ori_width = optionService.getOption("photo_max_width");
        String string_ori_height = optionService.getOption("photo_max_height");
        int MAX_WIDTH = StringUtils.isBlank(string_ori_width) ? DEFAULT_MAX_WIDTH : Integer.parseInt(string_ori_width);
        int MAX_HEIGHT = StringUtils.isBlank(string_ori_height) ? DEFAULT_MAX_HEIGHT : Integer.parseInt(string_ori_height);
        return new Size(MAX_WIDTH, MAX_HEIGHT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PhotoUploadService#resize(java.awt.image.
     * BufferedImage, int, int)
     */
    @Override
    public BufferedImage resize(BufferedImage source, int targetW, int targetH) {
        // TODO Auto-generated method stub
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        }
        else target = new BufferedImage(targetW, targetH, type);
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoUploadService#getPhotoEntity(java.awt.image
     * .BufferedImage, java.lang.String, long,
     * org.springframework.web.multipart.MultipartFile, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Photo getPhotoEntity(BufferedImage image, String url, long album, MultipartFile mf, String photoSaveName, String photoSavePath, String previewUrl, String previewSavePath) {
        // TODO Auto-generated method stub
        Photo photo = new Photo();
        photo.setWidth(image.getWidth());
        photo.setHeight(image.getHeight());
        photo.setAlbum(new Album(album));
        photo.setName(photoSaveName);
        photo.setFileName(photoSavePath);
        photo.setUrl(url);
        photo.setPreviewUrl(previewUrl);
        photo.setPreviewFileName(previewSavePath);
        photo.setCreateTime(new Date());
        photo.setSize((int) mf.getSize());
        photo = fillExifInfo(mf, photo);
        return photo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PhotoUploadService#fillExifInfo(org.springframework
     * .web.multipart.MultipartFile, org.mspring.mlog.entity.Photo)
     */
    @Override
    public Photo fillExifInfo(MultipartFile mf, Photo photo) {
        // TODO Auto-generated method stub
        try {
            Metadata metadata = JpegMetadataReader.readMetadata(mf.getInputStream());
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
            log.error("Reading EXIF of " + mf.getOriginalFilename() + " failed.", e);
        }
        return photo;
    }

}
