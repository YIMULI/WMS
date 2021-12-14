package com.yjxxt.wms.controller;

import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.User;
import com.yjxxt.wms.query.UserQuery;
import com.yjxxt.wms.service.UserService;
import com.yjxxt.wms.util.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("main")
    public String main(HttpServletRequest req){
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        User user = userService.selectByPrimaryKey(userId);
        req.setAttribute("user",user);
        return "main";
    }




    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }





}
