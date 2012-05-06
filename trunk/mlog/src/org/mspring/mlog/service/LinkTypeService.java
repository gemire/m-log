/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.LinkType;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public interface LinkTypeService {
    LinkType createLinkType(LinkType linkType);
    List<LinkType> findAllLinkType();
    void deleteLinkType(Long[] ids);
    LinkType findLinkTypeById(Long id);
    void updateLinkType(LinkType linkType);
    Page<LinkType> queryLinkType(Page<LinkType> page, QueryCriterion queryCriterion);
}
