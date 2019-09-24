package com.web.account.service;

import com.web.account.entity.FootPrint;

import java.util.Calendar;
import java.util.List;

/**
 * @Author luotao
 */
public interface IFootPrintService {

    /**
     * 查询所有的足迹信息
     * @param uid
     * @return
     */
    List<FootPrint> footPrintList(int uid);

    /**
     * 根据时间信息修改或者添加足迹信息
     * @param uid
     * @param shop_id
     * @param time
     * @return
     */
    int updateFootPrint(int uid , int shop_id , Calendar time);
}
