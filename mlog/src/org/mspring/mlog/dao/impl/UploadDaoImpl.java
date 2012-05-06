/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.UploadDao;
import org.mspring.mlog.entity.Upload;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class UploadDaoImpl extends AbstractHibernateDao<Upload> implements UploadDao {

    /**
     * @param entityClass
     */
    protected UploadDaoImpl() {
        super(Upload.class);
        // TODO Auto-generated constructor stub
    }

}
