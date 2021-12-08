package com.huawei.permissionmapper;

import com.huawei.permissionentity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysPermissionMapper extends JpaRepository<SysPermission, Integer>, JpaSpecificationExecutor<SysPermission>  {
}
