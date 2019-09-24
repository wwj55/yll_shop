package com.web.account.controller;

import com.web.account.entity.Cron;
import com.web.account.service.impl.CronServiceImpl;
import com.web.account.utils.result.BaseResponseEntity;
import com.web.account.utils.result.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author luotao
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class CronController {

    @Resource
    CronServiceImpl cronService;

    @RequestMapping("/cron")
    public BaseResponseEntity<Cron> cron(){
        List<Cron> crons = cronService.cornList();
        if (crons != null && crons.size() > 0){
            return ResponseEntity.success(crons);
        }else{
            return ResponseEntity.error();
        }
    }
}
