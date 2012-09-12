/**
 * 
 */
package org.mspring.mlog.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO M-Log关于图片等附件的操作
 */
public interface FileService {
    /**
     * 将base64编码的字符串解码为图片文件
     * 
     * @param request
     * @param base64Data
     * @param fileType
     *            文件后缀名（不带“.”）
     * @return 图片的URL
     */
    public String saveBase64Image(HttpServletRequest request, String base64Data, String fileType);

    /**
     * 获取文件的上传的相对路径
     * 
     * @return
     */
    public String getAbstractFileUploadPath();

    /**
     * 获取文件上传的绝对路径
     * 
     * @param request
     * @param abstractPath
     *            相对路径
     * @return
     */
    public String getFileUploadPath(HttpServletRequest request, String abstractPath);
}
