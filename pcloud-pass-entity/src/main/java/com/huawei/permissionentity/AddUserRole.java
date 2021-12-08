package com.huawei.permissionentity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserAndRole.java
 * @Description 接受添加用户的对象
 * @createTime 2021年11月11日 22:06:00
 */
@Data
@ToString
@NoArgsConstructor
public class AddUserRole {

   //用户名
    private String username;

    //邮箱
    private String email;

    //添加角色id
    private int roleid;

    //添加地址
    private String address;

    //用户密码
    private String password;


}
