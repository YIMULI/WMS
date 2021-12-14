package com.yjxxt.wms.controller;

import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.User;
import com.yjxxt.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController1 extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("user/register")
    @ResponseBody
    public ResultInfo saveUser(User user) {
        System.out.println("正在注册");
        userService.saveUser(user);
        return success("用户注册成功！");
    }
}
