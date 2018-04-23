$(function(){
		$("#register").validate({	// 该功能为用户注册时的表单验证
			rules:{
				rname:{			//验证用户名
					required: true
				},
				rpwd:{			//验证密码
					required: true
				},
				remail:{		//验证邮箱
					required: true,
					email: true
				},
				rphone:{		//验证手机
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
		function checkName(senddata){			//该功能为检测用户名是否已经存在
			$.ajax({							//使用ajax传递表单数据
				url:"http://localhost:8080/GraduateDemo/com" +
						"/demo/controller/ChecknameServlet",
				type:"post",
				dataType:"html",
				data:senddata,					//确定传递的数据
				success:function(result){		//对后台返回的结果进行检测
					if(result == "no"){			//用户名存在则通知用户已存在
						$("#checkname").html("该用户名已存在"); 	
						$("#checkname").removeClass("checksuccess");
						$("#checkname").addClass("checkfail");
						$("#reg").attr("disabled",true); 
						$("#reg").css("background","lightgray");
					}
					else if(result == "yes"){	//用户名不存在则通知用户可用
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