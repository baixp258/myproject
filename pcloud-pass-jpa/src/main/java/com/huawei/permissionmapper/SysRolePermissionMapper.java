package com.huawei.permissionmapper;

import com.huawei.permissionentity.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author admin
 * @title: SysRolePermissionMapper
 * @projectName pcloudpassparent
 * @description: TODO
 * @date 2021/11/914:39
 */
public interface SysRolePermissionMapper extends JpaRepository<SysRolePermission, Integer>, JpaSpecificationExecutor<SysRolePermission> {
}
