/**
 * 
 */
package org.mspring.mlog.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mspring.mlog.api.bae.bcs.BCSUtils;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.entity.Size;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectory;

/**
 * @author Gao Youbo
 * @since 2012-11-22
 * @Description
 * @TODO
 */
public class PhotoUtils {

    private static final Logger log = Logger.getLogger(PhotoUtils.class);

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
    public static MultipartFile getPhotoMultipartFile(HttpServletRequest request) {
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

    /**
     * 从MultipartFile中读取BufferedImage
     * 
     * @param mf
     * @return
     */
    public static BufferedImage getBufferedImage(MultipartFile mf) {
        try {
            return ImageIO.read(mf.getInputStream());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片保存的名称
     * 
     * @param mf
     * @return
     */
    public static String getPhotoSaveName(MultipartFile mf) {
        return StringUtils.getFileName() + "." + StringUtils.getFileExtend(mf.getOriginalFilename());
    }

    /**
     * 图片保存的路径
     * 
     * @param photoSaveName
     * @return
     */
    public static String getPhotoSavePath(String photoSaveName) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return "/photos/" + year + "/" + month + "/" + day + "/" + photoSaveName;
    }

    /**
     * 获取缩略图保存的路径
     * 
     * @param photoSavePath
     *            原图片保存的路径
     * @return
     */
    public static String getPhotoPreviewSavePath(String photoSavePath) {
        photoSavePath = photoSavePath.replace("\\", "/");
        String ext = StringUtils.getFileExtend(photoSavePath);
        String path = StringUtils.substringBeforeLast(photoSavePath, "/");
        String name = photoSavePath.substring(photoSavePath.lastIndexOf("/"), photoSavePath.lastIndexOf("."));
        return path + "/preview" + name + "_preview." + ext;
    }

    /**
     * 获取文件的contentType
     * 
     * @param mf
     * @return
     */
    public static String getContentType(MultipartFile mf) {
        return mf.getContentType();
    }

    /**
     * 获取文件呃contentLength
     * 
     * @param mf
     * @return
     */
    public static long getContentLength(MultipartFile mf) {
        return mf.getSize();
    }

    /**
     * 按照特定比例缩放
     * 
     * @param sourceW 原图宽度
     * @param sourceH 原图高度
     * @param targetW 目标高度
     * @param targetH 目标宽度
     */
    public static Size getZoomSize(Integer sourceW, Integer sourceH, Integer targetW, Integer targetH) {
//        Size size = getMaxSize();
//        double targetW = size.getWidth();
//        double targetH = size.getHeight();
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

    /**
     * 是否限定图片大小
     * 
     * @param sourceWidth
     *            原图片宽度
     * @param sourceHeight
     *            原图片高度
     * @return
     */
    public static boolean isLimitSize(int sourceWidth, int sourceHeight) {
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

    /**
     * 获取最大size
     * 
     * @return
     */
    public static Size getMaxSize() {
        OptionService optionService = ServiceFactory.getOptionService();
        String string_ori_width = optionService.getOption("photo_max_width");
        String string_ori_height = optionService.getOption("photo_max_height");
        int MAX_WIDTH = StringUtils.isBlank(string_ori_width) ? DEFAULT_MAX_WIDTH : Integer.parseInt(string_ori_width);
        int MAX_HEIGHT = StringUtils.isBlank(string_ori_height) ? DEFAULT_MAX_HEIGHT : Integer.parseInt(string_ori_height);
        return new Size(MAX_WIDTH, MAX_HEIGHT);
    }

    /**
     * 按规定比例缩放图片
     * 
     * @param source
     * @param targetW
     * @param targetH
     * @return
     */
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
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

    /**
     * 上传Photo
     * 
     * @param image
     * @return
     * @throws IOException
     */
    public static String uploadPhoto(BufferedImage image, String fileName) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", bos);
        byte[] bytearray = bos.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(bytearray);

        BCSUtils.putFile(fileName, inputStream, "image/JPEG", bytearray.length);

        inputStream.close();
        bos.close();
        return BCSUtils.getURL(fileName);
    }

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
    public static Photo getPhotoEntity(BufferedImage image, String url, long album, MultipartFile mf, String photoSaveName, String photoSavePath, String previewUrl, String previewSavePath) {
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

    /**
     * 填充Exif信息
     * 
     * @param mf
     * @param photo
     * @return
     */
    public static Photo fillExifInfo(MultipartFile mf, Photo photo) {
        // Reading EXIF
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
