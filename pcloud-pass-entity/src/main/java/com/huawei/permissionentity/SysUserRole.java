package com.huawei.permissionentity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SysUserRole.java
 * @Description TODO
 * @createTime 2021年11月03日 16:46:00
 */

@Data
@Entity
@Table(name = "sys_user_role")
public class SysUserRole {

    @ApiModelProperty("主键、自增")
    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty("用户id")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private SysUser sysUser; // 用户

    @ApiModelProperty("角色id")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private SysRole sysRole; // 角色
}
