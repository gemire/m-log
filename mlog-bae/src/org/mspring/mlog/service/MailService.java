/**
 * 
 */
package org.mspring.mlog.service;

/**
 * @author Gao Youbo
 * @since 2012-9-28
 * @Description
 * @TODO
 */
public interface MailService {
    /**
     * 发送邮件
     * 
     * @param to
     *            收件人地址
     * @param personal
     *            收件人名称
     * @param subject
     *            主题
     * @param content
     *            内容
     */
    public void sendMail(String to, String personal, String subject, String content);
}
