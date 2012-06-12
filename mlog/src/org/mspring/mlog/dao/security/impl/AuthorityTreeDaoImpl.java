/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.AuthorityTreeDao;
import org.mspring.mlog.entity.security.AuthorityTree;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class AuthorityTreeDaoImpl extends AbstractHibernateDao<AuthorityTree> implements AuthorityTreeDao {

    /**
     * @param entityClass
     */
    protected AuthorityTreeDaoImpl() {
        super(AuthorityTree.class);
        // TODO Auto-generated constructor stub
    }

}
