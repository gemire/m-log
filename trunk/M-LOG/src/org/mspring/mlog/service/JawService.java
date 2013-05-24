/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.Jaw;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-5-24
 * @description
 * @TODO
 */
public interface JawService {
    public Jaw getJawById(Long id);

    public Long createJaw(Jaw jaw);

    public void deleteJaw(Long... id);

    public Page<Jaw> findJawPage(QueryCriterion queryCriterion, Page<Jaw> page);
}
