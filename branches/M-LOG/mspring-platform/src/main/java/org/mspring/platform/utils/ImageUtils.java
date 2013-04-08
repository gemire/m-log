/**
 * 
 */
package org.mspring.platform.utils;

import java.awt.Color;
import java.awt.Font;
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
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.math.NumberUtils;

//import com.gif4j.GifDecoder;
//import com.gif4j.GifEncoder;
//import com.gif4j.GifImage;
//import com.gif4j.GifTransformer;
import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class ImageUtils {
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
        if (fn == null) return false;
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
        if (fn == null) return false;
        String s_fn = fn.toLowerCase();
        return s_fn.endsWith("bmp");
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
        // String ex =
        // fromFileStr.substring(fromFileStr.indexOf("."),fromFileStr.length());
        String imgType = "JPEG";
        if (formImage.getName().toLowerCase().endsWith(".png")) {
            imgType = "PNG";
        }
        // 如果保存路径不存在，那么创建
        if (!saveImage.getParentFile().exists()) {
            saveImage.getParentFile().mkdirs();
        }
//        // 处理GIF动态图片
//        if (formImage.getName().toLowerCase().endsWith(".gif")) {
//            GifImage gifImage = GifDecoder.decode(formImage);
//            GifImage newGif = GifTransformer.resize(gifImage, maxWidth, maxHeight, false);
//            GifEncoder.encode(newGif, saveImage);
//        }
//        else {
            srcImage = ImageIO.read(formImage);
            if (maxWidth > 0 || maxHeight > 0) {
                srcImage = resize(srcImage, maxWidth, maxHeight);
            }
            ImageIO.write(srcImage, imgType, saveImage);
//        }
    }

    public static String BMP_TO_JPG(String imgPath) throws IOException {
        File fOrigionalImage = new File(imgPath);
        BufferedImage oldImage = (BufferedImage) ImageIO.read(fOrigionalImage);
        String jpgName = imgPath + ".jpg";
        FileOutputStream newimage = new FileOutputStream(jpgName);
        try {
            if (ImageIO.write(oldImage, "jpg", newimage)) return jpgName;
        }
        finally {
            if (newimage != null) newimage.close();
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
        }
        else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
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

    public static void resize(File originalFile, File thumnailFile, String dimension) {
        if ((StringUtils.isNotBlank(dimension)) && (StringUtils.contains(dimension, "x"))) {
            int wdth = NumberUtils.createInteger(StringUtils.substringBefore(dimension, "x")).intValue();
            int height = NumberUtils.createInteger(StringUtils.substringAfter(dimension, "x")).intValue();
            resize(originalFile, thumnailFile, wdth, height, "png");
        }
    }

    public static void resize(File originalFile, File thumnailFile, int newWidth, int newHeight, String format) {
        try {
            resize(new FileInputStream(originalFile), new FileOutputStream(thumnailFile), newWidth, newHeight, format);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        }
        catch (IOException e) {
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
        }
        finally {
            out.close();
        }
        return true;
    }

    /**
     * 生成随机验证码
     * 
     * @param outputStream
     *            输出流
     * @param allowValidateString
     *            允许验证码中出现的字符串
     * @return
     */
    public static String validateCode(OutputStream outputStream, String allowValidateString) {
        int width = 60;
        int height = 20;

        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 缓冲区Image
        Graphics2D g = buffImg.createGraphics(); // 获得在缓冲区Image中负责绘画的对象(画笔)他是Graphics的字类型

        // 创建一个随机数生成器类。
        Random random = new Random();

        g.setColor(Color.decode("#ffffff"));
        g.fillRect(0, 0, width, height); // 画一个填充的矩形背景颜色为白色

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        // 设置字体。
        g.setFont(font);

        // 画边框。
        // g.setColor(Color.blue);
        // g.drawRect(0, 0, width - 1, height - 1);

        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(Color.GRAY);
        for (int i = 0; i < 80; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生验证码。
        for (int i = 0; i < 4; i++) {
            // 得到随机产生的验证码数字。
            int randomIndex = random.nextInt(allowValidateString.length());
            if (randomIndex == 0) randomIndex = 1;
            String strRand = allowValidateString.substring(randomIndex - 1, randomIndex);

            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(110);
            green = random.nextInt(50);
            blue = random.nextInt(50);

            // 用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, 13 * i + 6, 16);

            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        try {
            ImageIO.write(buffImg, "jpeg", outputStream);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return randomCode.toString();
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
