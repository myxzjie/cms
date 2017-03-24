<!-- <div class="am-panel am-panel-default"> -->
<#list articles as article>
<div class="inner-box blog-img " style="border-top: 0px solid #e6e6e6;">
	<#if article.image?exists>
	<div class="am-g">
		<div class="am-u-sm-4">
			<a class="blog-a-curse" target="_blank" href="${f_ctx}/article?cid=${site.siteId}&id=${article.articleId}">
			<img alt="" class="am_img animated thumbnails"src="${u_img_url}${article.image}" data-original="${u_img_url}${article.image}" >
			</a>
		</div>
		<div class="am-u-sm-8">
	</#if>
			<div class="blog-header">
				<h2><a target="_blank" href="${f_ctx}/article?cid=${site.siteId}&id=${article.articleId}">${article.title}</a></h2>
			</div>
			<p class="blog-ext">
				<#-- <span> <i class="am-icon-comment blog-color"></i>&nbsp; ${article.countComment} &nbsp;</span> -->
				<span> <i class="am-icon-link blog-color"></i>&nbsp; ${article.countView?default(0)} &nbsp;</span>
				<span> <i class="am-icon-user blog-color blog-ext-ico"></i> ${article.authorName?default('')} &nbsp;</span>
				<span> <i class="am-icon-clock-o blog-color blog-ext-ico"></i> ${article.createDate?string('yyyy/MM/dd')} </span>
				<span> <i class="am-icon-tag blog-color blog-ext-ico"></i> 分类:${article.categoryName}</span>
			</p>
			<p class="blog-content-show">
			${article.description}
			</p>
			
	<#if article.image?exists>
		</div>
	</div>
	</#if>
</div>
</#list>
	
<!-- </div> -->
<ul id="pagination" class="am-pagination">
</ul>