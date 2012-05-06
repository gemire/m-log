/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.ArticleCategoryDao;
import org.mspring.mlog.entity.ArticleCategory;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class ArticleCategoryDaoImpl extends AbstractHibernateDao<ArticleCategory> implements ArticleCategoryDao {

    /**
     * @param entityClass
     */
    protected ArticleCategoryDaoImpl() {
        super(ArticleCategory.class);
        // TODO Auto-generated constructor stub
    }

}
