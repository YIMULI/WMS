<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">类名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="categoryName" id="categoryName" value="${category.categoryName}"   placeholder="请输入菜单名">
        </div>
    </div>
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">菜单样式</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input userName"-->
<#--                   name="moduleStyle" id="moduleStyle" value="${(category.moduleStyle)!""}" placeholder="请输入菜单样式">-->
<#--        </div>-->
<#--    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">排序</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input userName"-->
<#--                   name="orders" id="orders" placeholder="请输入排序值" value="${(category.orders)!""}">-->
<#--        </div>-->
<#--    </div>-->

    <!--
       添加根级菜单
    -->
    <input name="parentId" type="hidden" value="${category.parentId}"/>
    <input name="categoryId" type="hidden" value="${category.categoryId}"/>
    <input name="grade" type="hidden" value="${category.grade}"/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateModule">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeButton">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/category/update.js"></script>
</body>
</html>