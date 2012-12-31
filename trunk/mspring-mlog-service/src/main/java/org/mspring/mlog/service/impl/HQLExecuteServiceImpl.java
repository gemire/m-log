/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import org.mspring.mlog.service.HQLExecuteService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-12-31
 * @Description 
 * @TODO 
 */
@Service
@Transactional
public class HQLExecuteServiceImpl extends AbstractServiceSupport implements HQLExecuteService {

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.HQLExecuteService#query(java.lang.String)
     */
    @Override
    public List query(String hql) {
        // TODO Auto-generated method stub
        return getHibernateTemplate().find(hql);
    }

}
