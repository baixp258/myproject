package com.huawei.productmapper;

import com.huawei.permissionentity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


/**
 * 登录查询接口
 */
public interface UserLoginMapper extends JpaRepository<SysUser,Integer> ,JpaSpecificationExecutor<SysUser> {

    @Query(value = "select u.userid,u.username,u.address,u.email,u.createtime, ro.roleid,ro.role_name,ro.create_time,ro.auth_time,ro.auth_name,sp.permission_url \n" +
            "from sys_user u INNER JOIN sys_user_role r on u.userid=r.user_id INNER JOIN\n" +
            "sys_role ro on r.role_id=ro.roleid INNER JOIN sys_role_permission pr on ro.roleid=pr.role_id INNER JOIN sys_permission sp on pr.permission_id=sp.permissionid where u.username=?1 and `password`=?2",nativeQuery = true)
    List<Map<String,Object>> findLoginUser(String username, String  password);


}
