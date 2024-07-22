<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
	//获取tomcat 协议+地址+端口号
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/hospitalpic/";	
%>
<!DOCTYPE html>
<html>
<base href="<%=this.getServletContext().getContextPath() %>/Medicine/">
<head>
    <title>药品查询</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
	 $(function () {
		$('#newNav').click(function(){
				window.location.href="${path}medicine/add.jsp";
		 });
    });
	
    	function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
			if(ch.checked){
				for(var i=0;i<alls.length;i++){
					alls[i].checked=true;	
				}	
			}else{
				for(var i=0;i<alls.length;i++){
					alls[i].checked=false;	
				}	
			}
		}
    	
    	//批量删除
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids="";
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids+="'"+alls[i].value+"'"+",";
				}		
			}
			if(ids!=""){
				ids=ids.substring(0,ids.length-1);
				if(confirm("确认删除吗?")){
					window.location.href="${path}Medicine?method=deleteMedicine&ids="+ids;
				}
			}else{
				alert("请选中要删除的项");
			}
		}
		
    </script>
</head>
<body>

<form action="${path }Medicine?method=findMedicineByPage" method="post" class="definewidth m20">
	<table class="table table-bordered table-hover definewidth m10">
	    <tr>
	        <td width="10%" class="tableleft">药品名称：</td>
	        <td><input type="text" name="name" value=""/></td>
			
	        <td width="10%" class="tableleft">药品类型：</td>
	        <td>
		        <select name="type">
		        	<option value="0">==请选择==</option>
		       		<option value="1">处方药</option>
			        <option value="2">中药</option>
			        <option value="3">西药</option>
		        </select>
	        </td>
	    </tr>
	    <tr>
			  <td colspan="4">
				<center>
					<input id="find" name="find" type="submit" class="btn btn-primary" value="查询"/>
			  		<input name="ret" type="reset" class="btn btn-primary" value="清空"/> 
				</center>
	        </td>
	    </tr>
	</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>药品编号</th>
        <th>药品名称</th>
        <th>图片</th>
        <th>药品类型</th>
        <th>简单描述</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${medicineList }" var="medicine">
		     <tr >
	         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${medicine.mid }"></td>
	            <td style="vertical-align:middle;">${medicine.mid }</td>
	            <td style="vertical-align:middle;">${medicine.name }</td>
	            <td style="vertical-align:middle;">
	            	<img  width="80px" height="50px" src="<%=imgPath %>${medicine.picture }">
	            </td>
	            <td style="vertical-align:middle;">
	            	<c:if test="${medicine.type == 1}">处方药</c:if>
	            	<c:if test="${medicine.type == 2}">中药</c:if>
	            	<c:if test="${medicine.type == 3}">西药</c:if>
	            </td>
	            <td style="vertical-align:middle;">${medicine.descs }</td>
	            <td style="vertical-align:middle;">
						<a href="${path }Medicine?method=toUpdate&mid=${medicine.mid }">更改</a>&nbsp;&nbsp;&nbsp;
						<a href="${path }Medicine?method=findMedicineByMid&mid=${medicine.mid }">详情>>></a>
				</td>
	        </tr>
        </c:forEach>
     </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
  			<div class="inline pull-right page">
	          <a href="${path }Medicine?method=findMedicineByPage&currentPage=1&name=${medicine.name}&type=${medicine.type}" >首页</a> 
	          <a href="${path }Medicine?method=findMedicineByPage&currentPage=${pageTool.prePage }&name=${medicine.name}&type=${medicine.type}">上一页</a>     
	          <a href="${path }Medicine?method=findMedicineByPage&currentPage=${pageTool.nextPage }&name=${medicine.name}&type=${medicine.type}" >下一页</a> 
	          <a href="${path }Medicine?method=findMedicineByPage&currentPage=${pageTool.totalPages }&name=${medicine.name}&type=${medicine.type}" >尾页</a>
			  &nbsp;&nbsp;&nbsp;
			     共<span class='current'>${pageTool.totalCount }</span>条记录
			     <span class='current'> ${pageTool.currentPage }/${pageTool.totalPages } </span>页
		  </div>
		 <div>
			<button type="button" class="btn btn-success" id="newNav">添加新药</button>	
			<button type="button" class="btn btn-success" onclick="delAll()">批量删除</button>		
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
