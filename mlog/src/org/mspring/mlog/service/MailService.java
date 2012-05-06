/**
 * 
 */
package org.mspring.mlog.service;

import java.util.Map;

import org.mspring.mlog.entity.mail.Message;

/**
 * @author Gao Youbo
 * @since Apr 7, 2012
 */
public interface MailService {
    void sendMail(Message message, String template, Map<Object, Object> context);
    void sendMail(Message message);
}
