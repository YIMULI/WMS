layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



        form.on("submit(addOrUpdateSaleChance)",function (data) {
            var index = layer.msg("数据提交中,请稍后...",{
                icon:16, // 图标
                time:false, // 不关闭
                shade:0.8 // 设置遮罩的透明度
            });

            // console.log(data.field)
            var url = ctx + "/sale_chance/save";
            var fieldData = data.field;
            console.log(fieldData)
            if($("input[name=id]").val()){
                console.log("okokokoko")
                url = ctx + "/sale_chance/update";

            }

            console.log(url)
            // console.log(fieldData)
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

            return false;
        })

        $("#closeBtn").click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            // console.log()
            parent.layer.close(index)
            // parent.location.reload();
        })

        var assignMan = $("input[name='man']").val();
        $.ajax({
            type: "post",
            url: ctx +"/user/queryAllSales",
            dataType: "json",
            success:function (msg) {
                   for(var i = 0; i < msg.length; i++){
                    if(assignMan == msg[i].id){
                        $("#assignMan").append('<option value="'+msg[i].id+'"selected>'+msg[i].uname+'</option>');

                    }else {
                        $("#assignMan").append('<option value="'+msg[i].id+'">'+msg[i].uname+'</option>');
                    }
                }
                form.render("select");
            }
        })

});