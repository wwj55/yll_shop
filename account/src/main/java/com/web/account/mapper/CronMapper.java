package com.web.account.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.web.account.entity.Cron;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author luotao
 */

public interface CronMapper {
    /**
     * 查询所有的cron方法
     * @return
     */
    @DS("one")
    List<Cron> allCrons();
}
