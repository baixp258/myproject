package com.huawei.permissionmapper;

import com.huawei.permissionentity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface SysUserMapper extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {

    //查询当前的用户和角色关系
    @Query(value="select ro.role_name,u.username,u.email,u.userid,u.createtime,u.password,u.address from sys_user u INNER JOIN sys_user_role r on u.userid=r.user_id INNER JOIN sys_role ro on r.role_id=ro.roleid ",nativeQuery = true)
    List<Map<String,Object>> selectUserByLoginName();

    //根据用户id删除用户
    @Query(value="delete from sys_user where userid=?1",nativeQuery = true)
    @Transactional
    @Modifying
    int deleteUserByUserid(int userid);

    //根据用户id删除当前的用户角色关系
    @Query(value="delete from sys_user_role where user_id=?1", nativeQuery = true)
    @Transactional
    @Modifying
    int deleteUserRoleByUserid(int user_id);


    //根据用户userid修改当前用户的数据
    @Query(value="update sys_user set username=?2 ,password=?3,email=?4,address=?5 where userid=?1", nativeQuery = true)
    @Transactional
    @Modifying
    int updateUser(int user_id,String username,String password,String email,String address);



}
