/**
 * 
 */
package org.mspring.mlog.service.impl;

import org.mspring.mlog.entity.Job;
import org.mspring.mlog.service.JobService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO
 */
@Service
@Transactional
public class JobServiceImpl extends AbstractServiceSupport implements JobService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.JobService#findJob(org.mspring.platform.persistence
     * .support.Page, java.lang.String)
     */
    @Override
    public Page<Job> findJob(Page<Job> page, String queryString) {
        // TODO Auto-generated method stub
        return findPage(queryString, page);
    }

}
