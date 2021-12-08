package com.huawei.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Category.java
 * @Description TODO
 * @createTime 2021年10月15日 15:21:00
 */

@Data
@ToString
@Entity(name="t_cotegory")
@NoArgsConstructor
@Table(name="t_cotegory")
public class Category {

    //一级类别ID
    @Column(length = 50)
    private String parentId;

    //唯一id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //商品类别名称
    @Column(length = 50)
    private String name;

    //版本
    @Column(length = 10)
    private String  version;
}
