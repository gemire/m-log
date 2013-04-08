/**
 * 
 */
package org.mspring.mlog.api.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.FileTypeMap;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;

/**
 * @author Gao Youbo
 * @since 2012-9-27
 * @Description 邮件发送配置
 * @TODO <prop key="mail.smtp.timeout">${mail.smtp.timeout}</prop> <prop
 *       key="mail.smtp.auth">${mail.smtp.auth}</prop> <prop
 *       key="mail.smtp.starttls.enable">${mail.smtp.starttls.enable}</prop>
 *       <prop
 *       key="mail.smtp.socketFactory.port">${mail.smtp.socketFactory.port}
 *       </prop> <prop
 *       key="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory
 *       </prop> <prop
 *       key="mail.smtp.socketFactory.fallback">${mail.smtp.socketFactory
 *       .fallback}</prop>
 */
public class MailSenderConf {

    /**
     * 默认协议
     */
    public static final String DEFAULT_PROTOCOL = "smtp";
    /**
     * 默认邮件发送端口
     */
    public static final int DEFAULT_PORT = -1;

    public static final String DEFAULT_ENCODING = "UTF-8";

    public static int getPort() {
        String str = ServiceFactory.getOptionService().getOption("smtp_port");
        if (StringUtils.isBlank(str) || !ValidatorUtils.isNumber(str)) {
            return DEFAULT_PORT;
        }
        return Integer.parseInt(str.trim());
    }

    public static String getHost() {
        return ServiceFactory.getOptionService().getOption("smtp_host");
    }

    public static String getUsername() {
        return ServiceFactory.getOptionService().getOption("smtp_username");
    }

    public static String getPassword() {
        return ServiceFactory.getOptionService().getOption("smtp_password");
    }

    public static String getFrom() {
        return ServiceFactory.getOptionService().getOption("mail_from");
    }
    
    public static boolean getSSLEnable(){
        String smtp_ssl_enable = ServiceFactory.getOptionService().getOption("smtp_ssl_enable");
        if (StringUtils.isNotBlank(smtp_ssl_enable) && smtp_ssl_enable.equals("true")) {
            return true;
        }
        return false;
    }

    public static String getDefaultEncoding() {
        return DEFAULT_ENCODING;
    }

    public static FileTypeMap getDefaultFileTypeMap() {
        ConfigurableMimeFileTypeMap fileTypeMap = new ConfigurableMimeFileTypeMap();
        fileTypeMap.afterPropertiesSet();
        return fileTypeMap;
    }

    public static Properties getJavaMailProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("mail.smtp.timeout", "50000");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.socketFactory.port", 25);
        if (getSSLEnable()) { //如果启用了SSL
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        properties.put("mail.smtp.socketFactory.fallback", false);
        Properties javaMailProperties = new Properties();
        if (properties != null && properties.size() > 0) {
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                javaMailProperties.put(entry.getKey(), entry.getValue());
            }
        }
        return javaMailProperties;
    }

    public static synchronized Session getSession() {
        Session session = null;
        if (session == null) {
            session = Session.getInstance(getJavaMailProperties());
        }
        return session;
    }

    public static Transport getTransport() throws NoSuchProviderException {
        Session session = getSession();
        String protocol = null;
        if (protocol == null) {
            protocol = session.getProperty("mail.transport.protocol");
            if (protocol == null) {
                protocol = DEFAULT_PROTOCOL;
            }
        }
        return session.getTransport(protocol);
    }
}
