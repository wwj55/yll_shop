package com.web.account.utils;

import org.quartz.*;

import java.util.Date;

/**
 * @Author luotao
 */
public class JobUtils {

    public static void createJobStartAt(Scheduler scheduler, long startAtTime, String name, String group, Class jobBean){
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(name, group)
                .startAt(new Date(startAtTime))
                .build();
        createJob(scheduler,name,group,trigger,jobBean);
    }

    public static void createJobByCron(Scheduler scheduler, String name, String group,String cron,Class jobBean){
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        createJob(scheduler,name,group,trigger,jobBean);
    }


    private static void createJob(Scheduler scheduler, String name, String group, Trigger trigger,Class jobBean) {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(jobBean).withIdentity(name,group).build();
        try {
            //将触发器与任务绑定到调度器内
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
