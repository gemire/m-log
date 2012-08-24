package org.mspring.mlog.web.api.t.tencent.api;

import org.mspring.mlog.web.api.t.tencent.constants.APIConstants;
import org.mspring.mlog.web.api.t.tencent.constants.OAuthConstants;
import org.mspring.mlog.web.api.t.tencent.oauthv1.OAuthV1Request;
import org.mspring.mlog.web.api.t.tencent.oauthv2.OAuthV2Request;
import org.mspring.mlog.web.api.t.tencent.utils.QHttpClient;

/**
 * API类的通用部分
 */
public abstract class BasicAPI {
    
    protected RequestAPI requestAPI;
    protected String apiBaseUrl=null;

    public BasicAPI(String OAuthVersion){
        if (OAuthVersion == OAuthConstants.OAUTH_VERSION_1 ) {
            requestAPI = new OAuthV1Request();
            apiBaseUrl=APIConstants.API_V1_BASE_URL;
        }else if(OAuthVersion == OAuthConstants.OAUTH_VERSION_2_A){
            requestAPI = new OAuthV2Request();
            apiBaseUrl=APIConstants.API_V2_BASE_URL;
        }
    }
    
    public BasicAPI(String OAuthVersion, QHttpClient qHttpClient){
        if (OAuthVersion == OAuthConstants.OAUTH_VERSION_1  ) {
            requestAPI = new OAuthV1Request(qHttpClient);
            apiBaseUrl=APIConstants.API_V1_BASE_URL;
        }else if(OAuthVersion == OAuthConstants.OAUTH_VERSION_2_A){
            requestAPI = new OAuthV2Request(qHttpClient);
            apiBaseUrl=APIConstants.API_V2_BASE_URL;
        }
    }
    
    public void shutdownConnection(){
        requestAPI.shutdownConnection();
    }

    public String getAPIBaseUrl() {
        return apiBaseUrl;
    }

    public abstract  void setAPIBaseUrl(String apiBaseUrl);
    
}
