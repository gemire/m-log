/**
 * 
 */
package org.mspring.mlog.service.impl.standard;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.impl.AbstractFileService;
import org.mspring.mlog.utils.WebUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-12-19
 * @Description
 * @TODO
 */
@Service
public class StandardFileServiceImpl extends AbstractFileService {
    private static final Logger log = Logger.getLogger(StandardFileServiceImpl.class);

    @Autowired
    private OptionService optionService;

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String,
     * java.io.File)
     */
    @Override
    public String uploadFile(String fileName, File file) {
        // TODO Auto-generated method stub
        File destFile = getDestFile(fileName);

        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        try {
            FileUtils.copyFile(file, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getFileUrl(fileName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String,
     * java.io.InputStream, long)
     */
    @Override
    public String uploadFile(String fileName, InputStream inputStream) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getFileUrl(fileName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.FileService#uploadBase64File(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String uploadBase64File(String fileName, String base64) {
        // TODO Auto-generated method stub
        byte[] bytes = StringUtils.decodeBASE64(base64.getBytes());
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return uploadFile(fileName, inputStream);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#deleteFile(java.lang.String)
     */
    @Override
    public void deleteFile(String path) {
        // TODO Auto-generated method stub
        File file = getDestFile(path);
        FileUtils.deleteQuietly(file);
        log.debug("delete file " + path);
    }

    /**
     * 获取要保存的文件路径
     * 
     * @param fileName
     * @return
     */
    private File getDestFile(String fileName) {
        String uploadFolder = WebUtils.getRealContextPath("/uploads");

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
