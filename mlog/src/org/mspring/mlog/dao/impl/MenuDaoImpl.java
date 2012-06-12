/**
 * 
 */
package org.mspring.mlog.dao.impl;

import java.util.List;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.common.MenuType;
import org.mspring.mlog.dao.MenuDao;
import org.mspring.mlog.entity.Menu;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class MenuDaoImpl extends AbstractHibernateDao<Menu> implements MenuDao {

    /**
     * @param entityClass
     */
    protected MenuDaoImpl() {
        super(Menu.class);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.dao.MenuDao#findWithoutSystem()
     */
    @Override
    public List<Menu> findWithoutSystem() {
        // TODO Auto-generated method stub
        String hql = "select m from Menu m where m.type <> " + MenuType.SYSTEM;
        return find(hql);
    }

}
