/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.AuthorityResourceDao;
import org.mspring.mlog.entity.security.AuthorityResource;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class AuthorityResourceDaoImpl extends AbstractHibernateDao<AuthorityResource> implements AuthorityResourceDao {

    /**
     * @param entityClass
     */
    protected AuthorityResourceDaoImpl() {
        super(AuthorityResource.class);
        // TODO Auto-generated constructor stub
    }

}
