<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../css-custom/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.js"></script>
</head>
<body class="mainbody">
	<nav class="navbar navbar-fixed-top" style="height:61px;background-color: #222">
		<div class="container-fluid">
			<div id="navbar" class="navbar-collapse collapse" style="display: block!important;">
				<div class="projectname">
					资源共享平台
				</div>
          		<div class="navbar-right row" id="uloginstate" style="display:block;width: 300px;">
            		<a class="col-md-9" href="login.jsp" target="_top" style="color: #9d9d9d;text-align:right;margin-top:20px">登录</a>
            		<a class="col-md-3" href="#" style="color: #9d9d9d;margin-top:20px">帮助</a>
            	</div>
            	<div class="navbar-right row" id="loginstate" style="display:none;width: 300px;">
            		<a class="col-md-4" href="../com/demo/controller/MysourceServlet" target="right" style="color: #9d9d9d;float:left;margin-top:20px">我的资源</a>
            		<a class="col-md-4" href="../com/demo/controller/CupsourceServlet" target="right" style="color: #9d9d9d;float:left;margin-top:20px">上传资源</a>
            		<a class="col-md-4" href="../com/demo/controller/SelfinfoServlet" target="right" style="color: #9d9d9d;float:right;margin-top:20px">个人中心</a>
            	</div>
          		<form class="navbar-form navbar-right" style="margin-right:130px;margin-top:13px;" action="../com/demo/controller/SearchServlet" method="post" target="right">
          			<div class="row">
          				<input type="text" class="form-control col-md-9" placeholder="Search..." name="search">
            			<button type="submit" class="btn col-md-2"style="margin-left:10px;background-color:#fff;color:#9d9d9d">Go</button>
          			</div>
          		</form>
        	</div>
		</div>
	</nav>
</body>
<script type="text/javascript">
	var cid; 
	cid = "<%=session.getAttribute("Userid")%>";
	if(cid == 'null'){
		console.log(cid);
		$("#uloginstate").css('display','block'); 
		$("#loginstate").css('display','none');
	}else{
		console.log(cid);
		$("#uloginstate").css('display','none'); 
		$("#loginstate").css('display','block');
		
	}
</script>
</html>