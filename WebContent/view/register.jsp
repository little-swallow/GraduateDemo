<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<style>
	body{
		font-family: "微软雅黑";
    	font-size: 14px;
    	background: url(../img/bg.jpg)fixed center center; 
    	background-size:cover;
	}
</style>
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../js-import/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js-import/additional-methods.min.js"></script>
<script type="text/javascript" src="../js-custom/register.js"></script>
</head>
<body>
	<div class="loginbox" id="registerbox">
		<div class="alert" id = "tool1" style="display:none">
			<a href="#" class="close" data-dismiss="alert">
			&times;
			</a>
			<strong id="tooltip1"></strong>
		</div>
		<h3>欢迎注册</h3>
		<form action="../com/demo/controller/RegisterServlet" method="post" id="register">
			<label>用户名：</label>
			<label id="checkname" class="check"></label>
			<div class="input_div">
				<input name="rname" id="rname" class="text" style="color: #FFFFFF !important" type="text" >
			</div>
			<label>密码：</label>
			<div class="input_div">
				<input name="rpwd" id="rpwd" class="text" style="color: #FFFFFF !important" type="password">
			</div>
			<label>邮箱地址：</label>
			<div class="input_div">
				<input name="remail" id="remail" class="text" style="color: #FFFFFF !important" type="email">
			</div>
			<label>联系方式：</label>
			<div class="input_div">
				<input name="rphone" id="rphone" class="text" style="color: #FFFFFF !important" type="text">
			</div>
			<div style="margin-bottom: 20px;margin-top:45px">
				<button type="submit" id="reg" value="注册" class="act-but submit"style="color: #FFFFFF;text-decoration: none;outline: none;">注册</button>
			</div> 
		</form>
		<div class="resdiv" style="margin-top:10px">
			<a href="login.jsp" id="showlog">已有账号！点击登录</a>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var msg ='<%=request.getParameter("success")%>';
	if(msg == 'no'){
		$("#tooltip1").text("注册失败! 请重新注册");
		$("#tool1").addClass("alert-danger");
		$("#tool1").css('display','block');
	}
})	
</script>
</html>