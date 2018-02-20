$(function(){
		$("#register").validate({
			rules:{
				rname:{
					required: true
				},
				rpwd:{
					required: true
				},
				remail:{
					required: true,
					email: true
				},
				rphone:{
					required: true
				}
			},
			messages: {
				rname: {
				   required: "请输入用户名"
				},
				rpwd:{
					required: "请输入密码"
				},
				remail:{
					required: "请输入邮箱",
					email: "请输入有效的电子邮件地址"
				},
				rphone:{
					required: "请输入联系方式"
				}
			}
		})
		$("#rname").focus(function(){
			$("#checkname").html(""); 
			$("#reg").attr("disabled",false);
			$("#reg").css("background","#0096e6");
		})
		$("#rname").blur(function() {
			var name = $("#rname").val();
			if(name != null && name != ''){
				var senddata = {username: name};  
			  	checkName(senddata);
			}else{
				$("#checkname").html(""); 
				$("#reg").attr("disabled",false);
				$("#reg").css("background","#0096e6");
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
						$("#checkname").html("该用户名已存在"); 	
						$("#checkname").removeClass("checksuccess");
						$("#checkname").addClass("checkfail");
						$("#reg").attr("disabled",true); 
						$("#reg").css("background","lightgray");
					}
					else if(result == "yes"){
						$("#checkname").html("该用户名可用"); 
						$("#checkname").removeClass("checkfail");
						$("#checkname").addClass("checksuccess");
						$("#reg").attr("disabled",false);
						$("#reg").css("background","#0096e6");
					}
				},
				error:function(){
					console.log("检查用户是否存在发生错误");
				}
			});
		}
})