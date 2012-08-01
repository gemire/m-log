/**
 * 
 */
package org.mspring.platform.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class ImageUtils {
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
        } catch (FileNotFoundException e) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成随机验证码
     * @param outputStream 输出流
     * @param allowValidateString 允许验证码中出现的字符串
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
            if (randomIndex == 0)
                randomIndex = 1;
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return randomCode.toString();
    }
}
