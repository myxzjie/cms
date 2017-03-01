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
    <div class="am-u-md-8 am-u-sm-12" >
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
		dataType: 'xml',
		contentType: "application/xml; charset=utf-8",
		scriptCharset: "utf-8" ,
		url: 'http://localhost:8080/cms-web/api/example/demoitem.xml',
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

/* 
//获取滚动条当前的位置  
function getScrollTop() {  
    var scrollTop = 0;  
    if (document.documentElement && document.documentElement.scrollTop) {  
        scrollTop = document.documentElement.scrollTop;  
    }  
    else if (document.body) {  
        scrollTop = document.body.scrollTop;  
    }  
    return scrollTop;  
}  
  
//获取当前可是范围的高度  
function getClientHeight() {  
    var clientHeight = 0;  
    if (document.body.clientHeight && document.documentElement.clientHeight) {  
        clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);  
    }  
    else {  
        clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);  
    }  
    return clientHeight;  
}  
  
//获取文档完整的高度  
function getScrollHeight() {  
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);  
}  
var currentPage=3;
var isload=true;

window.onscroll = function () { 
	if(!isload){
		return false;
	}
    if (getScrollTop() + getClientHeight() == getScrollHeight()) {
    	var data={};
    	data.currentPage=currentPage;
    	data.showCount=global.showCount;
    	data.cmId=$('#cmId').val();
    	
    	$.ajax({
			type: "POST",
			data: data,
			dataType: 'json',
			url: global.basePath+'/f/material/datapage/2',
			success: function(res){
				if(res.success){
					currentPage++;
					var data=res.data;
					if(data.length<1){
						isload=false;
						$('.notdata').show();
						return false;
					}else{
						$('.notdata').hide();
					}
					
					var downloadFileWeb=$('#downloadFileWeb').val();
					
					for(var i=0;i<data.length;i++){
						var row=data[i];
						var models=$(".models").clone()[0];
						//console.log(models);
						var imgurl=global.basePath+'/resources/front/assets/image/icon-img.png';
						var downurl=global.basePath+'/f/material/download?id='+row.materialId+'&fileName='+row.uploadFileName;
						
						if(row.image){
							imgurl=downloadFileWeb+row.image;
						}
						$(models).find('img').attr('src',imgurl);
						$(models).find('img').attr('alt',row.uploadFileName);
						$(models).find('.download h5').text(row.uploadFileName);
						var btn_down= $(models).find('.download');
						$(btn_down).attr('href',downurl);
						$(btn_down).unbind("click")
						$(btn_down).click(function(e){
							location.href =$(this).attr('href');
						});
						
						$('.main-data-box').append(models);
					}
					
				}else{
					layer.alert(res.message, {icon: 2});
				}
			}
		});
    }  
}   */




</script>
</body>
</html>