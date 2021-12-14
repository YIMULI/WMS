<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${(adg.id)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"  name="goodsId" id="goodsId" value="${(adg.goodsId)!}" placeholder="请输入商品编号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">入库数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="addNum" id="addNum" value="${(adg.addNum)!}" placeholder="请输入入库数量">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">入库地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="addAddress" lay-verify="required" value="${(adg.addAddress)!}" placeholder="请输入入库地址">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">管理员编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="adminId" value="${(adg.adminId)!}" id="adminId" placeholder="请输入管理员编号">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateGoods">
                确认
            </button>
            <button id="closeBtn" class="layui-btn layui-btn-lg layui-btn-normal">取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/customer/addPage.js"></script>
</body>
</html>