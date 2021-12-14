<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="userId" type="hidden" value="${(user.userId)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">余额</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="balance" id="balance"  value="${(user.balance)!}" placeholder="修改余额">
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

<script type="text/javascript" src="${ctx}/js/user/add_balance.js"></script>
</body>
</html>