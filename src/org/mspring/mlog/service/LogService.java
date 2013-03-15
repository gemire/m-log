/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.Log;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-3-12
 * @description
 * @TODO
 */
public interface LogService {
    public Log getLogById(Long id);

    public void createLog(Log log);

    public Page<Log> findLog(QueryCriterion queryCriterion, Page<Log> logPage);

    public void deleteLogs(int days);
}
