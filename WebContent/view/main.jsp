<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../css-custom/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
</head>
<frameset rows="10%,90%" border="1">
	<frame src="top.jsp" name="top" noresize="noresize"/>
		<frameset cols="15%,85%" border="1">
			<frame src="left.jsp" name="left" noresize="noresize"/>
		 	<frame src="../com/demo/controller/SelectmainServlet" name="right" noresize="noresize"/>
		</frameset>
</frameset>
</html>