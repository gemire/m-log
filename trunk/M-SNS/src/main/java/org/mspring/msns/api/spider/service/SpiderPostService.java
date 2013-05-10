/**
 * 
 */
package org.mspring.msns.api.spider.service;

import org.mspring.msns.api.spider.vo.SpiderPost;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
public interface SpiderPostService {
    SpiderPost getSpiderPostById(Long id);

    void createSpiderPost(SpiderPost spiderPost);

    Page<SpiderPost> findSpiderPostPage(QueryCriterion queryCriterion, Page<SpiderPost> spiderPostPage);

    void deleteSpiderPost(Long... id);

    void changePosted(Boolean posted, Long... id);

    void publishPost(Long[] catalogs, Long... id);
}
