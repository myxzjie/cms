<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!doctype html>
<html>
<head>
  <%@ include file="include/meta.jsp" %>
  <title>${model.title}-${site.siteName}</title>
  <meta name="description" content="${model.keywords}">
  <meta name="keywords" content="${model.description}">
  <%@ include file="include/style.jsp" %>
  
</head>
<body id="blog">
	<jsp:include page="include/navs.jsp">
	   	<jsp:param name="active" value="index"/>
	</jsp:include>


<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
	<ol class="am-breadcrumb" style=" margin-bottom: 0rem;">
			  <li><a href="/" class="am-icon-home">首页</a></li>
			  <li><a href="/user/personalInfo">个人主页</a></li>
			  <li class="am-active">详情</li>
			</ol>
	<hr style="margin: 0 0 0.5rem;">	
	
	<div class="am-u-md-8 am-u-sm-12" style="padding-left:0">
    	<div class="am-panel am-panel-default">
    		<c:forEach var="article" items="${articles}">
    		<div class="inner-box blog-img ">
    			<c:if test="${article.image !=null }">
	  			<div class="am-g">
		  			<div class="am-u-sm-4">
						<a class="blog-a-curse" target="_blank" href="${ctx_front}/article?cid=${site.siteId}&id=${article.articleId}">
						<img alt="" class="am_img animated thumbnails"src="${uploadImageWeb}${article.image}" data-original="${uploadImageWeb}${article.image}" >
						</a>
					</div>
					<div class="am-u-sm-8">
				</c:if>
						<div class="blog-header">
							<h2><a target="_blank" href="${ctx_front}/article?cid=${site.siteId}&id=${article.articleId}">${article.title}</a></h2>
						</div>
						<p class="blog-ext">
							<%-- <span> <i class="am-icon-comment blog-color"></i>&nbsp; ${article.countComment} &nbsp;</span> --%>
							<span> <i class="am-icon-link blog-color"></i>&nbsp; ${article.countView} &nbsp;</span>
							<span> <i class="am-icon-user blog-color blog-ext-ico"></i> ${article.authorName} &nbsp;</span>
							<span> <i class="am-icon-clock-o blog-color blog-ext-ico"></i> <fmt:formatDate value="${article.createDate}" pattern="yyyy/MM/dd" /></span>
							<span> <i class="am-icon-tag blog-color blog-ext-ico"></i> 分类:${article.categoryName}</span>
						</p>
						<p class="blog-content-show">
						${article.description}
						</p>
						<div class="am-fr">
						<c:if test="${article.approveStatus==1}">
						<span class="am-text-danger" style="margin-right: 5px;">正在审核中，请耐心等待...</span>
						</c:if>
						<c:if test="${article.approveStatus==0}">
						<button type="button" class="am-btn am-btn-primary am-btn-xs " onclick="publish(${article.articleId})">发布</button>
						</c:if>
						<button type="button" class="am-btn am-btn-primary am-btn-xs " onclick="location.href='${ctx_front}/blog/edit/${article.articleId}?cid=${site.siteId}'">修改</button>
						<button type="button" class="am-btn am-btn-danger am-btn-xs " onclick="deleteblog(${article.articleId})">删除</button>
						
						</div>
				<c:if test="${article.image !=null }">
					</div>
				</div>
				</c:if>
	 		</div>
    		</c:forEach>
	 		
		</div>
        <ul id="pagination" class="am-pagination">
		</ul>
    </div>

    <div class="am-u-md-4 am-u-sm-12 ">
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor">
    	<a href="${ctx_front}/blog/edit?cid=${site.siteId}" class="am-btn am-btn-primary am-btn-block">发布博文</a>
    	</div>
    	
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor" style="margin-top: 5px;">
    	<h2 style="border-bottom: 1px #eee solid; margin: 0 0 0.5rem; padding-left: 0.5rem">关于博主</h2>
    	<div class="blog-sidebar-widget">
    	<%-- <img style="width: 100%" alt="<shiro:principal property="username"/>" src="${uploadImageWeb}<shiro:principal property="photo"/>" > --%>
    	<img style="width: 100%" alt="" src="${ctx}/resources/front/images/user_default.jpg" />
    	
    	<!-- onerror="javascript:this.src='/attached/avatar/default.jpg'" -->
    	<div style="text-align: left;">
    		<ul class="am-list am-list-static am-list-border">
			  <li>
			    <i class="am-icon-user-secret"></i>
			   	 博主:
			   	 <shiro:principal property="username"/>
			   	 <button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '编辑个人信息', trigger: 'hover focus'}" onclick="javascript:location.href='${ctx_front}/personal/edit'">编辑</button>
			  </li>
			  <li>
			    <i class="am-icon-mortar-board"></i>
			   	职业:java工程师
			  </li>
			  <li>
			    <i class="am-icon-newspaper-o"></i>
			   	 简介
			   	 
			  </li>
			 </ul>
    	</div>
    	</div>
    	</div>
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

<%-- ${param.name} --%>
<%-- ${paramValues.name}可以取得所有同名参数的值
${paramValues.hobbies[0]}可以通过指定下标来访问特定的参数的值 --%>

<script type="text/javascript">


<%-- var currentPage=${empty param.currentPage? 1:param.currentPage}; --%>
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
         	 location.href = global.frontPath+'/personal/info?cid=1&currentPage='+obj.curr;
         }
     }
 });
 
 function publish(id){
	 $.ajax({
		type: "POST",
		data: {cid:location.search.match(/cid=(\d+)/)[1]},
		dataType: 'json',
		url:global.frontPath+'/blog/category/1/'+id,
		success: function(res){
			if(res.success){
				//layer.alert("发布成功，请等待申请...", {icon: 1});
				location.reload();
			}else{
				layer.alert(res.message, {icon: 2});
			}
		}
	});
 }
 
 function deleteblog(id){
	 $.ajax({
			type: "POST",
			data: {cid:location.search.match(/cid=(\d+)/)[1]},
			dataType: 'json',
			url:global.frontPath+'/blog/delete/'+id,
			success: function(res){
				if(res.success){					
					location.reload();
				}else{
					layer.alert(res.message, {icon: 2});
				}
			}
		});
 }
</script>
</body>
</html>