layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    //监听提交
    form.on('submit(saveBtn)', function(data){
        // layer.msg(JSON.stringify(data.field));

        var fieldData = data.field;

        if(fieldData.old_password === "undefined" && fieldData.old_password.trim() === ""){
            layer.msg(" 请输入原密码!")
            return ;
        }
        if(fieldData.new_password === "undefined" && fieldData.new_password.trim() === ""){
            layer.msg("请输入新密码!")
            return ;
        }
        if(fieldData.again_password === "undefined" && fieldData.again_password.trim() === ""){
            layer.msg("请确认新密码!")
            return ;
        }

        $.ajax({
            type:"post",
            url:ctx+"/user/upuser",
            data:{
                oldPwd:fieldData.old_password,
                newPwd:fieldData.new_password,
                towPwd:fieldData.again_password,

            },
            dataType:"json",
            success:function (msg) {
                if(msg.code === 200){
                    layer.msg("修改成功，3秒后重新登录",function () {
                        //删除cookie
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"})
                        $.removeCookie("userName",{domain:"localhost",path:"/crm"})
                        $.removeCookie("tureName",{domain:"localhost",path:"/crm"})
                        //跳转
                        window.parent.location.href=ctx+"/index";

                    })
                }else {
                    layer.msg(msg.msg)
                }
            }
        })
        return false;
    });
});