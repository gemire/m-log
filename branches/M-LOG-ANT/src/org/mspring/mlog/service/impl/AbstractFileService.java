/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import org.mspring.mlog.service.FileService;

import eu.medsea.mimeutil.MimeUtil;

/**
 * @author Gao Youbo
 * @since 2012-12-11
 * @Description
 * @TODO
 */
public abstract class AbstractFileService implements FileService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#getMimeType(java.io.File)
     */
    @Override
    public String getMimeType(File file) {
        // TODO Auto-generated method stub
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        Collection collection = MimeUtil.getMimeTypes(file);
        if (collection == null || collection.size() < 1) {
            return "application/octet-stream";
        }
        return collection.toArray()[0].toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.FileService#getMimeType(java.io.InputStream)
     */
    @Override
    public String getMimeType(InputStream inputStream) {
        // TODO Auto-generated method stub
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        Collection collection = MimeUtil.getMimeTypes(inputStream);
        if (collection == null || collection.size() < 1) {
            return "application/octet-stream";
        }
        return collection.toArray()[0].toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#getMimeType(byte[])
     */
    @Override
    public String getMimeType(byte[] bytes) {
        // TODO Auto-generated method stub
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        Collection collection = MimeUtil.getMimeTypes(bytes);
        if (collection == null || collection.size() < 1) {
            return "application/octet-stream";
        }
        return collection.toArray()[0].toString();
    }

}
