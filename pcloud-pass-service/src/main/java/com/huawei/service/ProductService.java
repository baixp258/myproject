package com.huawei.service;

import com.huawei.entity.Product;
import com.huawei.status.Response;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年09月22日 16:23:00
 */
public interface ProductService {

    /**
     * 获取产品列表
     * @return
     */
    public Response queryProductList();

    /**
     * 分页查询不带条件
     */
    public Response queryProductListOfLimit(int pageNum,int pageSize);

    /**
     * 条件查询带分页
     */
    public Response queryProductListAndCondition(int pageNum,int pageSize,String productName,String productDesc);

    /**
     * 根据当前产品查询产品父类别
     */
    public Response findProductPcategoryByPId(String pcategoryId);

    /**
     *查询当前产品类别
     */
    public Response findProductCategoryById(String categoryId);


    /**
     * 更新产品的状态（上架，下线）
     */
    public Response updateProductStatusById(String id,int status);

    /**
     * 添加产品
     */
    public Response addProduct(Product product);

    /**
     * 修改产品信息
     */
    public Response updateProduct(Product product);
}
