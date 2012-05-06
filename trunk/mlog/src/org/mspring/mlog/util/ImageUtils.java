/*
 * 
 */
package org.mspring.mlog.util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mspring.mlog.entity.Photo;
import org.mspring.platform.utils.StringUtils;

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
     * 生成御览图
     * 
     * @param photo
     *            图片
     * @param previewImageName
     *            预览图名称
     * @param width
     * @param height
     * @throws IOException
     */
    public static String createPreviewImage(File photo, String previewImageName, int width, int height) throws IOException {
        // 判断缩略图路径是否存在, 如果不存在就创建
        File previewFile = new File(previewImageName);
        if (!previewFile.getParentFile().exists()) {
            previewFile.getParentFile().mkdirs();
        }
        String extendName = StringUtils.getFileExtend(previewImageName).toLowerCase();
        FileOutputStream newimage = null;
        InputStream fis = new FileInputStream(photo);
        File file;
        try {
            if ("gif".equalsIgnoreCase(extendName)) {
                GifImage gifImage = GifDecoder.decode(fis);
                fis.close();
                fis = null;
                GifImage newGif = GifTransformer.resize(gifImage, width, height, false);
                newimage = new FileOutputStream(previewImageName);
                GifEncoder.encode(newGif, newimage);
            } else {
                BufferedImage orig_portrait = (BufferedImage) ImageIO.read(fis);
                fis.close();
                fis = null;
                // 统一转成JPG格式
                BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                bi.getGraphics().drawImage(orig_portrait, 0, 0, width, height, null);
                // if (!previewImageName.endsWith(".jpg"))
                // previewImageName += ".jpg";
                newimage = new FileOutputStream(previewImageName);
                ImageIO.write(bi, "jpg", newimage);
            }
        } finally {
            if (newimage != null)
                newimage.close();
            if (fis != null)
                fis.close();
        }
        return previewImageName;
    }

    // /**
    // * 将上传的图片保存到磁盘中
    // *
    // * @param imgFile
    // * @param origionalPath
    // * @throws IOException
    // */
    // public static void writeToFile(FormFile imgFile, String origionalPath)
    // throws IOException {
    // // 保存上传的文件
    // FileOutputStream oldimage = null;
    // InputStream fin = null;
    // byte[] data = new byte[8192];
    // try {
    // fin = imgFile.getInputStream();
    // oldimage = new FileOutputStream(origionalPath);
    // do {
    // int rc = fin.read(data);
    // if (rc == -1)
    // break;
    // oldimage.write(data, 0, rc);
    // if (rc < data.length)
    // break;
    // } while (true);
    // } finally {
    // data = null;
    // if (oldimage != null)
    // oldimage.close();
    // if (fin != null)
    // fin.close();
    // }
    // }

    /**
     * 上传图片
     */
    public void upload(HttpServletRequest request, String origionalPath) throws IOException {
        File saveFile = new File(origionalPath); // 保存的图片完整路径
        File saveFolder = new File(saveFile.getParent()); // 保存图片的文件夹路径
        if (!saveFolder.exists())
            saveFolder.mkdirs();

        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List<FileItem> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException e) {
            // TODO: handle exception
            return;
        }
        Iterator<FileItem> it = fileList.iterator();
        String name = ""; // 文件名
        String extName = ""; // 文件后缀
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                long size = item.getSize();
                String type = item.getContentType();
                if (StringUtils.isBlank(name)) {
                    continue;
                }
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }

                try {
                    item.write(saveFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

}
