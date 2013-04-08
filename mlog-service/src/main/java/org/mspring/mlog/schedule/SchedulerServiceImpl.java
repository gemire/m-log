/**
 * 
 */
package org.mspring.mlog.schedule;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SimpleTriggerBean;
import org.springframework.stereotype.Service;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {
    private static final Logger log = Logger.getLogger(SchedulerServiceImpl.class);

    private Scheduler scheduler;

    @Autowired
    public void setScheduler(@Qualifier("quartzScheduler") Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.inf.standard.schedule.SchedulerService#schedule(java
     * .lang.String, java.lang.Class, long)
     */
    @Override
    public void schedule(String jobName, Class jobClass, long repeatInterval) {
        // TODO Auto-generated method stub
        String triggerName = jobName + "Tigger";
        try {
            JobDetail jobDetail = new JobDetail(jobName, Scheduler.DEFAULT_GROUP, jobClass);
            scheduler.addJob(jobDetail, true);
            SimpleTriggerBean simpleTrigger = new SimpleTriggerBean();
            simpleTrigger.setJobName(jobName);
            simpleTrigger.setRepeatInterval(repeatInterval);
            simpleTrigger.setName(triggerName);
            simpleTrigger.setGroup(Scheduler.DEFAULT_GROUP);
            simpleTrigger.setJobGroup(Scheduler.DEFAULT_GROUP);
            simpleTrigger.setStartTime(new Date());
            
            if (scheduler.getTrigger(triggerName, Scheduler.DEFAULT_GROUP) == null) {
                scheduler.scheduleJob(simpleTrigger);
            }
            scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP, simpleTrigger);
        }
        catch (SchedulerException e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error("register job [name = " + jobName + ", class = " + jobClass + "] failure.");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.inf.standard.schedule.SchedulerService#schedule(java
     * .lang.String, java.lang.Class, java.lang.String)
     */
    @Override
    public void schedule(String jobName, Class jobClass, String cronExpression) {
        // TODO Auto-generated method stub
        try {
            schedule(jobName, jobClass, new CronExpression(cronExpression));
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("register job [name = " + jobName + ", class = " + jobClass + ", cron = " + cronExpression + "] failure.");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.inf.standard.schedule.SchedulerService#schedule(java
     * .lang.String, java.lang.Class, org.quartz.CronExpression)
     */
    @Override
    public void schedule(String jobName, Class jobClass, CronExpression cronExpression) {
        // TODO Auto-generated method stub
        String triggerName = jobName + "Tigger";

        try {
            JobDetail jobDetail = new JobDetail(jobName, Scheduler.DEFAULT_GROUP, jobClass);
            scheduler.addJob(jobDetail, true);
            CronTrigger cronTrigger = new CronTrigger(triggerName, Scheduler.DEFAULT_GROUP, jobName, Scheduler.DEFAULT_GROUP);
            cronTrigger.setCronExpression(cronExpression);
            
            if (scheduler.getTrigger(triggerName, Scheduler.DEFAULT_GROUP) == null) {
                scheduler.scheduleJob(cronTrigger);
            }
            scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP, cronTrigger);
        }
        catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("register job [name = " + jobName + ", class = " + jobClass + ", cron = " + cronExpression + "] failure.");
        }
    }

}
