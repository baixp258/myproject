package com.huawei.service;

import com.huawei.entity.UsersDetails;
import com.huawei.status.Response;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年09月22日 16:23:00
 */
public interface UserLoginService {

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     */
    public Response login(String username,String password);


}
