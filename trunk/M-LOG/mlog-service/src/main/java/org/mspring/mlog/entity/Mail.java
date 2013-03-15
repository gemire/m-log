/**
 * 
 */
package org.mspring.mlog.entity;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since 2012-9-28
 * @Description
 * @TODO
 */
public class Mail implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7565547990960624426L;

    private static final Logger log = Logger.getLogger(Mail.class);

    private String subject;
    private String content;
    private List<InternetAddress> to;
    private List<InternetAddress> cc;
    private List<InternetAddress> bcc;
    private List<File> attachFiles;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<InternetAddress> getTo() {
        return to;
    }

    public void setTo(List<InternetAddress> to) {
        this.to = to;
    }

    public List<InternetAddress> getCc() {
        return cc;
    }

    public void setCc(List<InternetAddress> cc) {
        this.cc = cc;
    }

    public List<InternetAddress> getBcc() {
        return bcc;
    }

    public void setBcc(List<InternetAddress> bcc) {
        this.bcc = bcc;
    }

    public List<File> getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(List<File> attachFiles) {
        this.attachFiles = attachFiles;
    }

    public void addTo(String email, String personal) {
        if (this.to == null) {
            to = new ArrayList<InternetAddress>();
        }
        try {
            this.to.add(new InternetAddress(email, personal, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addCc(String email, String personal) {
        if (this.cc == null) {
            cc = new ArrayList<InternetAddress>();
        }
        try {
            this.cc.add(new InternetAddress(email, personal, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addBcc(String email, String personal) {
        if (this.bcc == null) {
            bcc = new ArrayList<InternetAddress>();
        }
        try {
            this.bcc.add(new InternetAddress(email, personal, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addAttach(File attach) {
        if (this.attachFiles == null) {
            attachFiles = new ArrayList<File>();
        }
        if (attach != null && attach.exists())
            this.attachFiles.add(attach);
        else
            log.debug("attach file [" + attach + "] is't exists");
    }
}
