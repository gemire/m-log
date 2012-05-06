/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.entity.mail.Message;
import org.mspring.mlog.exception.MailSendException;
import org.mspring.mlog.service.MailService;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.utils.FreemarkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since Apr 7, 2012
 */
@Service
@Transactional
public class MailServiceImpl implements MailService {

    private static final Logger log = Logger.getLogger(MailServiceImpl.class);

    private OptionService optionService;
    private Configuration configuration;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Autowired
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Map<String, String> mailoptions = optionService.findSystemWebConfig();
        mailSender.setHost(mailoptions.get(ConfigTokens.mspring_config_system_mail_host));
        mailSender.setPort(new Integer(mailoptions.get(ConfigTokens.mspring_config_system_mail_port)));
        mailSender.setUsername(mailoptions.get(ConfigTokens.mspring_config_system_mail_username));
        mailSender.setPassword(mailoptions.get(ConfigTokens.mspring_config_system_mail_password));
        return mailSender;
    }
    
    
    public void sendMail(final Message message, final String template, final Map<Object, Object> context) {
        String content = FreemarkerUtils.render(configuration, template, context);
        message.setContent(content);
        sendMail(message);
    }

    @Override
    public void sendMail(final Message message) {
        // TODO Auto-generated method stub
        if (message == null) {
            log.error("message cound be null", new MailSendException("message cound be null"));
            return;
        }

        if (message.getTo() == null || message.getTo().size() == 0) {
            log.error("mail to is null", new MailSendException("mail to is null"));
            return;
        }
        final String from = optionService.getOption(ConfigTokens.mspring_config_system_mail_username);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
//                Map<Object, Object> context = new HashMap<Object, Object>();
//                context.put("message", message);
//                String subject = FreemarkerUtils.render(configuration, TEMPLATE_MESSAGE_CREATE_SUBJECT, context);
//                String content = FreemarkerUtils.render(configuration, TEMPLATE_MESSAGE_CREATE_CONTENT, context);

                // 是否包含附件
                boolean marltipart = (message.getAttachFiles() != null && message.getAttachFiles().size() > 0) ? true : false;
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, marltipart, "UTF-8");
                helper.setFrom(from);
                helper.setSubject(message.getSubject());
                helper.setText(message.getContent(), true);

                // 收件人
                if (message.getTo() != null && message.getTo().size() > 0) {
                    for (InternetAddress address : message.getTo()) {
                        helper.addTo(address);
                    }
                }

                // 抄送
                if (message.getCc() != null && message.getCc().size() > 0) {
                    for (InternetAddress address : message.getCc()) {
                        helper.addCc(address);
                    }
                }

                // 密送
                if (message.getBcc() != null && message.getBcc().size() > 0) {
                    for (InternetAddress address : message.getBcc()) {
                        helper.addBcc(address);
                    }
                }

                // 附件
                if (message.getAttachFiles() != null && message.getAttachFiles().size() > 0) {
                    for (File attach : message.getAttachFiles()) {
                        helper.addAttachment(attach.getName(), attach);
                    }
                }
            }
        };
        getMailSender().send(preparator);
    }
}
