//package com.web.account.job;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * @Author luotao
// */
//@Configuration
//@Component
////开启任务调度(也可以写在程序开始的位置)
//@EnableScheduling
//public class ScheduleTask {
//    private static final Logger LOGGER =  LoggerFactory.getLogger(ScheduleTask.class);
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void sayHello(){
//        LOGGER.info("Hello world, i'm the king of the world!!!");
//    }
//}
