package com.yjxxt.wms.controller;

import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.Role;
import com.yjxxt.wms.query.RoleQuery;
import com.yjxxt.wms.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;


    @RequestMapping("findalltolist")
    @ResponseBody
    public Map<String, Object> findAllToList(RoleQuery roleQuery){
        return roleService.queryByParamsForTable(roleQuery);
    }


    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer roleId, Model model){
//        System.out.println(roleId);
        Role role = roleService.selectByPrimaryKey(roleId);
//        System.out.println(role);
        if(null != roleId){
            model.addAttribute("role",role);
        }
        return "role/add_update";
    }

    @RequestMapping("toRoleGrantPage")
    public String toRoleGrantPage(Integer roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }



    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Role role){
        ResultInfo resultInfo = new ResultInfo();
        roleService.addRole(role);
        return resultInfo;
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Role role){
        ResultInfo resultInfo = new ResultInfo();
        roleService.updateRole(role);
        return resultInfo;
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer roleId){
        System.out.println(roleId+"<<<<<<<");
        ResultInfo resultInfo = new ResultInfo();
        roleService.deleteRole(roleId);
        return resultInfo;
    }



    @RequestMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer[] mids,Integer roleId){
//        ResultInfo resultInfo =  new ResultInfo();
        roleService.addGrant(mids, roleId);
        return success("权限修改成功");
    }

}
