/*
 * 
 */
package org.mspring.mlog.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mspring.mlog.entity.Photo;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectory;
import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;

/**
 * 图像处理工具类
 * 
 * @author Gao Youbo
 * @since Dec 29, 2011 TODO: 如果解决图象经过处理后丢失EXIF的问题。
 */
public class ImageUtils {

    private static Log log = LogFactory.getLog(ImageUtils.class);

    /**
     * 保存图片，并以一定的比例缩放
     * @param formImage
     * @param saveImage
     * @param maxWidth
     * @param maxHeight
     * @throws IOException
     */
    public static void saveImage(File formImage, File saveImage, int maxWidth, int maxHeight) throws IOException {
        BufferedImage srcImage;
        // String ex =
        // fromFileStr.substring(fromFileStr.indexOf("."),fromFileStr.length());
        String imgType = "JPEG";
        if (formImage.getName().toLowerCase().endsWith(".png")) {
            imgType = "PNG";
        }
        //如果保存路径不存在，那么创建
        if (!saveImage.getParentFile().exists()) {
            saveImage.getParentFile().mkdirs();
        }
        //处理GIF动态图片
        if (formImage.getName().toLowerCase().endsWith(".gif")) {
            GifImage gifImage = GifDecoder.decode(formImage);
            GifImage newGif = GifTransformer.resize(gifImage, maxWidth, maxHeight, false);
            GifEncoder.encode(newGif, saveImage);
        }
        else {
            srcImage = ImageIO.read(formImage);
            if (maxWidth > 0 || maxHeight > 0) {
                srcImage = resize(srcImage, maxWidth, maxHeight);
            }
            ImageIO.write(srcImage, imgType, saveImage);
        }
    }

    /**
     * 在原照片文件基础上进行旋转
     * 
     * @param img_fn
     * @param orient
     * @return
     * @throws IOException
     */
    public static boolean rotateImage(String img_fn, int orient) throws IOException {
        return rotateImage(img_fn, orient, img_fn);
    }

    /**
     * 根据照片的拍摄对照片进行方向校正 目前只支持两种方向的旋转 3: 180度 6: 顺时针旋转90度 8: 顺时针旋转270度或者逆时针旋转90度
     * 
     * @param img_fn
     * @param orient
     * @throws IOException
     */
    public static boolean rotateImage(String img_fn, int orient, String dest_fn) throws IOException {
        double radian = 0;
        switch (orient) {
        case 3:
            radian = 180.0;
            break;
        case 6:
            radian = 90.0;
            break;
        case 8:
            radian = 270.0;
            break;
        default:
            return false;
        }
        BufferedImage old_img = (BufferedImage) ImageIO.read(new File(img_fn));
        int width = old_img.getWidth();
        int height = old_img.getHeight();

        BufferedImage new_img = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = new_img.createGraphics();

        AffineTransform origXform = g2d.getTransform();
        AffineTransform newXform = (AffineTransform) (origXform.clone());
        // center of rotation is center of the panel
        double xRot = 0;
        double yRot = 0;
        switch (orient) {
        case 3:
            xRot = width / 2.0;
            yRot = height / 2.0;
        case 6:
            xRot = height / 2.0;
            yRot = xRot;
            break;
        case 8:
            xRot = width / 2.0;
            yRot = xRot;
            break;
        default:
            return false;
        }
        newXform.rotate(Math.toRadians(radian), xRot, yRot);

        g2d.setTransform(newXform);
        // draw image centered in panel
        g2d.drawImage(old_img, 0, 0, null);
        // Reset to Original
        g2d.setTransform(origXform);

        FileOutputStream out = new FileOutputStream(dest_fn);
        try {
            ImageIO.write(new_img, "JPG", out);
        } finally {
            out.close();
        }
        return true;
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
            if (!metadata.containsDirectory(ExifDirectory.class))
                return photo;
            Directory exif = metadata.getDirectory(ExifDirectory.class);
            if (exif != null) {
                if (exif.containsTag(ExifDirectory.TAG_ORIENTATION))
                    photo.setOrientation(exif.getInt(ExifDirectory.TAG_ORIENTATION));
                if (exif.containsTag(ExifDirectory.TAG_MAKE))
                    photo.setManufacturer(exif.getString(ExifDirectory.TAG_MAKE));
                if (exif.containsTag(ExifDirectory.TAG_MODEL))
                    photo.setModel(exif.getString(ExifDirectory.TAG_MODEL));
                if (exif.containsTag(ExifDirectory.TAG_APERTURE))
                    photo.setAperture(exif.getDescription(ExifDirectory.TAG_APERTURE));
                if (exif.containsTag(ExifDirectory.TAG_COLOR_SPACE))
                    photo.setColorSpace(exif.getDescription(ExifDirectory.TAG_COLOR_SPACE));
                if (exif.containsTag(ExifDirectory.TAG_EXPOSURE_BIAS))
                    photo.setExposureBias(exif.getDescription(ExifDirectory.TAG_EXPOSURE_BIAS));
                if (exif.containsTag(ExifDirectory.TAG_FOCAL_LENGTH))
                    photo.setFocalLength(exif.getDescription(ExifDirectory.TAG_FOCAL_LENGTH));
                if (exif.containsTag(ExifDirectory.TAG_ISO_EQUIVALENT))
                    photo.setISO(exif.getInt(ExifDirectory.TAG_ISO_EQUIVALENT));
                if (exif.containsTag(ExifDirectory.TAG_SHUTTER_SPEED))
                    photo.setShutter(exif.getDescription(ExifDirectory.TAG_SHUTTER_SPEED));
                if (exif.containsTag(ExifDirectory.TAG_EXPOSURE_TIME))
                    photo.setExposureTime(exif.getDescription(ExifDirectory.TAG_EXPOSURE_TIME));
                return photo;
            }
        } catch (Exception e) {
            log.error("Reading EXIF of " + img_path + " failed.", e);
        }
        return photo;
    }

    /**
     * 判断是否为图片
     * 
     * @param extendName
     * @return
     */
    public static boolean isImage(String extendName) {
        return "png".equalsIgnoreCase(extendName) || "jpg".equalsIgnoreCase(extendName) || "jpeg".equalsIgnoreCase(extendName) || "bmp".equalsIgnoreCase(extendName) || "gif".equalsIgnoreCase(extendName);
    }

    /**
     * 判断是否为JPG图片
     * 
     * @param fn
     * @return
     */
    public static boolean isJPG(String fn) {
        if (fn == null)
            return false;
        String s_fn = fn.toLowerCase();
        return s_fn.endsWith("jpg") || s_fn.endsWith("jpge");
    }

    /**
     * 判断是否为JPG图片
     * 
     * @param fn
     * @return
     */
    public static boolean isBMP(String fn) {
        if (fn == null)
            return false;
        String s_fn = fn.toLowerCase();
        return s_fn.endsWith("bmp");
    }

    public static String BMP_TO_JPG(String imgPath) throws IOException {
        File fOrigionalImage = new File(imgPath);
        BufferedImage oldImage = (BufferedImage) ImageIO.read(fOrigionalImage);
        String jpgName = imgPath + ".jpg";
        FileOutputStream newimage = new FileOutputStream(jpgName);
        try {
            if (ImageIO.write(oldImage, "jpg", newimage))
                return jpgName;
        } finally {
            if (newimage != null)
                newimage.close();
        }
        return null;
    }

    /**
     * 按规定比例缩放图片
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
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
        if (sx > sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else
            target = new BufferedImage(targetW, targetH, type);
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

}
