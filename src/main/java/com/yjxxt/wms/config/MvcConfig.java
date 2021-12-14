package com.yjxxt.wms.config;

import com.yjxxt.wms.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Bean
    public NoLoginInterceptor noLoginInterceptor() {
        return new NoLoginInterceptor();
    }
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/***")
                .excludePathPatterns("/user/register","/register","/index","/user/login","/css/**","/images/**","/js/**","/lib/**","/admin_login","/adminlogin/login");
    }
}
