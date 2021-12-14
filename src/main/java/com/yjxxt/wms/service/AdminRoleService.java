package com.yjxxt.wms.service;

import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.AdminRole;
import com.yjxxt.wms.dao.AdminRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminRoleService extends BaseService<AdminRole,Integer> {



    @Resource
    private AdminRoleMapper adminRoleMapper;


    public AdminRole selectByadminId(Integer adminId) {
        return adminRoleMapper.selectByAdminId(adminId);
    }

    public void deleteByAdminId(Integer adminId) {
        adminRoleMapper.deleteByAdminId(adminId);
    }
}
