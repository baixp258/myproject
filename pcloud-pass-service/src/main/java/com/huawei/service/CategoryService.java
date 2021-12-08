package com.huawei.service;

import com.huawei.entity.Category;
import com.huawei.status.Response;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年09月22日 16:23:00
 */
public interface CategoryService {

    /**
     * 查询类别
     * @param parentId
     * @return
     */
    public Response findCategoryByParentId(String parentId);

    /**
     * 一级类别添加
     * @param parentId
     * @return
     */
    public Response findCategoryAdd(Category category);

    /**
     * 一级类别修改
     * @param parentId
     * @return
     */
    public Response findCategoryUpdate(Category category);

    /**
     * 查询类别子类
     * @param parentId
     * @return
     */
    public Response findCategoryChildrenByParentId(String parentId);


}
