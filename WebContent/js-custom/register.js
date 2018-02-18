$(function(){
		$("#rname").blur(function() {
			var name = $("#rname").val();
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