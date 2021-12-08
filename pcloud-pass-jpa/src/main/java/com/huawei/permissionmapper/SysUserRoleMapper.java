package com.huawei.permissionmapper;

import com.huawei.permissionentity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserRoleMapper extends JpaRepository<SysUserRole, Integer>, JpaSpecificationExecutor<SysUserRole> {



}
