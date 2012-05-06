/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.AlbumDao;
import org.mspring.mlog.entity.Album;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class AlbumDaoImpl extends AbstractHibernateDao<Album> implements AlbumDao {

    /**
     * @param entityClass
     */
    protected AlbumDaoImpl() {
        super(Album.class);
        // TODO Auto-generated constructor stub
    }

}
