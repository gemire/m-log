/**
 * 
 */
package org.mspring.mlog.api.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-9-27
 * @Description
 * @TODO
 */
@Service
public class JavaMailSenderImpl implements JavaMailSender {

    private static final Logger log = Logger.getLogger(JavaMailSenderImpl.class);

    private static final String HEADER_MESSAGE_ID = "Message-ID";

    public JavaMailSenderImpl() {

    }

    // ---------------------------------------------------------------------
    // Implementation of MailSender
    // ---------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.mail.MailSender#send(org.springframework.mail.
     * SimpleMailMessage)
     */
    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        // TODO Auto-generated method stub
        send(new SimpleMailMessage[] { simpleMessage });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.mail.MailSender#send(org.springframework.mail.
     * SimpleMailMessage[])
     */
    @Override
    public void send(SimpleMailMessage[] simpleMessages) throws MailException {
        // TODO Auto-generated method stub
        List<MimeMessage> mimeMessages = new ArrayList<MimeMessage>(simpleMessages.length);
        for (SimpleMailMessage simpleMessage : simpleMessages) {
            MimeMailMessage message = new MimeMailMessage(createMimeMessage());
            simpleMessage.copyTo(message);
            mimeMessages.add(message.getMimeMessage());
        }
        doSend(mimeMessages.toArray(new MimeMessage[mimeMessages.size()]), simpleMessages);
    }

    // ---------------------------------------------------------------------
    // Implementation of JavaMailSender
    // ---------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.mail.javamail.JavaMailSender#createMimeMessage()
     */
    @Override
    public MimeMessage createMimeMessage() {
        // TODO Auto-generated method stub
        //return new SmartMimeMessage(getSession(), getDefaultEncoding(), getDefaultFileTypeMap());
        return new SmartMimeMessage(MailSenderConf.getSession(), MailSenderConf.getDefaultEncoding(), MailSenderConf.getDefaultFileTypeMap());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.mail.javamail.JavaMailSender#createMimeMessage(java
     * .io.InputStream)
     */
    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        // TODO Auto-generated method stub
        try {
            return new MimeMessage(MailSenderConf.getSession(), contentStream);
        }
        catch (MessagingException ex) {
            throw new MailParseException("Could not parse raw MIME content", ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.mail.javamail.JavaMailSender#send(javax.mail.internet
     * .MimeMessage)
     */
    @Override
    public void send(MimeMessage mimeMessage) throws MailException {
        // TODO Auto-generated method stub
        send(new MimeMessage[] {mimeMessage});
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.mail.javamail.JavaMailSender#send(javax.mail.internet
     * .MimeMessage[])
     */
    @Override
    public void send(MimeMessage[] mimeMessages) throws MailException {
        // TODO Auto-generated method stub
        doSend(mimeMessages, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.mail.javamail.JavaMailSender#send(org.springframework
     * .mail.javamail.MimeMessagePreparator)
     */
    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
        // TODO Auto-generated method stub
        send(new MimeMessagePreparator[] { mimeMessagePreparator });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.mail.javamail.JavaMailSender#send(org.springframework
     * .mail.javamail.MimeMessagePreparator[])
     */
    @Override
    public void send(MimeMessagePreparator[] mimeMessagePreparators) throws MailException {
        // TODO Auto-generated method stub
        try {
            List<MimeMessage> mimeMessages = new ArrayList<MimeMessage>(mimeMessagePreparators.length);
            for (MimeMessagePreparator preparator : mimeMessagePreparators) {
                MimeMessage mimeMessage = createMimeMessage();
                preparator.prepare(mimeMessage);
                mimeMessages.add(mimeMessage);
            }
            send(mimeMessages.toArray(new MimeMessage[mimeMessages.size()]));
        }
        catch (MailException ex) {
            throw ex;
        }
        catch (MessagingException ex) {
            throw new MailParseException(ex);
        }
        catch (IOException ex) {
            throw new MailPreparationException(ex);
        }
        catch (Exception ex) {
            throw new MailPreparationException(ex);
        }
    }

    protected void doSend(MimeMessage[] mimeMessages, Object[] originalMessages) throws MailException {
        Map<Object, Exception> failedMessages = new LinkedHashMap<Object, Exception>();

        final String host = MailSenderConf.getHost();
        final String username = MailSenderConf.getUsername();
        final int port = MailSenderConf.getPort();
        final String password = MailSenderConf.getPassword();
        
        Transport transport = null;
        try {
            transport = MailSenderConf.getTransport();
            transport.connect(host, port, username, password);
        }
        catch (AuthenticationFailedException ex) {
            log.error("Can't login mail server. MailAuthenticationException", ex);
            return;
        }
        catch (MessagingException ex) {
            // Effectively, all messages failed...
            for (int i = 0; i < mimeMessages.length; i++) {
                Object original = (originalMessages != null ? originalMessages[i] : mimeMessages[i]);
                failedMessages.put(original, ex);
            }
            //throw new MailSendException("Mail server connection failed", ex, failedMessages);
            log.error("Mail server connection failed. failedMessages : " + failedMessages, ex);
            return;
        }

        try {
            for (int i = 0; i < mimeMessages.length; i++) {
                MimeMessage mimeMessage = mimeMessages[i];
                try {
                    if (mimeMessage.getSentDate() == null) {
                        mimeMessage.setSentDate(new Date());
                    }
                    String messageId = mimeMessage.getMessageID();
                    mimeMessage.saveChanges();
                    if (messageId != null) {
                        // Preserve explicitly specified message id...
                        mimeMessage.setHeader(HEADER_MESSAGE_ID, messageId);
                    }
                    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                }
                catch (MessagingException ex) {
                    Object original = (originalMessages != null ? originalMessages[i] : mimeMessage);
                    failedMessages.put(original, ex);
                }
            }
        }
        finally {
            try {
                transport.close();
            }
            catch (MessagingException ex) {
                if (!failedMessages.isEmpty()) {
                    throw new MailSendException("Failed to close server connection after message failures", ex, failedMessages);
                }
                else {
                    throw new MailSendException("Failed to close server connection after message sending", ex);
                }
            }
        }

        if (!failedMessages.isEmpty()) {
            throw new MailSendException(failedMessages);
        }
    }

}
