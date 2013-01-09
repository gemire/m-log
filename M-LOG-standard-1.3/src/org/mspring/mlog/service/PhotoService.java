/**
 * 
 */
package org.mspring.mlog.service;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.entity.Photo;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-10-17
 * @Description
 * @TODO
 */
public interface PhotoService {
    public Photo createPhoto(HttpServletRequest request, Long album);

    public Photo getPhotoById(Long photoId);

    public void deletePhoto(Long... id);
    
    public void updatePhoto(Photo photo);

    public Page<Photo> findPhoto(Page<Photo> page, QueryCriterion queryCriterion);

    public Page<Photo> findPhoto(Page<Photo> page, String queryString);

    public Page<Photo> findPhoto(Page<Photo> page, String queryString, Object... params);
}
