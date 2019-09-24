package com.web.account.service.impl;

import com.web.account.entity.Permission;
import com.web.account.entity.Role;
import com.web.account.entity.User;
import com.web.account.mapper.PermissionMapper;
import com.web.account.mapper.RoleMapper;
import com.web.account.mapper.UserMapper;
import com.web.account.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author luotao
 */
@Service
public class UserService implements IUserService {

    @Resource
    UserMapper userMapper;
    @Resource
    PermissionMapper permissionMapper;
    @Resource
    RoleMapper roleMapper;

    @Override
    public User findByName(String username) {
        User user = userMapper.findByName(username);
        return user;
    }

    @Override
    public int insertUser(String username, String password) {
        int count = userMapper.insertUser(username, password);
        return count;
    }

    @Override
    public List<Permission> userPermissions(int uid) {
        List<Permission> permissions = permissionMapper.findByUid(uid);
        return permissions;
    }

    @Override
    public List<Role> userRoles(int uid) {
        List<Role> roles = roleMapper.findByUid(uid);
        return roles;
    }
}
