package com.huawei.permissionentity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SysRolePermission.java
 * @Description TODO
 * @createTime 2021年11月03日 16:48:00
 */

@Data
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermission {

    @ApiModelProperty("主键、自增")
    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty("角色id")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private SysRole sysRole; // 角色

    @ApiModelProperty("权限id")
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private SysPermission sysPermission; // 权限
}
