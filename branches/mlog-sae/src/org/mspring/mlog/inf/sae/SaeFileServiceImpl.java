/**
 * 
 */
package org.mspring.mlog.inf.sae;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.impl.AbstractFileService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Gao Youbo
 * @since 2012-12-19
 * @Description
 * @TODO
 */
@Service
public class SaeFileServiceImpl extends AbstractFileService {
    private static final Logger log = Logger.getLogger(SaeFileServiceImpl.class);

    @Autowired
    private OptionService optionService;

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

}
