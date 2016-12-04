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
<body>
<div class="am-container" id="main">
	  <div class="am-g am-g-fixed">
	    <ol class="am-breadcrumb">
			  <li><a href="/" class="am-icon-home">首页</a></li>
			  <li><a href="/user/personalInfo">个人主页</a></li>
			  <li class="am-active">详情</li>
			</ol>
		
	    <div class="am-u-md-4 persionalInfo-userinfo">
			<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout">
				  <div class="am-g am-intro-bd">
				    <div class="am-intro-left am-u-sm-4">
					    
	   		 			
					    	<img alt="xzjie" src="/attached/avatar/default.jpg" ><!-- onerror="javascript:this.src='/attached/avatar/default.jpg'" -->
	   		 			
				    </div>
				    <div class="am-intro-right am-u-sm-8" style="margin-top: 10px;font-size: 18px;font-weight: 500;line-height: 1.1;">
				      	<p><span data-am-popover="{content: '用户名', trigger: 'hover focus'}">xzjie</span></p>
				      	<p><span data-am-popover="{content: '昵称', trigger: 'hover focus'}">xzjie</span></p>
				    </div>
				  </div>
				</div>
				<!-- 其他信息 -->
				<ul class="am-list am-list-static am-list-border">
				  <li>
				    <i class="am-icon-pencil"></i>
				   	 您的博客
				   	 <button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '点我管理您的博客', trigger: 'hover focus'}" onclick="javascript:location.href='/blog/myblog/'">2</button>
				  </li>
				  <li>
				    <i class="am-icon-photo"></i>
				   	您的美图 
				   	<button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '点我管理您的美图', trigger: 'hover focus'}" onclick="javascript:location.href='/beauty/myBeauty/'">0</button>
				  </li>
				  <li>
				    <i class="am-icon-play-circle-o"></i>
				   	 您的视频
				   	 <button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '点我管理您的视频', trigger: 'hover focus'}" onclick="javascript:location.href='/video/myVideo/'">0</button>
				  </li>
				  
				  
				  	<li>
					   <i class="am-icon-hand-o-right"></i>
					   	 快捷入口
						<div class="am-btn-group am-btn-group-xs am-fr">
				          <button type="button" class="am-btn am-btn-secondary" onclick="javascript:location.href='/blog/add/'"> 写博客</button>
				          <button type="button" class="am-btn am-btn-secondary" onclick="javascript:location.href='/beauty/add/'"> 传美图</button>
				          <button type="button" class="am-btn am-btn-secondary" onclick="javascript:location.href='/video/add/'">发视频</button>
				        </div>
					  </li>
				  
				</ul>
				<div class="am-alert am-alert-secondary">
				<p class="am-text-xl">尊敬的用户您好！</p>
				<p>注意用户名是您登陆系统的重要信息，昵称提供相关模块展示的作用。用户名不能与系统中的其他用户相同且不能用系统保留字段。</p>
				</div>
				
	    </div>
	    <div class="am-u-md-8">
	    
			<div data-am-widget="tabs" class="am-tabs am-tabs-default am-no-layout">
			  <ul class="am-tabs-nav am-cf">
			   <li class="am-active">
			      <a href="[data-tab-panel-0]">我的资料</a>
			    </li>
			    <li class="">
			      <a href="[data-tab-panel-1]">资料修改</a>
			    </li>
			    <li class="">
			      <a href="[data-tab-panel-2]" onclick="editavatar();">头像修改</a>
			    </li>
			    <li class="">
			      <a href="[data-tab-panel-3]">密码修改</a>
			    </li>
			  </ul>
			  <div class="am-tabs-bd" style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
			  <div data-tab-panel-0="" class="am-tab-panel am-active">
			  		<table class="am-table am-table-bordered am-table-striped am-table-hover">
			  			<thead>
			  				<tr>
			  					<th width="20%">类别</th>
			  					<th width="80%">内容</th>
			  				</tr>
			  			</thead>
					    <tbody>
					        <tr>
					            <td>用户名</td>
					            <td>xzjie</td>
					        </tr>
					        <tr>
					            <td>昵称</td>
					            <td>xzjie</td>
					        </tr>
					        <tr>
					            <td>性别</td>
					            
					            	<td></td>
					            
					            
					             
					        </tr>
					        <tr>
					        	<td>年龄</td>
					        	<td></td>
					        </tr>
					        <tr>
					            <td>民族</td>
					            <td></td>
					        </tr>
					        <tr>
					            <td>邮箱</td>
					            <td>513961835@qq.com</td>
					        </tr>
					        <tr>
					            <td>手机</td>
					            <td></td>
					        </tr>
					        <tr>
					            <td>QQ</td>
					            <td></td>
					        </tr>
					        <tr>
					            <td>地址</td>
					            <td></td>
					        </tr>
					        <tr>
					            <td>职业和工作</td>
					            <td></td>
					        </tr>
					        <tr>
					            <td>创建时间</td>
					            <td>2016-12-04 20:32:19</td>
					        </tr>
					         <tr>
					            <td>最后登陆时间</td>
					            <td>2016-12-04 20:32:33</td>
					        </tr>
					        <tr>
					            <td>类型</td>
					            
						            <td>注册用户</td>
					            
					             
					        </tr>
					    </tbody>
					</table>
			  </div>
			  	<!-- 资料修改tab -->
			    <div data-tab-panel-1="" class="am-tab-panel">
			    	<form class="am-form" data-am-validator="" action="/user/update" method="post" enctype="multipart/form-data" novalidate="novalidate">
					  <fieldset>
					   	<div class="am-form-group">
					      <label for="persionalInfo-username">用户名 <span style="color: red;">*</span></label>
					      <input type="text" id="persionalInfo-username" name="user.username" value="xzjie" pattern="^[a-zA-Z]\w{2,14}$" placeholder="请输入用户名（3~15位数字字母下划线)" required="">
					    </div>
					    <div class="am-form-group">
					      <label for="persionalInfo-username">昵称 </label>
					      <input type="text" id="persionalInfo-username" name="user.realname" value="xzjie" maxlength="6" placeholder="请输入呢称（<=6位任意字符)" required="">
					    </div>
					    <div class="am-form-group">
					      <label for="persionalInfo-sex">性别</label>
					      <select id="persionalInfo-sex" name="user.sex">
					      	
						        <option value="0">男</option>
						        <option value="1">女</option>
					      	
					      	
					      	
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					    <div class="am-form-group">
					    	<label for="persionalInfo-age">年龄</label>
					    	<input type="number" name="user.age" value="" id="persionalInfo-age" max="100" placeholder="请输入年龄" pattern="^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$">
					    </div>
					    	<div class="am-form-group">
					      <label for="persionalInfo-folk">民族</label>
					      <input type="text" id="persionalInfo-folk" name="user.folk" value="" placeholder="请输入民族全称">
					    </div>
					    
					    <div class="am-form-group">
					      <label for="persionalInfo-mail">邮件</label>
					      <input type="email" name="user.mail" value="513961835@qq.com" id="persionalInfo-mail" placeholder="请输入电子邮件" required="" pattern="^((([a-zA-Z]|\d|[!#\$%&amp;'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-zA-Z]|\d|[!#\$%&amp;'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-zA-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-zA-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-zA-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-zA-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-zA-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$">
					    </div>
					    <div class="am-form-group">
					    	<label for="persionalInfo-mobile">手机</label>
					    	<input type="number" name="user.mobile" value="" id="persionalInfo-mobile" maxlength="11" placeholder="请输入手机" pattern="^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$">
					    </div>
					     <div class="am-form-group">
					    	<label for="persionalInfo-qq">QQ</label>
					    	<input type="number" name="user.qq" value="" id="persionalInfo-qq" maxlength="20" placeholder="请输入QQ" pattern="^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$">
					    </div>
					    <div class="am-form-group">
					    	<label for="persionalInfo-address">地址</label>
					    	<input type="text" name="user.address" value="" id="persionalInfo-address" placeholder="请输入地址">
					    </div>
					  	<div class="am-form-group">
					      <label for="persionalInfo-job">职业和工作</label>
					      <input type="text" id="persionalInfo-job" name="user.job" value="" maxlength="10" placeholder="请输入职业和工作)">
					    </div>
					    <p><button type="submit" class="am-btn am-btn-default">提交</button></p>
					  </fieldset>
					</form>
			    </div>
			    <!-- 头像修改pannel -->
			    <div id="personalInfo-editavatardiv" data-tab-panel-2="" class="am-tab-panel">
			    	
			    </div>
			    <!-- 密码修改pannel -->
			    <div data-tab-panel-3="" class="am-tab-panel">
			    	<div class="am-g">
					  <div class="am-u-md-8 am-u-sm-centered">
					    <form class="am-form" enctype="multipart/form-data" method="post" action="/user/updatePassword">
					      <fieldset class="am-form-set">
					      	
						        <input type="password" name="oldpwd" placeholder="原密码">
					      	
					        <input type="password" name="newpwd" placeholder="新密码">
					        <input type="password" name="newpwd_ag" placeholder="重复密码">
					      </fieldset>
					      <button type="submit" class="am-btn am-btn-primary am-btn-block">提交</button>
					    </form>
					  </div>
					</div>
			    </div>
			  </div>
			</div>

	    </div>
	    
	  </div>

	</div>
</body>
</html>