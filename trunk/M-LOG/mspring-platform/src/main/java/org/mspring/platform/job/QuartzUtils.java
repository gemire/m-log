/**
 * 
 */
package org.mspring.platform.job;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

/**
 * @author Gao Youbo
 * @since May 5, 2012
 */
public class QuartzUtils {
    private static final Logger log = Logger.getLogger(QuartzUtils.class);

    private QuartzUtils() {
    }

    /**
     * checking if the job is executing.
     * 
     * @param context
     * @return
     */
    public static boolean isJobExecuting(JobExecutionContext context) {
        try {
            List executingJobs = context.getScheduler().getCurrentlyExecutingJobs();
            Comparator c = new JobDetailComparator();
            JobExecutionContext executingContext;
            for (int i = 0; i < executingJobs.size(); i++) {
                executingContext = (JobExecutionContext) executingJobs.get(i);
                if (executingContext != context && c.compare(context.getJobDetail(), executingContext.getJobDetail()) == 0) {
                    log.info("Quartz job: " + context.getJobDetail().getName() + " is running.");
                    return true;
                }
            }
        } catch (SchedulerException e) {
            log.warn("Failed to determine if quartz job was already executing.", e);
        }

        return false;
    }

    private static class JobDetailComparator implements Comparator, Serializable {

        public int compare(Object o1, Object o2) {
            JobDetail a = (JobDetail) o1;
            JobDetail b = (JobDetail) o2;
            int groupComparison = a.getGroup().compareTo(b.getGroup());
            return groupComparison != 0 ? groupComparison : a.getName().compareTo(b.getName());
        }
    }
}
