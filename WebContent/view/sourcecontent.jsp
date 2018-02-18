<%@page import="com.demo.bean.SourceBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-import/bootstrap.css" rel="stylesheet">
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../css-custom/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<div class="container selfdiv" style="width: 700px;">
		<%  SourceBean sourceBean = (SourceBean)session.getAttribute("Singleinfo"); %>
			<div class="headpart">
				<h4 style="text-align:center;margin-top:10px"><%= sourceBean.getDescribe() %></h4>
			</div>
			<div class="bodypart" style="margin-top:20px">
				<form action="../com/demo/controller/DownloadServlet" method="post" style="margin-right: 45px;">
					<input value=<%= sourceBean.getSuname()%> name="oldname" style="display:none"></input>
					<div class="infodetail"> 
						<div class="row" style="margin-bottom:10px">
							<div class="col-md-2" style="font-weight: bold">文件名：</div>
							<div class="col-md-9"><%= sourceBean.getSname()%></div>
						</div>
						<div class="row" style="margin-bottom:10px">
							<div class="col-md-2" style="font-weight: bold">类别：</div>
							<div class="col-md-9"><%=sourceBean.getSourcesort() %></div>
						</div>
						<div class="row" style="margin-bottom:10px">
							<div class="col-md-2" style="font-weight: bold">上传者：</div>
							<div class="col-md-9"><%=sourceBean.getUpname() %></div>
						</div>
						<div class="row" style="margin-bottom:10px">
							<div class="col-md-2" style="font-weight: bold">简介：</div>
							<div class="col-md-9"><%=sourceBean.getIntro() %></div>
						</div>
					</div>
					<div style="margin-left: 45px;margin-bottom: 40px;">
						<button type="button" class="btn btn-default"><a class="cancel" href="home.jsp" target="right" style="color:black">取消</a></button>
      					<button type="submit" class="btn btn-primary" style="float:right">下载</button>	
					</div>
				</form>
			</div>
		</div>
		<div class="commentpart">
			<h4 class="ctitle">评论区</h4>
			<form action="../com/demo/controller/CommentServlet" method="post">
				<div class="form-group">
    				<textarea class="form-control" rows="3" id="comment" placeholder="发表你的评论" style="resize:none" name="comment"></textarea>
  				</div>	
  				<div class="form-group">
      				<button type="submit" id="docomment" class="btn btn-primary" style="float:right;margin-bottom:30px">发表</button>
  				</div>
			</form>
			<ul class="commentlist">
				<c:forEach items="${commentinfo}" var="item">
					<c:set var="uid" value="${item.uid}"/> 
					<c:set var="logid" value="${sessionScope.Userid}"/>
					<hr style="border-top: 2px solid #eee;width: 940px;"></hr>
					<li>
						<div class="row">
							<div class="col-md-10" style="font-weight:bold">${item.scname}</div>
							<div class="col-md-2">${item.sctime}</div>
						</div>
						<div style="margin-top: 20px;">${item.scont}</div>
						<c:if test="${uid eq logid }">
							<div style="float:right;margin-bottom:10px" id="${item.uid}" >
								<a href="../com/demo/controller/DelcomServlet">删除</a>
							</div>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
