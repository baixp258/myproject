package com.huawei.permissionentity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SysPermission.java
 * @Description TODO
 * @createTime 2021年11月03日 16:09:00
 */
@Data
@Table(name = "sys_permission")
@Entity
public class SysPermission {

    @ApiModelProperty("主键、自增")
    @Id
    @GeneratedValue
    private Integer permissionid;

    @ApiModelProperty("权限名")
    @Column(name = "permission_name",length = 20) // 菜单名
    private String permissionName;

    @ApiModelProperty("权限链接")
    @Column(name = "permission_url",length = 200) // 请求接口 url
    private String permissionUrl;


}
