//package com.web.account.job;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Author luotao
// */
//public class HelloJob implements Job {
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        //输出当前时间
//        Date date = new Date();
//        SimpleDateFormat dateFormate = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//        String dateString = dateFormate.format(date);
//        //工作内容
//        System.out.println(dateString);
//
//    }
//}
