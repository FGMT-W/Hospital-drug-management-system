<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<title>Insert title here</title>

<style type="text/css">
	.welinfo{
		padding-left:40px;
	}
</style>

</head>
<body>
<br><br>
	<div class="welinfo">
    <span><img src="Images/sun.png" alt="天气" /></span>
    <b>${sessionScope.users.name } 您好，欢迎登录在线医疗管理系统</b>
    <a href="Update_user.jsp" style="padding-left:8px" target="_parent">帐号设置</a>
    </div>
 <br>
    <div class="welinfo">
    <span><img src="Images/time.png" alt="时间" /></span>
    <i>您上次登录的时间：2021-6-20 15:22</i> （不是您登录的？<a href="User?method=logout" target="_parent">请点这里</a>）
    </div>
</body>
</html>