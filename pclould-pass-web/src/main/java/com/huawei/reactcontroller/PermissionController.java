package com.huawei.reactcontroller;

import com.huawei.permissionentity.*;
import com.huawei.service.PermissionService;
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
 * @ClassName PermissionController.java
 * @Description 用于权限管理操作
 * @createTime 2021年11月03日 17:00:00
 */
@Api("权限接口")
@CrossOrigin
@RestController
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value="selectAllSysUser", notes="查询当前所有的用户")
    @ApiImplicitParam(name= "selectAllSysUser", value = "selectAllSysUser", required = false, dataType = "test")
    @RequestMapping(value = "/selectAllSysUser",method = RequestMethod.POST)
    public Response selectAllSysUser() {
        return permissionService.selectAllSysUser();
    }


    @ApiOperation(value="addSysUser", notes="添加用户")
    @ApiImplicitParam(name= "addSysUser", value = "selectAllSysUser", required = false, dataType = "test")
    @RequestMapping(value = "/addSysUser",method = RequestMethod.POST)
    public Response addSysUser(@RequestBody AddUserRole addUserRole) {
        if(StringUtils.isEmpty(addUserRole.getUsername()) || StringUtils.isEmpty(addUserRole.getPassword()) || addUserRole.getRoleid()==0){
            return rtnFail("参数为null");
        }
        return permissionService.addUserAndRole(addUserRole);
    }

    @ApiOperation(value="updateSysUser", notes="修改用户以及用户权")
    @ApiImplicitParam(name= "updateSysUser", value = "selectAllSysUser", required = false, dataType = "test")
    @RequestMapping(value = "/updateSysUser",method = RequestMethod.POST)
    public Response updateSysUser(@RequestBody UpdateUserAndRole updateuser) {
        if(StringUtils.isEmpty(String.valueOf(updateuser.getUserid())) || StringUtils.isEmpty(String.valueOf(updateuser.getRoleid()))){
            return rtnFail("参数为null");
        }
        return permissionService.updateUserAndRole(updateuser);
    }



    @ApiOperation(value="selectAllSysRole", notes="查询当前角色信息")
    @ApiImplicitParam(name= "selectAllSysRole", value = "selectAllSysRole", required = false, dataType = "test")
    @RequestMapping(value = "/selectAllSysRole",method = RequestMethod.GET)
    public Response selectAllSysRole(){

        return permissionService.selectAllSysRole();
    }



    @ApiOperation(value="addSysRole", notes="添加角色信息")
    @ApiImplicitParam(name= "addSysRole", value = "selectAllSysRole", required = false, dataType = "test")
    @RequestMapping(value = "/addSysRole",method = RequestMethod.POST)
    public Response addSysRole(@RequestBody SysRole role){
         if(StringUtils.isEmpty(role.getRole_name()) || StringUtils.isEmpty(role.getAuth_name())){
             return rtnFail("参数为null");
         }
        return permissionService.addSysAllRole(role);
    }




    @ApiOperation(value="selectAllSysPermission", notes="查询当前权限信息")
    @ApiImplicitParam(name= "selectAllSysPermission", value = "selectAllSysPermission", required = false, dataType = "test")
    @RequestMapping(value = "/selectAllSysPermission",method = RequestMethod.POST)
    public Response selectAllSysPermission(){
        return permissionService.selectAllSysUser();
    }




    @ApiOperation(value="addSysPermission", notes="添加权限信息")
    @ApiImplicitParam(name= "addSysPermission", value = "addSysPermission", required = false, dataType = "test")
    @RequestMapping(value = "/addSysPermission",method = RequestMethod.POST)
    public Response addSysPermission(@RequestBody SysPermission sysPermission){
        return permissionService.addSysPermission(sysPermission);
    }


    @ApiOperation(value="selectAllRoleAndPermission", notes="查询所有角色下权限")
    @ApiImplicitParam(name= "selectAllRoleAndPermission", value = "selectAllRoleAndPermission", required = false, dataType = "test")
    @RequestMapping(value = "/selectAllRoleAndPermission",method = RequestMethod.GET)
    public Response selectAllRoleAndPermission(){

        return permissionService.selectAllRoleAndPermission();
    }

    @ApiOperation(value="selectAllRoleAndPermissionByRoleId", notes="根据当前角色查询权限")
    @ApiImplicitParam(name= "selectAllRoleAndPermissionByRoleId", value = "selectAllRoleAndPermissionByRoleId", required = false, dataType = "test")
    @RequestMapping(value = "/selectAllRoleAndPermissionByRoleId",method = RequestMethod.GET)
    public Response selectAllRoleAndPermissionByRoleId(@RequestParam("role_id")int role_id){
       if(StringUtils.isEmpty(String.valueOf(role_id))){
           return rtnFail("请求参数为null");
       }
        return permissionService.selectAllRoleAndPermissionByid(role_id);
    }




    @ApiOperation(value="setRoleAndPermission", notes="设置当前用户权限")
    @ApiImplicitParam(name= "setRoleAndPermission", value = "setRoleAndPermission", required = false, dataType = "test")
    @RequestMapping(value = "/setRoleAndPermission",method = RequestMethod.POST)
    public Response setRoleAndPermission(@RequestBody ResRolePermission resRolePermission){
         //ResRolePermission
        if(StringUtils.isEmpty(String.valueOf(resRolePermission.getRoleid())) || resRolePermission.getMenu().size()==0){
            return rtnFail("参数为nulll");
        }
        return permissionService.setRoleAndPermission(resRolePermission.getRoleid(),resRolePermission.getMenu());
    }


    @ApiOperation(value="selectUserByLoginName", notes="根据当前的登录用户查询角色信息")
    @ApiImplicitParam(name= "selectUserByLoginName", value = "setRoleAndPermission", required = false, dataType = "test")
    @RequestMapping(value = "/selectUserByLoginName",method = RequestMethod.GET)
    public Response selectUserByLoginName(){
        //查询用户角色信息
        return permissionService.selectUserByLoginName();
    }



    @ApiOperation(value="deleteUserByUserid", notes="根据userId删除当前用户及用户角色关系")
    @ApiImplicitParam(name= "deleteUserByUserid", value = "deleteUserByUserid", required = false, dataType = "test")
    @RequestMapping(value = "/deleteUserByUserid",method = RequestMethod.GET)
    public Response deleteUserByUserid(@RequestParam("userid") int userid){
        if(StringUtils.isEmpty(String.valueOf(userid))){
            return rtnFail("用户id为null");
        }
        return permissionService.deleteUserByUserid(userid);
    }





}
