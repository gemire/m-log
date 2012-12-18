/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.mspring.mlog.api.mail.MailSenderConf;
import org.mspring.mlog.entity.Mail;
import org.mspring.mlog.service.MailService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-9-28
 * @Description
 * @TODO
 */
@Service
@Transactional
public class MailServiceImpl implements MailService {
    private static final Logger log = Logger.getLogger(MailServiceImpl.class);

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
        final String from = MailSenderConf.getFrom();
        if (StringUtils.isBlank(from)) {
            log.warn("send mail error, from email address is blank");
            return;
        }
        if (mail.getTo() == null || mail.getTo().size() == 0) {
            log.error("send mail error, to emailAddress is blank");
            return;
        }
        if (StringUtils.isBlank(mail.getSubject())) {
            log.warn("send mail error, mail subject is blank");
        }
        if (StringUtils.isBlank(mail.getContent())) {
            log.warn("send mail error, mail content is blank");
        }
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                // 是否包含附件
                boolean marltipart = (mail.getAttachFiles() != null && mail.getAttachFiles().size() > 0) ? true : false;
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, marltipart, MailSenderConf.getDefaultEncoding());
                helper.setFrom(from == null ? "" : from);
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
