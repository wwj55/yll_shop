package com.web.account.mapper;

import com.web.account.entity.Role;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface RoleMapper {

    List<Role> findByUid(@Param("uid") int uid);
}