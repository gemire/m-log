/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.LinkType;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-12-1
 * @Description
 * @TODO
 */
public interface LinkTypeService {
    List<LinkType> findAll();

    List<LinkType> findAllVisable();

    LinkType createLinkType(LinkType linkType);

    LinkType getLinkTypeById(Long id);

    Page<LinkType> findLinkTypes(Page<LinkType> linkTypePage, String queryString);

    void deleteLinkType(Long... id);

    void updateLinkType(LinkType lt);

    void setLinkTypeVisable(boolean visable, Long... id);

    void updateLinkTypeOrders(Long[] ids, Long[] orders);

    void updateLinkTypeNames(Long[] ids, String[] names);
}
