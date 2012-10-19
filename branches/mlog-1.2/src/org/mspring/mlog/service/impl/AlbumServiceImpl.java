/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.Date;
import java.util.List;

import org.mspring.mlog.entity.Album;
import org.mspring.mlog.service.AlbumService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-10-15
 * @Description
 * @TODO
 */
@Service
@Transactional
public class AlbumServiceImpl extends AbstractServiceSupport implements AlbumService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.AlbumService#createAlbum(org.mspring.mlog.entity
     * .Album)
     */
    @Override
    public Album createAlbum(Album album) {
        // TODO Auto-generated method stub
        if (album.getCreateTime() == null) {
            album.setCreateTime(new Date());
        }
        Long id = (Long) create(album);
        return getAlbumById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#deleteAlbum(java.lang.Long[])
     */
    @Override
    public void deleteAlbum(Long... idArray) {
        // TODO Auto-generated method stub
        if (idArray != null && idArray.length > 0) {
            String hql = "delete from Album a where a.id = ?";
            for (int i = 0; i < idArray.length; i++) {
                executeUpdate(hql, idArray[i]);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#getAlbumById(java.lang.Long)
     */
    @Override
    public Album getAlbumById(Long id) {
        // TODO Auto-generated method stub
        return (Album) getById(Album.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.AlbumService#findAlbum(org.mspring.platform.
     * persistence.support.Page, java.lang.String)
     */
    @Override
    public Page<Album> findAlbum(Page<Album> page, String queryString) {
        // TODO Auto-generated method stub
        return findPage(queryString, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.AlbumService#findAlbum(org.mspring.platform.
     * persistence.support.Page, java.lang.String, java.lang.Object[])
     */
    @Override
    public Page<Album> findAlbum(Page<Album> page, String queryString, Object... params) {
        // TODO Auto-generated method stub
        return findPage(queryString, page, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.AlbumService#findAlbum(org.mspring.platform.
     * persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<Album> findAlbum(Page<Album> page, QueryCriterion criterion) {
        // TODO Auto-generated method stub
        return findPage(criterion, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.AlbumService#updateAlbum(org.mspring.mlog.entity
     * .Album)
     */
    @Override
    public void updateAlbum(Album album) {
        // TODO Auto-generated method stub
        update(album);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.AlbumService#findAllAlbum()
     */
    @Override
    public List<Album> findAllAlbum() {
        // TODO Auto-generated method stub
        return find("from Album album order by album.sortOrder");
    }

}
