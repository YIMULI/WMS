package com.yjxxt.wms.service;

import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.bean.Permission;
import com.yjxxt.wms.dao.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission,Integer> {

    @Resource
    private PermissionMapper permissionMapper;


    public List<String> selectByParamsByAdmin(Integer adminid) {
        return permissionMapper.selectByPrimaryByAdminId(adminid);
    }


}
