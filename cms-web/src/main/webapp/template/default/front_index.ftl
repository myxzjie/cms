<#include "/_inc/_layout.ftl"/>
<#--
<#macro css>
<style type="text/css">ff</style>
</#macro>
-->
<#macro script>
	
</#macro> 

<@layout >
	<#include "_inc/_navs.ftl">
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
			<#include "/_inc/_article.ftl">
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
	    
	        <#include "/_inc/_about.ftl">
	    </div>
	</div>
	<!-- content end -->
	
    <#include "_inc/_footer.ftl">
</@layout>

