/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Album;
import org.mspring.platform.dao.support.Page;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since Dec 18, 2011
 */
public interface AlbumService {
    Album findAlbumById(Long id);
    Album createAlbum(Album album);
    List<Album> findAllAlbum();
    Page<Album> queryAlbum(Page<Album> page, String queryString);
    void deleteAlbum(Long... albumIds);
}
