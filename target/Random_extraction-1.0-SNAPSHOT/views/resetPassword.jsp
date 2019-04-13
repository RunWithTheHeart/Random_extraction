<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
	<title>评委随机抽取系统-忘记密码</title>
	<link href="../static/css/bootstrap.min.css" rel="stylesheet">
	<link href="../static/css/login.css" rel="stylesheet">
</head>
<body>
<div class="headdd col-md-12">
	<h2>评委随机抽取系统</h2>
</div>
<div class="col-md-4"></div>

<div class="login col-md-4" id="reset-step1">
	<h4 align="center"><b>重置密码</b></h4><hr />
	<form action="resetPwd" method="post">
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon"><span class="glyphicon glyphicon-envelope"></span></span>
			<input type="text" id="email" name="email" class="form-control" placeholder="请输入邮箱"  aria-describedby="sizing-addon">
		</div>
        <!-- 显示验证码错误信息-->
        <span id="checkEmail" style="color: red"></span>
		<br /><br />
		<div class="input-group">
			<div>
				<input type="text" id="code" name="code" class="form-control" placeholder="请输入邮箱验证码" style="width:50%;"/>
				&emsp;
				<input id="sendCode" type="button" onclick="sendvCode(this)" value="点击发送验证码" style="height: 34px"></input>
			</div>
			<!-- 显示验证码错误信息-->
			<span id="checkmsg" style="color: red"></span>
		</div>

		<%--<span style="color: red;">${errMsg}</span>--%>
		<br /><br />
		<div>
			&nbsp;&nbsp;&nbsp;账号类型：
			<input type="radio" id="rdoAdmin" name="type" value="1">
			<label for="rdoAdmin">管理员</label> &nbsp;&nbsp;&nbsp;
			<input type="radio" id="rdoJudge" name="type" value="2"  checked>
			<label for="rdoJudge">普通用户</label>
		</div>

		<div>
			<input class="btn" type="button" onclick="show2()" value="下一步"> onclick="return checkCode();"<br />
			<a class="resetPwd" href="login" >返回登录</a>
		</div>
	<%--</form>--%>
</div>

<div class="login col-md-4"id="reset-step2" style="visibility: hidden">
	<h4 align="center"><b>重置密码</b></h4><hr />
	<%--<form action="resetPassword" method="post">--%>
		<%--<div>
			<input type="hidden" name="email" value="${email}"/>
			<input type="hidden" name="type" value="{type}"/>
		</div>--%>
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2"><span class=" glyphicon glyphicon-lock"></span></span>
			<input type="password" id="password" name="password" class="form-control" placeholder="请输入新密码" aria-describedby="sizing-addon2" onblur="checkPwd()">
		</div>
		<span id="errMsg"></span>
		<br /><br /><br />
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon3"><span class=" glyphicon glyphicon-lock"></span></span>
			<input type="password" id="password2" name="password2" class="form-control" placeholder="请再次输入新密码" aria-describedby="sizing-addon3" onblur="checkPwd2()">
		</div>
		<span id="errMsg2">以字母开头，长度在6~18之间，只能包含字母、数字和下划线</span>
		<br /><br />
		<div>
			<input class="btn" type="button" onclick="return validate()"  value="完成"><br />
			<a class="resetPwd" href="login" >返回登录</a>

		</div>
	</form>
</div>

<script type="text/javascript">
    var clock = '';
    var nums = 60;
    var btn;

    //显示不同的div
    function show2(){
        $("#reset-step1").hide();
        $("#reset-step2").css("visibility","visible");
        /*$("#reset-step2").toggle();*/
    }
    //请求发送验证码
    function sendvCode(thisBtn)
    {

        /*判断邮箱格式是否正确*/
        if (jemail()==false){
            return false;
		}

        checkEmail();
        /*判断邮箱是否存在*/
		/*if(checkEmail() == false){
            return false;
        }*/

        /*请求发送验证码*/
        $.ajax({
            type: "POST",
            url: "/sendCode",
            data: {email:$("#email").val(),type:$("input[name='type']:checked").val()},
            success: function(msg){
               /* alert( "Data Saved: " + msg );*/
            }
        });
        /*计时*/
        countDown(thisBtn);
    }

    function countDown(thisBtn)
    {
        btn = thisBtn;
        btn.disabled = true; //将按钮置为不可点击
        btn.value = nums+'秒后可重新获取';
        clock = setInterval(doLoop, 1000); //一秒执行一次
    }

    function doLoop()
    {
        nums--;
        if(nums > 0){
            btn.value = nums+'秒后可重新获取';
        }else{
            clearInterval(clock); //清除js定时器
            btn.disabled = false;
            btn.value = '点击发送验证码';
            nums = 60; //重置时间
        }
    }
    //判断邮箱是否格式正确
    function jemail(){
		if($("#email").val()=="")
		{
			alert("邮箱不能为空");
			return false;
		}
		var email=$("#email").val();
		if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
		{
			alert("格式不正确！请重新输入");
            return false;
			/*$("#email").focus();*/
		}
	}

    /*判断邮箱是否存在*/
    function checkEmail(){
        $.ajax({
            url:'/checkEmail',
            data: {email:$("#email").val(),type:$("input[name='type']:checked").val()},
            type:'post',
            dataType:'text',
            success:function(data){
                if(data=='false'){
                    document.getElementById("checkEmail").innerHTML="邮箱不存在";
                  /* alert("邮箱不存在");*/
                    return false;
                }else{
                    document.getElementById("checkEmail").innerHTML="";
                }
            }

        })
    }
    /*判断邮箱验证码是否正确*/
    function checkCode(){
		$.ajax({
			url:'/checkCode',
			data:{code:$("#code").val()},
			type:'post',
			dataType:'text',
			success:function(data){
				if(data=='true'){
                    document.getElementById("checkmsg").innerHTML="";
                    show2();
					return true;
				}else{
				    document.getElementById("checkmsg").innerHTML="验证码错误";
                    return false;
				}
			}

		})
	}
    /*判断密码是否重置成功*/
    function resetPwd(){
        $.ajax({
            url:'/resetPwd',
            data:{email:$("#email").val(),type:$("input[name='type']:checked").val(),password:$("#password").val()},
            type:'post',
            dataType:'text',
            success:function(data){
                if(data=='true'){
                   alert("密码重置成功！点击确认返回登录");
                   window.location.href="/login";//需要跳转的地址
                }else{
                    alert("密码重置失败！请重新操作");
                    window.location.href="/a";//需要跳转的地址
                }
            },
			error:function (data) {
				alert("密码重置失败！请重新操作");
                window.location.href="/a";//需要跳转的地址
            }

        })
    }

	/*判断密码是否符合要求*/
    function checkPwd(){
        var pwd = $("#password").val();
        if(pwd.match(/^[a-zA-Z]\w{5,17}$/)){
            $("#errMsg").html("");
        }else{
            $("#errMsg").html("密码不符合要求");
            $("#errMsg").css("color","red")
            return false;
        }

    }

    function checkPwd2(){
        var pwd2 = $("#password2").val();
		if(pwd2.match(/^[a-zA-Z]\w{5,17}$/)){
            $("#errMsg2").html("");  //可以设置成图片
        }else{
            $("#errMsg2").html("密码不符合要求");
            $("#errMsg2").css("color","red")
            return false;
        }

    }
    /*判断两次密是否一致*/
    function validate() {
        var pwd = $("#password").val();
        var pwd2 = $("#password2").val();
        <!-- 对比两次输入的密码 -->
        if(pwd == pwd2) {
            resetPwd();
           return true;
        } else {
            $("#errMsg2").html("两次密码不相同");
            return false;
        }

    }

</script>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/login.js"></script>
</body>
</html>

