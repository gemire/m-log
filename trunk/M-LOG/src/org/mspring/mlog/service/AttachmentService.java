/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gao Youbo
 * @since 2013-2-19
 * @description
 * @TODO
 */
public interface AttachmentService {
    Attachment getAttachmentById(Long id);

    /**
     * 创建附件
     * 
     * @param mf
     * @param from
     * @param fid
     * @return
     */
    Attachment createAttachment(MultipartFile mf, String from, Long fid);

    /**
     * 创建附件
     * 
     * @param base64Data
     * @param ext
     * @param user
     * @param from
     * @param fid
     * @return
     */
    Attachment createAttachment(String base64Data, String ext, Long user, String from, Long fid);

    /**
     * 根据文章查找该文章对应的附件
     * 
     * @param post
     * @return
     */
    List<Attachment> findAttachmentsByPost(Long post);

    /**
     * 删除附件
     * 
     * @param id
     */
    void deleteAttachment(Long id);
}
