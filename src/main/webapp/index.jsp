<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>在线医疗管理系统</title>
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
		</style>
		<link rel="icon" href="Images/logo_favicon.ico" type="image/x-icon" />
	</head>
	<frameset rows="80,*" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
			<frame src="left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="hello.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
		</frameset>
	</frameset>
</html>