layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    //监听提交
    form.on('submit(login)', function(data){
        // layer.msg(JSON.stringify(data.field));
        var fieldData = data.field;
        if(fieldData.username === "undefined" && fieldData.username.trim() === ""){
            layer.msg("用户名不能为空!")
            return ;
        }
        if(fieldData.password === "undefined" && fieldData.password.trim() === ""){
            layer.msg("密码不能为空!")
            return ;
        }
        var url = ctx+"/adminlogin/login"
        console.log(data.field)
        console.log(url)
        $.ajax({
            type:"post",
            url:url,
            data: {
                adminAccount:fieldData.adminAccount,
                adminPassword:fieldData.adminPassword
            },
            dataType:"json",
            success:function (msg) {
                console.log(msg.result+"ok");
                if(msg.code === 200){
                    layer.msg("登陆成功");
                    console.log("okokokok")
                    var result = msg.result;
                    console.log(result)
                    console.log(result.userIdStr);
                    console.log("okkokok");
                    if($("input[type='checkbox']").is(":checked")){
                        $.cookie("userIdStr",result.userIdStr,{ expires:7 })
                        $.cookie("userName",result.adminAccount,{ expires:7 })
                        $.cookie("tureName",result.userName,{ expires:7 })
                    }else {
                        $.cookie("userIdStr",result.userIdStr)
                        $.cookie("userName",result.adminAccount)
                        $.cookie("tureName",result.userName)
                    }
                    window.location.href = ctx+"/admin_main"


                }else {
                    layer.msg(msg.msg);
                }
            }
        })

        return false;
    });

});