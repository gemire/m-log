/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mspring.mlog.entity.Mail;
import org.mspring.mlog.service.MailService;
import org.mspring.mlog.web.api.mail.MailSenderConf;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-9-28
 * @Description
 * @TODO
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MailService#sendMail(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void sendMail(String to, String personal, String subject, String content) {
        // TODO Auto-generated method stub
        Mail mail = new Mail();
        mail.addTo(to, StringUtils.isBlank(personal) ? to : personal);
        mail.setSubject(subject);
        mail.setContent(content);
        doSend(mail);
    }

    private void doSend(final Mail mail) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                // 是否包含附件
                boolean marltipart = (mail.getAttachFiles() != null && mail.getAttachFiles().size() > 0) ? true : false;
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, marltipart, MailSenderConf.getDefaultEncoding());
                helper.setFrom(MailSenderConf.getFrom());
                helper.setSubject(mail.getSubject());
                helper.setText(mail.getContent(), true);

                // 收件人
                if (mail.getTo() != null && mail.getTo().size() > 0) {
                    for (InternetAddress address : mail.getTo()) {
                        helper.addTo(address);
                    }
                }

                // 抄送
                if (mail.getCc() != null && mail.getCc().size() > 0) {
                    for (InternetAddress address : mail.getCc()) {
                        helper.addCc(address);
                    }
                }

                // 密送
                if (mail.getBcc() != null && mail.getBcc().size() > 0) {
                    for (InternetAddress address : mail.getBcc()) {
                        helper.addBcc(address);
                    }
                }

                // 附件
                if (mail.getAttachFiles() != null && mail.getAttachFiles().size() > 0) {
                    for (File attach : mail.getAttachFiles()) {
                        helper.addAttachment(attach.getName(), attach);
                    }
                }
            }
        };
        javaMailSender.send(preparator);
    }

}
