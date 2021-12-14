package com.yjxxt.wms.aop;

import com.yjxxt.wms.annotation.RequirePermission;
import com.yjxxt.wms.exception.NoLoginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {

    @Resource
    private HttpSession session;

    @Around(value = "@annotation(com.yjxxt.wms.annotation.RequirePermission)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        System.out.println(permissions);
        System.out.println("上面");
        if(null == permissions || permissions.size() == 0){
            throw new NoLoginException("未登录");
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        RequirePermission declaredAnnotation = methodSignature.getMethod().getDeclaredAnnotation(RequirePermission.class);
        if(!permissions.contains(declaredAnnotation.code())){
            throw new NoLoginException("无权限，请重新登陆");
        }
        return  point.proceed();

    }

}
