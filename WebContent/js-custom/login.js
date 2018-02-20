$().ready(function() {
	$("#login").validate({
		rules: {
			logname:{
				required:true
			},
			logpass:{
				required:true
			}
				
		},
		messages: {
			logname: {
			   required: "请输入用户名"
			},
			logpass:{
				required: "请输入密码"
			}
		}
	})	
});