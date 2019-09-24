package com.web.account.service;

import com.web.account.entity.Permission;
import com.web.account.entity.Role;
import com.web.account.entity.User;

import java.util.List;

public interface IUserService {
    User findByName(String username);

    int insertUser (String username , String password);

    List<Permission> userPermissions(int uid);

    List<Role> userRoles(int uid);
}
