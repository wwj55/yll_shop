package com.web.account.service.impl;

import com.web.account.entity.Cron;
import com.web.account.mapper.CronMapper;
import com.web.account.service.ICronService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author luotao
 */
@Service
public class CronServiceImpl implements ICronService {

    @Resource
    CronMapper cronMapper;

    @Override
    public List<Cron> cornList() {
        List<Cron> crons = cronMapper.allCrons();
        return crons;
    }
}
