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
				<div class="am-tabs" id="doc-my-tabs">
					<ul class="am-tabs-nav am-nav am-nav-tabs am-nav-justify">
						<li class="am-active"><a href="">邮箱注册</a></li>
						<li><a href="">手机号注册</a></li>
					</ul>
					
					<div class="am-tabs-bd">
						<div class="am-tab-panel am-active">
							<form method="post">
								<div class="user-email">
									<label class="label" for="email"><i class="am-icon-envelope-o"></i></label>
									<input class="" type="email" name="eMail" id="email" placeholder="请输入邮箱账号">
								</div>										
                 				<div class="user-pass">
									<label class="label" for="password"><i class="am-icon-lock"></i></label>
								    <input type="password" name="password" id="password" placeholder="设置密码">
                				 </div>										
                 				<div class="user-pass">
									<label class="label" for="passwordRepeat"><i class="am-icon-lock"></i></label>
								    <input type="password" name="passwordRepeat" id="passwordRepeat" placeholder="确认密码">
                 				</div>	
                 				
                 				<!-- <div class="login-links">
									<label for="reader-me">
										<input id="reader-me" type="checkbox"> 点击表示您同意商城《服务协议》
									</label>
							  	</div> -->
										
								<div class="am-cf">
									<button type="submit" name=""  class="am-btn am-btn-primary am-btn-sm am-fl">
									注册
									</button>
								</div>
                 
                 			</form>
						</div>
						
						<div class="am-tab-panel">
							<form method="post">
								<div class="user-phone">
								    <label class="label" for="phone"><i class="am-icon-mobile-phone am-icon-md"></i></label>
								    <input type="tel" name="phone" id="phone" placeholder="请输入手机号">
                 				</div>
                 				<!-- <div class="verification">
									<label class="label" for="code"><i class="am-icon-code-fork"></i></label>
									<input type="tel" name="phonecode" id="code" placeholder="请输入验证码">
									<a class="am-btn am-btn-default" href="javascript:void(0);" onclick="sendMobileCode();" id="sendMobileCode">
										<span id="dyMobileButton">获取验证码</span></a>
								</div> -->
								
								<div class="user-pass">
									<label class="label" for="password"><i class="am-icon-lock"></i></label>
									<input type="password" name="password" id="password" placeholder="设置密码">
				                </div>										
				                <div class="user-pass">
									<label class="label" for="passwordRepeat"><i class="am-icon-lock"></i></label>
									<input type="password" name="" id="passwordRepeat" placeholder="确认密码">
				                </div>	
				                
				                <!-- <div class="login-links">
										<label for="reader-me">
											<input id="reader-me" type="checkbox"> 点击表示您同意商城《服务协议》
										</label>
							  	</div> -->
								<div class="am-cf">
									<button type="submit" name=""  class="am-btn am-btn-primary am-btn-sm am-fl">
									注册
									</button>
								</div>
							</form>
						</div>
					</div>
					
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
		$('#doc-my-tabs').tabs();
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
					        try { 
					            if (window.opener) { 
					                // ie下如果跨域则抛出权限异常 
					                // safari和chrome下window.opener.location没有任何属性 
					                referrer = window.opener.location.href; 
					            } 
					        }  
					        catch (e) {} 
					    } else{
					    	location.href=referrer;//global.frontPath+"/index?cid=1"
					    }
						/* layer.alert(res.message, {icon: 1});
						var index = parent.layer.getFrameIndex(window.name);
						if(href){
							parent.location.href=href;
						}else{
							parent.location.href=location.href;
						}
						parent.layer.close(index); */
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