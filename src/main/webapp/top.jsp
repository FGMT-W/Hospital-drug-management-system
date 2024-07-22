<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
    	<style type="text/css">
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			.c1{
				color: white;
				font-size: 20px;
				font-weight: bold;
				padding-left: 20px;
				line-height: 50px;
				width: 100%;
				height: 50px;
				background-color:darkslateblue
			}
			.c1 span{
				color: white;
				font-size: 14px;
				font-weight: bold;
				float: right;
				padding-right: 50px;
			}
			.c1 span a{
				color: aliceblue;
				text-decoration: none;
			}
			.c2{
				width: 100%;
				height: 30px;
				background-color:#eaeaea;
			}
		</style>
	</head>
	<body>
		<div class="c1">
			优秀就业-在线医疗管理系统
			<span id="">
				<c:if test="${sessionScope.users == null }">
              		<a href="login.jsp" target="_parent">登录</a>
             	 	<a href="regist.jsp" target="_parent">注册</a>
           		</c:if>
          		 <c:if test="${sessionScope.users != null }">
           			<span style="color:white;font-size: 12px;float: left;padding-right: 10px;">欢迎${users.name } </span>
           		</c:if>
					<a href="User?method=logout" target="_parent">【退出】</a>
			</span>
		</div>
		<div class="c2"></div>
	</body>
</html>
