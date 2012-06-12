/**
 * 
 */
package org.mspring.mlog.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.mspring.mlog.dao.ArticleCategoryDao;
import org.mspring.mlog.dao.ArticleDao;
import org.mspring.mlog.dao.ArticleTagDao;
import org.mspring.mlog.entity.Article;
import org.mspring.platform.persistence.hibernate.dao.AbstractHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class ArticleDaoImpl extends AbstractHibernateDao<Article> implements ArticleDao {

    /**
     * @param entityClass
     */
    protected ArticleDaoImpl() {
        super(Article.class);
        // TODO Auto-generated constructor stub
    }

    private ArticleTagDao articleTagDao;
    private ArticleCategoryDao articleCategoryDao;

    @Autowired
    public void setArticleTagDao(ArticleTagDao articleTagDao) {
        this.articleTagDao = articleTagDao;
    }

    @Autowired
    public void setArticleCategoryDao(ArticleCategoryDao articleCategoryDao) {
        this.articleCategoryDao = articleCategoryDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.dao.ArticleDao#getRecentArticles(int)
     */
    @Override
    public List<Article> getRecentArticles(int numposts) {
        // TODO Auto-generated method stub
        Query query = getSession().createQuery("select article from Article article order by article.createTime desc");
        query.setMaxResults(numposts);
        return query.list();
    }

}
