/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Album;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since Dec 18, 2011
 */
public interface AlbumService {
    Album findAlbumById(Long id);

    Album createAlbum(Album album);

    List<Album> findAllAlbum();

    Page<Album> queryAlbum(Page<Album> page, String queryString);

    Page<Album> queryAlbum(Page<Album> page, QueryCriterion queryCriterion);

    void deleteAlbum(Long... albumIds);
}
