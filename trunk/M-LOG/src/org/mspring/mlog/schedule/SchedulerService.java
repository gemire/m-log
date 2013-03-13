/**
 * 
 */
package org.mspring.mlog.schedule;

import org.quartz.CronExpression;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO
 */
public interface SchedulerService {
    /**
     * 按照时间间隔执行调度
     * 
     * @param jobName
     *            调度名字
     * @param jobClass
     *            调度类
     * @param repeatInterval
     *            多久执行一次，单位毫秒
     */
    void schedule(String jobName, Class jobClass, long repeatInterval);


    /**
     * 根据 Quartz Cron Expression 调试任务
     * 
     * @param jobName
     *            调度名字
     * @param jobClass
     *            调度类
     * @param cronExpression
     *            Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     */
    void schedule(String jobName, Class jobClass, String cronExpression);

    /**
     * 根据 Quartz Cron Expression 调试任务
     * 
     * @param jobName
     *            调度名字
     * @param jobClass
     *            调度类
     * @param cronExpression
     *            Quartz CronExpression
     */
    void schedule(String jobName, Class jobClass, CronExpression cronExpression);

}
