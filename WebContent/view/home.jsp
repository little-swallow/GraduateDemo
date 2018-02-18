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
	 	<ul class="showlist" id="showlist"> 
	 	 	<c:forEach items="${info}" var="item"> 
        	  	<c:url value="../com/demo/controller/DetailServlet" var="url">  
            		<c:param name="fileid" value="${item.sid}"></c:param>  
        		</c:url>  
			 	<li class="sourcelist">
					<div class="details">
						<h4 style="font-weight:bold"><a href="${url}" style="color:black">${item.describe}</a></h4>
						<dl class="infobar">
							<dd>上传者：${item.upname}</dd>
							<dd>${item.sourcesort}</dd>
							<dd>${item.sdate}</dd>
							<dd>浏览量：${item.viewcount}</dd>
							<dd>下载量：${item.dlcount}</dd>
						</dl>
					</div>
				</li>
			</c:forEach> 
	  	</ul>
	</div>
	<div class="modal fade" id="logModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   		<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header" >
                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               	 	<h4 class="modal-title" id="myModalLabel">提示：</h4>
            	</div>
            	<div class="modal-body" style="border:none;text-align:center">
            		<div style="font-size: 16px;margin-bottom: 30px;">请先登录</div>
            		<div><a href="login.jsp" target="_top">点击此处登录</a></div>
            	</div>
        	</div>
    	</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var msg ='<%=request.getParameter("login")%>';
		if(msg == 'no'){
			$('#logModal').modal('show');
		}
	})
</script>
</html>