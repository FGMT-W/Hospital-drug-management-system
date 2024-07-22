<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	pageContext.setAttribute("path", basePath);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			#left{
				width: 200px;
				height: 768px;
				background-color: #eaeaea;
				padding-left: 10px;
			}
			#left ul{
				padding-top: 10px;
				padding-left: 20px;
			}
			
			#left ul li{
				list-style: none;
				line-height: 25px;
			}
			
			#left ul li a{
				text-decoration: none;
				font-size: 14px;
				
			}
		</style>
	</head>
	<body>
		<div id="left">
			<h3>业务办理</h3>
			<ul class="menuson">
        		<li><a href="${path }Doctor?method=findDoctorsByPage" target="rightFrame">门诊医生管理</a></li>
				<li><a href="${path }Medicine?method=findMedicineByPage" target="rightFrame">药品管理</a></li>
				<li><a href="${path }Register?method=findRegisterByPage" target="rightFrame">挂号信息管理</a></li>
				<li><a href="${path }register/add.jsp" target="rightFrame">住院办理</a></li>
				<li><a href="${path }Trolley?method=findTrolleyList" target="rightFrame">检查收费项管理</a></li>
				<li><a href="${path }Trolley?method=findMedicineByPage" target="rightFrame">检查收费项目登记</a></li>
			</ul>
		</div>
	</body>
</html>
