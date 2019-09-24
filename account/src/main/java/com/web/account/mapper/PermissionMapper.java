package com.web.account.mapper;

import com.web.account.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    List<Permission> findByUid(@Param("uid") int uid);
}