package com.yjxxt.wms.controller;

import com.yjxxt.wms.annotation.RequirePermission;
import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.bean.AdminRole;
import com.yjxxt.wms.query.AdminQuery;
import com.yjxxt.wms.query.UserQuery;
import com.yjxxt.wms.service.AdminRoleService;
import com.yjxxt.wms.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("admins")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;

    @Resource
    private AdminRoleService adminRoleService;



    @RequestMapping("power")
    public String power(){
        return "role/role";

    }


    @RequestMapping("index")
    public String index(HttpServletRequest req){
        return "admin/admin";

    }


    @RequestMapping("list")
    @ResponseBody
    @RequirePermission(code = "40")
    public Map<String,Object> saylist(AdminQuery query){
        System.out.println(query);
        return adminService.queryUserByParams(query);
    }

    @RequestMapping("addOrUpdateUser")
    public String addOrUpdateSaleChance(Integer adminId, Model model){
        AdminRole adminRole = new AdminRole();
        adminRole.setRoleId(9999);
        if(null != adminId){
            Admin admin = adminService.selectByPrimaryKey(adminId);
            model.addAttribute("admin",admin);

        }
        model.addAttribute("adminRole",adminRole);
        return "/admin/add_update";
    }


    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdateSaleChancePage(Integer adminId, Model model){
        System.out.println(11110+adminId);
        AdminRole adminRole = adminRoleService.selectByadminId(adminId);
        System.out.println(adminRole);
        if(null == adminRole){
            adminRole = new AdminRole();
            adminRole.setRoleId(9999);
        }
        if(null != adminId){
            System.out.println("roleids"+adminRole.getRoleId());
            Admin admin = adminService.selectByPrimaryKey(adminId);
            model.addAttribute("admin",admin);
            model.addAttribute("adminRole",adminRole);
        }
        return "/admin/add_update";
    }



    @RequestMapping("savenewuser")
    @ResponseBody
    public ResultInfo addUser(Admin admin,int roleIds){
        System.out.println(admin);
        System.out.println(roleIds);
        ResultInfo resultInfo = new ResultInfo();
        adminService.addUserMsg(admin);
        Admin admin1 = adminService.selectByParamsByAccount(admin.getAdminAccount());
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(admin1.getAdminId());
        adminRole.setRoleId(roleIds);
        adminRole.setCreateDate(new Date());
        adminRole.setUpdateDate(new Date());
        adminRoleService.insertSelective(adminRole);
        return resultInfo;
    }


    @RequestMapping("deleteadmin")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        System.out.println(ids);
        ResultInfo resultInfo = new ResultInfo();
        adminService.deleteUser(ids);
        return resultInfo;
    }

    @RequestMapping("remove")
    @ResponseBody
    public ResultInfo removeUser(Admin admin){

        System.out.println(admin);
        ResultInfo resultInfo = new ResultInfo();
        adminService.removeUser(admin);
        return resultInfo;
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo upDateUser(Admin admin,Integer roleIds){
        System.out.println(roleIds+"roleIds");
        ResultInfo resultInfo = new ResultInfo();
        adminService.changeUser(admin);
        adminRoleService.deleteByAdminId(admin.getAdminId());
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(admin.getAdminId());
        adminRole.setRoleId(roleIds);
        adminRole.setUpdateDate(new Date());
        adminRoleService.insertSelective(adminRole);
        return resultInfo;
    }

}
