/**
 * 
 */
package org.mspring.mlog.service.search;

import org.mspring.mlog.entity.Article;
import org.mspring.platform.dao.support.Page;

/**
 * @author Gao Youbo
 * @since Apr 2, 2012
 */
public interface ArticleSearchService {
    Page<Article> search(Page<Article> page, String queryString);
}
