<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page import="com.demo.bean.SourceBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-import/bootstrap.css" rel="stylesheet">
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../css-custom/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.js"></script>
</head>
<body style="background-color:#f4f4f4">
	<div class="container-fluid">
	 	<ul class="showlist"> 
	 	 	<c:forEach items="${info}" var="item"> 
        	  	<c:url value="../com/demo/controller/DeleteServlet" var="url">  
            		<c:param name="oldname" value="${item.suname}"></c:param>  
        		</c:url>  
			 	<li class="sourcelist">
			 		<div class="row">
						<div class="col-md-9 details">
							<h4 style="font-weight:bold">${item.describe}</h4>
							<dl class="infobar">
								<dd>上传者：${item.upname}</dd>
								<dd>${item.sourcesort}</dd>
								<dd>${item.sdate}</dd>
								<dd>浏览量：${item.viewcount}</dd>
								<dd>下载量：${item.dlcount}</dd>
							</dl>
						</div>
						<div class="col-md-3" style="margin-top: 20px;"> 
							<button type="button" class="btn btn-primary" style="float:right"><a href="${url}" style="color:white">删除</a></button>
						</div>
					</div>
				</li>
			</c:forEach> 
	  	</ul>
	</div>
</body>
</html>