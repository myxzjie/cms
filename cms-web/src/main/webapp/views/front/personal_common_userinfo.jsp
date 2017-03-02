<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>

	<div class="am-u-md-4 am-u-sm-12 ">
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor">
    	<a href="${ctx_front}/blog/edit?cid=${site.siteId}" class="am-btn am-btn-primary am-btn-block">发布博文</a>
    	</div>
    	
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor" style="margin-top: 5px;">
    	<h2 style="border-bottom: 1px #eee solid; margin: 0 0 0.5rem; padding-left: 0.5rem">关于博主</h2>
    	<div class="blog-sidebar-widget">
    	<%-- <img style="width: 100%" alt="<shiro:principal property="username"/>" src="${uploadImageWeb}<shiro:principal property="photo"/>" > --%>
    	<img style="width: 100%" alt="" src="${uploadImageWeb}${model.headPortrait}" onerror="javascript:this.src='${ctx}/resources/front/images/user_default.jpg'" />
    	
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