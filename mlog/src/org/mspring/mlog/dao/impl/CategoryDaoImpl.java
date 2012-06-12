/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.CategoryDao;
import org.mspring.mlog.entity.Category;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class CategoryDaoImpl extends AbstractHibernateDao<Category> implements CategoryDao {

    /**
     * @param entityClass
     */
    protected CategoryDaoImpl() {
        super(Category.class);
        // TODO Auto-generated constructor stub
    }

}
