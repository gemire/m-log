/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.dao.LinkTypeDao;
import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
@Service
@Transactional
public class LinkTypeServiceImpl implements LinkTypeService {

    private LinkTypeDao linkTypeDao;

    @Autowired
    public void setLinkTypeDao(LinkTypeDao linkTypeDao) {
        this.linkTypeDao = linkTypeDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#createLinkType(org.mspring.mlog.entity.LinkType)
     */
    @Override
    public LinkType createLinkType(LinkType linkType) {
        // TODO Auto-generated method stub
        Serializable s = linkTypeDao.save(linkType);
        return linkTypeDao.get(s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#findAllLinkType()
     */
    @Override
    public List<LinkType> findAllLinkType() {
        // TODO Auto-generated method stub
        return linkTypeDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#deleteLinkType(java.lang.Long[])
     */
    @Override
    public void deleteLinkType(Long[] ids) {
        // TODO Auto-generated method stub
        linkTypeDao.delete(ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#findLinkTypeById(java.lang.Long)
     */
    @Override
    public LinkType findLinkTypeById(Long id) {
        // TODO Auto-generated method stub
        return linkTypeDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#queryLinkType(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<LinkType> queryLinkType(Page<LinkType> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return linkTypeDao.findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#updateLinkType(org.mspring.mlog.entity.LinkType)
     */
    @Override
    public void updateLinkType(LinkType linkType) {
        // TODO Auto-generated method stub
        linkTypeDao.update(linkType);
    }

}
