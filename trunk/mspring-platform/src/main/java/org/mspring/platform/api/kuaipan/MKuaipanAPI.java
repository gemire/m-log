/**
 * 
 */
package org.mspring.platform.api.kuaipan;

import java.util.HashMap;
import java.util.Map;

import org.mspring.platform.api.kuaipan.client.JSONUtility;
import org.mspring.platform.api.kuaipan.client.KuaipanAPI;
import org.mspring.platform.api.kuaipan.client.KuaipanHTTPUtility;
import org.mspring.platform.api.kuaipan.client.OauthUtility;
import org.mspring.platform.api.kuaipan.client.exception.KuaipanAuthExpiredException;
import org.mspring.platform.api.kuaipan.client.exception.KuaipanIOException;
import org.mspring.platform.api.kuaipan.client.exception.KuaipanServerException;
import org.mspring.platform.api.kuaipan.client.model.KuaipanHTTPResponse;
import org.mspring.platform.api.kuaipan.client.model.KuaipanURL;
import org.mspring.platform.api.kuaipan.client.session.Session;

/**
 * @author Gao Youbo
 * @since 2013-1-6
 * @Description
 * @TODO
 */
public class MKuaipanAPI extends KuaipanAPI {

    private String siteurl;

    /**
     * @param session
     */
    public MKuaipanAPI(Session session, String siteurl) {
        super(session);
        // TODO Auto-generated constructor stub
        this.siteurl = siteurl;
    }

    /*
     * 重写原有方法，解决callback问题 (non-Javadoc)
     * 
     * @see org.mspring.mlog.api.kuaipan.client.KuaipanAPI#requestToken()
     */
    @Override
    public String requestToken() throws KuaipanIOException, KuaipanServerException, KuaipanAuthExpiredException {
        // TODO Auto-generated method stub
        String host = KuaipanHTTPUtility.API_HOST;
        getSession().unAuth();

        String callback = siteurl;
        if (callback.endsWith("/")) {
            callback = callback + "admin/api/kuaipan/accessToken";
        } else {
            callback = callback + "/admin/api/kuaipan/accessToken";
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("oauth_callback", callback);

        KuaipanURL url = OauthUtility.buildGetURL(host, "/open/requestToken", params, getSession().consumer, null, true);

        KuaipanHTTPResponse resp = KuaipanHTTPUtility.requestByGET(url);
        Map<String, Object> result = parseResponseToMap(resp);

        String key = (String) result.get("oauth_token");
        String secret = (String) result.get("oauth_token_secret");

        if (key == null || secret == null)
            throw new KuaipanIOException(resp.toString());

        getSession().setTempToken(key, secret);
        return KuaipanHTTPUtility.AUTH_URL + key;
    }

    /**
     * parse the response content and throw the right exception.
     * 
     * @param resp
     * @param url
     * @return
     * @throws KuaipanAuthExpiredException
     * @throws KuaipanServerException
     */
    private Map<String, Object> parseResponseToMap(KuaipanHTTPResponse resp) throws KuaipanAuthExpiredException, KuaipanServerException {
        Map<String, Object> result = JSONUtility.parse(resp.content);
        if (resp.code == KuaipanHTTPResponse.KUAIPAN_OK)
            return result;

        if (resp.code == KuaipanHTTPResponse.KUAIPAN_AUTHORIZATION_ERROR) {
            if (result != null) {
                String msg = (String) result.get("msg");
                if (msg != null && msg.equals(KuaipanHTTPResponse.MSG_AUTHORIZATION_EXPIRED))
                    throw new KuaipanAuthExpiredException();
            }
        }

        throw new KuaipanServerException(resp);
    }

}
