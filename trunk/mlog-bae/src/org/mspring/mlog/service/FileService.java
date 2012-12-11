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
    /**
     * 上传文件
     * 
     * @param fileName
     * @param file
     * @return
     */
    public String uploadFile(String fileName, File file);

    /**
     * 上传文件
     * 
     * @param fileName
     * @param inputStream
     * @param contentType
     * @param contentLength
     * @return
     */
    public String uploadFile(String fileName, InputStream inputStream, long contentLength);

    /**
     * 上传base64加密文件
     * 
     * @param fileName
     * @param base64
     * @param contentType
     * @return
     */
    public String uploadBase64File(String fileName, String base64);

    /**
     * 删除文件
     * 
     * @param path
     */
    public void deleteFile(String path);

    /**
     * 获取文件的mimetype
     * 
     * @param file
     * @return
     */
    public String getMimeType(File file);
    
    /**
     * 获取文件的mimetype
     * @param inputStream
     * @return
     */
    public String getMimeType(InputStream inputStream);
    
    /**
     * 获取mimetype
     * @param bytes
     * @return
     */
    public String getMimeType(byte[] bytes);
}
