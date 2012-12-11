/**
 * 
 */
package org.mspring.mlog.api.bae.bcs;

import java.io.File;
import java.io.InputStream;

import org.mspring.mlog.service.FileService;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-12-11
 * @Description
 * @TODO
 */
@Service
public class BcsFileServiceImpl implements FileService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String,
     * java.io.File)
     */
    @Override
    public String uploadFile(String fileName, File file) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String,
     * java.io.InputStream, java.lang.String, long)
     */
    @Override
    public String uploadFile(String fileName, InputStream inputStream, String contentType, long contentLength) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.FileService#uploadBase64File(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String uploadBase64File(String fileName, String base64, String contentType) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#deleteFile(java.lang.String)
     */
    @Override
    public String deleteFile(String path) {
        // TODO Auto-generated method stub
        return null;
    }

}
