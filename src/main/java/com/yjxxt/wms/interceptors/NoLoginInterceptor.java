package com.yjxxt.wms.interceptors;

import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.exception.NoLoginException;
import com.yjxxt.wms.service.AdminService;
import com.yjxxt.wms.service.UserService;
import com.yjxxt.wms.util.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取Cookie中的用户ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 判断用户ID是否不为空，且数据库中存在对应的用户记录
        if (null == userId || null == userService.selectByPrimaryKey(userId) && null == adminService. selectByPrimaryKey(userId)) {
            // 抛出未登录异常=
            System.out.println(userId);
            System.out.println("00");
            throw new NoLoginException();
        }

        return true;

    }
}
