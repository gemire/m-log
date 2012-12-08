/**
 * 
 */
package org.mspring.mlog.api.bae.bcs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.platform.utils.StringUtils;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.auth.BCSSignCondition;
import com.baidu.inf.iis.bcs.http.HttpMethodName;
import com.baidu.inf.iis.bcs.model.Empty;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.DeleteObjectRequest;
import com.baidu.inf.iis.bcs.request.GenerateUrlRequest;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

/**
 * @author Gao Youbo
 * @since 2012-10-19
 * @Description
 * @TODO
 */
public class BCSUtils {
    private static final Logger log = Logger.getLogger(BCSUtils.class);

    // ----------------------------------------
    // static String host = "bcs.duapp.com";
    // static String accessKey = "01b32d0f23fd7dff318190b7046561b4";
    // static String secretKey = "7613a6504ed373a03ab44c727f4c5e9a";
    // static String bucket = "javamlog";

    private static String getBCSHost() {
        return ServiceFactory.getOptionService().getOption("bcs_host");
    }

    private static String getBCSAccessKey() {
        return ServiceFactory.getOptionService().getOption("bcs_access_key");
    }

    private static String getBCSSecretKey() {
        return ServiceFactory.getOptionService().getOption("bcs_secret_key");
    }

    private static String getBCSBucket() {
        return ServiceFactory.getOptionService().getOption("bcs_bucket");
    }

    public static BaiduBCS getBaiduBCS() {
        BCSCredentials credentials = new BCSCredentials(getBCSAccessKey(), getBCSSecretKey());
        BaiduBCS baiduBCS = new BaiduBCS(credentials, getBCSHost());
        baiduBCS.setDefaultEncoding("UTF-8");
        return baiduBCS;
    }

    public static BaiduBCSResponse<ObjectMetadata> putFile(String fileName, File file) {
        PutObjectRequest request = new PutObjectRequest(getBCSBucket(), fileName, file);
        ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setContentType("text/html");
        request.setMetadata(metadata);
        return getBaiduBCS().putObject(request);
    }

    public static BaiduBCSResponse<ObjectMetadata> putFile(String fileName, InputStream inputStream, String contentType, long contentLength) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(contentLength);
        PutObjectRequest request = new PutObjectRequest(getBCSBucket(), fileName, inputStream, metadata);
        return getBaiduBCS().putObject(request);
    }

    public static BaiduBCSResponse<ObjectMetadata> putFile(String fileName, String base64, String contentType) {
        byte[] bytes = StringUtils.decodeBASE64(base64.getBytes());
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        InputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(bytes.length);
        PutObjectRequest request = new PutObjectRequest(getBCSBucket(), fileName, inputStream, metadata);
        return getBaiduBCS().putObject(request);
    }

    public static String getURL(String fileName) {
        GenerateUrlRequest generateUrlRequest = new GenerateUrlRequest(HttpMethodName.GET, getBCSBucket(), fileName);
        generateUrlRequest.setBcsSignCondition(new BCSSignCondition());
        // generateUrlRequest.getBcsSignCondition().setIp("1.1.1.1");
        // generateUrlRequest.getBcsSignCondition().setTime(new Date().getTime()
        // + 60 * 1000);
        // generateUrlRequest.getBcsSignCondition().setSize(123455L);
        return getBaiduBCS().generateUrl(generateUrlRequest);
    }

    public static void deleteObject(String filePath) {
        DeleteObjectRequest request = new DeleteObjectRequest(getBCSBucket(), filePath);
        Empty empty = getBaiduBCS().deleteObject(request).getResult();
        log.debug(empty);
    }
}
