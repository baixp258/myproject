package com.huawei.permissionentity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserAndRole.java
 * @Description TODO
 * @createTime 2021年11月11日 22:06:00
 */
@Data
@ToString
@NoArgsConstructor
public class UserAndRole {

    private String username;

    private int userid;

    private String email;

    private String role_name;

    private String createtime;

    private String password;

    private String address;


}
