package com.huawei.permissionentity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SysRole.java
 * @Description TODO
 * @createTime 2021年11月03日 16:07:00
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole {

    @ApiModelProperty("主键、自增")
    @Id
    @GeneratedValue
    private Integer roleid;

    @ApiModelProperty("角色名称")
    @Column(name = "role_name",length = 20)
    private String role_name;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time",length = 20)
    private String create_time;

    @ApiModelProperty("授权时间")
    @Column(name = "auth_time",length = 20)
    private String auth_time;

    @ApiModelProperty("授权人")
    @Column(name = "auth_name",length = 20)
    private String auth_name;


    /**
     *  @ManyToMany(targetEntity = SysPermission.class,cascade = CascadeType.ALL)
     *     @JoinTable(name = "sys_user_role",
     *             //   joinColumns 当前表在中间表中的外键   参照当前表的主键字段
     *             joinColumns = {@JoinColumn(name = "roleid", referencedColumnName = "user_id")},
     *
     *             //inverseJoinColumns 对方对象在中间表 的外键    参照当前表的主键字段
     *             inverseJoinColumns = {@JoinColumn( name = "permissionid", referencedColumnName = "permission_id")}
     *     )
     *     private Set<SysPermission> roles = new HashSet<>();
     */

}
