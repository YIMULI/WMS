package com.yjxxt.wms.service;

import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.Module;
import com.yjxxt.wms.bean.Permission;
import com.yjxxt.wms.dao.ModuleMapper;
import com.yjxxt.wms.dao.PermissionMapper;
import com.yjxxt.wms.dto.TreeDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    public List<TreeDto> selectAllModiles02(Integer roleId){
        List<TreeDto> list = moduleMapper.selectAllModules();
        List<Integer> roleHasMids = permissionMapper.queryRoleHasAllModuleIdsByRoleId(roleId);
        if(null != roleHasMids && roleHasMids.size() > 0){
            list.forEach(treeDto -> {
                if(roleHasMids.contains(treeDto.getId())){
                    treeDto.setChecked(true);
                }
            });
        }
        return list;
    }
}
