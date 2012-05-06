/**
 * 
 */
package org.mspring.mlog.service.search.impl;

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

    @Autowired
    public void setArticleSearchDao(ArticleSearchDao articleSearchDao) {
        this.articleSearchDao = articleSearchDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.search.ArticleSearchService#search(org.mspring.platform.dao.support.Page,
     *      java.lang.String)
     */
    @Override
    public Page<Article> search(Page<Article> page, String queryString) {
        // TODO Auto-generated method stub
        return articleSearchDao.search(page, queryString);
    }

}
