/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Attachment;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.AttachmentService;
import org.mspring.mlog.service.FileService;
import org.mspring.mlog.utils.AttachmentUtils;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gao Youbo
 * @since 2013-2-19
 * @description
 * @TODO
 */
@Transactional
@Service
public class AttachmentServiceImpl extends AbstractServiceSupport implements AttachmentService {
    private static final Logger log = Logger.getLogger(AttachmentServiceImpl.class);

    @Autowired
    private FileService fileService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.AttachmentService#getAttachmentById(java.lang
     * .Long)
     */
    @Override
    public Attachment getAttachmentById(Long id) {
        // TODO Auto-generated method stub
        return (Attachment) loadById(Attachment.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AttachmentService#createAttachment(org.
     * springframework.web.multipart.MultipartFile)
     */
    @Override
    public Attachment createAttachment(MultipartFile mf) {
        // TODO Auto-generated method stub
        String url = "";
        try {
            String fileName = AttachmentUtils.getUploadPath(mf);
            url = fileService.uploadFile(fileName, mf.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("upload file error, " + e.getMessage());
        }
        Attachment attachment = new Attachment();
        attachment.setPath(url);
        attachment.setSize(mf.getSize());
        attachment.setUploadTime(new Date());
        attachment.setUser(SecurityUtils.getCurrentUser().getId());
        Long id = (Long) create(attachment);
        return getAttachmentById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.AttachmentService#createAttachment(java.lang
     * .String, java.lang.String, java.lang.Long)
     */
    @Override
    public Attachment createAttachment(String base64Data, String ext, Long user) {
        // TODO Auto-generated method stub
        String url = "";
        Long size = null;
        try {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String fileName = "/attachment/" + year + "/" + month + "/" + day + "/" + StringUtils.getFileName() + "." + ext;

            byte[] bytes = StringUtils.decodeBASE64(base64Data.getBytes());
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            InputStream inputStream = new ByteArrayInputStream(bytes);
            url = fileService.uploadFile(fileName, inputStream);
            size = new Long(bytes.length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("upload file error, " + e.getMessage());
        }
        Attachment attachment = new Attachment();
        attachment.setPath(url);
        attachment.setSize(size);
        attachment.setUploadTime(new Date());
        attachment.setUser(user);
        Long id = (Long) create(attachment);
        return getAttachmentById(id);
    }

}
