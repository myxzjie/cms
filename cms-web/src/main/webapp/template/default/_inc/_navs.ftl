
<div class="am-g am-g-fixed blog-fixed ">
<h1 class="am-topbar-brand">
	<img alt="logo" src="${ctx}/resources/assets/images/logo/TRen-32x32.png" />
  <a href="${f_ctx}/index">${site.siteName}</a>
</h1>

<!-- nav start am-g am-g-fixed blog-fixed-->
<nav class=" blog-nav">
<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only blog-button" data-am-collapse="{target: '#blog-collapse'}" ><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="blog-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav">
      <li ><a href="${f_ctx}/index">首页</a></li>
      <#list navs as nav >
      	<#if (nav.children)?exists && nav.children?size == 0>
      	<li ><a href="${f_ctx}/category?cid=${site.siteId}&id=${nav.categoryId}">${nav.categoryName}</a></li>
      	<#elseif (nav.children)?exists && nav.children?size gt 0>
      	<li class="am-dropdown" data-am-dropdown="">
  			<a class="am-dropdown-toggle" data-am-dropdown-toggle="" href="javascript:;">
     		 ${nav.categoryName}<span class="am-icon-caret-down"></span>
     		</a>
     		<ul class="am-dropdown-content">
  			<#list nav.children as children>
  				<li><a href="${f_ctx}/category?cid=${site.siteId}&id=${children.categoryId}">${children.categoryName}</a></li>         
  			</#list>
  			</ul>
      	</li>
      	</#if>
      </#list> 
      
    </ul>
   
   
   <!--  <form class="am-topbar-form am-topbar-right am-form-inline" role="search">
      <div class="am-form-group">
        <input type="text" class="am-form-field am-input-sm" placeholder="搜索">
      </div>
    </form> -->
  </div>
</nav>
</div>
<hr>
<!-- nav end -->