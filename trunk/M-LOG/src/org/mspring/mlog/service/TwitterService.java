/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.Twitter;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-5-24
 * @description
 * @TODO
 */
public interface TwitterService {
    public Twitter getTwitterById(Long id);

    public Long createTwitter(Twitter twitter);

    public void deleteTwitter(Long... id);

    public Page<Twitter> findTwitterPage(QueryCriterion queryCriterion, Page<Twitter> page);

    /**
     * 设置同步腾讯微博后返回的微博ID
     * 
     * @param twitter
     * @param weiboId
     */
    public void setTencentWeiboId(Long twitter, String weiboId);
}
