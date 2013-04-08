/**
 * 
 */
package org.mspring.platform.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import eu.medsea.mimeutil.MimeUtil;

/**
 * @author Gao Youbo
 * @since 2013-3-15
 * @description
 * @TODO
 */
@SuppressWarnings("rawtypes")
public class MimeTypeUtils {
    /**
     * 获取mimeType
     * 
     * @param file
     * @return
     */
    public static String getMimeType(File file) {
        Collection collection = MimeUtil.getMimeTypes(file);
        if (collection == null || collection.size() < 1) {
            return "application/octet-stream";
        }
        return collection.toArray()[collection.size() - 1].toString();
    }

    /**
     * 获取mimeType
     * 
     * @param inputStream
     * @return
     */
    public static String getMimeType(InputStream inputStream) {
        Collection collection = MimeUtil.getMimeTypes(inputStream);
        if (collection == null || collection.size() < 1) {
            return "application/octet-stream";
        }
        return collection.toArray()[collection.size() - 1].toString();
    }

    /**
     * 获取mimeType
     * 
     * @param bytes
     * @return
     */
    public static String getMimeType(byte[] bytes) {
        Collection collection = MimeUtil.getMimeTypes(bytes);
        if (collection == null || collection.size() < 1) {
            return "application/octet-stream";
        }
        return collection.toArray()[collection.size() - 1].toString();
    }

    /**
     * 根据mimetype获取文件的后缀名
     * @param mimeType
     * @return
     */
    public static String getExtension(String mimeType) {
        return MimeUtil.getSubType(mimeType);
    }
}
