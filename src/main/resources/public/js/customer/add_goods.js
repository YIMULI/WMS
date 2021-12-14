layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#addGoodsList', // 表格绑定的ID
        url : ctx + '/customer_serve1/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "addGoodsListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'goodsId', title: '商品编号',align:"center"},
            {field: 'addNum', title: '入库数量',  align:'center'},
            {field: 'addAddress', title: '入库地址', align:'center'},
            {field: 'adminId', title: '管理员id', align:'center'},
            {field: 'addDate', title: '入库时间',  align:'center'},
            {title: '操作', templet:'#addChanceListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        table.reload('addGoodsListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                id: $("input[name='id']").val(), // 客户名
                goodsId: $("input[name='goodsId']").val(), // 创建人
                adminId: $("input[name='adminId']").val() // 状态
            }
            , page: {
                curr: 1 // 重新从第 1 页开始
            }
        }); // 只重载数据
    });

    /**
     * 头部工具栏 监听事件
     */
    table.on('toolbar(addGoods)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                // 点击添加按钮，打开添加营销机会的对话框
                openAddOrUpdateGoodsDialog();
                break;
            case 'del':
                // 点击删除按钮，将对应选中的记录删除
                deleteGoodsDialog(checkStatus.data);
        };
    });

    /**
     * 打开添加营销机会的对话框
     */
    function openAddOrUpdateGoodsDialog() {
        var title = "<h2>商品入库管理 - 记录添加</h2>";
        var url = ctx + "/customer_serve1/addOrUpdateGoodsPage";
        layui.layer.open({
            title: title,
            type: 2, //iframe
            content: url,
            area: ["500px", "620px"],
            maxmin: true
        });
    }


    /**
     * 删除入库记录
     * @param data
     */
    function deleteGoodsDialog(data) {
        // 判断用户是否选择了要删除的记录
        if (data.length == 0) {
            layer.msg("请选择要删除的记录！");
            return;
        }
        // 询问用户是否确认删除
        layer.confirm("您确定要删除选中的记录吗？", {
            btn: ["确认", "取消"],
        }, function (index) {
            // 关闭确认框
            layer.close(index);
            // ids=1&ids=2&ids=3
            var ids = "ids=";
            // 遍历获取对应的id
            for (var i = 0; i < data.length; i++) {
                if (i < data.length - 1) {
                    ids = ids + data[i].id + "&ids=";
                } else {
                    ids = ids + data[i].id;
                }
            }
            // 发送ajax请求，删除记录
            $.ajax({
                type: "post",
                url: ctx + "/customer_serve1/delete",
                data: ids, // 参数传递的是数组
                dataType: "json",
                success: function (result) {
                    if (result.code == 200) {
                        // 加载表格
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }


    /**
     * 表格行 监听事件
     * saleChances为table标签的lay-filter 属性值
     */
    table.on('tool(addGoods)', function(obj){
        var data = obj.data; // 获得当前行数据
        var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        // 判断事件类型
        if(layEvent === 'edit'){ // 编辑操作
            // 获取当前要修改的行的id
            var ID = data.id;
            // 点击表格行的编辑按钮，打开更新营销机会的对话框
            openAddOrUpdateSaleChanceDialog2(ID);
        } else if (layEvent == "del") { // 删除操作
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"入库记录管理"},
                function (index) {
                    // 关闭窗口
                    layer.close(index);
                    // 发送ajax请求，删除记录
                    $.ajax({
                        type:"post",
                        url: ctx + "/customer_serve1/delete",
                        data:{
                            ids:data.id
                        },
                        dataType:"json",
                        success:function (result) {
                            if (result.code == 200) {
                                // 加载表格
                                tableIns.reload();
                            } else {
                                layer.msg(result.msg, {icon: 5});
                            }
                        }
                    });
                });
        }
    });

    /**
     * 打开添加营销机会的对话框
     */
    function openAddOrUpdateSaleChanceDialog2(ID) {
        var title = "<h2>商品入库管理 - 记录添加</h2>";
        var url = ctx + "/customer_serve1/addOrUpdateGoodsPage";
        // 通过id判断是添加操作还是修改操作
        if (ID) {
            // 如果id不为空，则为修改操作
            title = "<h2>商品入库管理 - 记录更新</h2>";
            url = url + "?id=" + ID;
        }
        layui.layer.open({
            title: title,
            type: 2,
            content: url,
            area: ["500px", "620px"],
            maxmin: true
        });
    }
});
