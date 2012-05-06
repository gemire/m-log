/**
 * 
 */
package org.mspring.mlog.web.cache;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since Jan 17, 2012 http://www.mspring.org
 */
public class CacheTokens implements Serializable {
    public static final String DEFAULT_CACHE_NAME = "DEFAULT_CACHE";
    
    public static final String PAGE_CACHE_NAME = "PageCachingFilter";
    
    //public static final String DB_QUERY_CACHE_NAME = "DB_Query_Cache";
    public static final String DB_QUERY_CACHE_NAME = "DEFAULT_CACHE";
    
    public static final String ARTICLE_CACHE_PREFIX = "MSPRING_MLOG_ARTICLE_CACHE_";
    
    public static final String INDEX_CACHE_KEY = "index_cache_key";
}
