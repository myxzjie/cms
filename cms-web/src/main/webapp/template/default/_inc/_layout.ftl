<#assign ctx="${resources}" />
<#assign cdn="${resources}/resources/assets" />
<#assign f_ctx="${ctx}/f" />
<#macro css>
</#macro> 
<#macro script>
</#macro> 
<#--
<#macro css_import>
</#macro>
<#macro script_import>
</#macro> 
-->
<#macro layout title="" keywords="" description="" bcls="" charset="utf-8" lang="zh-CN">
<!DOCTYPE HTML>
<html lang="${lang}">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>${title}</title>
	<meta name="keywords" content="${keywords}">
    <meta name="description" content="${description}">
    <meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp"/>
	<link rel="icon" type="image/png" href="${ctx}/favicon.ico">
	<meta name="mobile-web-app-capable" content="yes">
	<link rel="icon" sizes="192x192" href="${ctx}/favicon.ico">
	<#-- <link rel="icon" sizes="192x192" href="${ctx}/resources/front/assets/i/app-icon72x72@2x.png"> -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-title" content="技术网,编程技术网,IT技术网"/>
	<meta name="baidu-site-verification" content="gibLcvPw0x" />
	<link rel="apple-touch-icon-precomposed" href="${ctx}/favicon.ico">
	<meta name="msapplication-TileImage" content="${ctx}/favicon.ico">
	<#-- <link rel="apple-touch-icon-precomposed" href="${ctx}/resources/front/assets/i/app-icon72x72@2x.png">
	<meta name="msapplication-TileImage" content="${ctx}/resources/front/assets/i/app-icon72x72@2x.png"> -->
	<meta name="msapplication-TileColor" content="#0e90d2">

	<base href="${ctx}">

	<link href="${ctx}/resources/front/assets/css/amazeui.min.css" rel="stylesheet" />
	<link href="${ctx}/resources/assets/lib/laypage/1.3/skin/laypage.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/front/assets/css/app.css" rel="stylesheet" type="text/css" >
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
    <@css/>
    
</head>
 <body id="blog" ${bcls}>
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
		  <#assign navs=CategoryTag.getNavs()>
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
	<#nested>
	
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="${ctx}/resources/front/assets/js/jquery.min.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8 ]>
	<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
	<script src="${ctx}/resources/front/assets/js/amazeui.ie8polyfill.min.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${ctx}/resources/front/assets/js/amazeui.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/assets/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/assets/lib/laypage/1.3/laypage.js"></script>  
	<script type="text/javascript" src="${ctx}/resources/assets/js/common.js"></script>
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-88650660-1', 'auto');
	  ga('send', 'pageview');
	
	</script>
    
    <@script/>
 </body>
</html>
</#macro>



 