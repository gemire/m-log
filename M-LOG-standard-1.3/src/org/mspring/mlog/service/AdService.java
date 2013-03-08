/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.Ad;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public interface AdService {
    Ad getAdById(Long id);

    Ad createAd(Ad ad);

    void updateAd(Ad ad);

    Page<Ad> findAdPage(QueryCriterion queryCriterion, Page<Ad> adPage);

    void deleteAd(Long... id);
}
