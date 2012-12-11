/**
 * 
 */
package org.mspring.mlog.service;

import java.io.File;
import java.io.InputStream;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO M-Log文件操作的相关类
 */
public interface FileService {
    public String uploadFile(String fileName, File file);

    public String uploadFile(String fileName, InputStream inputStream, String contentType, long contentLength);

    public String uploadBase64File(String fileName, String base64, String contentType);

    public void deleteFile(String path);
}
