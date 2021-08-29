package com.example.mumuoa.db.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色表
 */
@Getter
@Setter
@ToString
public class Role {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限集合
     */
    private String permissions;
}