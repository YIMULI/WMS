layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //角色列表展示
    var  tableIns = table.render({
        elem: '#roleList',
        url : ctx+'/goods/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "roleListTable",
        cols : [[
            // {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'goods_name', title: '商品名称', minWidth:50, align:"center"},
            {field: 'goods_address', title: '商品来源', minWidth:100, align:'center'},
            {field: 'goods_num', title: '商品数量', align:'center',minWidth:150},
            {field: 'goods_id', title: '商品编码', align:'center',minWidth:150},
            {field: 'category_name', title: '商品类型', align:'center',minWidth:150},
            {field: 'goods_price', title: '商品价格', align:'center',minWidth:150},
            // {title: '操作', minWidth:150, templet:'#roleListBar',fixed:"right",align:"center"}
        ]]
    });
    // 多条件搜索
    $(".search_btn").on("click",function(){
        console.log()
        tableIns.reload({
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                categoryName: $("input[name='categoryName']").val()
            }
        })
    });

});