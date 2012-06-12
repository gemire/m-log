/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.UserRoleDao;
import org.mspring.mlog.entity.security.UserRole;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class UserRoleDaoImpl extends AbstractHibernateDao<UserRole> implements UserRoleDao {

    /**
     * @param entityClass
     */
    protected UserRoleDaoImpl() {
        super(UserRole.class);
        // TODO Auto-generated constructor stub
    }

}
