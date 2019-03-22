<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<title>评委随机抽取系统</title>
		<link href="../static/css/bootstrap.min.css" rel="stylesheet">
		<link href="../static/css/index.css" rel="stylesheet">
	</head>

	<body>
		<!--小屏幕导航按钮和logo -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<!--小屏幕导航按钮和logo-->
				<div class="navbar-header">
					<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a href="index.jsp" class="navbar-brand">评委随机抽取系统</a>
				</div>
				<!--小屏幕导航按钮和logo-->
				<!--导航-->
				
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><marquee scrollamount="6" behavior="scroll"><!--<%= session.getAttribute( "username" ) %>-->王洋洋，欢迎登录！</marquee></li>
						<li><a href="#home">首页</a></li>
						<li><a href="#pre-selection_list">备选名单</a></li>
						<li><a href="#winning_list">抽中名单</a></li>
						<li><a href="#login_out.jsp">注销</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<!--导航-->
		
		
		
		<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
		<script src="/static/js/bootstrap.min.js"></script>
	</body>

</html>