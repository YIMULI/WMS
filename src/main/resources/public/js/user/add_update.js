layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        formSelects = layui.formSelects;



    form.on("submit(addOrUpdateUser)",function (data) {
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });

        console.log(data.field)
        var url = ctx + "/user/save";
        var fieldData = data.field;

        if($("input[name=id]").val()){
            // console.log("okokokoko")
            url = ctx + "/user/update/user";

        }

        console.log(fieldData)
        console.log("我到这了1"+url)
        $.ajax({
            type:"post",
            url:url,
            data:fieldData,
            dataType:"json",
            success:function (msg) {
                if(msg.code === 200){
                    // 提示成功
                    layer.msg("操作成功！");
                    // 关闭加载层
                    layer.close(index);
                    // 关闭弹出层
                    layer.closeAll("iframe");
                    // 刷新父页面，重新渲染表格数据
                    parent.location.reload();
                }else {
                    layer.msg(msg.msg)
                }
            }
        })
        console.log("我到这了2"+url)

        return false;
    })

    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        // console.log()
        parent.layer.close(index)
        // parent.location.reload();
    })


    /**
     * 加载下拉框数据
     */
    // console.log($("input[name=id]").val())
    // var userId = $("input[name='id']").val();
    // formSelects.config('selectId',{
    //     type:"post",
    //     searchUrl:ctx + "/user/list?userId="+userId,
    //     //自定义返回数据中name的key, 默认 name
    //     keyName: 'roleName',
    //     //自定义返回数据中value的key, 默认 value
    //     keyVal: 'id',
    //     keySel: 'selected'
    //
    // },true);


    // formSelects.config('selectId',{
    //     type:"post",
    //     searchUrl:ctx+"/role/list?userId="+userId,
    //     keyName: 'roleName', //自定义返回数据中name的key, 默认 name
    //     keyVal: 'id' //自定义返回数据中value的key, 默认 value
    // },true);

});