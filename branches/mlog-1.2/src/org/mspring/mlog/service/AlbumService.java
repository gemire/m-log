/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Album;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-10-15
 * @Description
 * @TODO
 */
public interface AlbumService {
    public Album createAlbum(Album album);

    public void deleteAlbum(Long... idArray);

    public Album getAlbumById(Long id);

    public void updateAlbum(Album album);
    
    public List<Album> findAllAlbum();

    public Page<Album> findAlbum(Page<Album> page, String queryString);

    public Page<Album> findAlbum(Page<Album> page, String queryString, Object... params);

    public Page<Album> findAlbum(Page<Album> page, QueryCriterion criterion);
}
