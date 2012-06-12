/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.AuthorityDao;
import org.mspring.mlog.entity.security.Authority;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class AuthorityDaoImpl extends AbstractHibernateDao<Authority> implements AuthorityDao {

    /**
     * @param entityClass
     */
    protected AuthorityDaoImpl() {
        super(Authority.class);
        // TODO Auto-generated constructor stub
    }

}
