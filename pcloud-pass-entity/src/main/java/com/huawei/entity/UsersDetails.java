package com.huawei.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Table;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UsersDetails.java
 * @Description TODO
 * @createTime 2021年09月22日 10:45:00
 */
@Data
@Entity(name="t_user")
@NoArgsConstructor
@Table(name="t_user")
public class UsersDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private  String username;
    @Column(length = 50)
    private  int age;
    @Column(length = 50)
    private String sex;
    @Column(length = 50)
    private String address;


}
