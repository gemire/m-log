/**
 * 
 */
package org.mspring.mlog.utils;

import java.io.IOException;
import java.util.Calendar;

import org.mspring.mlog.api.bae.bcs.BCSUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gao Youbo
 * @since 2012-11-22
 * @Description
 * @TODO 附件
 */
public class AttachmentUtils {
    /**
     * 获取文件保存名称
     * 
     * @param mf
     * @return
     */
    public static String getFileName(MultipartFile mf) {
        return StringUtils.getFileName() + "." + StringUtils.getFileExtend(mf.getOriginalFilename());
    }

    /**
     * 获取文件保存路径
     * 
     * @param mf
     * @return
     */
    public static String getUploadPath(MultipartFile mf) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return "/attachment/" + year + "/" + month + "/" + day + "/" + getFileName(mf);
    }

    /**
     * 保存图片
     * 
     * @param mf
     * @return
     */
    public static String upload(MultipartFile mf) {
        String fileName = getUploadPath(mf);
        String contentType = mf.getContentType();
        long contentLength = mf.getSize();
        try {
            BCSUtils.putFile(fileName, mf.getInputStream(), contentType, contentLength);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return BCSUtils.getURL(fileName);
    }

    public static String uploadBase64(String data) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String fileName = "/attachment/" + year + "/" + month + "/" + day + "/" + StringUtils.getFileName() + ".jpg";

        try {
            BCSUtils.putFile(fileName, data, "image/JPEG");
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return BCSUtils.getURL(fileName);
    }
}
