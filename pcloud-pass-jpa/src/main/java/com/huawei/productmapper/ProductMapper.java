package com.huawei.productmapper;

import com.huawei.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 查询商品列表mapper接口
 */
public interface ProductMapper extends JpaRepository<Product,Integer> ,JpaSpecificationExecutor<Product> {

    @Query(value = "select * from t_product",nativeQuery = true)
    List<Product> findProduct();

    /**
     * 查询一级分类
     * @param parentId
     * @return
     */
    @Query(value = "select * from t_cotegory where id = ?1",nativeQuery = true)
    Map<String,Object> findCategoryByParentId(String parentId);

    /**
     * 查询二级分类
     * @param parentId
     * @return
     */
    @Query(value = "select * from t_cotegory_children where id = ?1",nativeQuery = true)
    Map<String,Object> findCategoryChildrenByParentId(String parentId);


    /**
     * 根据产品id修改产品状态
     * @param id
     * @param status
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update t_product set status=?2 where id = ?1",nativeQuery = true)
    int updateProducStatusById(String id,int status);



    /**
     * 获取商品类别中ID的最大值
     */
    @Query("select MAX(id) from t_product")
    String queryProductMaxId();

    /**
     * 添加商品
     * :#{#product.id},:#{#product.pname},:#{#product.pdesc},:#{#product.price},:#{#product.pCategoryId},:#{#product.categoryId},:#{#product.images},:#{#product.detail}
     */
    @Query(value="insert into t_product(id,pname,pdesc,price,p_category_id,category_id,images,detail,status) values " +
            "(?1,?2,?3,?4,?5,?6,?7,?8,?9)",nativeQuery = true)
    @Modifying
    @Transactional
    Integer addProduct(String id,String pname,String pdesc,int price,String p_category_id,String category_id,String images,String detail,int status);



    /**
     * 修改商品
     */
    @Query(value="update t_product set pname=?2, pdesc=?3,price=?4,p_category_id=?5,category_id=?6,images=?7,detail=?8 where id=?1 ",nativeQuery = true)
    @Modifying
    @Transactional
    Integer updateProduct(String id,String pname,String pdesc,int price,String p_category_id,String category_id,String images,String detail);

    /**
     * 查询产品销量和库存
     */
    @Query(value = "select * from t_product",nativeQuery = true)
    List<Product> selectProducts();
}
