/**
 * 
 */
package org.mspring.mlog.task.cache;

import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.service.ArticleService;
import org.mspring.platform.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since Apr 10, 2012
 * @see 用于在文章内容修改后，清楚文章缓存，确保文章能够正确显示
 */
@Component
public class CleanArticleCacheTask extends AbstractTask {
    private static final Logger log = Logger.getLogger(CleanArticleCacheTask.class);
    
    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.task.AbstractTask#doTask(java.util.Map)
     */
    @Override
    protected void doTask(Map<Object, Object> context) throws Exception {
        // TODO Auto-generated method stub
        Long[] ids = (Long[]) context.get(CacheAspect.ARTICLE_ID_ARRAY);
        if (ids != null && ids.length > 0) {
            for (Long articleId : ids) {
                articleService._removeArticleCache(articleId);
                log.debug("clean article cache [articleId = " + articleId + "]");
            }
        }
    }

}
