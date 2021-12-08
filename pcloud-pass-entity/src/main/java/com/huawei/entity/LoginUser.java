package com.huawei.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName LoginUser.java
 * @Description TODO
 * @createTime 2021年11月17日 19:36:00
 */
@Data
@Transactional
@ToString
@NoArgsConstructor
public class LoginUser {

        private int userid;

        private String username;

        private String address;

        private String email;

        private String createtime;

        private int  roleid;

        private String role_name;

        private String create_time;

        private String auth_name;

        private List<String> menu;
}
