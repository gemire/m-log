/**
 * 
 */
package org.mspring.platform.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.IImageMetadata;

import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;
import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class ImageUtils {

    public static final class ImageType {
        public static final String PNG = "PNG";
        public static final String JPG = "JPG";
        public static final String JPEG = "JPEG";
        public static final String BMP = "BMP";
        public static final String GIF = "GIF";

        public static final String[] types = { PNG, JPG, JPEG, BMP, GIF };
    }

    /**
     * 判断是否为图片
     * 
     * @param extendName
     * @return
     */
    public static boolean isImage(String extendName) {
        for (String type : ImageType.types) {
            if (type.equalsIgnoreCase(extendName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取图片尺寸
     * 
     * @param stream
     * @return
     */
    public static Size getImageSize(InputStream stream) {
        try {
            BufferedImage sourceImg = ImageIO.read(stream);
            return new Size(sourceImg.getWidth(), sourceImg.getHeight());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Size(0, 0);
        }
    }

    /**
     * 获取图片尺寸
     * 
     * @param file
     * @return
     */
    public static Size getImageSize(File file) {
        try {
            BufferedImage sourceImg = ImageIO.read(file);
            return new Size(sourceImg.getWidth(), sourceImg.getHeight());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Size(0, 0);
        }
    }

    /**
     * 保存图片，并以一定的比例缩放
     * 
     * @param formImage
     * @param saveImage
     * @param maxWidth
     * @param maxHeight
     * @throws IOException
     */
    public static void saveImage(File formImage, File saveImage, int maxWidth, int maxHeight) throws IOException {
        BufferedImage srcImage;
        String imgType = ImageType.JPG;
        if (formImage.getName().toUpperCase().endsWith(ImageType.PNG)) {
            imgType = ImageType.PNG;
        }
        // 如果保存路径不存在，那么创建
        if (!saveImage.getParentFile().exists()) {
            saveImage.getParentFile().mkdirs();
        }
        // 处理GIF动态图片
        if (formImage.getName().toUpperCase().endsWith(ImageType.GIF)) {
            GifImage gifImage = GifDecoder.decode(formImage);
            GifImage newGif = GifTransformer.resize(gifImage, maxWidth, maxHeight, false);
            GifEncoder.encode(newGif, saveImage);
        } else {
            srcImage = ImageIO.read(formImage);
            if (maxWidth > 0 || maxHeight > 0) {
                srcImage = resize(srcImage, maxWidth, maxHeight);
            }
            ImageIO.write(srcImage, imgType, saveImage);
        }
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
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }

        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    /**
     * 图片缩放
     * 
     * @param originalFile
     *            源文件
     * @param thumnailFile
     *            缩放后文件
     * @param newWidth
     *            缩放后宽度
     * @param newHeight
     *            缩放后高度
     * @param format
     *            缩放后图片格式
     */
    public static void resize(File originalFile, File thumnailFile, int newWidth, int newHeight, String format) {
        try {
            resize(new FileInputStream(originalFile), new FileOutputStream(thumnailFile), newWidth, newHeight, format);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片缩放
     * 
     * @param originalStream
     *            源文件
     * @param thumnailStream
     *            缩放后文件
     * @param newWidth
     *            缩放后宽度
     * @param newHeight
     *            缩放后高度
     * @param format
     *            缩放后图片格式
     */
    public static void resize(InputStream originalStream, OutputStream thumnailStream, int newWidth, int newHeight, String format) {
        try {
            BufferedImage originalImage = ImageIO.read(originalStream);

            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            if ((width > 0) || (height > 0)) {
                ResampleOp resampleOp = new ResampleOp(newWidth, newHeight);
                resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
                BufferedImage thumbnailImage = resampleOp.filter(originalImage, null);
                ImageIO.write(thumbnailImage, format, thumnailStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
     * 图片剪切
     * 
     * @param source
     *            原图片
     * @param startX
     *            开始位置X坐标
     * @param startY
     *            开始位置Y坐标
     * @param endX
     *            结束位置X坐标
     * @param endY
     *            结束位置Y坐标
     */
    public BufferedImage crop(BufferedImage source, int startX, int startY, int endX, int endY) {
        int width = source.getWidth();
        int height = source.getHeight();

        if (startX == -1) {
            startX = 0;
        }

        if (startY == -1) {
            startY = 0;
        }

        if (endX == -1) {
            endX = width - 1;
        }

        if (endY == -1) {
            endY = height - 1;
        }

        BufferedImage result = new BufferedImage(endX - startX + 1, endY - startY + 1, 4);

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int rgb = source.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }

    /**
     * 获取图片的metadata信息
     * 
     * @param inputStream
     * @return
     * @throws ImageReadException
     * @throws IOException
     */
    public static IImageMetadata getMetadataInfo(InputStream inputStream) throws ImageReadException, IOException {
        return Imaging.getMetadata(inputStream, "");
    }

    /**
     * 将base64加密文件转换为图片,并保存
     * 
     * @param base64
     * @param path
     */
    public static void saveBase64AsImage(String base64, String path) {
        try {
            // Base64解码
            byte[] bytes = StringUtils.decodeBASE64(base64.getBytes());
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
