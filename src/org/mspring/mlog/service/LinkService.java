/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Link;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-7-30
 * @Description
 * @TODO
 */
public interface LinkService {
    Page<Link> findLinks(Page<Link> linkPage, String queryString);

    Page<Link> findLinks(Page<Link> linkPage, String queryString, Object... params);
    
    Page<Link> findLinks(Page<Link> linkPage, QueryCriterion queryCriterion);

    List<Link> findVisableLinks();
    
    Link getLinkById(Long id);

    Link createLink(Link link);

    void updateLink(Link link);

    void deleteLinks(Long... idArray);
}
