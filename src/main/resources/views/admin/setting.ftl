<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <#include "../common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">管理账号</label>
                <div class="layui-input-block">
                    <input type="text" name="userAccount" lay-verify="required" readonly="readonly" class="layui-input" value="${(admin.adminAccount)!}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">手机</label>
                <div class="layui-input-block">
                    <input type="number" name="userPhone" lay-verify="required" lay-reqtext="手机不能为空" placeholder="请输入手机"
                           value="${(admin.adminPhone)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">邮箱</label>
                <div class="layui-input-block">
                    <input type="email" name="userEmail"  placeholder="请输入邮箱"  value="${(admin.adminEmail)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label ">性别</label>
                <div class="layui-input-block">
                    <input type="text" name="userSex"  placeholder="性别"  value="${(admin.adminSex)!}" class="layui-input" readonly="readonly">
                </div>
                <tip class="layui-input-block" style="color: red">只读，如要修改，请联系客服。</tip>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="userAddress"  placeholder="请输入地址"  value="${(admin.adminAddress)!}" class="layui-input" readonly="readonly">
                </div>
                <tip class="layui-input-block" style="color: red">只读，如要修改，请联系客服。</tip>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">年龄</label>
                <div class="layui-input-block">
                    <input type="text" name="userAge"  placeholder="请输入年龄"  value="${(admin.adminAge)!}" class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="userName"  placeholder="请输入真实姓名"  value="${(admin.adminName)!}" class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="id"   value="${(admin.adminId)!}" >
                    <button class="layui-btn" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/admin/setting.js"></script>
</body>
</html>