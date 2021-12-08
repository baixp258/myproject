package com.huawei.controller;


import com.huawei.entity.UsersDetails;
import com.huawei.entity.UsersMenu;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import com.huawei.serviceimpl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

/**
 * test
 */
@Api("jpa测试接口")
@CrossOrigin
@RestController
@RequestMapping("/index")
public class MemberController extends BaseController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value="jpa测试接口", notes="jpa接口")
    @ApiImplicitParam(name= "test", value = "jpa接口", required = false, dataType = "test")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){

        return "OK";
    }
    @ApiOperation(value="根据获取用户详情", notes="根据获取用户详情")
    @ApiImplicitParam(name= "selectByUserId", value = "jpa接口", required = false, dataType = "selectByUserId")
    @RequestMapping(value="/selectByUserId",method = RequestMethod.GET)
    public Response selectByUserId(String userId){
        if(StringUtils.isEmpty(userId)){
            return rtnParmterMiss();
        }
        return userServiceImpl.selectById(userId);
    }

    @ApiOperation(value="根据获取用户列表", notes="根据获取用户列表")
    @ApiImplicitParam(name= "findAllUser", value = "jpa接口", required = false, dataType = "findAllUser")
    @RequestMapping(value="/findAllUser",method = RequestMethod.GET)
    public Response findAllUser(){
        return userServiceImpl.findAllUser();
    }


    /**
     * 分页查询不带条件
     * @param
     */
    @ApiOperation(value="分页查询不带条件", notes="分页查询不带条件")
    @ApiImplicitParam(name= "findUserNoCondition", value = "jpa接口", required = false, dataType = "findUserNoCondition")
    @RequestMapping(value="/findUserNoCondition",method = RequestMethod.GET)
    public Response findUserNoCondition(@RequestParam(value = "page",defaultValue = "0") int page,@RequestParam(value = "size",defaultValue = "4") int size){

        if(StringUtils.isEmpty(String.valueOf(page)) || StringUtils.isEmpty(String.valueOf(size))){
            return rtnParmterMiss();
        }
        return userServiceImpl.findUserNoCondition(page,size);
    }

    /**
     * 分页查询带条件
     * @param
     */
    @ApiOperation(value="分页带条件", notes="分页带条件")
    @ApiImplicitParam(name= "findUserCondition", value = "jpa接口", required = false, dataType = "findUserCondition")
    @RequestMapping(value="/findUserCondition",method = RequestMethod.POST)
    public Response findUserCondition(int page, int size, @RequestBody UsersDetails user){
        if(StringUtils.isEmpty(String.valueOf(page)) || StringUtils.isEmpty(String.valueOf(size))){
            return rtnParmterMiss();
        }
        return userServiceImpl.findUsersCondition(page,size,user);
    }


    /**
     * 增加用户
     * @param
     */
    @ApiOperation(value="增加用户", notes="增加用户")
    @ApiImplicitParam(name= "addUser", value = "jpa接口", required = false, dataType = "addUser")
    @RequestMapping(value="/addUser",method = RequestMethod.GET)
    public Response addUser(String name, String  age, String  sex){
        if(StringUtils.isEmpty(String.valueOf(name)) || StringUtils.isEmpty(String.valueOf(age))||StringUtils.isEmpty(String.valueOf(sex))){
            return rtnParmterMiss();
        }
        return userServiceImpl.addUser(name,age,sex);
    }

    public static void main(String[] args){
       String aa="admin";
         String bb="admin";
        String cc=new String("admin");
        System.out.print(aa==bb);
        System.out.print(aa.equals(bb));
        System.out.print(aa==cc);
        System.out.print(aa.equals(cc));
    }

}
