/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.Date;

import org.mspring.mlog.entity.Ad;
import org.mspring.mlog.service.AdService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-3-7
 * @description
 * @TODO
 */
@Service
@Transactional
public class AdServiceImpl extends AbstractServiceSupport implements AdService {

    @Override
    public Ad getAdById(Long id) {
        // TODO Auto-generated method stub
        return (Ad) getById(Ad.class, id);
    }

    @Override
    public Ad createAd(Ad ad) {
        // TODO Auto-generated method stub
        if (ad.getCreateTime() == null) {
            ad.setCreateTime(new Date());
        }
        Long id = (Long) create(ad);
        return getAdById(id);
    }

    @Override
    public void updateAd(Ad ad) {
        // TODO Auto-generated method stub
        update(ad);
    }

    @Override
    public Page<Ad> findAdPage(QueryCriterion queryCriterion, Page<Ad> adPage) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, adPage);
    }

    @Override
    public void deleteAd(Long... id) {
        // TODO Auto-generated method stub
        remove(Ad.class, id);
    }

}
