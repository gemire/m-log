/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.Date;

import org.mspring.mlog.entity.Jaw;
import org.mspring.mlog.service.JawService;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-5-24
 * @description
 * @TODO
 */
@Service
@Transactional
public class JawServiceImpl extends AbstractServiceSupport implements JawService {

    @Override
    public Jaw getJawById(Long id) {
        // TODO Auto-generated method stub
        Object obj = getById(Jaw.class, id);
        return obj == null ? null : (Jaw) obj;
    }

    @Override
    public Long createJaw(Jaw jaw) {
        // TODO Auto-generated method stub
        jaw.setCreateTime(new Date());
        if (jaw.getAuthor() == null) {
            jaw.setAuthor(SecurityUtils.getCurrentUser());
        }
        return (Long) create(jaw);
    }

    @Override
    public void deleteJaw(Long... id) {
        // TODO Auto-generated method stub
        remove(Jaw.class, id);
    }

    @Override
    public Page<Jaw> findJawPage(QueryCriterion queryCriterion, Page<Jaw> page) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, page);
    }

}
