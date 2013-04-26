/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.Date;

import org.mspring.mlog.entity.Log;
import org.mspring.mlog.service.LogService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-3-12
 * @description
 * @TODO
 */
@Service
@Transactional
public class LogServiceImpl extends AbstractServiceSupport implements LogService {
    @Override
    public Log getLogById(Long id) {
        // TODO Auto-generated method stub
        Object log = getById(Log.class, id);
        if (log == null) {
            return null;
        }
        return (Log) log;
    }

    @Override
    public void createLog(Log log) {
        // TODO Auto-generated method stub
        if (log.getActionTime() == null) {
            log.setActionTime(new Date());
        }
        create(log);
    }

    @Override
    public Page<Log> findLog(QueryCriterion queryCriterion, Page<Log> logPage) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, logPage);
    }

    @Override
    public void deleteLogs(int days) {
        // TODO Auto-generated method stub
        String hql = "delete Log log ";
        if (days > 0) {
            Date date = DateUtils.addDays(new Date(), -days);
            String dateStr = DateUtils.format(date, DateUtils.YYYY_MM_DD_HH_MM_SS);
            hql += "where log.actionTime < '" + dateStr + "'";
        }
        executeUpdate(hql);
    }

}
