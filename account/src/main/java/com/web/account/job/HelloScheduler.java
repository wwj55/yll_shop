//package com.web.account.job;
//
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
///**
// * @Author luotao
// */
//public class HelloScheduler {
//    public static void main(String[] args) throws SchedulerException {
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        //任务实例
//        JobDetail jobDetail =
//                //加载人物类,与HelloJob完成绑定,要求Hello的实体类
//                JobBuilder.newJob(HelloJob.class)
//                //参数1:任务的名称 参数2:任务组的名称
//                .withIdentity("job1", "group1")
//                .build();
//                //触发器
//        SimpleTrigger trigger = TriggerBuilder.newTrigger()
//                //参数1:触发器的名称  参数2:触发器组的名称
//                .withIdentity("trigger1", "group1")
//                //立即启动
//                .startNow()
//                //每五秒重复一次
//                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
//                .build();
//
//        scheduler.scheduleJob(jobDetail , trigger);
//
//        scheduler.start();
//    }
//}
