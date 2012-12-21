/**
 * 
 */
package org.mspring.mlog.inf.standard;

import java.io.File;
import java.io.InputStream;

import org.mspring.mlog.service.FileService;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-12-19
 * @Description 
 * @TODO 
 */
@Service
public class StandardFileServiceImpl implements FileService {

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String, java.io.File)
     */
    @Override
    public String uploadFile(String fileName, File file) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.FileService#uploadFile(java.lang.String, java.io.InputStream, long)
     */
    @Override
    public String uploadFile(String fileName, InputStream inputStream, long contentLength) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.FileService#uploadBase64File(java.lang.String, java.lang.String)
     */
    @Override
    public String uploadBase64File(String fileName, String base64) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.FileService#deleteFile(java.lang.String)
     */
    @Override
    public void deleteFile(String path) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.FileService#getMimeType(java.io.File)
     */
    @Override
    public String getMimeType(File file) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.FileService#getMimeType(java.io.InputStream)
     */
    @Override
    public String getMimeType(InputStream inputStream) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.FileService#getMimeType(byte[])
     */
    @Override
    public String getMimeType(byte[] bytes) {
        // TODO Auto-generated method stub
        return null;
    }

}
