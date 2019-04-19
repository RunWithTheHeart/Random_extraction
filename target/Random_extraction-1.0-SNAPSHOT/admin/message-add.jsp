<%@ page import="com.wyy.bean.Admin" %>
<!--_meta 作为公共模版分离出去-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="favicon.ico" >
<link rel="Shortcut Icon" href="favicon.ico" />
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
<!--/meta 作为公共模版分离出去-->

<title>添加评标项目</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<%
	Admin admin=(Admin)session.getAttribute("admin");
%>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add" enctype="multipart/form-data">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>评标项目标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="title" name="title">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>评委抽取时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" id="extraction_time" name="extraction_time" onfocus="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="datemax" class="input-text Wdate" style="width:200px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>项目内容：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<script id="editor" name="contents" type="text/plain" style="width:100%;height:400px;"></script></div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">发布人：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden" class="input-text" value="<%=admin.getUsername() %>" name="username"> <%=admin.getUsername() %>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="message_save_submit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存并提交</button>
				<button onClick="message_save();" class="btn btn-secondary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存草稿</button>
				<button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../static/js/jquery.min.js"></script>
<script type="text/javascript" src="../static/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../static/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../static/js/jquery.validate.js"></script>
<script type="text/javascript" src="../static/js/validate-methods.js"></script>
<script type="text/javascript" src="../static/js/messages_zh.js"></script>
<!--<script type="text/javascript" src="static/webuploader/0.1.5/webuploader.min.js"></script>-->
<script type="text/javascript" src="../static/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="../static/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="../static/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	//表单验证
	$("#form-article-add").validate({
		rules:{
			title:{
				required:true,
			},
            extraction_time:{
                required:true,
            },
			username:{
				required:true,
			},

		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			//$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
	
	/*文本编辑*/
	var ue = UE.getEditor('editor');
	
});
/*保存并提交*/
function message_save_submit(){
	layer.confirm('确认要提交吗？',function(index){
       //var data = UE.getEditor('editor').getContent();
		$.ajax({
			type: 'POST',
            data: $("#form-article-add").serialize(),
			//{"data": data},
			url: '/submit_project',
			dataType: 'json',
			success: function(data ){
				layer.msg(data.message,{icon:1,time:10000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
/*保存项目*/
function message_save(){
	$.ajax({
		type: 'POST',
		url: '/save_project',
        data: $("#form-article-add").serialize(),
		dataType: 'json',
		success: function(data){
            layer.msg(data.message,{icon:1,time:1000});
		},
		error:function(data) {
            layer.msg("保存失败了",{icon:1,time:1000});
		},
	});		
}
/*加载保存的项目*/
$(document).ready(function() {
    $.ajax({
        type: 'POST',
        url: '/select_save_project',
        /*dataType: 'json',*/
        success: function (data) {
            //alert("hh")
            //var project = JSON.parse(data);
			if(data != null){
            document.getElementById("title").value=data.title;
            document.getElementById("editor").value=data.contents;
            document.getElementById("extraction_time").value= data.strdate;
			}
        },
        error: function (data) {
            console.log(data.msg);
        },
    });
});


</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>