/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.dao.AlbumDao;
import org.mspring.mlog.dao.PhotoDao;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since Dec 18, 2011
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    private AlbumDao albumDao;
    private PhotoService photoService;

    @Autowired
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#createAlbum(org.mspring.mlog.entity.Album)
     */
    @Override
    public Album createAlbum(Album album) {
        // TODO Auto-generated method stub
        Serializable s = albumDao.save(album);
        return albumDao.get(s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#queryAlbum(org.mspring.platform.dao.support.Page,
     *      java.lang.String)
     */
    @Override
    public Page<Album> queryAlbum(Page<Album> page, String queryString) {
        // TODO Auto-generated method stub
        return albumDao.findPage(page, queryString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#findAllAlbum()
     */
    @Override
    public List<Album> findAllAlbum() {
        // TODO Auto-generated method stub
        return albumDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#findAlbumById(java.lang.Long)
     */
    @Override
    public Album findAlbumById(Long id) {
        // TODO Auto-generated method stub
        return albumDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#deleteAlbum(java.lang.Long[])
     */
    @Override
    public void deleteAlbum(Long... albumIds) {
        // TODO Auto-generated method stub
        for (Long id : albumIds) {
            if (!photoService.hasPhotoInAlbum(id)) {
                albumDao.delete(id);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#queryAlbum(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Album> queryAlbum(Page<Album> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return albumDao.findPage(page, queryCriterion);
    }

}
