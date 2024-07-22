<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>支付方式</title>
</head>
<script type='text/javascript'>
	var num=21;
	function calc(){
	if(num>0){
		num--;
		document.getElementById('second').innerHTML=num;
	}else{
		location='Trolley?method=PayFail'
	}
	setTimeout('calc()',1000);
}
</script>

<body onload="calc()">
	<a style="float:left; margin-left:530px; font-size:20px;">离支付时间结束还有：</a>
	<div id='second' style="width: 60px;float: left; height: 40px; margin-left: 5px; font-weight:bold; font-size:20px; color:red">20</div>
	<a style="margin-left:8px; margin-top:10px; font-size:20px;">秒</a>
</body>

<body  onload="calc()">
	<div class="head_for_pay" style="color:black;margin-left: 50px; margin-top: 30px; border-bottom: 2px solid darkslateblue;">
		<h1>选择支付方式</h1>
	</div>
	<div class="middle_for_pic" style="padding-left:300px;">
		<!-- UI -->
		<span><img src="Images/PayWay.png" style="width: 600px; height: 400px; margin-left: 75px; margin-top: 0px; margin-bottom: 20px;"/></span>
	</div>
	<div class="user_button">
		<a href="Trolley?method=pay" style="display:inline-block; width: 150px; color: #ffffff; background-color: #0044cc; border-radius: 4px; padding-top: 15px; padding-bottom:15px; margin-left: 350px;
    	text-decoration: none; padding-left: 70px;">我已支付</a>
    	<a href="Trolley?method=findTrolleyList" style="display:inline-block; width: 150px; color: #ffffff; background-color: #0044cc; border-radius: 4px; padding-top: 15px; padding-bottom:15px; margin-left: 250px;
    	text-decoration: none; padding-left: 70px;">还未支付</a>
    </div>  
    
</body>
</html>