package com.web.account.entity;

import lombok.Data;

/**
 * 权限表
 * @author Administrator
 */
@Data
public class Permission {

    private Integer permissionId;

    private Integer uid;

    private Integer roleId;

    private String permissionName;
}