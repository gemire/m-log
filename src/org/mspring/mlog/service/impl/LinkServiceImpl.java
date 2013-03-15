/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.service.LinkService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-30
 * @Description
 * @TODO
 */
@Service
@Transactional
public class LinkServiceImpl extends AbstractServiceSupport implements LinkService {

    private static final Logger log = Logger.getLogger(LinkServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#findLinks(org.mspring.platform.
     * persistence.support.Page, java.lang.String)
     */
    @Override
    public Page<Link> findLinks(Page<Link> linkPage, String queryString) {
        // TODO Auto-generated method stub
        return findPage(queryString, linkPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#findLinks(org.mspring.platform.
     * persistence.support.Page, java.lang.String, java.lang.Object[])
     */
    @Override
    public Page<Link> findLinks(Page<Link> linkPage, String queryString, Object... params) {
        // TODO Auto-generated method stub
        return findPage(queryString, linkPage, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#findVisableLinks()
     */
    @Override
    public List<Link> findVisableLinks() {
        // TODO Auto-generated method stub
        String queryString = "select link from Link link where link.visable = true order by link.order asc";
        return find(queryString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkService#findVisableLinks(java.lang.Long)
     */
    @Override
    public List<Link> findVisableLinks(Long linkType) {
        // TODO Auto-generated method stub
        String queryString = "select link from Link link where link.visable = true and link.type.id = ? order by link.order asc";
        return find(queryString, linkType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkService#createLink(org.mspring.mlog.entity
     * .Link)
     */
    @Override
    public Link createLink(Link link) {
        // TODO Auto-generated method stub
        Serializable s = create(link);
        return (Link) getById(Link.class, s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkService#updateLink(org.mspring.mlog.entity
     * .Link)
     */
    @Override
    public void updateLink(Link link) {
        // TODO Auto-generated method stub
        update(link);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#deleteLinks(java.lang.Long[])
     */
    @Override
    public void deleteLinks(Long... idArray) {
        // TODO Auto-generated method stub
        remove(Link.class, idArray);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#getLinkById(java.lang.Long)
     */
    @Override
    public Link getLinkById(Long id) {
        // TODO Auto-generated method stub
        return (Link) getById(Link.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#findLinks(org.mspring.platform.
     * persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<Link> findLinks(Page<Link> linkPage, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, linkPage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkService#setLinkVisable(boolean,
     * java.lang.Long[])
     */
    @Override
    public void setLinkVisable(boolean visable, Long... id) {
        // TODO Auto-generated method stub
        if (id != null) {
            for (Long i : id) {
                executeUpdate("update Link l set l.visable = ? where l.id = ?", visable, i);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkService#updateLinkOrders(java.lang.Long[],
     * java.lang.Long[])
     */
    @Override
    public void updateLinkOrders(Long[] ids, Long[] orders) {
        // TODO Auto-generated method stub
        if (ids == null || orders == null || ids.length == 0 || orders.length == 0) {
            log.error("ids or orders is empty", new BusinessException("ids or orders is empty"));
            return;
        }
        if (ids.length != orders.length) {
            log.error("ids.length != orders.length", new BusinessException("ids.length != orders.length"));
            return;
        }
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            Long order = orders[i];
            if (id == null) {
                log.warn("id is null");
                continue;
            }
            executeUpdate("update Link l set l.order = ? where l.id = ?", order, id);
        }
    }

}
