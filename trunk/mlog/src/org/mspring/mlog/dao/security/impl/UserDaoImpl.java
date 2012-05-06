/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.UserDao;
import org.mspring.mlog.entity.security.User;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

    /**
     * @param entityClass
     */
    protected UserDaoImpl() {
        super(User.class);
        // TODO Auto-generated constructor stub
    }

}
