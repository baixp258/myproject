package com.huawei.serviceimpl;

import com.huawei.permissionentity.*;
import com.huawei.permissionmapper.*;
import com.huawei.service.PermissionService;
import com.huawei.status.BaseController;
import com.huawei.status.Response;
import com.huawei.utils.DateUtils;
import com.huawei.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName PermissionServiceImpl.java
 * @Description TODO
 * @createTime 2021年11月03日 17:14:00
 */
@Slf4j
@Service
public class PermissionServiceImpl extends BaseController implements PermissionService {

    /**
     * 权限管理mapper接口
     */
    @Resource
    private SysPermissionMapper sysPermissionMapper;


    /**
     * 角色管理mapper接口
     */
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 用户管理mapper接口
     */
    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 更新角色权限
     */
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * 更新用户角色mapper接口
     * @return
     */
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Response selectAllSysUser() {
        //获取所有用户数据
        List<SysUser> users = sysUserMapper.findAll();
        return rtnSuccess(users);
    }

    //添加用户
    @Override
    public Response addUser(SysUser user) {
        SysUser save = sysUserMapper.save(user);
        if(save==null){
            return rtnFail("添加用户失败");
        }
        return rtnSuccess(save);
    }



    //获取所有角色数据
    @Override
    public Response selectAllSysRole() {

        List<SysRole> roles = sysRoleMapper.findAll();
        return rtnSuccess(roles);
    }


    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public Response addSysAllRole(SysRole role) {
        role.setAuth_time(DateUtils.getCurrentTime());
        role.setCreate_time(DateUtils.getCurrentTime());
        SysRole roles = sysRoleMapper.save(role);
        if(role==null){
            return  rtnFail("添加用户失败");
        }
        return rtnSuccess(roles);
    }



    //获取所有的权限数据
    @Override
    public Response selectAllSysPermission() {

        List<SysPermission> permissions = sysPermissionMapper.findAll();
        return rtnSuccess(permissions);
    }

    //添加权限
    @Override
    public Response addSysPermission(SysPermission sysPermission) {
        SysPermission permissions = sysPermissionMapper.save(sysPermission);
        if(permissions==null){
            return rtnFail("添加失败");
        }
        return rtnSuccess(permissions);
    }

    //查询所有角色和对应的权限信息
    @Override
    public Response selectAllRoleAndPermission() {
        //存放角色权限对象
        List<ResRolePermission> ResRolePermissions = new ArrayList<>();
        //获取所有的角色信息
        List<SysRole> roles = sysRoleMapper.findAll();
        //获取所有角色和权限关联信息
        List<Map<String,Object>> allRoleAndPermission = sysRoleMapper.findAllRoleAndPersions();
        if(allRoleAndPermission.size()==0){
            return rtnFail("当前用户没有分配可用权限");
        }
        //根据当前的角色信息获取相应的权限信息
        for(int j=0;j<roles.size();j++){
            //存放当前角色中权限访问url
            List<String> urlList = getRolePermissionUrl(roles, allRoleAndPermission, j);
            //获取角色权限对象
            getResultResRolePermission(ResRolePermissions, roles, j, urlList);
        }
        log.info(ResRolePermissions.toString());
        return rtnSuccess(ResRolePermissions);
    }

    //根据角色id查询角色和权限信息
    @Override
    public Response selectAllRoleAndPermissionByid(int role_id) {
        List<Map<String, Object>> allRoleAndPermissionByroleid = sysRoleMapper.findAllRoleAndPermissionByroleid(role_id);
        if(allRoleAndPermissionByroleid.size()==0 || allRoleAndPermissionByroleid==null){
            return rtnFail("根据role查询当前权限信息为null");
        }
        //存放权限数据
        List<String> urls = new ArrayList<>();
        //获取当前权限信息
        for(int i=0;i<allRoleAndPermissionByroleid.size();i++){
            String permissionUrl =(String) allRoleAndPermissionByroleid.get(i).get("permission_url");
            if(StringUtils.isNotEmpty(permissionUrl)){
                urls.add(permissionUrl);
            }
        }
        //封装返回权限信息
        ResRolePermission resRolePermission = getRolePermission(role_id, allRoleAndPermissionByroleid, urls);
        return rtnSuccess(resRolePermission);
    }



    //封装相应的权限信息
    private ResRolePermission getRolePermission(int role_id, List<Map<String, Object>> allRoleAndPermissionByroleid, List<String> urls) {
        ResRolePermission resRolePermission = new ResRolePermission();
        resRolePermission.setRoleid(role_id);
        resRolePermission.setRole_name((String)allRoleAndPermissionByroleid.get(0).get("role_name"));
        resRolePermission.setCreate_time((String)allRoleAndPermissionByroleid.get(0).get("create_time"));
        resRolePermission.setAuth_time((String)allRoleAndPermissionByroleid.get(0).get("auth_time"));
        resRolePermission.setAnth_name((String)allRoleAndPermissionByroleid.get(0).get("username"));
        resRolePermission.setMenu(urls);
        return resRolePermission;
    }

    //根据角色id删除就权限
    private int deleteRoleAndPermission(int role_id) {
        if(StringUtils.isEmpty(String.valueOf(role_id))){
            return 0;
        }
        int deleteRoleAndPermission = sysRoleMapper.deleteRoleAndPermission(role_id);
        return deleteRoleAndPermission;

    }

    //根据角色id设置新的权限
    @Transactional
    @Override
    public Response setRoleAndPermission(int role_id,List<String> rules) {
        //插入数据库条数标识
        int inertRoleNum=0;
        //更新后的数据
        Object data=null;
        if(rules.size()<0){
            return rtnFail("要更新权限数据为null");
        }
        //删除当前角色下所有权限
        int delNum = deleteRoleAndPermission(role_id);
      try {
          //更新新的权限信息
          for ( String rule:rules) {
              //获取redis权限码表，并更新到数据库中
              String permission_id =RedisUtils.getJedis().getResource().get(rule);
              if(StringUtils.isEmpty(permission_id)){
                  continue;
                //  return rtnFail("权限码表未找到新赋值权限");
              }
              //获取新的权限信息
              SysRolePermission save = getResRolePermission(role_id, permission_id);
              if(save!=null)
              inertRoleNum++;
          }
      }catch (Exception e){
            e.printStackTrace();
            return rtnFail("权限更新失败");
      }
      if(inertRoleNum > 0){
          Response response =  selectAllRoleAndPermissionByid(role_id);
          if(response.getCode()=="0000") {
              data = response.getData();
          }
      }
      return rtnSuccess(data);
    }
    //查询用户角色信息
    @Override
    public Response selectUserByLoginName() {
       //获取用户信息
        List<Map<String, Object>> selectUsers = sysUserMapper.selectUserByLoginName();
        if(selectUsers.size()==0 && selectUsers==null){
            return rtnSuccess("查询成功数据为null");
        }
        //获取封装后的数据
        List<UserAndRole> users = getSelectUsers(selectUsers);
        return rtnSuccess(users);

    }
    //根据userid删除用户以及用户的关联的角色关系
    @Transactional
    @Override
    public Response deleteUserByUserid(int userid) {
        if(StringUtils.isEmpty(String.valueOf(userid))){
            return rtnFail("用户id为null");
        }
        try{
            //根据用户id删除用户和用户角色关系  (多级删除：先删除从表，在删除主表)
            int  delRoleNum= sysUserMapper.deleteUserRoleByUserid(userid);
            int  delUserNum = sysUserMapper.deleteUserByUserid(userid);
             if(delUserNum==0 && delRoleNum==0){
                 return rtnFail("删除失败,数据库无关联数据");
             }
        }catch(Exception e){
           throw e;
           // return rtnFail("删除异常");
        }
        return rtnSuccess();
    }

    @Override
    public Response addUserAndRole( AddUserRole addUserRole) {
        if(addUserRole==null){
            return rtnFail("参数为null");
        }
        //创建用户
        SysUser sysUser = new SysUser();
        sysUser.setUsername(addUserRole.getUsername());
        sysUser.setPassword(addUserRole.getPassword());
        sysUser.setAddress(addUserRole.getAddress());
        sysUser.setCreatetime(DateUtils.getCurrentTime());
        sysUser.setEmail(addUserRole.getEmail());
        //添加用户到数据库
        SysUser saveUser = sysUserMapper.save(sysUser);
        if(StringUtils.isEmpty(String.valueOf(saveUser.getUserid()))){
            return rtnFail("添加U用户失败，无法关联角色");
        }
        //创建用户角色
        SysUserRole sysUserRole = new SysUserRole();
        SysRole sysRole = new SysRole();
        SysUser sysuser=new SysUser();
        sysRole.setRoleid(addUserRole.getRoleid());
        sysuser.setUserid(saveUser.getUserid());
        sysUserRole.setSysRole(sysRole);
        sysUserRole.setSysUser(sysuser);
        //添加用户角色到数据库
        try {
           //添加用户角色到数据库
            SysUserRole  save = sysUserRoleMapper.save(sysUserRole);
       }catch(Exception e){
            e.printStackTrace();
            //删除用户
            sysUserMapper.delete(sysUser);
            return rtnFail("添加用户权限失败");
       }
       return rtnSuccess();
    }

    @Transactional
    @Override
    public Response updateUserAndRole(UpdateUserAndRole updateuser) {

        //1.根据用户id删除用户对应的角色关系
        int userid = updateuser.getUserid();
        if(userid==0){
            return rtnFail("用户id为null");
        }
        //删除从表信息数据
        int delNum = sysUserMapper.deleteUserRoleByUserid(userid);
        //2.根据用户id修改用户信息
        if(delNum <=0){
           return rtnFail("删除用户数据失败");
        }
        int updateNum = sysUserMapper.updateUser(userid, updateuser.getUsername(), updateuser.getPassword(), updateuser.getEmail(), updateuser.getAddress());
        if(updateNum <= 0){
            return rtnFail("修改用户数据失败");
        }
        //3.将新的用户角色关系更新到表中
        SysUserRole sysUserRole = new SysUserRole();
        SysUser sysUser = new SysUser();
        SysRole sysRole = new SysRole();
        sysUser.setUserid(userid);
        sysRole.setRoleid(updateuser.getRoleid());
        sysUserRole.setSysUser(sysUser);
        sysUserRole.setSysRole(sysRole);
        SysUserRole save = sysUserRoleMapper.save(sysUserRole);
        if(save==null){
            return rtnFail("用户权限修改失败");
        }
        return rtnSuccess();
    }


    //封装当前查询到用户角色关系
    private List<UserAndRole> getSelectUsers(List<Map<String, Object>> selectUsers) {
        List<UserAndRole> users = new ArrayList<>();
        for(int i=0;i<selectUsers.size();i++){
            UserAndRole userAndRole = new UserAndRole();
            userAndRole.setUserid((int)selectUsers.get(i).get("userid"));
            userAndRole.setUsername((String)selectUsers.get(i).get("username"));
            userAndRole.setEmail((String)selectUsers.get(i).get("email"));
            userAndRole.setRole_name((String)selectUsers.get(i).get("role_name"));
            userAndRole.setCreatetime((String)selectUsers.get(i).get("createtime"));
            userAndRole.setAddress((String)selectUsers.get(i).get("address"));
            userAndRole.setEmail((String)selectUsers.get(i).get("email"));
            userAndRole.setPassword((String)selectUsers.get(i).get("password"));
            users.add(userAndRole);
        }
        return users;
    }

    //查询当前角色权限信息
    private SysRolePermission getResRolePermission(int role_id, String permission_id) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        SysRole sysRole = new SysRole();
        SysPermission sysPermission = new SysPermission();
        sysRole.setRoleid(role_id);
        sysPermission.setPermissionid(Integer.valueOf(permission_id));
        sysRolePermission.setSysRole(sysRole);
        sysRolePermission.setSysPermission(sysPermission);
        return sysRolePermissionMapper.save(sysRolePermission);
    }


    //根据当前的role名称获取角色和权限管联查的中role名称，并获取访问url存放到集合中
    private List<String> getRolePermissionUrl(List<SysRole> roles, List<Map<String, Object>> allRoleAndPermission, int j) {
        //存放当前url
        List<String> urlList = new ArrayList<>();
        for(int i=0;i<allRoleAndPermission.size();i++){
            //判断角色和权限关联查询角色名称值，是否包含当前role名称
            if(allRoleAndPermission.get(i).containsValue(roles.get(j).getRole_name())){
                //获取url
                String permissionUrl= (String)allRoleAndPermission.get(i).get("permission_url");
                if(permissionUrl!=null){
                    urlList.add(permissionUrl);
                }
            }
        }
        return urlList;
    }


    //获取角色权限对象
    private void getResultResRolePermission(List<ResRolePermission> resRolePermissions, List<SysRole> roles, int j, List<String> urlList) {
        //创建角色权限对象，将角色和权限的关联数据封装
        ResRolePermission resRolePermission = new ResRolePermission();
        //获取用户和角色对应的关系
        List<Map<String, Object>> roleMaps = sysRoleMapper.selectAllRoleAndPermission();
        resRolePermission.setMenu(urlList);
        resRolePermission.setRoleid(roles.get(j).getRoleid());
        resRolePermission.setAnth_name(roles.get(j).getAuth_name());
        resRolePermission.setRole_name(roles.get(j).getRole_name());
        resRolePermission.setCreate_time(roles.get(j).getCreate_time());
        resRolePermission.setAuth_time(roles.get(j).getAuth_time());
        resRolePermissions.add(resRolePermission);
    }


}
