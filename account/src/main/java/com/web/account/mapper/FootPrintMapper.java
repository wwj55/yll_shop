package com.web.account.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.web.account.entity.FootPrint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author Administrator
 */
public interface FootPrintMapper {
    /**
     * 查询所有的商品信息
     * @return
     */
    List<FootPrint> findAll();

    /**
     * 跟新所有的过期足迹信息
     * @return
     */
    int updateOverdue(@Param("footId") int footId);
    /**
     * 根据uid查找该用户该商品最新的足迹
     * @param uid
     * @return
     */
    @DS("one")
    FootPrint findNewest(@Param("uid") int uid , @Param("shopId") int shopId);

    /**
     * 所有足迹接口
     * @param uid
     * @return
     */
    @DS("one")
    List<FootPrint> findByUid(@Param("uid") int uid);

    /**
     * 添加足迹信息
     * @param uid
     * @param shop_id
     * @return
     */
    int insertPoint(@Param("uid") int uid , @Param("shop_id") int shop_id,
                    @Param("days") int days , @Param("mouth") int mouth);

    /**
     * 根据uid和shop_id修改浏览次数
     * @param uid
     * @param shop_id
     * @return
     */
    int updateViews(@Param("uid") int uid , @Param("shop_id") int shop_id);

}