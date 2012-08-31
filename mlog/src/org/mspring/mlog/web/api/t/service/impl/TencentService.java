/**
 * 
 */
package org.mspring.mlog.web.api.t.service.impl;

import java.util.List;

import org.mspring.mlog.web.api.t.common.ParamArrayList;
import org.mspring.mlog.web.api.t.common.TConfigTokens;
import org.mspring.mlog.web.api.t.common.TencentTokens;
import org.mspring.mlog.web.api.t.model.OAuthV2RequestParams;
import org.mspring.mlog.web.api.t.service.TService;
import org.mspring.mlog.web.api.t.utils.TConfigUtils;
import org.mspring.mlog.web.api.t.utils.THttpUtils;
import org.mspring.mlog.web.api.t.utils.TUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public class TencentService implements TService {
    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.api.t.service.TService#add()
     */
    @Override
    public String add(String content) {
        // TODO Auto-generated method stub
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

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.api.t.service.TService#list()
     */
    @Override
    public List<String> list() {
        // TODO Auto-generated method stub
        ParamArrayList params = new ParamArrayList();
        params.add("oauth_consumer_key", TConfigUtils.getClientId(TConfigTokens.APP_TENCENT));
        params.add("access_token", TConfigUtils.getAccessToken(TConfigTokens.APP_TENCENT));
        params.add("openid", TConfigUtils.getOpenid(TConfigTokens.APP_TENCENT));
        params.add("oauth_version", OAuthV2RequestParams.OAUTH_V2_VERSION);
        params.add("scope", "all");
        params.add("format", "json");
        params.add("clientip", "127.0.0.1");

        params.add("ids", "83179096184071,16880093593589");

        String queryString = TUtils.getQueryString(params);
        String response = "";
        
        try {
            response = THttpUtils.httpGet(TencentTokens.LIST_URL, queryString);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(response);
        return null;
    }
    
    public String broadcast_timeline_ids(){
        ParamArrayList params = new ParamArrayList();
        params.add("oauth_consumer_key", TConfigUtils.getClientId(TConfigTokens.APP_TENCENT));
        params.add("access_token", TConfigUtils.getAccessToken(TConfigTokens.APP_TENCENT));
        params.add("openid", TConfigUtils.getOpenid(TConfigTokens.APP_TENCENT));
        params.add("oauth_version", OAuthV2RequestParams.OAUTH_V2_VERSION);
        params.add("scope", "all");
        params.add("format", "json");
        params.add("clientip", "127.0.0.1");
        
        params.add("pageflag", "0");
        params.add("reqnum", "20");
        params.add("lastid", "0");
        params.add("type", "3");
        

        //params.add("ids", "gaoyoubo");

        String queryString = TUtils.getQueryString(params);
        String response = "";
        
        //http://open.t.qq.com/api/statuses/broadcast_timeline_ids?format=json&pageflag=0&reqnum=20&pagetime=0&lastid=6843121&type=0x1&contenttype=1
        
        try {
            //response = THttpUtils.httpGet(TencentTokens.LIST_URL, queryString);
            response = THttpUtils.httpGet("http://open.t.qq.com/api/statuses/broadcast_timeline_ids", queryString);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }

    public static void main(String[] args) {
        new TencentService().list();
    }

}
