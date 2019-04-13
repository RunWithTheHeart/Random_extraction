<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<title>评委随机抽取系统-登录</title>
		<link href="../static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="../static/css/login.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<div class="headdd col-md-12">
    		<h2>评委随机抽取系统</h2>
    	</div>
    	
    	<div class="container">
    	<!--轮播图-->
    	<div class="carousel_map col-md-8">
    		<div id="myCarousel" class="carousel slide">
			<!-- 轮播（Carousel）指标 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>   
			<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner ">
				<div class="item active">
					<img src="../static/images/carousel01.jpg" alt="First slide">
				</div>
				<div class="item">
					<img src="../static/images/carousel02.jpg" alt="Second slide">
				</div>
				<div class="item">
					<img src="../static/images/carousel03.jpg" alt="Third slide">
				</div>
			</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				</a>                       
				<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				</a>
			</div> 
    	</div>
    	<!--登录表单-->
    	<div class="login col-md-4">
    		<h4 align="center">账号密码登录</h4><hr />
    		<form action="userLogin" method="post">
    			<div class="input-group">
					<span class="input-group-addon" id="sizing-addon2"><span class="glyphicon glyphicon-user"></span></span></span>
				  <input type="text"  id="username" name="username" class="form-control" placeholder="请输入用户名" aria-describedby="sizing-addon2">
    			</div>
				<br /><br />
				<div class="input-group">
					<span class="input-group-addon" id="sizing-addon3"><span class="glyphicon glyphicon-lock" ></span></span>
				  <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" aria-describedby="sizing-addon3">
				</div>
				<br />
				<div style="color: red">&emsp;${errmsg}</div>
				<br />
				<div class="input-group">
					 &nbsp;&nbsp;&nbsp;账号类型：
					 <input type="radio" id="rdoAdmin" name="type" value="1">
					 <label for="rdoAdmin">管理员</label> &nbsp;&nbsp;&nbsp;
					 <input type="radio" id="rdoJudge" name="type" value="2"  checked>
					 <label for="rdoJudge">普通用户</label>
				</div>
                  
				<div>
					<input class="btn" type="submit" onclick="return check();"  value="登录"><br />
					<a class="resetPwd" href="a" >忘记密码？</a>
				</div>
    		</form>
    	</div>
    	</div>
    	<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
		<script src="/static/js/bootstrap.min.js"></script>
		<script src="/static/js/login.js"></script>
 	</body>
 	
</html>