<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="../static/js/html5shiv.js"></script>
<script type="text/javascript" src="../static/js/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../static/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="../static/js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>项目发布列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 发布信息 <span class="c-gray en">&gt;</span> 发布信息表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<span class="select-box inline">
			<input type="text" name="username" id="username" placeholder="发布人" style="width:250px" class="input-text">
			<button name="search" id="btn_search" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		</span>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
		<a href="javascript:;" id="deletes" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span>
		<!--<span class="r">共有数据：<strong>54</strong> 条</span> -->
	</div>
	<div class="mt-20">
		<table id="table_show"  class="table table-border table-bordered table-bg table-hover  table-responsive">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="60">项目ID</th>
					<th width="200">项目标题</th>
					<th width="40">发布人</th>
					<th width="100">发布时间</th>
					<th width="100">抽取时间</th>
					<th width="70">操作</th>

				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../static/js/jquery.min.js"></script>
<script type="text/javascript" src="../static/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../static/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../static/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../static/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
	var table;
$(function(){
     table = $('#table_show').DataTable({
        "paging":true,
        "info" : true,
        "serverSide": true,
        "searching": true,
        "ordering": false,
        "colReorder": true,
		"deferRender":true,
        "aaSorting": [[ 1, "asc" ]],//默认第几个排序
        "ajax":{
            "url":"/show_project",
            "type":"post"
        },
        columns: [
            {
                data:function(row,type,val,meta){
                    return '<input id="' + row.id + '"  name="checkitem" value="'+row.id+'" type="checkbox" style="position: relative;left: 17px" />';
                }
            },

            { data: 'id' },
            { data: 'title' },
			{ data: 'username' },
            { data: 'str_issuing_time' },
            { data: 'str_extraction_time' },

            {
                data:function(row,type,val,meta){
                    return '<a title="删除" href="javascript:;" onclick="project_del(this,' + row.id + ')" style="position: relative;right: -40px" ><i class="Hui-iconfont">&#xe6e2;</i></a>'+
                    		'<a title="查看详情" href="javascript:;" onclick="project_contents(this,' + row.id + ')" style="position: relative;right: -52px"><i class="Hui-iconfont">&#xe695;</i></a>';//<i class="fa fa-trash-o"><i>删除class="btn btn-sm btn-danger"
                }
            },
        ],

    });

});
/*批量删除数据*/
$('#deletes').click(function() {
    if ($("input[name='checkitem']:checked")[0] == null) {
        layer.msg('请至少选择一件商品!', {icon: 5, time: 1000});
        return;
    }
    layer.confirm('您确定要彻底删除吗?', {btn: ['确定', '取消'], title: "提示"}, function () {
        var ids = new Array();
        $("input[name='checkitem']:checked").each(function () {
            ids.push($(this).val());
        });


        $.ajax({
            url: "/delete_projects",
            data: "ids=" + ids,
            type: "post",
            dataType: "json",
            success: function (data) {
                layer.msg(data.message, {icon: 1,time:1000});
                table.ajax.reload();
            }
        });
    })
});



/*项目-删除*/
function project_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/delete_project',
			data:{"id":id},
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg(data.message,{icon:1,time:1000});

			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
/*查看项目内容*/
function project_contents(obj,id){
    layer.confirm('确认要删除吗？',function(index){
	$.ajax({
		type: 'POST',
		url: 'delete_project',
		dataType: 'json',
		success: function(data){
			$(obj).parents("tr").remove();
			layer.msg('已删除!',{icon:1,time:1000});
		},
		error:function(data) {
			console.log(data.msg);
		},
	});
    });
}



//条件查询
$('#btn_search').click(function() {
    var username = $("#username").val();
    $('#table_show').DataTable({
        "destroy": true,
        "paging": true,
        "info": true,
        "serverSide": true,
        "searching": true,
        "ordering": false,
        "colReorder": true,
        "deferRender": true,
        "aaSorting": [[1, "asc"]],//默认第几个排序
        "ajax": {
            "url": "/username_search_project",
            "data": {"username": username},
            "type": "post"
        },
        columns: [
            {
                data: function (row, type, val, meta) {
                    return '<input id="' + row.id + '"  name="checkitem" value="' + row.id + '" type="checkbox" style="position: relative;left: 17px" />';
                }
            },

            {data: 'id'},
            {data: 'title'},
            {data: 'username'},
            {data: 'str_issuing_time'},
            {data: 'str_extraction_time'},

            {
                data: function (row, type, val, meta) {
                    return '<a title="删除" href="javascript:;" onclick="project_del(this,' + row.id + ')" style="position: relative;right: -40px" ><i class="Hui-iconfont">&#xe6e2;</i></a>' +
                        '<a title="查看详情" href="javascript:;" onclick="project_contents(this,' + row.id + ')" style="position: relative;right: -52px"><i class="Hui-iconfont">&#xe695;</i></a>';//<i class="fa fa-trash-o"><i>删除class="btn btn-sm btn-danger"
                }
            },
        ],

    });
});

</script> 
</body>
</html>