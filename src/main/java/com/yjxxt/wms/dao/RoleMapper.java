package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.Role;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    Role selectAllByName(String roleName);
}