/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Job;
import org.mspring.mlog.schedule.SchedulerService;
import org.mspring.mlog.service.JobService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final Logger log = Logger.getLogger(JobServiceImpl.class);

    @Autowired
    private SchedulerService schedulerService;

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

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.JobService#setEnabled(boolean,
     * java.lang.Long[])
     */
    @Override
    public void setEnabled(boolean enabled, Long... id) {
        // TODO Auto-generated method stub
        if (id != null) {
            for (Long i : id) {
                executeUpdate("update Job job set job.enabled = ? where job.id = ?", new Object[] { enabled, i });
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.JobService#setExpressions(java.lang.Long[],
     * java.lang.String[])
     */
    @Override
    public void setExpressions(Long[] ids, String[] expressions) {
        // TODO Auto-generated method stub
        if (ids == null || expressions == null || ids.length == 0 || expressions.length == 0) {
            log.error("ids or expressions is empty", new BusinessException("ids or expressions is empty"));
        }
        if (ids.length != expressions.length) {
            log.error("ids.length != expressions.length", new BusinessException("ids.length != expressions.length"));
        }

        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            String expression = expressions[i];
            if (id == null || StringUtils.isBlank(expression)) {
                log.warn("id or expression is null");
                continue;
            }
            executeUpdate("update Job job set job.expression = ? where job.id = ?", new Object[] { expression, id });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.JobService#setExecTypes(java.lang.Long[],
     * java.lang.String[])
     */
    @Override
    public void setExecTypes(Long[] ids, String[] execTypes) {
        // TODO Auto-generated method stub
        if (ids == null || execTypes == null || ids.length == 0 || execTypes.length == 0) {
            log.error("ids or execTypes is empty", new BusinessException("ids or execTypes is empty"));
        }
        if (ids.length != execTypes.length) {
            log.error("ids.length != execTypes.length", new BusinessException("ids.length != execTypes.length"));
        }

        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            String execType = execTypes[i];
            if (id == null || StringUtils.isBlank(execType)) {
                log.warn("id or expression is null");
                continue;
            }
            executeUpdate("update Job job set job.execType = ? where job.id = ?", new Object[] { execType, id });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.JobService#reloadJobServer(java.lang.Long)
     */
    @Override
    public void reloadJobServer(Long id) {
        // TODO Auto-generated method stub
        Job job = (Job) getById(Job.class, id);
        reloadJobServer(job);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.JobService#reloadJobServer(org.mspring.mlog.
     * entity.Job)
     */
    @Override
    public void reloadJobServer(Job job) {
        // TODO Auto-generated method stub
        String className = job.getJobClass();
        if (StringUtils.isBlank(className)) {
            log.error("can't register job server, jobClass is null.");
            throw new BusinessException("can't register job server, jobClass is null.");
        }
        Class clazz = null;
        try {
            clazz = ClassUtils.getClass(className);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            log.error(String.format("can't register job server, class not found. [id=%s, className=%s]", job.getId(), className));
            throw new BusinessException(String.format("can't register job server, class not found. [id=%s, className=%s]", job.getId(), className));
        }

        String execType = job.getExecType();
        if (StringUtils.isBlank(execType)) execType = Job.ExecType.SIMPLE;
        try {
            if (execType.equalsIgnoreCase(Job.ExecType.CRON)) {
                schedulerService.schedule(job.getId().toString(), clazz, job.getExpression());
            }
            else {
                Long repeatInterval = new Long(job.getExpression());
                schedulerService.schedule(job.getId().toString(), clazz, repeatInterval);
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            log.error(String.format("can't register job server. [id=%s, className=%s, execType=%s, expression=%s]", job.getId(), className, execType, job.getExpression()));
            throw new BusinessException(String.format("can't register job server. [id=%s, className=%s, execType=%s, expression=%s]", job.getId(), className, execType, job.getExpression()));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.JobService#loadJobServer()
     */
    @Override
    public void loadJobServer() {
        // TODO Auto-generated method stub
        log.info("init JobService...");
        List<Job> enabledJobs = find("select job from Job job where job.enabled = ?", true);
        for (Job job : enabledJobs) {
            try {
                reloadJobServer(job);
                log.info(String.format("Job Server load success. name = [id=%s, className=%s, execType=%s, expression=%s]", job.getId(), job.getClass(), job.getExecType(), job.getExpression()));
            }
            catch (Exception e) {
                // TODO: handle exception
                log.error(e.getMessage());
                continue;
            }
        }
    }

}
