package com.huawei.reactcontroller;

import com.huawei.permissionentity.SysUser;
import com.huawei.service.UserLoginService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO
 * @createTime 2021年10月11日 17:54:00
 */

@Api("登录接口")
@CrossOrigin
@RestController
public class LoginController extends BaseController {


    @Autowired
    private UserLoginService userLoginService;


    @ApiOperation(value="login", notes="login接口")
    @ApiImplicitParam(name= "login", value = "login接口", required = false, dataType = "test")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(@RequestBody SysUser user){
        //检验参数
        if(user == null){
            return rtnFail("请求参数为null");
        }
        String username = user.getUsername();
        String password = user.getPassword();
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return rtnFail("回去参数内容为null");
        }
        return userLoginService.login(username,password);
    }
}
