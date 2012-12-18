/**
 * 
 */
package org.mspring.mlog.api.t.original;

import org.mspring.mlog.api.t.common.ParamArrayList;
import org.mspring.mlog.api.t.common.TConfigTokens;
import org.mspring.mlog.api.t.common.TencentTokens;
import org.mspring.mlog.api.t.model.OAuthV2RequestParams;
import org.mspring.mlog.api.t.utils.TConfigUtils;
import org.mspring.mlog.api.t.utils.THttpUtils;
import org.mspring.mlog.api.t.utils.TUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-31
 * @Description
 * @TODO 腾讯微博原生调用接口（即不对返回数据做任何处理的调用）
 */
public class TencentAPI {
    /**
     * 发表一条微博
     * 
     * @param content
     * @return
     */
    public static String t_add(String content) {
        ParamArrayList params = new ParamArrayList();
        params.add("oauth_consumer_key", TConfigUtils.getClientId(TConfigTokens.APP_TENCENT));
        params.add("access_token", TConfigUtils.getAccessToken(TConfigTokens.APP_TENCENT));
        params.add("openid", TConfigUtils.getOpenid(TConfigTokens.APP_TENCENT));
        params.add("oauth_version", OAuthV2RequestParams.OAUTH_V2_VERSION);
        params.add("scope", "all");
        params.add("format", "json");
        params.add("clientip", "127.0.0.1");

        params.add("syncflag", "0");
        params.add("content", content);

        String queryString = TUtils.getQueryString(params);
        String response = "";
        try {
            response = THttpUtils.httpPost(TencentTokens.ADD_URL, queryString);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    public static String t_list(String ids) {
        ParamArrayList params = new ParamArrayList();

        params.add("oauth_consumer_key", TConfigUtils.getClientId(TConfigTokens.APP_TENCENT));
        params.add("access_token", TConfigUtils.getAccessToken(TConfigTokens.APP_TENCENT));
        params.add("openid", TConfigUtils.getOpenid(TConfigTokens.APP_TENCENT));
        params.add("oauth_version", OAuthV2RequestParams.OAUTH_V2_VERSION);
        params.add("scope", "all");

        params.add("clientip", "127.0.0.1");
        params.add("format", "json");
        params.add("ids", ids);

        String queryString = TUtils.getQueryString(params);
        String response = "";

        try {
            response = THttpUtils.httpGet(TencentTokens.LIST_URL, queryString);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 我发表时间线索引
     * 
     * @param pageflag
     *            分页标识（0：第一页，1：向下翻页，2：向上翻页）
     * @param reqnum
     *            每次请求记录的条数（1-300条）
     * @param type
     *            拉取类型0x1:原创发表,
     *            0x2:转载,如需拉取多个类型请使用|，如(0x1|0x2)得到3，则type=3即可，填零表示拉取所有类型
     * @param contenttype
     *            内容过滤。0-表示所有类型，1-带文本，2-带链接，4-带图片，8-带视频，0x10-带音频,
     *            建议不使用contenttype为1的类型，如果要拉取只有文本的微博，建议使用0x80
     * @param pagetime
     *            本页起始时间（第一页：填0，向上翻页：填上一次请求返回的第一条记录时间，向下翻页：填上一次请求返回的最后一条记录时间）
     * @param lastid
     *            和pagetime配合使用（第一页：填0，向上翻页：填上一次请求返回的第一条记录id，向下翻页：
     *            填上一次请求返回的最后一条记录id）
     * @return
     */
    public static String statuses_broadcast_timeline_ids(String pageflag, String reqnum, String type, String contenttype, String pagetime, String lastid) {
        ParamArrayList params = new ParamArrayList();
        params.add("oauth_consumer_key", TConfigUtils.getClientId(TConfigTokens.APP_TENCENT));
        params.add("access_token", TConfigUtils.getAccessToken(TConfigTokens.APP_TENCENT));
        params.add("openid", TConfigUtils.getOpenid(TConfigTokens.APP_TENCENT));
        params.add("oauth_version", OAuthV2RequestParams.OAUTH_V2_VERSION);
        params.add("scope", "all");
        params.add("format", "json");
        params.add("clientip", "127.0.0.1");

        params.add("pageflag", pageflag);
        params.add("reqnum", reqnum);
        params.add("type", type);
        params.add("contenttype", contenttype);
        params.add("pagetime", pagetime);
        params.add("lastid", lastid);

        String queryString = TUtils.getQueryString(params);
        String response = "";
        try {
            response = THttpUtils.httpGet(TencentTokens.STATUSES_BROADCAST_TIMELINE_IDS, queryString);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }
}
