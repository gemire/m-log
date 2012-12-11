/**
 * 
 */
package org.mspring.mlog.inf.bae;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.impl.AbstractFileService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.auth.BCSSignCondition;
import com.baidu.inf.iis.bcs.http.HttpMethodName;
import com.baidu.inf.iis.bcs.model.Empty;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.DeleteObjectRequest;
import com.baidu.inf.iis.bcs.request.GenerateUrlRequest;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;

/**
 * @author Gao Youbo
 * @since 2012-12-11
 * @Description
 * @TODO
 */
@Service
public class BaeFileServiceImpl extends AbstractFileService {
    private static final Logger log = Logger.getLogger(BaeFileServiceImpl.class);

    @Autowired
    private OptionService optionService;

    private String getBCSHost() {
        return optionService.getOption("bcs_host");
    }

    private String getBCSAccessKey() {
        return optionService.getOption("bcs_access_key");
    }

    private String getBCSSecretKey() {
        return optionService.getOption("bcs_secret_key");
    }

    private String getBCSBucket() {
        return optionService.getOption("bcs_bucket");
    }

    private BaiduBCS getBaiduBCS() {
        BCSCredentials credentials = new BCSCredentials(getBCSAccessKey(), getBCSSecretKey());
        BaiduBCS baiduBCS = new BaiduBCS(credentials, getBCSHost());
        baiduBCS.setDefaultEncoding("UTF-8");
        return baiduBCS;
    }

    private String getURL(String fileName) {
        GenerateUrlRequest generateUrlRequest = new GenerateUrlRequest(HttpMethodName.GET, getBCSBucket(), fileName);
        generateUrlRequest.setBcsSignCondition(new BCSSignCondition());
        // generateUrlRequest.getBcsSignCondition().setIp("1.1.1.1");
        // generateUrlRequest.getBcsSignCondition().setTime(new Date().getTime()
        // + 60 * 1000);
        // generateUrlRequest.getBcsSignCondition().setSize(123455L);
        return getBaiduBCS().generateUrl(generateUrlRequest);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String,
     * java.io.File)
     */
    @Override
    public String uploadFile(String fileName, File file) {
        // TODO Auto-generated method stub
        PutObjectRequest request = new PutObjectRequest(getBCSBucket(), fileName, file);
        ObjectMetadata metadata = new ObjectMetadata();
        request.setMetadata(metadata);
        getBaiduBCS().putObject(request);
        return getURL(fileName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String,
     * java.io.InputStream, long)
     */
    @Override
    public String uploadFile(String fileName, InputStream inputStream, long contentLength) {
        // TODO Auto-generated method stub
        String contentType = getMimeType(inputStream);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(contentLength);
        PutObjectRequest request = new PutObjectRequest(getBCSBucket(), fileName, inputStream, metadata);
        getBaiduBCS().putObject(request);
        return getURL(fileName);
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

        String contentType = getMimeType(inputStream);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(bytes.length);
        PutObjectRequest request = new PutObjectRequest(getBCSBucket(), fileName, inputStream, metadata);
        getBaiduBCS().putObject(request);
        return getURL(fileName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#deleteFile(java.lang.String)
     */
    @Override
    public void deleteFile(String path) {
        // TODO Auto-generated method stub
        DeleteObjectRequest request = new DeleteObjectRequest(getBCSBucket(), path);
        Empty empty = getBaiduBCS().deleteObject(request).getResult();
        log.debug(empty);
    }

}
