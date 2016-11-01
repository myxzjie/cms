<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!doctype html>
<html>
<head>
  <%@ include file="include/meta.jsp" %>
  <title>${site.siteName}</title>
  <meta name="description" content="${site.keywords}">
  <meta name="keywords" content="${site.description}">
  <%@ include file="include/style.jsp" %>
  
  <style type="text/css">
  .am-slider .am-viewport{
  	max-height: 400px;
  }
  .thumbnails{
  	max-height: 145px;
  }
  .description{
  	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 3;
	overflow: hidden;
  }
  </style>
</head>

<body id="blog">

<%@include file="include/navs.jsp" %>

<!-- banner start -->
<div class="am-g am-g-fixed blog-fixed am-u-sm-centered blog-article-margin">
    <div data-am-widget="slider" class="am-slider am-slider-b1" data-am-slider='{&quot;controlNav&quot;:false}' >
    <ul class="am-slides">
    </ul>
    </div>
</div>
<!-- banner end -->

<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
    <div class="am-u-md-8 am-u-sm-12">
        <%@include file="include/article.jsp" %>

        <ul class="am-pagination">
		  <li class="am-pagination-prev"><a href="${ctx_front}/index?cid=${siteId}&currentPage=${page.currentPage-1}">&laquo; 上一页</a></li>
		  <li class="am-pagination-next"><a href="${ctx_front}/index?cid=${siteId}&currentPage=${page.currentPage+1}">下一页 &raquo;</a></li>
		</ul>
    </div>

    <div class="am-u-md-4 am-u-sm-12 blog-sidebar">
    	<div data-am-widget="list_news" class="am-list-news am-list-news-default " >
  			<!--列表标题-->
    		<div class="am-list-news-hd am-cf">
	       		<!--带更多链接-->
	        	<!-- <a href="##" class=""> -->
	          	<h2>内容推荐</h2>
	            <!-- <span class="am-list-news-more am-fr">更多 &raquo;</span> -->
	        	<!-- </a> -->
          	</div>

  			<div class="am-list-news-bd">
			  <ul class="am-list recommend-list">
			
			  </ul>
  			</div>

    	</div>
    
    	<%@include file="include/about.jsp" %>
        
    </div>
</div>
<!-- content end -->

<%@include file="include/footer.jsp" %>

<%@include file="include/script.jsp" %>
<script type="text/javascript">
$(function(){
	$.ajax({
		type: "POST",
		data: {},
		dataType: 'json',
		url: global.frontPath+'/slider?cid=${siteId}',
		success: function(res){
			if(res.success){
				var data=res.data,slides= $('.am-slides');
				for(var i=0;i<data.length;i++){
					var row=data[i],html='<li>';
					html+='<a href="'+global.frontPath+'/article?cid=${siteId}&id='+row.articleId+'"><img src="'+$('#uploadImageWeb').val()+row.image+'"></a>';
	           		html+='</li>';
	           		slides.append(html);
				}
				$('.am-slider').flexslider({
					    // options
				});
			}else{
				//$('#content').html('<p>没有内容...</p>');
				//layer.alert(res.message, {icon: 2});
			}
		}
	}); 
	
	
	$.ajax({
		type: "POST",
		data: {},
		dataType: 'json',
		url: global.frontPath+'/recommend?cid=${siteId}',
		success: function(res){
			if(res.success){
				
				var data=res.data,slides= $('.recommend-list');
				for(var i=0;i<data.length;i++){
					var row=data[i],html='<li class="am-g am-list-item-dated">';
					
					html+='<a href="'+global.frontPath+'/article?cid=${siteId}&id='+row.articleId+'" class="am-list-item-hd ">'+row.title+'</a>';
			
					html+='<span class="am-list-date">'+new Date(row.createDate).format("yyyy-MM-dd")+'</span>';
			
					html+='</li>';
					
	           		slides.append(html);
				}
			}else{
				//layer.alert(res.message, {icon: 2});
			}
		}
	});
});

</script>
</body>
</html>