/**
 * 
 */
package org.mspring.platform.job;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Gao Youbo
 * @since May 5, 2012
 */
public abstract class HibernateAwareJob extends QuartzJobBean {
    private static final Logger log = Logger.getLogger(HibernateAwareJob.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get a Hibernate Session for the given SessionFactory. Is aware of and
     * will return any existing corresponding Session bound to the current
     * thread. Will create a new Session otherwise
     * 
     * @return Session
     */
    protected Session getSession() {
        return SessionFactoryUtils.getSession(sessionFactory, true);
    }

    protected final void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobName = jobExecutionContext.getJobDetail().getName();
        if (QuartzUtils.isJobExecuting(jobExecutionContext) && !allowConcurrentExecution()) {
            log.info("Skipping scheduled job: " + jobName + ". It is already running.");
            return;
        }

        log.debug("Running scheduled job: " + jobName);
        log.debug("Opening Hibernate Session for job: " + jobName);
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        session.setFlushMode(FlushMode.NEVER);
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));

        try {
            doExecute(jobExecutionContext);
        } finally {
            TransactionSynchronizationManager.unbindResource(sessionFactory);
            log.debug("Closing Hibernate Session for job: " + jobName);
            SessionFactoryUtils.releaseSession(session, sessionFactory);
        }
    }

    /**
     * Is this job allow execute conocurrently
     * 
     * @return default return false
     */
    protected boolean allowConcurrentExecution() {
        return false;
    }

    protected abstract void doExecute(JobExecutionContext jobexecutioncontext) throws JobExecutionException;

}
