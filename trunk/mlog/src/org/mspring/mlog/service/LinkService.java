/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Link;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public interface LinkService {
    List<Link> findAllDisplayLinks();

    Link createLink(Link link);

    void deleteLink(Long[] ids);

    Link getLinkById(Long id);

    void updateLink(Link link);

    Page<Link> queryLink(Page<Link> page, QueryCriterion queryCriterion);
}
