package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.AdminRole;

public interface AdminRoleMapper extends BaseMapper<AdminRole,Integer> {

    AdminRole selectByAdminId(Integer adminId);

    void deleteByAdminId(Integer adminId);
}