package com.huawei.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年10月11日 18:05:00
 */
@Data
@Entity(name="table_user")
@NoArgsConstructor
@Table(name="table_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private  String username;
    @Column(length = 50)
    private  String  password;
    @Column(length = 50)
    private String email;
    @Column(length = 50)
    private String address;
}
