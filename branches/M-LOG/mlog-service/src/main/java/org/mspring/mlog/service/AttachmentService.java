/**
 * 
 */
package org.mspring.mlog.service;

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

    Attachment createAttachment(MultipartFile mf);
    
    Attachment createAttachment(String base64Data, String ext, Long user);
}
