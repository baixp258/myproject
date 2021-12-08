package com.huawei.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UsersMenu.java
 * @Description TODO
 * @createTime 2021年09月27日 09:50:00
 */
@Data
@NoArgsConstructor
@ToString
@Entity(name="t_usermenu")
@Table(name="t_usermenu")
public class UsersMenu {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private String userId;

    @Column(length = 50)
     private String userName;
}
