/**
 * 
 */
package org.mspring.mlog.inf.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Gao Youbo
 * @since 2013-1-7
 * @Description 
 * @TODO 
 */
public class SystemFileFunction extends AbstractFileFunction {
    private static final Logger log = Logger.getLogger(SystemFileFunction.class);

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#save(java.lang.String, java.io.File)
     */
    @Override
    public String save(String fileName, File file) {
        // TODO Auto-generated method stub
        File destFile = getDestFile(fileName);

        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        try {
            FileUtils.copyFile(file, destFile);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getFileUrl(fileName);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#save(java.lang.String, java.io.InputStream, long)
     */
    @Override
    public String save(String fileName, InputStream inputStream, long contentLength) {
        // TODO Auto-generated method stub
        File destFile = getDestFile(fileName);
        try {
            OutputStream os = new FileOutputStream(destFile);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return getFileUrl(fileName);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#saveBase64(java.lang.String, java.lang.String)
     */
    @Override
    public String saveBase64(String fileName, String base64) {
        // TODO Auto-generated method stub
        byte[] bytes = StringUtils.decodeBASE64(base64.getBytes());
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return save(fileName, inputStream, bytes.length);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.inf.standard.file.AbstractFileFunction#delete(java.lang.String)
     */
    @Override
    public void delete(String filePath) {
        // TODO Auto-generated method stub
        File file = getDestFile(filePath);
        FileUtils.deleteQuietly(file);
        log.debug("delete file " + filePath);
    }
    
    
    /**
     * 获取要保存的文件路径
     * 
     * @param fileName
     * @return
     */
    private File getDestFile(String fileName) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String uploadFolder = request.getSession().getServletContext().getRealPath("/uploads");

        if (!fileName.startsWith("/") && !fileName.startsWith("\\")) {
            fileName = "/" + fileName;
        }

        String filePath = uploadFolder + fileName;
        File destFile = new File(filePath);

        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        return destFile;
    }

    /**
     * 获取文件访问的URL
     * 
     * @param fileName
     * @return
     */
    private String getFileUrl(String fileName) {
        String url = "/uploads/" + fileName;
        url = url.replaceAll("//", "/");
        return url;
    }

}
