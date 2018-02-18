$(function(){
		alert('f');
		$("#upload").click(function(){
			var formData = new FormData($('form')[0]);
			$.ajax({
				url: '${pageContext.request.contextPath}/com/demo/controller/UploadServlet',
				type: 'post',
				xhr: function(){
					myXhr = $.ajaxSetttings.xhr();
					if(myXhr.upload){
						myXhr.upload.addEventListener('progress',function(e){
							if(e.lengthComputable){
								$('progress').attr({value:e.loaded,max:total});
							}
						},false);
					}
					return myXhr;
				},
				data: formData,
				beforedSend: function(){},
				success: function(){
					alert(data);
				},
				error: function(){},
				cache: false,
				contentType: false,
				processData: false
			});
		});
	})