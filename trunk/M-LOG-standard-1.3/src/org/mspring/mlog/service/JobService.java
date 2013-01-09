/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.Job;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO
 */
public interface JobService {
    Page<Job> findJob(Page<Job> page, String queryString);
    void setEnabled();
}
