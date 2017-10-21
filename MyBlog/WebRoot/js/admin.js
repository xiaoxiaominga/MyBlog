
layui.use('layedit',function(){
			var layedit=layui.layedit;
			layedit.build("input");//建立编辑器
		});
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element;
  
  //…
});
//创建上传的方法
layui.use('upload', function(){
	  var upload = layui.upload;
	   
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#test' //绑定元素
	    ,url: 'handleFileUpload' //上传接口
	    ,accept:'images',//允许接受的文件类型
	    multiple:true,
	    data:{state:'upload'},
	    
	    done: function(res){
	     if(res.code==0)
	    	 {
	    	 alert("上传成功");
	    	 }
	     else
	    	 alert("失败");
	    }
	    ,error: function(){
	      //请求异常回调
	    }
	  });
	});

//调用的上传按钮的方法--------输入的中文乱码问题------------过期
/*$(document).ready(function(){
	$("#submit").click(function(){
		var layer;
		layui.use('layer',function(){
			layer=layui.layer;
		});
	//layer.msg("zhelushi1");
		var title=$("#title").val();
		//var type=$("#selectedType").val();
		var author=$("#author").val();
		var input=$("#input").val();
		var type="type";
		$.ajax({
			url:"handleFileUpload",
			type:"post",
			dataType:"text",
			data:{"title":title,"type":type,"author":author,"input":input,"state":"submit"},
			success:function(result){
				//layer.msg(result);
			alert(result);
			},
			error:function(xhr){
				layer.msg("错误提示："+xhr.status+"内容："+xhr.statusText);
			}
		});
		
		
	});
});*/

$(document).ready(function(){
	$("#submit").click(function(){
		var formdata=new FormData(document.getElementById("uploadform"));
		$.ajax({
			url:"handleFileUpload",
			type:"post",
			dataType:"text",
			data:formdata,
			processData:false,
            contentType:false,
			success:function(result){
				//layer.msg(result);
			alert(result);
			},
			error:function(xhr){
				layer.msg("错误提示："+xhr.status+"内容："+xhr.statusText);
			}
		});
	});
});




