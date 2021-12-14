layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    //监听提交
    form.on('submit(saveBtn)', function(data){
        // layer.msg(JSON.stringify(data.field));
        var fieldData = data.field;

        $.ajax({
            type:"post",
            url:ctx+"/user/upusermsg",
            data:{
                userAccount:fieldData.userAccount,
                userPhone:fieldData.userPhone,
                userEmail:fieldData.userEmail,
                userSex:fieldData.userSex,
                userAddress:fieldData.userAddress,
                userAge:fieldData.userAge,
                userName:fieldData.userName,
                id:fieldData.id,
            },
            dataType:"json",
            success:function (msg) {
                if(msg.code === 200){
                    layer.msg("修改成功，3秒后重新登录",function () {
                        //删除cookie
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/wms"})
                        $.removeCookie("userName",{domain:"localhost",path:"/wms"})
                        $.removeCookie("tureName",{domain:"localhost",path:"/wms"})
                        //跳转
                        window.parent.location.href=ctx+"/index";

                    })
                }else {
                    layer.msg(msg.msg)
                }
            }
        })


    });
});