package com.huawei.productmapper;

import com.huawei.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 查询一级分类的列表接口
 */
public interface CategoryMapper extends JpaRepository<Category,String> ,JpaSpecificationExecutor<Category> {

    /**
     * 查询一级分类
     * @param parentId
     * @return
     */
    @Query(value = "select * from t_cotegory where parent_id = ?1",nativeQuery = true)
    List<Category> findCategoryByParentId(String parentId);

    /**
     * 根据一级分类
     * @param parentId
     * @return
     * UPDATE runoob_tbl SET runoob_title='学习C++' WHERE runoob_id=3;
     */
    @Transactional
    @Modifying
    @Query(value = "insert into t_cotegory (id,`name`,parent_id) values(?1,?2,?3)",nativeQuery = true)
    int addCategory(String id,String name,String parentId);

    /**
     * 根据parentId修改内容
     * @param
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update t_cotegory set `name`=?2 where id = ?1",nativeQuery = true)
    int updateCategoryByParentId(String id,String name);


    /**
     * 获取商品类别中ID的最大值
     */
    @Query("select MAX(id) from t_cotegory")
    String queryCategotyMaxId();


}
