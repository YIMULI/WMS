package com.yjxxt.wms.service;

import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.Permission;
import com.yjxxt.wms.bean.Role;
import com.yjxxt.wms.dao.PermissionMapper;
import com.yjxxt.wms.dao.RoleMapper;
import com.yjxxt.wms.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleService extends BaseService<Role,Integer> {
    @Resource
    private RoleMapper roleMapper;



    @Resource
    private PermissionMapper permissionMapper;
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role) {
        AssertUtil.isTrue(null == role.getRoleName(),"职务名不能为空！");
        Role roleTemp = roleMapper.selectAllByName(role.getRoleName());
        AssertUtil.isTrue(null != roleTemp,"职务已存在");
        AssertUtil.isTrue(null == role.getRoleRemark(),"职务备注不能为空！");
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.insertSelective(role)<1,"职务添加失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role){
        AssertUtil.isTrue(null == role.getId() || null == selectByPrimaryKey(role.getId()),"待修改的用户不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"职务名不能为空！");
        Role roleTemp = roleMapper.selectAllByName(role.getRoleName());
        AssertUtil.isTrue(role.getRoleName().equals(roleTemp.getRoleName()) && role.getRoleRemark().equals(roleTemp.getRoleRemark()),"该职务已存在！");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"职务记录更新失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer roleId) {
        System.out.println(roleId);
        Role role = roleMapper.selectByPrimaryKey(roleId);
        AssertUtil.isTrue(null == roleId || null == role,"待删除的用户不存在！");
        role.setIsValid(0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(role)<1,"职务记录删除失败!");
    }




    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer[] mids, Integer roleId){
        /**
         * 核心表-t_permission t_role(校验角色存在)
         * 如果角色存在原始权限 删除角色原始权限
         * 然后添加角色新的权限 批量添加权限记录到t_permission
         */
        Role role = roleMapper.selectByPrimaryKey(roleId);
        AssertUtil.isTrue(null == roleId || null == role,"待授权的角色不存在!");
        int count = permissionMapper.countPermissionByRoleId(roleId);
        if(count>0){
            AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(roleId) != count,"权限分配失败!");
        }
        if(null != mids && mids.length>0){
            List<Permission> permissions = new ArrayList<>();
            for(Integer mid : mids){
                Permission permission = new Permission();
                permission.setRoleId(roleId);
                permission.setUpdateDate(new Date());
                permission.setCreateDate(new Date());
                permission.setModuleId(mid);
                permission.setIsValid(1);
//                permission.setAclValue(moduleMapper.selectByPrimaryKey(mid).getOptValue());
                permissions.add(permission);
            }
            AssertUtil.isTrue(permissionMapper.insertBatch(permissions) != permissions.size(),"权限分配失败!");

        }



    }
}
