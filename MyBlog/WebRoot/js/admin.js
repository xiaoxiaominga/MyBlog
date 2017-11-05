var ct="测试内容";
var index;
var layedit;
layui.use('layedit',function(){
			layedit=layui.layedit;
			index=layedit.build("input");//建立编辑器
});
$(document).ready(function(){
	$("#submit").click(function(){
		var form=new FormData();
		form.append("title",$("#title").val());
		form.append("author",$("#author").val());
		form.append("articleType",$("#articleType").val());
		var input=layedit.getContent(index);
		form.append("input",input);
		form.append("files",$("#files").get(0).files[0]);
		$.ajax({
			url:"handleFileUpload",
			type:"post",
			dataType:"json",
			data:form,
			processData:false,
            contentType:false,
			success:function(result){
				//layer.msg(result);
			alert(result.Message);
			},
			error:function(xhr){
				alert("错误提示："+xhr.status+"内容："+xhr.statusText);
			}
		});
	});
});
function seal(str)
{
	var temp=str.replace("<","^");
	temp=temp.replace(">","*");
	alert(temp);
	return temp;
}






