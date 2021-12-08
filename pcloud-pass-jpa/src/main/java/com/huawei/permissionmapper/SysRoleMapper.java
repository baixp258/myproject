package com.huawei.permissionmapper;

import com.huawei.permissionentity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends JpaRepository<SysRole, Integer>, JpaSpecificationExecutor<SysRole>  {


    @Query(value="select * from sys_user u INNER JOIN sys_user_role r on u.userid=r.user_id INNER JOIN sys_role ro on r.role_id=ro.roleid",nativeQuery = true)
    List<Map<String,Object>> selectAllRoleAndPermission();

    /**
     * 获取当前用户对应的角色和权限信息
     * @return
     */
    @Query(value="select ro.roleid,ro.role_name,ro.create_time,ro.auth_time,sp.permission_url from sys_role ro INNER JOIN sys_role_permission pr on ro.roleid=pr.role_id INNER JOIN sys_permission sp on pr.permission_id=sp.permissionid",nativeQuery = true)
    List<Map<String,Object>>  findAllRoleAndPersions();



    @Query(value = "select r.roleid,r.role_name,r.create_time,r.auth_time,sp.permission_url from sys_role r INNER JOIN sys_role_permission rp on r.roleid=rp.role_id INNER JOIN sys_permission sp on rp.permission_id=sp.permissionid where roleid=?1",nativeQuery = true)
    List<Map<String,Object>> findAllRoleAndPermissionByroleid(int role_id);

    /**
     * 删除当前用户的角色id
     * @param role_id
     * @return
     */
    @Transactional
    @Modifying
    @Query(value="delete from sys_role_permission  where role_id=?1",nativeQuery = true)
    int deleteRoleAndPermission(int role_id);

    /**
     * 将新的权限插入数据库
     */
    @Transactional
    @Modifying
    @Query(value = "insert into sys_role_permission (role_id,permission_id) values(?1,?2)",nativeQuery = true)
    int insertRoleAndPermission(int role_id,int permission_id);
}
