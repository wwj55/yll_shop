package com.web.account.shiro;

import com.web.account.entity.Permission;
import com.web.account.entity.Role;
import com.web.account.entity.User;
import com.web.account.mapper.UserMapper;
import com.web.account.service.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author luotao
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源授权字符串
        //可以进行动态授权
        /**
         * 1.在数据库用户表添加授权字段perms
         * 2.根据格式书写授权页面:user:cron
         * 3.根据数据库进行动态获取
         */
//        User user = (User) principalCollection.getPrimaryPrincipal();
        Subject subject = SecurityUtils.getSubject();
        //获取到返回的数据
        //new SimpleAuthenticationInfo(user ,user.getPassword() , "" );中的第一个参数
        User user = (User) subject.getPrincipal();
        List<Role> roles = userService.userRoles(user.getUid());
        List<Permission> permissions = userService.userPermissions(user.getUid());
        //用户的角色集合
        List<String> roleStrList = new ArrayList<String>();
        //用户的权限集合
        List<String> permissionStrList = new ArrayList<String>();
        for (Role role : roles) {
            roleStrList.add(role.getRoleName());
        }
        for (Permission permission : permissions) {
            permissionStrList.add(permission.getPermissionName());
        }
        //单一角色进行添加
        info.addRole("角色");
        //多个角色进行一次性添加
        info.addRoles(roleStrList);
        //单一权限进行添加
        info.addStringPermission("user:cron");
        //多个权限进行添加
        info.addStringPermissions(permissionStrList);
        return info;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String credentials = new String((char[]) token.getCredentials());
        System.out.println(token.getCredentials());
        System.out.println(getName());
        System.out.println(token.getPrincipal());
        System.out.println(token.getUsername());
        User user = userService.findByName(token.getUsername());

        if (user == null) {
            //shiro底层会抛出UnKnowAccountException错误
            throw new AccountException("用户名不正确");
        } else {
            List<Permission> permissions = userService.userPermissions(user.getUid());
            List<Role> roles = userService.userRoles(user.getUid());
//            //用户的角色集合
//            List<String> roleStrList=new ArrayList<String>();
//            //用户的权限集合
//            List<String> permissionStrList=new ArrayList<String>();
//            for (Role role : roles) {
//                roleStrList.add(role.getRoleName());
//            }
//            for (Permission permission : permissions) {
//                permissionStrList.add(permission.getPermissionName());
//            }
//            user.setRoleStrList(roleStrList);
//            user.setPerminsStrList(perminsStrList);
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", user);
            //密码的比对:new SimpleAuthenticationInfo
            //第一个参数:需要返回给login方法的数据
            //第二个参数:需要比对的密码
            //shiro的名字
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
    }
}
