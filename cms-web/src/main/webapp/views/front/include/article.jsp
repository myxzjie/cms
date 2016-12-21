<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<!-- <div class="am-panel am-panel-default"> -->
    		<c:forEach var="article" items="${articles}">
    		<div class="inner-box blog-img " style="border-top: 0px solid #e6e6e6;">
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
						
				<c:if test="${article.image !=null }">
					</div>
				</div>
				</c:if>
	 		</div>
    		</c:forEach>
	 		
		<!-- </div> -->
        <ul id="pagination" class="am-pagination">
		</ul>
		
