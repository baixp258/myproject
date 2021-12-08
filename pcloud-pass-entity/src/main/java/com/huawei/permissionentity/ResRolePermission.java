package com.huawei.permissionentity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ResRolePermission.java
 * @Description TODO
 * @createTime 2021年11月03日 18:54:00
 */
@Data
@ToString
@NoArgsConstructor
public class ResRolePermission {

    //角色ID
    private int roleid;

    //角色名称
    private String role_name;

    //创建时间
    private String create_time;

    //授权时间
    private String auth_time;

    //授权人
    private String anth_name;

    //授权访问权限utl
    private List<String> menu;


}
