/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Attachment;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.AttachmentService;
import org.mspring.mlog.service.FileService;
import org.mspring.mlog.utils.AttachmentUtils;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.ImageUtils;
import org.mspring.platform.utils.Size;
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
        return (Attachment) getById(Attachment.class, id);
    }

    @Override
    public Attachment createAttachment(MultipartFile mf, String from) {
        // TODO Auto-generated method stub
        return createAttachment(mf, from, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AttachmentService#createAttachment(org.
     * springframework.web.multipart.MultipartFile)
     */
    @Override
    public Attachment createAttachment(MultipartFile mf, String from, Long fid) {
        // TODO Auto-generated method stub
        User user = SecurityUtils.getCurrentUser();
        if (user == null) {
            log.warn("can't upload attachment, please login.");
            return null;
        }
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
        attachment.setUser(user.getId());
        attachment.setFrom(from);
        attachment.setFid(fid);

        // 判断是否为图片
        String ext = StringUtils.getFileExtend(mf.getOriginalFilename());
        if (ImageUtils.isImage(ext)) {
            attachment.setIsImage(true);
            try {
                Size size = ImageUtils.getImageSize(mf.getInputStream());
                attachment.setImageWidth(size.getWidth());
                attachment.setImageHeight(size.getHeight());
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }
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
    public Attachment createAttachment(String base64Data, String ext, Long user, String from, Long fid) {
        // TODO Auto-generated method stub
        User loginUser = SecurityUtils.getCurrentUser();
        if (loginUser == null) {
            log.warn("can't upload attachment, please login.");
            return null;
        }
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
            String url = fileService.uploadFile(fileName, inputStream);
            Long size = new Long(bytes.length);

            Attachment attachment = new Attachment();
            attachment.setPath(url);
            attachment.setSize(size);
            attachment.setUploadTime(new Date());
            attachment.setUser(user);
            attachment.setFrom(from);
            attachment.setFid(fid);

            // 判断是否为图片
            if (ImageUtils.isImage(ext)) {
                attachment.setIsImage(true);
                Size imageSize = ImageUtils.getImageSize(inputStream);
                attachment.setImageWidth(imageSize.getWidth());
                attachment.setImageHeight(imageSize.getHeight());
            }
            Long id = (Long) create(attachment);
            return getAttachmentById(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("upload file error, " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Attachment> findAttachmentsByPost(Long post) {
        // TODO Auto-generated method stub
        return findAttachmentsByFid(post, Attachment.AttachFrom.FROM_POST);
    }

    @Override
    public List<Attachment> findAttachmentsByTwitter(Long twitter) {
        // TODO Auto-generated method stub
        return findAttachmentsByFid(twitter, Attachment.AttachFrom.FROM_TWITTER);
    }

    @Override
    public List<Attachment> findAttachmentsByFid(Long fid, String from) {
        // TODO Auto-generated method stub
        return find("select a from Attachment a where a.from = ? and a.fid = ?", new Object[] { from, fid });
    }

    @Override
    public void deleteAttachment(Long id) {
        // TODO Auto-generated method stub
        Attachment a = getAttachmentById(id);
        fileService.deleteFile(a.getPath());
        remove(Attachment.class, id);
    }

    @Override
    public void setAttachmentFid(Long id, String from, Long fid) {
        // TODO Auto-generated method stub
        Attachment attachment = getAttachmentById(id);
        if (attachment == null) {
            return;
        }
        attachment.setFid(fid);
        attachment.setFrom(from);
        update(attachment);
    }

}
