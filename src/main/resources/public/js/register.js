layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    //监听提交
    form.on('submit(register)', function(data){
        // layer.msg(JSON.stringify(data.field));
        var fieldData = data.field;

        console.log(fieldData)


        $.ajax({
            type:"post",
            url:ctx+"/user/register",
            data: {
                userAccount:fieldData.username,
                userPassword:fieldData.password,
                userName:fieldData.turename,
                userSex:fieldData.sex,
                userAge:fieldData.userage,
                userPhone:fieldData.phone,
                userAddress:fieldData.address,
                userEmail:fieldData.email,
            },
            dataType:"json",
            success:function (msg) {
                if(msg.code === 200){
                    alert("注册成功")
                    window.location.href = ctx+"/index"
                }else {
                    layer.msg(msg.msg);
                }
            }
        })

        return false;
    });

});