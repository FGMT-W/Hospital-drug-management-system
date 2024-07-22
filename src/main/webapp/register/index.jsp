<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=this.getServletContext().getContextPath() %>/Register/">
    <title>门诊查询</title>
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
				window.location.href="${path}register/add.jsp";
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
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids="";
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids+="'"+alls[i].value+"',";
				}		
			}
			if(ids!=""){
				ids=ids.substring(0,ids.length-1);
				if(confirm("确认要删除吗?")){
					alert(ids);
					window.location.href="${path}Register?method=deleteRegister&ids="+ids;
				}
			}else{
				alert("请选中要操作的项");
			}
		}
		
		function withdraw(rno){
			if(!confirm("确定退号？")){
				return false;
			}
			window.location.href = "../Register?mark=withdraw&rno="+rno;
			
		}
		
		
    </script>
</head>
<body>

<form action="${path }Register?method=findRegisterByPage" method="post" class="definewidth m20">
<!-- <input name="method" value="findRegisterByPage" type="hidden"/> -->
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" name="rid" value=""/></td>
		
        <td width="10%" class="tableleft">主治医生：</td>
        <td><input type="text" name="name" value=""/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td>
        	<select name="department" id="department">
	        		<option value="0">==请选择==</option>
	        		<option value="1">急诊科</option>
	        		<option value="2">儿科</option>
	        		<option value="3">妇科</option>
	        		<option value="4">皮肤科</option>
	        		<option value="5">内分泌科</option>
	        		<option value="6">牙科</option>
        	</select>
        </td>
    </tr>
    <tr>
		  <td colspan="6">
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
    	<th><input type="checkbox" id="checkall" name="checkall" onChange="checkall();"></th>
        <th>病例号</th>
        <th>病人姓名</th>
        <th>主治医生</th>
        <th>挂号时间</th>
        <th>挂号科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${registerList }" var="register">
		     <tr >
	         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${register.rid }"></td>
	            <td style="vertical-align:middle;">${register.rid }</td>
	            <td style="vertical-align:middle;">${register.name }</td>
	            <td style="vertical-align:middle;">${register.doctor.name }</td>
	            <td style="vertical-align:middle;">${register.registerDate }</td>
	            <td style="vertical-align:middle;">
	            	<c:if test="${register.department ==1 }">急诊</c:if>
	            	<c:if test="${register.department ==2 }">儿科</c:if>
	            	<c:if test="${register.department ==3 }">妇科</c:if>
	            	<c:if test="${register.department ==4 }">皮肤科</c:if>
	            	<c:if test="${register.department ==5 }">内分泌科</c:if>
	            	<c:if test="${register.department ==6 }">牙科</c:if>
	            </td>
	            <td style="vertical-align:middle;">
	            	<c:if test="${register.status ==1   }">挂号</c:if>
	            	<c:if test="${register.status ==2   }">已住院</c:if>
	            	<c:if test="${register.status ==3   }">已出院</c:if>
	            	<c:if test="${register.status ==4   }">已结算</c:if>
	            </td>
	            <td style="vertical-align:middle;">
	            <a href="${path }Register?method=findRegisterByRid&rid=${register.rid }">详情>>></a>&nbsp;&nbsp;&nbsp;
	            
	            <c:if test="${register.status == 1 }">
	                <a href="${path }Register?method=toUpdate&rid=${register.rid }&department=${register.department}">更改</a>&nbsp;&nbsp;&nbsp;
	            </c:if>
	            </td>
	        </tr>
        </c:forEach>
     </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
  		<div class="inline pull-right page">
	          <a href="${path }Register?method=findRegisterByPage&currentPage=1&rid=${register.rid}&name=${register.name}&department=${register.department}" >首页</a> 
	          <a href="${path }Register?method=findRegisterByPage&currentPage=${pageTool.prePage }&rid=${register.rid}&name=${register.name}&department=${register.department}">上一页</a>     
	          <a href="${path }Register?method=findRegisterByPage&currentPage=${pageTool.nextPage }&rid=${register.rid}&name=${register.name}&department=${register.department}" >下一页</a> 
	          <a href="${path }Register?method=findRegisterByPage&currentPage=${pageTool.totalPages }&rid=${register.rid}&name=${register.name}&department=${register.department}" >尾页</a>
			  &nbsp;&nbsp;&nbsp;
			     共<span class='current'>${pageTool.totalCount }</span>条记录
			     <span class='current'> ${pageTool.currentPage }/${pageTool.totalPages } </span>页
		  </div>
			 <div>
			 	<button type="button" class="btn btn-success" id="newNav">门诊挂号</button>&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success" id="delRegister" onclick="delAll()">批量删除</button>
			</div>
		 </th></tr>
  </table>
  
</body>
</html>
