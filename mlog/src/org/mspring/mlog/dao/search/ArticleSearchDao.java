/**
 * 
 */
package org.mspring.mlog.dao.search;

import org.mspring.mlog.entity.Article;
import org.mspring.platform.persistence.search.BaseSearchDao;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since Apr 1, 2012
 */
public abstract interface ArticleSearchDao extends BaseSearchDao<Article> {
    /**
     * 搜索
     * @param page
     * @param queryString
     * @return
     */
    Page<Article> search(Page<Article> page, String queryString);
}
