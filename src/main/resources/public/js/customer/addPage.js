layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    /**
     * 监听submit事件
     * 实现营销机会的添加与更新
     */
    form.on("submit(addOrUpdateGoods)", function (data) {
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });
        // 请求的地址
        var url = ctx + "/customer_serve1/add";

        if($("input[name='id']").val()) {
            url = ctx+ "/customer_serve1/update";
        }
        // 发送ajax请求
        $.post(url, data.field, function (result) {
            // 操作成功
            if (result.code == 200) {
                // 提示成功
                layer.msg("操作成功！");
                // 关闭加载层
                layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新父页面，重新渲染表格数据
                parent.location.reload();
            } else {
                layer.msg(result.msg);
            }
        });
        return false; // 阻止表单提交
    });


    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        // 先得到当前iframe层的索引
        var index = parent.layer.getFrameIndex(window.name);
        // 再执行关闭
        parent.layer.close(index);
    });


});