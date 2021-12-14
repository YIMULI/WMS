<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="adminId" type="hidden" value="${(admin.adminId)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="adminAccount" id="adminAccount"  value="${(admin.adminAccount)!}" placeholder="请输入用户名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="adminName" id="adminName" value="${(admin.adminName)!}" placeholder="请输入真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="email" name="adminEmail" value="${(admin.adminEmail)!}"
                   id="email"
                   placeholder="请输入邮箱">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="adminAge" id="adminAge" value="${(admin.adminAge)!}" placeholder="请输入真实年龄">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="adminSex" id="adminSex" value="${(admin.adminSex)!}" placeholder="请输入性别">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="phone" name="adminPhone" value="${(admin.adminPhone)!}" id="phone" placeholder="请输入手机号">
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">职位</label>
        <div class="layui-input-block">
            <select name="roleIds">
                <option value="" <#if adminRole.roleId=9999>selected="selected"</#if>>职位</option>
                <option value="1" <#if adminRole.roleId=1>selected="selected"</#if>>主管</option>
                <option value="2" <#if adminRole.roleId=2>selected="selected"</#if>>用户管理员</option>
                <option value="3" <#if adminRole.roleId=3>selected="selected"</#if>>商品管理员</option>
            </select>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/admin/add_update.js"></script>
</body>
</html>