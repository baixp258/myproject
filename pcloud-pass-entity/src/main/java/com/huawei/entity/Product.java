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
@Entity(name="t_product")
@NoArgsConstructor
@Table(name="t_product")
public class Product {

    //唯一id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //销售状态 0：表示下架 1：在售
    @Column(length = 50)
    private int status;

    //产品图片
    @Column(length = 50)
    private String images;

    //产品名称
    @Column(length = 50)
    private String pname;

    //产品描述
    @Column(length = 50)
    private String pdesc;

    //产品描述
    @Column(length = 50)
    private int price;

    //产品类别的Id
    @Column(length = 50)
    private String pCategoryId;

    //产品子类ID
    @Column(length = 50)
    private String categoryId;

    //产品详情
    @Column(length = 50)
    private String detail;

    //产品
    @Column(length = 10)
    private String version;

    //产品
    @Column(length = 50)
    private int salesVolume;

    //产品
    @Column(length = 50)
    private int stockQuantity;
}
