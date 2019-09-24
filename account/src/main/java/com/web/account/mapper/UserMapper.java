package com.web.account.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.web.account.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 通过名字查找用户
     * @param username
     * @return
     */
    @DS("one")
    User findByName(@Param("username") String username);

    /**
     * 添加用户
     */
    @DS("master")
    int insertUser(@Param("username") String username , @Param("password") String password);
}