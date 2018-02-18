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

</head>
<body>
	<div class="loginbox" id="loginbox" style="display:block">
		<div class="alert" id = "tool" style="display:none">
			<a href="#" class="close" data-dismiss="alert">
			&times;
			</a>
			<strong id="tooltip"></strong>
		</div>
		<h3>欢迎登录</h3>
		<form action="../com/demo/controller/LoginServlet" method="post" >
			<div class="input_div">
				<input name="logname" id="logname" class="text" onfocus=" if(this.value=='输入用户名登录') this.value=''" onblur="if(this.value=='') this.value='输入用户名登录'" value="输入用户名登录" style="color: #FFFFFF !important" type="text" required="required"/>
			</div>
			<div class="input_div">
				<label class="l-login login_password" style="color: rgb(255, 255, 255);display: block;">输入密码</label>
				<input name="logpass" id="logpass" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;" onfocus="$('.login_password').hide()" onblur="if(this.value=='') $('.login_password').show()" value="" type="password" />
			</div>
			<div style="margin-bottom: 20px">
				<input type="submit" value="登录" class="act-but submit"style="color: #FFFFFF;text-decoration: none;outline: none;"/>
			</div> 
			<input name="savesid" value="0" id="check-box" class="checkbox" type="checkbox" style="vertical-align: middle;margin: 5px 5px 0 0;float:left">
			<span>记住用户名</span>  
			<a href="#" class="login-fgetpwd" style="color: #FFFFFF;float: right;">忘记密码？</a>	    
		</form>
		<div class="resdiv">
			<a href="register.jsp" id="showres">还没注册！点击注册</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var msg ='<%=request.getParameter("error")%>';
		if(msg == 'yes'){
			$("#tooltip").text("用户名或密码错误!");
			$("#tool").addClass("alert-danger");
			$("#tool").css('display','block');
		}
		var msg ='<%=request.getParameter("success")%>';
	    if(msg=="yes"){
			$("#tooltip").text("注册成功！请登录");
			$("#tool").addClass("alert-success");
			$("#tool").css('display','block');
		}
	})
</script>
</html>