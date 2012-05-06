/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.RoleDao;
import org.mspring.mlog.entity.security.Role;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class RoleDaoImpl extends AbstractHibernateDao<Role> implements RoleDao {

    /**
     * @param entityClass
     */
    protected RoleDaoImpl() {
        super(Role.class);
        // TODO Auto-generated constructor stub
    }

}
