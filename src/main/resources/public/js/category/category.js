layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;
    treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'categoryId',
        treePidName: 'parentId',
        elem: '#munu-table',
        url: ctx+'/category/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'categoryName', minWidth: 100, title: '名称'},



            {
                field: 'grade', width: 80, align: 'center', templet: function(d) {
                    if (d.grade == 0) {
                        return '<span class="layui-badge layui-bg-blue">目录</span>';
                    }
                    if(d.grade==1){
                        return '<span class="layui-badge-rim">菜单</span>';
                    }
                    if (d.grade == 2) {
                        return '<span class="layui-badge layui-bg-gray">按钮</span>';
                    }
                },title: '类型'

            },
            {templet: '#auth-state', width: 180, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });
    //监听工具条
    table.on('tool(munu-table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'add') {
            if(data.grade==2){
                layer.msg("暂不支持四级菜单添加操作!");
                return;
            }
            openAddModuleDialog(data.grade+1,data.categoryId);
        } else if (layEvent === 'edit') {
// 记录修改
            openUpdateModuleDialog(data.categoryId);
        }
    });
    table.on('toolbar(munu-table)', function(obj){
        switch(obj.event){
            case "expand":
                treeTable.expandAll('#munu-table');
                break;
            case "fold":
                treeTable.foldAll('#munu-table');
                break;
            case "add":
                openAddModuleDialog(0,-1);
                break;
        };
    });
    // 打开添加菜单对话框
    function openAddModuleDialog(grade,parentId){
        var grade=grade;
        var url = ctx+"/category/addModulePage?grade="+grade+"&parentId="+parentId;
        var title="菜单添加";
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","250px"],
            maxmin:true,
            content : url
        });
    }
    function openUpdateModuleDialog(categoryId){
        console.log(categoryId)
        var url = ctx+"/category/updateModulePage?categoryId="+categoryId;
        var title="菜单更新";
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","200px"],
            maxmin:true,
            content : url
        });
    }

        //监听工具条
    table.on('tool(munu-table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'add') {
                if(data.grade==2){
                layer.msg("暂不支持四级菜单添加操作!");
                return;
            }
            openAddModuleDialog(data.grade+1,data.categoryId);
        } else if (layEvent === 'edit') {
        // 记录修改
            console.log(data)
            openUpdateModuleDialog(data.categoryId);
        } else if (layEvent === 'del') {
            layer.confirm('确定删除当前菜单？', {icon: 3, title: "菜单管理"}, function (index) {
                $.post(ctx+"/category/delete",{"id":data.categoryId},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        window.location.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            })
        }
    });

});
