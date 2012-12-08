/**
 * 
 */
package org.mspring.mlog.api.t.common;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public class TencentTokens {

    public static final String BASE_HTTP_URL = "http://open.t.qq.com";
    public static final String BASE_HTTPS_URL = "https://open.t.qq.com";
    
    public static final String BASE_HTTP_API_URL = BASE_HTTP_URL + "/api";
    public static final String BASE_HTTPS_API_URL = BASE_HTTPS_URL + "/api";
    

    /**
     * 发布微博接口
     */
    public static final String ADD_URL = BASE_HTTP_API_URL + "/t/add";

    /**
     * 微博列表接口
     */
    public static final String LIST_URL = BASE_HTTPS_API_URL + "/t/list";
    
    /**
     * 我发表时间线索引
     */
    public static final String STATUSES_BROADCAST_TIMELINE_IDS = BASE_HTTPS_API_URL + "/statuses/broadcast_timeline_ids";
}
