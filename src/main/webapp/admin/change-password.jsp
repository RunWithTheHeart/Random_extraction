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
<link rel="stylesheet" type="text/css" href="../static/Hui-iconfont/1.0.8/iconfont.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="../static/js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>修改密码 - 会员管理 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<%
	Admin admin=(Admin)session.getAttribute("admin");
%>
<article class="page-container">
	<form  id="change_password">
		<br>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账&emsp;户：</label>
			<div class="formControls col-xs-8 col-sm-9"> <%= admin.getUsername()%> </div>
			<input type="text" class="input-text" hidden="hidden" name="username" value="<%= admin.getUsername()%>" >
			<input type="text" class="input-text" hidden="hidden" name="type" value="<%= admin.getType()%>"  >
		</div>
		<br>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" autocomplete="off" placeholder="请输入密码" name="password" id="password">
			</div>
		</div>
		<br>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" autocomplete="off" placeholder="请再次输入密码" name="password2" id="password2">
			</div>
		</div>
		<br>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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

<script type="text/javascript" src="../static/js/jquery.validate.js"></script>
<script type="text/javascript" src="../static/js/validate-methods.js"></script>
<script type="text/javascript" src="../static/js/messages_zh.js"></script>

<script type="text/javascript">

$(function(){
	$("#change_password").validate({
		rules:{
			password:{
				required:true,
				minlength:6,
				maxlength:16
			},
			password2:{
				required:true,
				minlength:6,
				maxlength:16,
				equalTo: "#password"
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});
/*判断密码是否修改成功*/
$("#change_password").submit(function() {
    //parent.layer.close(index); 再执行关闭
    $.ajax({
        async: false,
        type: 'post',
        url: '/change_password',
        data: $("#change_password").serialize(),
        dataType: 'text',
        success: function (data) {
            if (data == 'true') {
                alert("密码重置成功！");
            } else {
                alert("密码重置失败！");
            }
        },
        error: function (data) {
            alert("密码重置失败！");
        }
    })
})
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>