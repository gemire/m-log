/**
 * 
 */
package org.mspring.mlog.dao.search;

import org.mspring.mlog.entity.Article;
import org.mspring.platform.dao.support.Page;

/**
 * @author Gao Youbo
 * @since Apr 1, 2012
 */
public interface ArticleSearchDao {
    Page<Article> search(Page<Article> page, String queryString);
}
