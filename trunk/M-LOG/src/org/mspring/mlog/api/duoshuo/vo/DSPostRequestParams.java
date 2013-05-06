/**
 * 
 */
package org.mspring.mlog.api.duoshuo.vo;

import java.util.List;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 * 
 *       返回数据{ "code":0, "response": { "1001":"1152985276292923001",
 *       "1002":"1152985276292923002", "1003":"1152985276292923003",
 *       "aboutme":"1152985276292923004" } } }
 * 
 *       返回数据参数说明
 * 
 *       code int 一定返回 结果码。0为成功。失败时为错误码。
 * 
 *       errorMessage string 错误消息。当code不为0时，返回错误消息。
 * 
 *       response array 多说api返回结果中，通常在response中含有主要返回数据。当code为0时返回。
 * 
 *       response是以thread_key为主键，thread_id为值的数组
 * 
 *       thread_id int64 一定返回 文章在多说的对应id
 */
public class DSPostRequestParams extends DSRequestParams {

    private List<DSPost> threads; // 文章列表

    public List<DSPost> getThreads() {
        return threads;
    }

    public void setThreads(List<DSPost> threads) {
        this.threads = threads;
    }

}
