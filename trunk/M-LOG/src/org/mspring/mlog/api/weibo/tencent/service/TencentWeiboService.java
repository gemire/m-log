/**
 * 
 */
package org.mspring.mlog.api.weibo.tencent.service;

import org.mspring.mlog.api.weibo.tencent.oauthv2.OAuthV2;
import org.mspring.platform.web.ResponseEntity;

/**
 * @author Gao Youbo
 * @since 2013-5-29
 * @description
 * @TODO
 */
public interface TencentWeiboService {

    /**
     * 绑定腾讯微博
     * 
     * @param userId
     * @param accessToken
     * @param openid
     * @param openkey
     */
    public void bindTencentWeibo(Long userId, String accessToken, String openid, String openkey);

    /**
     * 获取OAuthV2对象
     * 
     * @param userId
     * @return
     */
    public OAuthV2 getOAuthV2(Long userId);

    /**
     * 发表微博
     * 
     * @param userId
     * @param content
     * @param ip
     * @return 服务器返回结果
     */
    public ResponseEntity postWeibo(Long userId, String content, String ip);
}
