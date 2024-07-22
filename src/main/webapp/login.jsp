<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <title>优就业-线医疗管理系统</title>
	<meta charset="UTF-8">
	<link rel="icon" href="Images/logo_favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.validate.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/jquery-3.4.1.js"></script>
    <style type="text/css">
        body {
            padding-top: 140px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            font-family: "微软雅黑";
            background: url("Images/yy.jpg");
            background-size: 100%;
            background-repeat: no-repeat;
        }

        .form-signin {
            max-width: 400px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            background: rgba(255,255,255,0.5);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
            font-size: 24px;
            margin-left: 90px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
		/* #msg{
			font-size: 14px;
			color:red;
			margin-left: 40px;
		} */
		.input-block-level{
			width: 300px;
			margin-left: 40px;
		}
		.input-medium{
			margin-left: 40px;
		}
    	#img_code{
    	cursor: pointer;
    	}
		.error{
			color: red;
			font-size: 12px;
		}
		
    </style>  
</head>
<body>
<div class="container">
	
    <form class="form-signin" method="post" action="User">
    	<input type="hidden" name="method" value="checkLogin">
        <h2 class="form-signin-heading" >在线医疗管理系统</h2>
        <span id="message" class="message">${msg }</span><br>
        <input id="username" name="username" class="input-block-level" type="text" placeholder="输入账号">
        <input id="password" name="password" class="input-block-level" type="password" placeholder="输入密码">
        <input id="vercode" name="vercode" class="input-block-level" type="text" placeholder="输入验证码" style="width: 160px; margin-left: 40px;display: inline-block;">
        <!-- 新增加 -->
        <img id="img_code" align="middle" src="authImage" style="width: 115px; height: 35px; margin-left: 20px;margin-bottom: 15px; display:inline-block;"/>
        <p style="text-align: center;">
        <input id="login" type="button" value="登录" name="login" class="btn btn-large btn-primary" style="width: 150px;"/>
        <a href="regist.jsp">请先注册</a>
        </p>
    </form>
</div>	
</body>
<script type="text/javascript">
	$("#img_code").click(function(){
		$(this).prop("src","authImage?date="+new Date());
	});

	$("#login").click(function(){
		//清空错误消息
		$("#message").text("");
		//获取账号、密码、验证码
		var username = $("#username").val();
		var password = $("#password").val();
		var vercode = $("#vercode").val();
		//非空验证
		if($.trim(username)==""){
			$("#message").text("账号不能为空").css("color","red");
			return;
		}
		if($.trim(password)==""){
			$("#message").text("密码不能为空").css("color","red");
			return;
		}
		if($.trim(vercode)==""){
			$("#message").text("验证码不能为空").css("color","red");
			return;
		}
		//验证验证码是否正确
		 $.ajax({
			url:"User",
			data:{"vercode":vercode,"method":"checkeVercode"},
			type:"post",
			dataType:"json",
			success:function(obj){
				if(obj==true){
					//用户名和密码的验证
					window.location="User?method=checkLogin&username="+username+"&password="+password;
				}else{
					$("#message").text("验证码不正确").css("color","red");
					//刷新验证码
					$("#img_code").prop("src","authImage?date="+new Date());
				}
			}
		}) 
	});
	
</script>
</html>