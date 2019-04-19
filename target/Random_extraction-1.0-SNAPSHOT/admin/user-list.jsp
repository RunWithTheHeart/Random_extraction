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
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
  <div class="text-c"> 
    <input type="text" class="input-text" style="width:250px" placeholder="输入用户名" name="username"><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜用户</button>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="icon-trash"></i> 批量删除</a>
    <a href="javascript:;" onclick="user_add('550','','添加用户','user-add.jsp')" class="btn btn-primary radius"><i class="icon-plus"></i> 添加用户</a></span>
  </div>
  <table id="table_show" class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th><input type="checkbox"></th>
        <th>ID</th>
        <th>用户名</th>
        <th>邮箱</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody></tbody>
  </table>
  <div id="pageNav" class="pageNav"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../static/js/jquery.min.js"></script>
<script type="text/javascript" src="../static/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../static/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../static/js/jquery.dataTables.min.js"></script>
<!--<script type="text/javascript" src="../static/laypage/1.2/laypage.js"></script>-->
<script type="text/javascript">

  $(function(){
      $('#table_show').DataTable({
          /* "language": {url:"zh_CN.txt"},*/
          "paging":true,
          "info" : true,
          "serverSide": true,
          "processing":false,
          "searching": true,
          "ordering": true,
          "aaSorting": [[ 1, "asc" ]],//默认第几个排序
          "bStateSave": true,//状态保存
          "bAutoWidth": true,//自动宽度
          "aoColumnDefs": [
              //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
              {"orderable":false,"aTargets":[0,4]}// 不参与排序的列
          ],

          "ajax":{
              "url":"/show_user",
              "type":"post"
          },
          columns: [
              {
                  data:function(row,type,val,meta){
                      return '<input id="' + row.id + '" type="checkbox" vertical-align:middle; />';
                  }
              },

              { data: 'id' },
              { data: 'username' },
             // { data: 'password' },
              { data: 'email' },
              //{ data: 'type' },

              {
                  data:function(row,type,val,meta){
                      return '<a title="删除" href="javascript:;" onclick="user_del(this,' + row.id + ')"  style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>';//<i class="fa fa-trash-o"><i>删除class="btn btn-sm btn-danger"
                  }
              },
          ],
          /*
          "drawCallback":function (settings) {
              App.init();
          }*/
      });

    });

    /*用户-删除*/
function user_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
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


</script>
</body>
</html>
