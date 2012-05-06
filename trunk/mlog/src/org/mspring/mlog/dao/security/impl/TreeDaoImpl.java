/**
 * 
 */
package org.mspring.mlog.dao.security.impl;

import org.mspring.mlog.dao.security.TreeDao;
import org.mspring.mlog.entity.security.Tree;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class TreeDaoImpl extends AbstractHibernateDao<Tree> implements TreeDao {

    /**
     * @param entityClass
     */
    protected TreeDaoImpl() {
        super(Tree.class);
        // TODO Auto-generated constructor stub
    }

}
