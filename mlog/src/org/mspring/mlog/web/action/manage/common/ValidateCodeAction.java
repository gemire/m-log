/**
 * 
 */
package org.mspring.mlog.web.action.manage.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Mar 17, 2012
 */
public class ValidateCodeAction extends AbstractManageAction {
    public String execute() throws IOException {
        // // 验证码宽度
        // int width = StringUtils.isBlank(config.getValidateCodeWidth()) ? 60 :
        // new Integer(config.getValidateCodeWidth()).intValue();
        // // 验证码高度
        // int height = StringUtils.isBlank(config.getValidateCodeHeight()) ? 20
        // : new Integer(config.getValidateCodeHeight()).intValue();
        // // 验证码中允许出现的字符
        // String validateCodeString =
        // StringUtils.isBlank(config.getValidateCodeString()) ? "0123456789" :
        // config.getValidateCodeString();

        int width = 60;
        int height = 20;
        String validateCodeString = "0123456789";

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

        // 随机产生4位数字的验证码。
        for (int i = 0; i < 4; i++) {
            // 得到随机产生的验证码数字。
            int randomIndex = random.nextInt(validateCodeString.length());
            if (randomIndex == 0)
                randomIndex = 1;
            String strRand = validateCodeString.substring(randomIndex - 1, randomIndex);

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

        // 将四位数字的验证码保存到Session中。\
        setSessionAttribute(Const.SESSION_VALIDATE_CODE, randomCode.toString());

        HttpServletResponse response = ServletActionContext.getResponse();
        // 禁止图像缓存。
        // response.setHeader("Pragma", "no-cache");
        // response.setHeader("Cache-Control", "no-cache");
        // response.setDateHeader("Expires", 0);
        //
        // response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
        return NONE;
    }
}
