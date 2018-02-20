<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.demo.bean.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-import/bootstrap.css" rel="stylesheet">
<style>
	a:hover{ 
		text-decoration: none;
	}

</style>
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.js"></script>
<script type="text/javascript" src="../js-custom/selfinfo.js"></script>
<script type="text/javascript" src="../js-import/jquery.validate.min.js"></script>
</head>
<body>
	<div class="alert alert-success" id="show" style="display:none;width:300px;margin: 10px 0px -50px 350px;">
		<button type="button" class="close" aria-hidden="true" id="close">&times;</button>
		<strong id="showtip"></strong>
	</div>
	<div class="container selfdiv" style="width: 700px;">
		<div class="headpart">
			<h4 style="text-align:center;margin-top:10px">个人信息</h4>
		</div>
		<div class="bodypart" style="margin-top:20px">
			<form action="../com/demo/controller/UpdateinfoServlet" method="post" id="updateinfo">
				 <% UserBean userbean = (UserBean)session.getAttribute("Userinfo"); %>
				<div class="form-group infoform">
    				<label for="nameinfo">用户名</label>
    				<label id="checklable"></label>
    				<input type="text" class="form-control" id="nameinfo"  name="nameinfo" value=<%= userbean.getName()%>>
  				</div>
  				<div class="form-group infoform">
    				<label for="passinfo">密码</label>
    				<button type="button" class="btn btn-link" id="passinfo" name="passinfo">修改密码</button>
  				</div>	
  				<div class="form-group infoform">
    				<label for="emailinfo">电子邮箱</label>
    				<input type="text" class="form-control" id="emailinfo" name="emailinfo" value=<%= userbean.getEmail()%>>
  				</div>	
  				<div class="form-group infoform">
    				<label for="phoneinfo">联系方式</label>
    				<input type="text" class="form-control" id="phoneinfo"  name="phoneinfo" value=<%= userbean.getPhone()%>>
  				</div>	
  				<div class="form-group infoform" style="margin-top:30px;margin-bottom:30px">
      				<button type="button" class="btn btn-default"><a class="cancel" href="home.jsp" target="right" style="color:black">取消</a></button>
      				<button type="button" class="btn btn-primary" id="save" style="float:right">保存</button>
  				</div>		
			</form>
		</div>
	</div>
		<div class="modal fade" id="changpwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   		<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header" >
                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               	 	<h4 class="modal-title" id="myModalLabel">修改密码</h4>
            	</div>
            	<div class="modal-body" style="border:none;text-align:center">
            		<form id="pwdform">
            			<div class="form-group infoform">
    						<label for="pwdnew" style="float:left">新密码</label>
    						<input type="password" class="form-control" id="pwdnew" name="pwdnew">
  						</div>	
  						<div class="form-group infoform">
    						<label for="pwdagain" style="float:left">确认密码</label>
    						<label id="cpwdlable"></label>
    						<input type="password" class="form-control" id="pwdagain" name="pwdagain">
  						</div>
  						<div class="form-group infoform" style="margin-bottom: 65px;">
      						<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true" style="float:left">取消</button>
      						<button type="button" class="btn btn-primary" id="savepwd" style="float:right">确认修改</button>
  						</div>	
            		</form>	
            	</div>
        	</div>
    	</div>
	</div>
</body>
</html>