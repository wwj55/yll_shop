package com.web.account.controller;

import com.web.account.entity.Cron;
import com.web.account.entity.FootPrint;
import com.web.account.mapper.CronMapper;
import com.web.account.service.impl.FootPrintServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CronControllerTest {

    @Resource
    CronController cronController;
    @Resource
    FootPrintServiceImpl footPrintService;
    @Resource
    CronMapper cronMapper;

    @Test
    public void test(){
        List<FootPrint> footprints = footPrintService.footPrintList(1);
        System.out.println(footprints);
    }
    @Test
    public void test1(){
        List<Cron> crons = cronMapper.allCrons();
        log.debug(crons + "");
    }

}
