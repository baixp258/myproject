package com.huawei.productmapper;

import com.huawei.entity.CategoryChildren;
import com.huawei.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 查询子分类接口
 */
public interface CategoryChildrenMapper extends JpaRepository<CategoryChildren,Integer> ,JpaSpecificationExecutor<User> {

    @Query(value = "select * from t_cotegory_children where parent_id = ?1",nativeQuery = true)
    List<CategoryChildren> findCategoryChildrenByParentId(String parentId);

    /**
     * 根据一级分类
     * @param parentId
     * @return
     * UPDATE runoob_tbl SET runoob_title='学习C++' WHERE runoob_id=3;
     */
    @Transactional
    @Modifying
    @Query(value = "insert into t_cotegory_children (id,`name`,parent_id) values(?1,?2,?3)",nativeQuery = true)
    int addCategoryChildren(String id,String name,String parentId);


    /**
     * 获取商品类别中ID的最大值
     */
    @Query("select MAX(id) from t_cotegory_children")
    String queryCategotyChildrenMaxId();

}
