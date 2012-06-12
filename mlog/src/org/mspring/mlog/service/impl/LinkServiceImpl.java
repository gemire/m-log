/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.dao.LinkDao;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.service.LinkService;
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
public class LinkServiceImpl implements LinkService {
    private LinkDao linkDao;

    @Autowired
    public void setLinkDao(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#findAllDisplayLinks()
     */
    @Override
    public List<Link> findAllDisplayLinks() {
        // TODO Auto-generated method stub
        return linkDao.findAllDisplayLinks();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#createLink(org.mspring.mlog.entity.Link)
     */
    @Override
    public Link createLink(Link link) {
        // TODO Auto-generated method stub
        Serializable s = linkDao.save(link);
        return linkDao.get(s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#deleteLink(java.lang.Long[])
     */
    @Override
    public void deleteLink(Long[] ids) {
        // TODO Auto-generated method stub
        linkDao.delete(ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#getLinkById(java.lang.Long)
     */
    @Override
    public Link getLinkById(Long id) {
        // TODO Auto-generated method stub
        return linkDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#queryLink(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Link> queryLink(Page<Link> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return linkDao.findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#updateLink(org.mspring.mlog.entity.Link)
     */
    @Override
    public void updateLink(Link link) {
        // TODO Auto-generated method stub
        linkDao.update(link);
    }
}
