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
	<base href="<%=this.getServletContext().getContextPath() %>/doctor/">
    <title>门诊医生</title>
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
				window.location.href="add.jsp";
		 });
		
		//全选全不选
		 $("#chkall").click(function(){
			  $('[name="chk"]').prop("checked",$(this).prop("checked"));
		});
    });
	  //批量删除
	  function delAll(){
		  //保存所有被选中的记录的uid,ids=2,3,4
		  var ids = "";
		  //循环所有被选中的复选框获取id号并进行拼接
		  $('[name="chk"]:checked').each(function(){
			  ids+=","+$(this).val(); //ids=",2,3,4"
		  })
		  //判断非空
		  if(ids==""){
			  alert("请选择要删除的记录");
			  return;
		  }
		  //判断是否确定要删除
		  var bl=confirm("确定要删除吗？");
		  if(bl){
			  //将ids字符串中第一个逗号截取掉
			  ids=ids.substring(1);
			  //向服务器提交
			  window.location="${path}Doctor?method=deleteDoctor&ids="+ids;
		  }
	  }
    	/* function checkall(){
			var alls=document.getElementsByName("chk");
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
			var alls=document.getElementsByName("chk");
			var ids="";
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids=ids+alls[i].value+",";
				}		
			}
			if(ids!=""){
				if(confirm("确认要删除吗？")){
					ids=ids.substring(0,ids.length-1);
					window.location.href="${path}Doctor?method=deleteDoctor&ids="+ids;
				}
			}else{
				alert("请选中要删除的项");
			}
		} */
    </script>
</head>
<body>

<form action="${path }Doctor?method=findDoctorsByPage" method="post" class="definewidth m20">
	<table class="table table-bordered table-hover definewidth m10">
	    <tr>
	        <td width="10%" class="tableleft">医生姓名：</td>
	        <td><input type="text" id="name" name="name" value=""/></td>
			
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
			  <td colspan="6"><center>
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
        <th>医生编号</th>
        <th>医生姓名</th>
        <th>联系方式</th>
        <th>所属科室</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	 <c:forEach items="${doctorList }" var="doctor">
		     <tr >
		        <td><input type="checkbox" id="chk" name="chk" value="${doctor.did }"></td>
	            <td>${doctor.did }</td>
	            <td>${doctor.name }</td>
	            <td>${doctor.phone }</td>
	            <td>
	            	<c:if test="${doctor.department ==1 }">急诊</c:if>
	            	<c:if test="${doctor.department ==2 }">儿科</c:if>
	            	<c:if test="${doctor.department ==3 }">妇科</c:if>
	            	<c:if test="${doctor.department ==4 }">皮肤科</c:if>
	            	<c:if test="${doctor.department ==5 }">内分泌科</c:if>
	            	<c:if test="${doctor.department ==6 }">牙科</c:if>
	            </td>
	            <td style="vertical-align:middle;">
	            	<a href="${path }Doctor?method=findDoctorByDid&did=${doctor.did }">详情>>></a>&nbsp;&nbsp;&nbsp;
	            	<a href="${path }Doctor?method=toUpdate&did=${doctor.did }">更改</a>
	            </td>
	        </tr>
	    </c:forEach>
	 </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr>
  		<th colspan="5">  <div class="inline pull-right page">
          <a href='${path }Doctor?method=findDoctorsByPage&currentPage=1&name=${doctor.name }&department=${doctor.department }' >首页</a> 
          <a href='${path }Doctor?method=findDoctorsByPage&currentPage=${pageTool.prePage}&name=${doctor.name }&department=${doctor.department }'>上一页</a>
          <a href='${path }Doctor?method=findDoctorsByPage&currentPage=${pageTool.nextPage}&name=${doctor.name }&department=${doctor.department }' >下一页</a> 
          <a href='${path }Doctor?method=findDoctorsByPage&currentPage=${pageTool.totalPages}&name=${doctor.name }&department=${doctor.department }' >尾页</a>
		  &nbsp;&nbsp;&nbsp;共<span class='current'>${pageTool.totalCount }</span>条记录
		  <span class='current'> ${pageTool.currentPage }/${pageTool.totalPages } </span>页
		  </div>
		 <div>
		 <button type="button" class="btn btn-success" id="newNav">添加新医生</button>
		 <button type="button" class="btn btn-success" id="delAll" onclick="delAll()">批量删除</button>
		 </div>
		 
		 </th>
	</tr>
  </table>  
</body>
</html>
