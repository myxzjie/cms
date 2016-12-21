<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!doctype html>
<html>
<head>
  <%@ include file="include/meta.jsp" %>
  <title>${model.categoryName}-${site.siteName}</title>
  <meta name="description" content="${model.keywords}">
  <meta name="keywords" content="${model.description}">
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

<%-- <header class="am-g am-g-fixed blog-fixed blog-text-center blog-header">
    <div class="am-u-sm-8 am-u-sm-centered">
        <img width="200" src="http://s.amazeui.org/media/i/brand/amazeui-b.png" alt="Amaze UI Logo"/>
        <h2 class="am-hide-sm-only">${model.siteName}</h2>
    </div>
</header>
<hr> --%>

<%@include file="include/navs.jsp" %>

<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
    <div class="am-u-md-8 am-u-sm-12">
    	<%@include file="include/article.jsp" %>
       
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

var totalPage=${empty totalPage?0:totalPage};

//显示分页
laypage({
 cont: 'pagination', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
 pages: totalPage, //通过后台拿到的总页数
 curr: function(){//通过url获取当前页，也可以同上（pages）方式获取
		var currentPage = location.search.match(/currentPage=(\d+)/);
 		return currentPage ? currentPage[1] : 1;
 }(), //当前页
 skip: false, //是否开启跳页
 skin: '#5eb95e',
 groups: 3, //连续显示分页数
 jump: function(obj, first){ //触发分页后的回调
     if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
     	 location.href = global.frontPath+'/index?cid=1&currentPage='+obj.curr;
     }
 }
});

$(function(){
	
	
	$.ajax({
		type: "POST",
		data: {},
		dataType: 'json',
		url: global.frontPath+'/recommend?cid=${siteId}&categoryId=${model.categoryId}',
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