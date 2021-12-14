package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    Integer countPermissionByRoleId(Integer roleId);

    Integer deletePermissionByRoleId(Integer roleId);

    List<Integer> queryRoleHasAllModuleIdsByRoleId(Integer roleId);

    List<String> queryUserHasRolesHasPermissions(Integer userId);



    List<String> selectByPrimaryByAdminId(int adminId);
}