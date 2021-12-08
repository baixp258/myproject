package com.huawei.permissionentity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SysUser.java
 * @Description TODO
 * @createTime 2021年11月03日 15:53:00
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser {

    @ApiModelProperty("主键、自增")
    @Id
    @GeneratedValue
    private Integer userid;


    @ApiModelProperty("用户名")
    @Column(name = "username",length = 20)
    private String username;

    @ApiModelProperty("密码")
    @Column(name = "password",length =100) // 若 md5 加密，长度要足够
    private String password;


    @ApiModelProperty("邮箱")
    @Column(name = "email",length = 50)
    private String email;


    @ApiModelProperty("地址")
    @Column(name = "address",length = 100)
    private String address;

    @ApiModelProperty("创建时间")
    @Column(name = "createtime",length = 20)
    private String createtime;

    /**
     * 配置用户到角色的多对多关系
     *      配置多对多的映射关系
     *          1.声明表关系的配置
     *              @ManyToMany(targetEntity = SysRole.class,cascade = CascadeType.ALL)  //多对多
     *                  targetEntity：代表对方的实体类字节码
     *          2.配置中间表（包含两个外键）
     *                @JoinTable
     *                  name : 中间表的名称
     *                  joinColumns：配置当前对象在中间表的外键
     *                      @JoinColumn的数组
     *                          name：外键名
     *                          referencedColumnName：参照的主表的主键名
     *                  inverseJoinColumns：配置对方对象在中间表的外键
     *                   @ManyToMany(targetEntity = SysRole.class,cascade = CascadeType.ALL)
     *     @JoinTable(name = "sys_user_role",
     *             //   joinColumns 当前表在中间表中的外键   参照当前表的主键字段
     *             joinColumns = {@JoinColumn(name = "id", referencedColumnName = "_id")},
     *
     *             //inverseJoinColumns 对方对象在中间表 的外键    参照当前表的主键字段
     *             inverseJoinColumns = {@JoinColumn( name = "roleid", referencedColumnName = "role_id")}
     *     )
     *     private Set<SysRole> roles = new HashSet<>();
     */


}
