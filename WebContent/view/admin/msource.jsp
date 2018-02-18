<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page import="com.demo.bean.SourceBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link type="text/css" href="../../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../../css-import/bootstrap.css" rel="stylesheet">
<link type="text/css" href="../../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../../css-custom/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="../../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js-import/bootstrap.js"></script>
<style>
	th{
	    border-right: 1px solid #ddd;
	 	}
	 	
	 td{
	 	    border-right: 1px solid #ddd;
	 	}
</style>
</head>
<body style="background-color:#f4f4f4">
	<div class="container-fluid">
		<div class="sourcediv">
			<table class="table">
				<tbody>
					<tr>
						<th>资源ID</th>
						<th>资源名称</th>
						<th>资源描述</th>
						<th>资源分类</th>
						<th>上传者</th>
						<th>上传时间</th>
						<th>浏览量</th>
						<th>下载量</th>
						<th>是否删除</th>
					</tr>
					<c:forEach items="${allsource}" var="item">
						<c:url value="../../com/demo/controller/MsourceServlet" var="url">  
            				<c:param name="oldname" value="${item.suname}"></c:param>  
        				</c:url>
	 					<tr>
	 						<td>${item.sid}</td>
	 						<td>${item.sname}</td>
	 						<td>${item.describe}</td>
	 						<td>${item.sourcesort}</td>
	 						<td>${item.upname}</td>
	 						<td>${item.sdate}</td>
	 						<td>${item.viewcount}</td>
	 						<td>${item.dlcount}</td>
	 						<td>
	 							<button type="button" class="btn btn-default">
	 								<a class="cancel" href="${url}" target="right" style="color:black">删除</a>
	 							</button>
	 						</td>
	 					</tr>
	 				</c:forEach>
	 			</tbody>
			</table>
		</div>
	</div>
	
</body>
<script type="text/javascript">
$(document).ready(function(){
    $("tr:odd").css("background-color","#eee");
});
</script>
</html>