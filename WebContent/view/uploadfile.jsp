<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传资源</title>
<link type="text/css" href="../css-import/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css-import/bootstrap.css" rel="stylesheet">
<link type="text/css" href="../css-custom/theme.css" rel="stylesheet">
<link type="text/css" href="../css-custom/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="../js-import/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.min.js"></script>
<script type="text/javascript" src="../js-import/bootstrap.js"></script>
</head>
<body>
<div class="alert" id = "filetool" style="display:none;width:300px;margin: 10px 0px -50px 350px;">
	<a href="#" class="close" data-dismiss="alert">
	&times;
	</a>
	<strong id="filetip"></strong>
</div>
<div class="container selfdiv" style="width: 700px;">
		<div class="headpart">
			<h4 style="text-align:center;margin-top:10px">资源上传</h4>
		</div>
		<div class="bodypart" style="margin-top:20px">
			<form action="../com/demo/controller/UploadServlet" method="post" enctype="multipart/form-data">
				<div class="form-group infoform">
    				<label for="inputfile">资源选择</label>
    				<input type="file" id="inputfile" name="infile">
  				</div>
				<div class="form-group infoform">
    				<label for="snameinfo">资源描述</label>
    				<input type="text" class="form-control" id="snameinfo" placeholder="资源描述" name="inname">
  				</div>
  				<div class="form-group infoform">
    				<label for="scateinfo">资源类别</label>
    				<select class="form-control" id="scateinfo" name="insort">
  						<option value="电子科技">电子科技</option>
  						<option value="金融">金融</option>
  						<option value="文学历史">文学历史</option>
  						<option value="英语">英语</option>
  						<option value="数理化">数理化</option>
  						<option value="建筑">建筑</option>
  						<option value="艺术设计">艺术设计</option>
  						<option value="日语">日语</option>
  						<option value="心理学">心理学</option>
  						<option value="教育">教育</option>
					</select>
  				</div>	
  				<div class="form-group infoform">
    				<label for="stextinfo">资源简介</label>
    				<textarea class="form-control" rows="3" id="stextinfo" placeholder="请在此处输入简介" style="resize:none" name="inabstract"></textarea>
  				</div>	
  				<div class="form-group infoform" style="margin-top:30px;margin-bottom:30px">
      				<button type="submit" id="upload" class="btn btn-primary" style="float:right;margin-bottom:30px"">上传</button>
  				</div>		
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var msg ='<%=request.getParameter("fileup")%>';
		if(msg == 'no'){
			$("#filetip").text("上传文件失败");
			$("#filetool").addClass("alert-danger")
			$("#filetool").css('display','block');
		}else if(msg == 'yes'){
			$("#filetip").text("上传文件成功");
			$("#filetool").addClass("alert-success");
			$("#filetool").css('display','block');
		}
	})
</script>
</html>