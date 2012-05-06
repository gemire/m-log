/**
 * 
 */
package org.mspring.mlog.task.cache;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.mspring.platform.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since Apr 10, 2012
 */
@Aspect
@Component
public class CacheAspect {
    private static final Logger log = Logger.getLogger(CacheAspect.class);
    
    public static final String ARTICLE_ID_ARRAY = "ARTICLE_ID_ARRAY";
    
    private Task cleanArticleCacheTask;

    @Autowired
    @Qualifier("cleanArticleCacheTask")
    public void setCleanArticleCacheTask(Task cleanArticleCacheTask) {
        this.cleanArticleCacheTask = cleanArticleCacheTask;
    }
    
    public void cleanArticleCache(){
        
    }
}
