/**
 * 
 */
package org.mspring.mlog.api.weibo.tencent.service;

import org.mspring.mlog.api.weibo.tencent.Const;
import org.mspring.mlog.api.weibo.tencent.api.TAPI;
import org.mspring.mlog.api.weibo.tencent.oauthv2.OAuthV2;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.UserService;
import org.mspring.mlog.utils.WebUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.JSONUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;

/**
 * @author Gao Youbo
 * @since 2013-5-29
 * @description
 * @TODO
 */
@Service
@Transactional
public class TencentWeiboServiceImpl extends AbstractServiceSupport implements TencentWeiboService {

    @Autowired
    private UserService userService;

    @Override
    public void bindTencentWeibo(Long userId, String accessToken, String openid, String openkey) {
        // TODO Auto-generated method stub
        User user = userService.getUserById(userId);
        user.setTencentWeiboAccessToken(accessToken);
        user.setTencentWeiboOpenid(openid);
        user.setTencentWeiboOpenkey(openkey);
        userService.updateUserInfo(user);
    }

    @Override
    public OAuthV2 getOAuthV2(Long userId) {
        // TODO Auto-generated method stub
        User user = userService.getUserById(userId);
        if (userId == null) {
            return null;
        }
        if (StringUtils.isBlank(user.getTencentWeiboAccessToken()) || StringUtils.isBlank(user.getTencentWeiboOpenid()) || StringUtils.isBlank(user.getTencentWeiboOpenkey())) {
            return null;
        }
        OAuthV2 oAuthV2 = new OAuthV2();
        oAuthV2.setClientId(Const.APP_KEY);
        oAuthV2.setClientSecret(Const.APP_SECRET);
        oAuthV2.setAccessToken(user.getTencentWeiboAccessToken());
        oAuthV2.setOpenid(user.getTencentWeiboOpenid());
        oAuthV2.setOpenkey(user.getTencentWeiboOpenkey());
        return oAuthV2;
    }

    @Override
    public ResponseEntity postWeibo(Long userId, String content, String ip) {
        // TODO Auto-generated method stub
        return postWeibo(userId, content, ip, null);
    }

    @Override
    public ResponseEntity postWeibo(Long userId, String content, String ip, String picpath) {
        // TODO Auto-generated method stub
        ResponseEntity response = new ResponseEntity();

        OAuthV2 oAuthV2 = getOAuthV2(userId);
        if (oAuthV2 == null) {
            response.setSuccess(false);
            response.setMessage("用户未授权");
            return response;
        }
        TAPI tAPI = new TAPI(oAuthV2.getOauthVersion());
        try {
            String responseString = "";
            if (StringUtils.isBlank(picpath)) {
                responseString = tAPI.add(oAuthV2, "json", content, ip);
            } else {
                picpath = WebUtils.getRealContextPath(picpath);
                responseString = tAPI.addPic(oAuthV2, "json", content, ip, picpath);
            }
            JsonObject jsonObject = JSONUtils.getAsJsonObject(responseString);
            int errcode = jsonObject.get("errcode").getAsInt();
            if (errcode == 0) {
                response.setSuccess(true);
                response.put("id", jsonObject.get("data").getAsJsonObject().get("id").getAsString());
            } else {
                response.setSuccess(false);
                response.setMessage(jsonObject.get("msg").getAsString());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

}
