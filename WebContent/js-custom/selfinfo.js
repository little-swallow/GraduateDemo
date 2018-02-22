$(function(){
		$("#nameinfo").blur(function() {
			var name = $("#nameinfo").val();
			if(name != null && name != ''){
				var senddata = {username: name};  
			  	checkName(senddata);
			}
		});
		function checkName(senddata){
			$.ajax({
				url:"http://localhost:8080/GraduateDemo/com/demo/controller/ChecknameServlet",
				type:"post",
				dataType:"html",
				data:senddata,
				success:function(result){
					if(result == "no"){
						$("#checklable").html("该用户名已存在"); 	
						$("#checklable").removeClass("labelsuccess");
						$("#checklable").addClass("labelfail");
						$("#save").attr("disabled",true); 
					
					}
					else if(result == "yes"){
						$("#checklable").html("该用户名可用"); 
						$("#checklable").removeClass("labelfail");
						$("#checklable").addClass("labelsuccess");
						$("#save").attr("disabled",false);
					
					}
					else if(result == "same"){
						$("#checklable").html(""); 
						$("#save").attr("disabled",false);
					}
				},
				error:function(){
					console.log("检查用户是否存在发生错误");
				}
			});
		};
		$("#passinfo").on('click',function(){
			$("#changpwd").modal('show');
			$("#pwdform")[0].reset();
		});
		$("#savepwd").on('click',function(){
			var pwd = $("#pwdnew").val();
			var pwd2 = $("#pwdagain").val();
			if(pwd2 != pwd){
				$("#cpwdlable").html("两次输入密码不同"); 	
				$("#cpwdlable").addClass("labelfail");
			}else{
				var data = {password: pwd};
				changePwd(data);
			}	
		});
		function changePwd(data){
			$.ajax({
				url:"http://localhost:8080/GraduateDemo/com/demo/controller/ChangePwdServlet",
				type:"post",
				dataType:"html",
				data:data,
				success:function(result){
					if(result == "success"){
						$("#showtip").text("修改密码成功!");
						$("#show").addClass("alert-success");
						$("#show").css('display','block');
						$('#changpwd').modal('hide');
					}
					else if(result == "fail"){
						$("#showtip").text("修改密码失败!");
						$("#show").addClass("alert-danger");
						$("#show").css('display','block');
						$('#changpwd').modal('hide');
						
					}
				},
				error:function(){
					console.log("修改发生错误");
				}
			});
		};		
		$("#save").on('click',function(){
//			$('#updateinfo').validate({
//				rules: {
//					nameinfo: {
//						required: true
//					},
//					emailinfo: {
//						required: true,
//						email: true
//					},
//					phoneinfo:{
//						required: true,
//					}
//				}
//			});
			var name = $("#nameinfo").val();
			var email = $("#emailinfo").val();
			var phone = $("#phoneinfo").val();
			var data = {	
							nameinfo:name,
							emailinfo:email,
							phoneinfo:phone
						};
			$.ajax({
				url:"http://localhost:8080/GraduateDemo/com/demo/controller/UpdateinfoServlet",
				type:"post",
				dataType:"html",
				data:data,
				success:function(result){
					if(result == "success"){
						$("#showtip").text("修改信息成功!");
						$("#show").addClass("alert-success");
						$("#show").css('display','block');
					}
					else if(result == "fail"){
						$("#showtip").text("修改信息失败!");
						$("#show").addClass("alert-danger");
						$("#show").css('display','block');
					}
				},
				error:function(){
					console.log("修改发生错误");
				}
			})
		});
		$("#close").on('click',function(){
			$("#show").css('display','none');
		})
		
})