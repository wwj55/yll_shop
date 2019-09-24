package com.web.account.entity;

import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 */
@Data
public class FootPrint {
    /**
     * 用户id
     */
    private Integer uid;
    /**
    * 浏览记录id,主键,自增长
    */
    private Integer footId;

    /**
    * 关联商品表的商品id
    */
    private Integer shopId;

    /**
    * 浏览的实时时间
    */
    private Date browseTime;

    /**
    * 浏览次数
    */
    private Integer views;

    /**
     * 当前足迹所在日期天数
     */
    private int days;

    /**
     * 当前足迹日期月数
     */
    private int mouth;

    /**
    * 商品足迹是否被删除
    */
    private Integer idDelete;
}