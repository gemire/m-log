/**
 * 
 */
package org.mspring.mlog.service.search.impl;

import java.util.List;

import org.mspring.mlog.dao.ArticleDao;
import org.mspring.mlog.dao.search.ArticleSearchDao;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.service.search.ArticleSearchService;
import org.mspring.platform.dao.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Apr 2, 2012
 */
@Service
@Transactional
public class ArticleSearchServiceImpl implements ArticleSearchService {
    private ArticleSearchDao articleSearchDao;
    private ArticleDao articleDao;

    @Autowired
    public void setArticleSearchDao(ArticleSearchDao articleSearchDao) {
        this.articleSearchDao = articleSearchDao;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.ArticleSearchService#search(org.mspring
     * .platform.dao.support.Page, java.lang.String)
     */
    @Override
    public Page<Article> search(Page<Article> page, String queryString) {
        // TODO Auto-generated method stub
        return articleSearchDao.search(page, queryString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.ArticleSearchService#rebuildAllArticleIndex
     * ()
     */
    @Override
    public void rebuildAllArticleIndex() {
        // TODO Auto-generated method stub
        List<Article> articles = articleDao.findAll();
        if (articles != null && articles.size() > 0) {
            for (Article article : articles) {
                articleSearchDao.index(article);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.search.ArticleSearchService#rebuildArticleIndex
     * (java.lang.Long)
     */
    @Override
    public void rebuildArticleIndex(Long articleId) {
        // TODO Auto-generated method stub
        if (articleId != null) {
            Article article = articleDao.get(articleId);
            if (article != null) {
                articleSearchDao.index(article);
            }
        }
    }

}
