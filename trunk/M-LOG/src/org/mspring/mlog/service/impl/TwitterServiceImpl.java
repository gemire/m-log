/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.Date;

import org.mspring.mlog.entity.Twitter;
import org.mspring.mlog.service.TwitterService;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-5-24
 * @description
 * @TODO
 */
@Service
@Transactional
public class TwitterServiceImpl extends AbstractServiceSupport implements TwitterService {

    @Override
    public Twitter getTwitterById(Long id) {
        // TODO Auto-generated method stub
        Object obj = getById(Twitter.class, id);
        return obj == null ? null : (Twitter) obj;
    }

    @Override
    public Long createTwitter(Twitter twitter) {
        // TODO Auto-generated method stub
        twitter.setCreateTime(new Date());
        if (twitter.getAuthor() == null) {
            twitter.setAuthor(SecurityUtils.getCurrentUser());
        }
        return (Long) create(twitter);
    }

    @Override
    public void deleteTwitter(Long... id) {
        // TODO Auto-generated method stub
        remove(Twitter.class, id);
    }

    @Override
    public Page<Twitter> findTwitterPage(QueryCriterion queryCriterion, Page<Twitter> page) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, page);
    }

    @Override
    public void setTencentWeiboId(Long twitter, String weiboId) {
        // TODO Auto-generated method stub
        executeUpdate("update Twitter t set t.tencentWeiboId = ? where t.id = ?", new Object[] { weiboId, twitter });
    }

}
