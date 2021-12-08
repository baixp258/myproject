package com.huawei.service;

import com.huawei.permissionentity.*;
import com.huawei.status.Response;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName PermissionService.java
 * @Description TODO
 * @createTime 2021年11月03日 17:09:00
 */
public interface PermissionService {

    /**
     * 查询当前所有的用户信息
     */
    public Response selectAllSysUser();


    /**
     * 添加用户
     */
    public Response addUser(SysUser user);

    /**
     * 查询当前所有的角色信息
     */
    public Response selectAllSysRole();


    /**
     * 添加角色
     */
    public Response addSysAllRole(SysRole role);


    /**
     * 查询当前所有的权限信息
     */
    public Response selectAllSysPermission();

    /**
     * 添加权限信息
     */
    public Response addSysPermission(SysPermission sysPermission);

    /**
     * 查询所有角色和对应的权限信息
     */
    public  Response selectAllRoleAndPermission();

    /**
     * 根据当前role_id查询当前用户权限信息
     */
    public  Response selectAllRoleAndPermissionByid(int role_id);

    /**
     * 设置当前的用户
     */
    public Response setRoleAndPermission(int role_id,List<String> rules);


    /**
     * 根据登录用户查询当前用户的角色和权限信息
     */
    public Response selectUserByLoginName();

    /**
     * 根据用户id删除当前用户以及当前用户对应的角色关系
     */
    public Response deleteUserByUserid(int userid);

    /**
     * 根据userid和roleid添加用户和用户角色
     */
    public Response addUserAndRole(AddUserRole addUserRole);

    /**
     * 根据userid和roleid修改用户名和用户角色
     */
    public Response updateUserAndRole(UpdateUserAndRole updateuser);
}
