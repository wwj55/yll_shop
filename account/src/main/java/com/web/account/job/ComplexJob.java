//package com.web.account.job;
//
//import com.web.account.entity.Cron;
//import com.web.account.mapper.CronMapper;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//
///**
// * @Author luotao
// */
//@Component
//@Configuration
//@EnableScheduling
//public class ComplexJob implements SchedulingConfigurer {
//
//    @Resource
//    CronMapper cronMapper;
//
//    /**
//     * 还需要任务,调度,触发器
//     * @param scheduledTaskRegistrar
//     */
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//
//        scheduledTaskRegistrar.addTriggerTask(
//                () -> System.out.println(LocalDateTime.now()),
//                triggerContext -> {
//                    List<Cron> all = cronMapper.allCrons();
//                    Cron cron1 = all.get(0);
//                    String cron = cron1.getCron();
//                    return  new CronTrigger(cron).nextExecutionTime(triggerContext);
//                });
//    }
//}
