/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.PhotoDao;
import org.mspring.mlog.entity.Photo;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class PhotoDaoImpl extends AbstractHibernateDao<Photo> implements PhotoDao {

    /**
     * @param entityClass
     */
    protected PhotoDaoImpl() {
        super(Photo.class);
        // TODO Auto-generated constructor stub
    }

}
