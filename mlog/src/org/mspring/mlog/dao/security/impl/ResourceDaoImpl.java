/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.ResourceDao;
import org.mspring.mlog.entity.security.Resource;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class ResourceDaoImpl extends AbstractHibernateDao<Resource> implements ResourceDao {

    /**
     * @param entityClass
     */
    protected ResourceDaoImpl() {
        super(Resource.class);
        // TODO Auto-generated constructor stub
    }

}
