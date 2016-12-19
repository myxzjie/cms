<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
<%@ include file="include/meta.jsp"%>
<title>登录-${site.siteName}</title>
<meta name="description" content="${site.keywords}">
<meta name="keywords" content="${site.description}">
<%@ include file="include/style.jsp"%>
<style>
.header {
	text-align: center;
}

.header h1 {
	font-size: 200%;
	color: #333;
	margin-top: 30px;
}

.header p {
	font-size: 14px;
}
</style>
</head>
<body class="login-body" >
	<div class="login-boxtitle">
		<a href="${ctx_front}/index?cid=${site.siteId}">
		<img alt="logo" src="${ctx}/favicon.ico" />
		${site.siteName}
		</a>
	</div>

	<div class="login-banner">
		<div class="login-main">
			<div class="login-banner-bg">
				<span></span>
				<img src="${ctx}/resources/front/images/big.jpg" />
			</div>
			<div class="login-box">

				<h3 class="title">登录</h3>

				<div class="clear"></div>

				<form id="login-form" action="${ctx_front}/auth" method="post">
				<div class="login-form">
					
						<div class="user-name">
							<label class="label" for="user"><i class="am-icon-user"></i></label> 
							<input type="text" name="username" id="user" placeholder="邮箱/手机/用户名">
						</div>
						<div class="user-pass">
							<label class="label" for="password"><i class="am-icon-lock"></i></label> 
							<input type="password" name="password" id="password" placeholder="请输入密码">
						</div>
						<div class="user-captcha">
							<input type="text" name="verifycode" id="verifycode" placeholder="请输入验证码">
							<img id="captcha_img" src="${ctx}/kaptcha/image" >
							<a id="kanbuq" href="javascript:;">换一张</a>
						</div>
					
				</div>

				<div class="login-links">
					<label for="remember-me">
					<input id="remember-me" name="rememberMe" type="checkbox">记住密码</label> 
					<a href="#" class="am-fr">忘记密码</a> 
					<a href="${frontPath}/register?cid=${site.siteId}" class="zcnext am-fr ">注册</a> 
					<br />
				</div>
				<div class="am-cf">
					<button type="button" id="btn_login" class="am-btn am-btn-primary am-btn-sm">
					登 录
					</button>
				</div>
				</form>
				<div class="partner">
					<!-- <h3>合作账号</h3> -->
					<ul class="am-btn-group">
						<li>
						<a href="#"><i class="am-icon-qq am-icon-sm"></i><span>QQ登录</span></a>
						</li>
						<li>
						<a href="#"><i class="am-icon-weibo am-icon-sm"></i><span>微博登录</span></a>
						</li>
						<li>
						<a href="#"><i class="am-icon-weixin am-icon-sm"></i><span>微信登录</span></a>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</div>


	<div class="footer ">
		<div class="footer-hd ">
			<p>
				<!-- <a href="# ">XXX</a> 
				<b>|</b> 
				<a href="# ">XXX</a> 
				<b>|</b> 
				<a href="# ">XXX</a> 
				<b>|</b> 
				<a href="# ">XXX</a> -->
			</p>
		</div>
		<div class="footer-bd ">
			<p>
				<!-- <a href="# ">XXX</a> 
				<a href="# ">XXX</a> 
				<a href="# ">XXXX</a> 
				<a href="# ">XXX</a>  -->
				<em>Copyright © 2016 dev56.com All Rights Reserved 闽ICP备16032514号-1</em>
			</p>
		</div>
	</div>
	<%@include file="include/script.jsp" %>
	<script type="text/javascript">
	$(function(){
		$('#kanbuq').click(function(){
			$('#captcha_img').attr('src','${ctx}/kaptcha/image');
		});
		
		$('#btn_login').click(function(){
			var form= $('#login-form');
			var data={};
			data=form.serializeJSON();
			
			$.ajax({
				type: "POST",
				data: data,
				dataType: 'json',
				url:form[0].action,
				success: function(res){
					if(res.success){
						var referrer = document.referrer; 
					    if (!referrer) { 
					    	location.href=global.frontPath+"/index?cid=1"
					        //try { 
					        //    if (window.opener) { 
					        //        // ie下如果跨域则抛出权限异常 
					        //        // safari和chrome下window.opener.location没有任何属性 
					        //        referrer = window.opener.location.href; 
					        //    } else{
					        //    	location.href=global.frontPath+"/index?cid=1"
					        //    }
					       // }  
					       // catch (e) {} 
					    } else{
					    	if(referrer.indexOf("/f/register")>-1){
					    		location.href=global.frontPath+"/index?cid=1"
					    	}else{
					    		location.href=referrer;
					    	}
					    }
						
					}else{
						layer.alert(res.message, {icon: 2});
					}
				}
			});
		});
	})
	</script>
</body>
</html>