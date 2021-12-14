<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${(seg.id)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"  name="goodsId" id="goodsId" value="${(seg.goodsId)!''}" placeholder="请输入商品编号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出库数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="sellNum" id="sellNum" value="${(seg.sellNum)!''}" placeholder="请输入出库数量">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出库地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="sellAddress"  value="${(seg.sellAddress)!''}" placeholder="请输入出库地址">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="userId" value="${(seg.userId)!''}" id="userId" placeholder="请输入用户编号">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="sellOrUpdateGoods">
                确认
            </button>
            <button id="closeBtn" class="layui-btn layui-btn-lg layui-btn-normal">取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/customer/sell_addPage.js"></script>
</body>
</html>