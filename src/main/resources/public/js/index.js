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

        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            data: {
                userAccount:fieldData.username,
                userPassword:fieldData.password
            },
            dataType:"json",
            success:function (msg) {
                console.log(msg.result);
                if(msg.code === 200){
                    layer.msg("登陆成功");
                    var result = msg.result;

                    console.log(result.userId);
                    console.log("okkokok");
                    if($("input[type='checkbox']").is(":checked")){
                        $.cookie("userIdStr",result.userIdStr,{ expires:7 })
                        $.cookie("userName",result.userAccount,{ expires:7 })
                        $.cookie("tureName",result.userName,{ expires:7 })
                    }else {
                        $.cookie("userIdStr",result.userIdStr)
                        $.cookie("userName",result.userAccount)
                        $.cookie("tureName",result.userName)
                    }
                    window.location.href = ctx+"/main"


                }else {
                    layer.msg(msg.msg);
                }
            }
        })

        return false;
    });

});