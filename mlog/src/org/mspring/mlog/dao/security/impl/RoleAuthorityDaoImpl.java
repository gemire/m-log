/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.RoleAuthorityDao;
import org.mspring.mlog.entity.security.RoleAuthority;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class RoleAuthorityDaoImpl extends AbstractHibernateDao<RoleAuthority> implements RoleAuthorityDao {

    /**
     * @param entityClass
     */
    protected RoleAuthorityDaoImpl() {
        super(RoleAuthority.class);
        // TODO Auto-generated constructor stub
    }

}
