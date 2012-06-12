/**
 * 
 */
package org.mspring.mlog.dao.impl;

import java.util.List;

import org.mspring.mlog.dao.LinkDao;
import org.mspring.mlog.entity.Link;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class LinkDaoImpl extends AbstractHibernateDao<Link> implements LinkDao {

    /**
     * @param entityClass
     */
    protected LinkDaoImpl() {
        super(Link.class);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.dao.LinkDao#findAllDisplayLinks()
     */
    @Override
    public List<Link> findAllDisplayLinks() {
        // TODO Auto-generated method stub
        String queryString = "select l from Link l where l.display = ?";
        return find(queryString, true);
    }

}
