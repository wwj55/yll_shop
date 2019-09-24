package com.web.account.RealJob;

import com.web.account.entity.FootPrint;
import com.web.account.mapper.FootPrintMapper;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author luotao
 */
@Component
@EnableScheduling
@Configuration
public class UpdateFootPrintJob{

    @Resource
    FootPrintMapper footprintMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void footPrintJob() throws JobExecutionException {
        List<FootPrint> footPrints = footprintMapper.findAll();
        Date date = new Date();
        long currentTime = date.getTime();
        for (FootPrint footPrint : footPrints) {
            Date browseTime = footPrint.getBrowseTime();
            long browseTimeTime = browseTime.getTime();
            long days = currentTime - browseTimeTime;
            if (days > 30){
                Integer footId = footPrint.getFootId();
                int count = footprintMapper.updateOverdue(footId);
            }
        }
    }
}
