package com.huawei.service;

import com.huawei.entity.UsersDetails;
import com.huawei.status.Response;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年09月22日 16:23:00
 */
public interface UserService {

    /**
     * 根据用户id获取用户详情
     * @param userId
     * @return
     */
    public Response selectById(String userId);

    /**
     * 获取用户信息
     */
    public Response findAllUser();


    /**
     * 分页查询
     */
    public Response findUserNoCondition(Integer page, Integer size);

    /**
     * 分页查询带条件
     */
    public Response findUsersCondition(Integer page,Integer size,UsersDetails user);

    /**
     * 添加用户
     */
    public Response addUser(String name,String age,String sex);
}
