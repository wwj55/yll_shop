package com.web.account.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CronMapperTest {
    @Resource
    CronMapper mapper;

    @Test
    public void allCrons() {
        mapper.allCrons();
    }
}
