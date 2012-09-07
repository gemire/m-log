/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.File;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.service.FileService;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.utils.ImageUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OptionService optionService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.FileService#saveBase64Image(javax.servlet.http
     * .HttpServletRequest, java.lang.String, java.lang.String)
     */
    @Override
    public String saveBase64Image(HttpServletRequest request, String base64Data, String fileType) {
        // TODO Auto-generated method stub
        String fileName = StringUtils.getFileName() + "." + fileType;
        String abstractPath = getAbstractFileUploadPath();
        String filePath = getFileUploadPath(request, abstractPath);
        String path = filePath + fileName;
        ImageUtils.saveBase64AsImage(base64Data, path);
        String blogurl = optionService.getOption("blogurl");
        return blogurl + abstractPath + fileName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.FileService#getAbstractFileUploadPath()
     */
    @Override
    public String getAbstractFileUploadPath() {
        // TODO Auto-generated method stub
        Calendar cal = Calendar.getInstance();
        return String.format("/uploads/%s/%s/", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.FileService#getFileUploadPath(javax.servlet.
     * http.HttpServletRequest, java.lang.String)
     */
    @Override
    public String getFileUploadPath(HttpServletRequest request, String abstractPath) {
        // TODO Auto-generated method stub
        String basePath = request.getRealPath("/") + abstractPath;
        File file = new File(basePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return basePath;
    }

}
