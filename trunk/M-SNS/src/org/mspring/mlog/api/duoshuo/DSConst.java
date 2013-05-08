/**
 * 
 */
package org.mspring.mlog.api.duoshuo;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
public class DSConst {
    /**
     * 同步数据类型
     */
    public static final String DATA_TYPE_JSON = "json";
    public static final String DATA_TYPE_JSONP = "jsonp";

    /**
     * 文章同步URL
     */
    public static final String POST_SYNC_URL = "http://api.duoshuo.com/threads/import.";
    
    /**
     * 评论同步URL
     */
    public static final String COMMENT_SYNC_URL = "http://api.duoshuo.com/posts/import.";
}
