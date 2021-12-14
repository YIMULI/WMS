package com.yjxxt.wms.controller;

import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.bean.Permission;
import com.yjxxt.wms.bean.User;
import com.yjxxt.wms.query.UserQuery;
import com.yjxxt.wms.service.AdminService;
//import com.yjxxt.wms.service.PermissionService;
import com.yjxxt.wms.service.PermissionService;
import com.yjxxt.wms.util.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminLoginController extends BaseController {

    @Resource
    private AdminService adminService;
//
    @Resource
    private PermissionService permissionService;

    @RequestMapping("admin_login")
    public String adminLogin(){
        return "admin_login";
    }

    @RequestMapping("adminlogin/login")
    @ResponseBody
    public ResultInfo login(Admin admin) {
        System.out.println("ok");
        ResultInfo resultInfo = new ResultInfo();
        UserQuery userModel = adminService.adminLogin(admin.getAdminAccount(),admin.getAdminPassword());
        resultInfo.setResult(userModel);
        return resultInfo;
    }

    @RequestMapping("admin_main")
    public String adminMain(HttpServletRequest req){
        int adminId = LoginUserUtil.releaseUserIdFromCookie(req);
        System.out.println(adminId+"<<<<<<<<<,,,");
        Admin admin = adminService.selectByPrimaryKey(adminId);
        System.out.println(admin);
        List<String> permissions = permissionService.selectByParamsByAdmin(admin.getAdminId());
        for(String str : permissions){
            System.out.println(str);
        }
        req.setAttribute("admin",admin);
        req.getSession().setAttribute("permissions",permissions);
        return "admin_main";
    }

    @RequestMapping("admin_welcome")
    public String adminWelcome(HttpServletRequest req){
        int adminId = LoginUserUtil.releaseUserIdFromCookie(req);
        Admin admin = adminService.selectByPrimaryKey(adminId);

        req.setAttribute("admin",admin);
        return "admin_welcome";
    }



    @RequestMapping("admin_login/upuser")
    @ResponseBody
    public ResultInfo updateUserPassword (HttpServletRequest request, String oldPwd, String newPwd, String towPwd) {
        ResultInfo resultInfo = new ResultInfo();
//        获取userID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
//        调用service层的密码修改密码
        adminService.updateUserPassword(userId,oldPwd,newPwd,towPwd);
////        设置状态栏和提示信息
//        resultInfo.setCode(getcode);
//        resultInfo.setMsg(getmsg);
        return resultInfo;
    }



    @RequestMapping("admin_login/upusermsg")
    @ResponseBody
    public ResultInfo upusermsg (Admin admin,HttpServletRequest req) {
        ResultInfo resultInfo = new ResultInfo();
        int id = LoginUserUtil.releaseUserIdFromCookie(req);
        adminService.upDateUser(admin,id);
        return resultInfo;
    }



    @RequestMapping("admin/toPasswordPage")
    public String toPasswordPage(){
        return "admin/password";
    }


    @RequestMapping("admin/toSettingPage")
    public String toSettingPage(HttpServletRequest req){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        Admin admin = adminService.selectByPrimaryKey(userId);
        req.setAttribute("admin",admin);
        return "admin/setting";
    }


}
