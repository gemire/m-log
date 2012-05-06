/**
 * 
 */
package org.mspring.mlog.dao.impl;

import org.mspring.mlog.dao.ArticleTagDao;
import org.mspring.mlog.entity.ArticleTag;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class ArticleTagDaoImpl extends AbstractHibernateDao<ArticleTag> implements ArticleTagDao {

    /**
     * @param entityClass
     */
    protected ArticleTagDaoImpl() {
        super(ArticleTag.class);
        // TODO Auto-generated constructor stub
    }

}
