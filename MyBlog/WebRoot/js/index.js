//从Article表中获取文章列表 
var recommandedArticle=[]; 
var flag=false;
var htmlArtticle="<h2><p><span>推荐</span>文章</p></h2>";
function Load()
 {
	getRecommandedArticle();
	
		
		alert(htmlArtticle);
		
		
 }
 function getRecommandedArticle()
 {
	 $.ajax({
		 url:"handleArticles",
	     type:"post",
	     dataType:"json",
	     data:{type:"fristload"},
	     success:function(result){
	    	 alert(result);
	    	 recommandedArticle=result;
	    	 flag=true;
	    	 //alert("成功"+recommandedArticle[0].title);
	    	 getHtml();
	    	 $("#bloglists").html(htmlArtticle);
	     },
	     error:function(xhr){
	    	 alert("错误提示："+xhr.status+"内容："+xhr.statusText);
	     }
	 });
 }
 function getHtml()
 {
	 if(recommandedArticle==null)
		 return;
	 for(var i=0;i<recommandedArticle.length;i++)
		 {
		 htmlArtticle+="<div class=\"blogs\"><h3><a href=\"#\">"+recommandedArticle[i].title+"</a></h3><figure><img src=\"uploadimages/"+recommandedArticle[i].imageurl+"\" ></figure>"+
		 "<ul><p>"+recommandedArticle[i].content+"</p><a href=\"new.html?articalId=1\" target=\"_blank\" class=\"readmore\">阅读全文&gt;&gt;</a>"+
		 "</ul><p class=\"autor\"><span>作者：小马</span><span>分类：【<a href=\"#\">"+recommandedArticle[i].type+"</a>】</span><span>浏览（<a href=\"#\">"+recommandedArticle[i].count+"</a>）</span><span>评论（<a href=\"#\">"+recommandedArticle[i].count+"</a>）</span></p>"+
		 "<div class=\"dateview\">"+recommandedArticle[i].date+"</div></div>";
		 }
 }