layui.use(['table','layer','formSelects'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        formSelects = layui.formSelects;

    /**
     * 用户列表展示
     */
    var tableIns = table.render({
        elem: '#userList',
        url : ctx+'/user/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "userId", title:'编号',fixed:"true", width:80},
            {field: 'userAccount', title: '用户名', minWidth:50, align:"center"},
            {field: 'userPhone', title: '用户电话', minWidth:100, align:'center'},
            {field: 'balance', title: '用户余额', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150,
                templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    $(".search_btn").click(function () {
        //这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                userAccount: $("input[name=userAccount]").val()
                ,phone: $("input[name=phone]").val()
                //…
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })



    table.on('toolbar(users)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus)
        switch(obj.event){
            case 'add':
                openAddOrUpdateSaleChanceDialog(checkStatus.data.userId);
                break;
            case 'del':
                // layer.msg('删除');
                removeUser(checkStatus.data);
                break;
        };
    });


    function removeUser(data){
        console.log(data)
        if(data.length == 0){
            layer.msg("请选择要删除的记录！")
            return;
        }
        layer.confirm("您确定要删除选中的记录吗？",function (index) {
                layer.close(index);
                var ids = "ids=";
                for (var i = 0; i < data.length; i++) {
                    if (i < data.length - 1) {
                        ids = ids + data[i].userId + "&ids=";
                    } else {
                        ids = ids + data[i].userId;
                    }

                }
                $.ajax({
                    type:"post",
                    url: ctx + "/user/delete",
                    data:ids, // 参数传递的是数组
                    dataType:"json",
                    success:function (msg) {
                        if (msg.code == 200) {
                            layer.msg("删除成功");
                            tableIns.reload();
                        } else {
                            layer.msg(msg.msg, {icon: 5});
                        }

                    }
                })
            }

        )
    }


    function openAddOrUpdateSaleChanceDialog(userId) {
        var title = "<h2>用户管理-用户添加</h2>";
        var url = ctx + "/user/addOrUpdateUserPageByBalance";

        if(userId){
            console.log(userId)
            title = "<h2>用户管理-用户更新</h2>";
            url = url + "?id=" + userId;
        }


        layui.layer.open({
            title:title,
            type:2, //iframe
            content: url,
            area:["500px","300px"],
            maxmin:true
        });
    }

    //工具条事件
    table.on('tool(users)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        console.log(obj.data)
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'del'){ //删除

            layer.confirm('真的删除行么', function(index){
                console.log("okoko")
                console.log(obj.data)
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                console.log(obj)
                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    type:"post",
                    url:ctx+"/user/remove",
                    data:{
                        ids:obj.data.userId,
                        isValid:0
                    },
                    dataType:"json",
                    success:function (msg) {
                        if(msg.code == 200){
                            layer.msg("删除成功");
                            tableIns.reload();
                        }else {
                            layer.msg(msg.msg);
                        }
                    }

                })
            });
        } else if(layEvent === 'edit'){ //编辑
            var saleChanceId = data.userId;
            openAddOrUpdateSaleChanceDialog(saleChanceId);
        }
    });





});

