package com.web.account.entity;

import lombok.Data;

/**
 * 角色表
 * @author Administrator
 */
@Data
public class Role {

    private Integer roleId;

    private String roleName;

    private Integer uid;
}