<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理-登录</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/css/register.css" media="all">
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form register-form">
            <form class="layui-form" action="" >
                <div class="layui-form-item logo-title">
                    <h1>WMS用户注册</h1>
                </div>
                <div class="layui-form-item ">
                    <label class="layui-icon layui-icon-username" for="username"></label>
                    <input type="text" name="username" lay-verify="required|account" placeholder="用户名或者邮箱" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                        <label class="layui-icon layui-icon-username" for="turename"></label>
                    <input type="text" name="turename" lay-verify="required|account" placeholder="真实姓名" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-search" for="sex"></label>
                    <select name="sex" lay-verify="required|account" class="sex">
                        <option value="">选择您的性别</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="userage"></label>
                    <input type="text" name="userage" lay-verify="required|account" placeholder="年龄" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-cellphone" for="phone"></label>
                    <input type="text" name="phone" lay-verify="required|account" placeholder="手机号" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-code-circle" for="email"></label>
                    <input type="text" name="email" lay-verify="required|account" placeholder="邮箱" autocomplete="off" class="layui-input" >
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-location" for="address"></label>
                    <select name="address" lay-verify="required|account" class="address">
                        <option value="">请选择一个城市</option>
                        <option value="北京">北京</option>
                        <option value="上海">上海</option>
                        <option value="杭州">杭州</option>
                        <option value="合肥">合肥</option>
                        <option value="广州">广州</option>
                        <option value="江苏">江苏</option>
                        <option value="重庆">重庆</option>
                        <option value="四川">四川</option>
                    </select>
                </div>

                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="register">注 册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${ctx}/js/register.js" charset="utf-8"></script>
</body>
</html>