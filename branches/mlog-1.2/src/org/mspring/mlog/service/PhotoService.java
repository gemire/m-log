/**
 * 
 */
package org.mspring.mlog.service;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.entity.Photo;

/**
 * @author Gao Youbo
 * @since 2012-10-17
 * @Description
 * @TODO
 */
public interface PhotoService {
    public Photo createPhoto(HttpServletRequest request, Long album);
    
    public Photo createPhoto(HttpServletRequest request, Long album, boolean autoRotate);
    
    public Photo getPhotoById(Long photoId);
}
