package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.bean.User;

public interface AdminMapper extends BaseMapper<Admin,Integer> {


    Admin selectUserByName(String adminAccount);

    Integer updateByPrimaryKey(Admin admin);

    Integer updateIsByPrimaryKey(Admin admin);
}