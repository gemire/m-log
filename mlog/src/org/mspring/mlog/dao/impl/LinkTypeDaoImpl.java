/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.LinkTypeDao;
import org.mspring.mlog.entity.LinkType;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class LinkTypeDaoImpl extends AbstractHibernateDao<LinkType> implements LinkTypeDao {

    /**
     * @param entityClass
     */
    protected LinkTypeDaoImpl() {
        super(LinkType.class);
        // TODO Auto-generated constructor stub
    }

}
