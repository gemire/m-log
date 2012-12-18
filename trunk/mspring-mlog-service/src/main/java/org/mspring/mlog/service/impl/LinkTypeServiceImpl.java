/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-12-1
 * @Description
 * @TODO
 */
@Service
@Transactional
public class LinkTypeServiceImpl extends AbstractServiceSupport implements LinkTypeService {

    private static final Logger log = Logger.getLogger(LinkTypeServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkTypeService#createLinkType(org.mspring.mlog
     * .entity.LinkType)
     */
    @Override
    public LinkType createLinkType(LinkType linkType) {
        // TODO Auto-generated method stub
        Serializable id = create(linkType);
        return getLinkTypeById((Long) id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkTypeService#getLinkTypeById(java.lang.Long)
     */
    @Override
    public LinkType getLinkTypeById(Long id) {
        // TODO Auto-generated method stub
        return (LinkType) getById(LinkType.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkTypeService#findLinkTypes(org.mspring.platform
     * .persistence.support.Page, java.lang.String)
     */
    @Override
    public Page<LinkType> findLinkTypes(Page<LinkType> linkTypePage, String queryString) {
        // TODO Auto-generated method stub
        return findPage(queryString, linkTypePage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkTypeService#deleteLinkType(java.lang.Long[])
     */
    @Override
    public void deleteLinkType(Long... id) {
        // TODO Auto-generated method stub
        if (id != null) {
            remove(LinkType.class, id);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#setLinkTypeVisable(boolean,
     * java.lang.Long[])
     */
    @Override
    public void setLinkTypeVisable(boolean visable, Long... id) {
        // TODO Auto-generated method stub
        if (id != null) {
            for (Long i : id) {
                executeUpdate("update LinkType lt set lt.visable = ? where lt.id = ?", visable, i);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkTypeService#updateLinkTypeOrders(java.lang
     * .Long[], java.lang.Long[])
     */
    @Override
    public void updateLinkTypeOrders(Long[] ids, Long[] orders) {
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
            executeUpdate("update LinkType lt set lt.order = ? where lt.id = ?", order, id);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkTypeService#updateLinkTypeNames(java.lang
     * .Long[], java.lang.String[])
     */
    @Override
    public void updateLinkTypeNames(Long[] ids, String[] names) {
        // TODO Auto-generated method stub
        if (ids == null || names == null || ids.length == 0 || names.length == 0) {
            log.error("ids or names is empty", new BusinessException("ids or names is empty"));
            return;
        }
        if (ids.length != names.length) {
            log.error("ids.length != names.length", new BusinessException("ids.length != names.length"));
            return;
        }
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            String name = names[i];
            if (id == null || StringUtils.isBlank(name)) {
                log.warn("id or name is null");
                continue;
            }
            executeUpdate("update LinkType lt set lt.name = ? where lt.id = ?", new Object[] { name, id });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.LinkTypeService#updateLinkType(org.mspring.mlog
     * .entity.LinkType)
     */
    @Override
    public void updateLinkType(LinkType lt) {
        // TODO Auto-generated method stub
        update(lt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#findAll()
     */
    @Override
    public List<LinkType> findAll() {
        // TODO Auto-generated method stub
        return findAll(LinkType.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.LinkTypeService#findAllVisable()
     */
    @Override
    public List<LinkType> findAllVisable() {
        // TODO Auto-generated method stub
        return find("select lt from LinkType lt where lt.visable = ? order by lt.order asc", true);
    }

}
