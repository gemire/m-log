/**
 * 
 */
package org.mspring.mlog.api.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author Gao Youbo
 * @since 2012-9-28
 * @Description
 * @TODO
 */
public class MailSenderTest {
    public static void main(String[] args) {
        JavaMailSender sender = new JavaMailSenderImpl();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("gaoyoubo@mspring.org");
        simpleMailMessage.setTo("330721072@qq.com");
        simpleMailMessage.setCc("gaoyoubo@foxmail.com");
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("<b><font size='18' color='red'>测试邮件</font></b>");
        sender.send(simpleMailMessage);
    }
}
