<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>${client.orgName}-登录</title>
<meta name="keywords" content="">
<meta name="description" content="">
<%@ include file="/common/style.jsp"%>
<link href="${ctx}/resources/assets/css/H-ui.login.css" rel="stylesheet"
	type="text/css" />
<style>
.error {
	color: red;
}
</style>
</head>
<body>
<!-- <div class="header"></div> -->
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form action="" class="form form-horizontal" method="post">
      <div class="row cl">
      	<label class="form-label col-3"></label>
      	<div class="formControls col-8">
			<c:choose>
			<c:when test="${not empty error}">
			<div class="error" style="color: red;">${error}</div>
			</c:when>
			<c:otherwise>
			<!-- 请输入您的用户名和密码 -->
			</c:otherwise>
			</c:choose>
		</div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
        	<input type="hidden" id="TenantId" name="TenantId" value="" />
        	<input type="hidden" id="stype" name="stype" value="1">
            <input type="hidden" name="isMobile" value="false">
        	<input type="text" name="username" placeholder="用户名/邮箱/手机" value="<shiro:principal property="username"/>" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input type="password" name="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" name="captcha"  style="width:150px;">
          <!-- onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" -->
          <img src="${ctx}/kaptcha/image" style="width: 100px;height: 35px;"> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online">
            <input type="checkbox" id="rememberMe" name="rememberMe" value="true">
            自动登录
          </label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <!-- <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;"> -->
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 你的公司名称 by xxx.v0.0.1</div>
<%@ include file="/common/script.jsp" %>

	<%-- <div>使用你的Shiro示例Server帐号访问 [${client.orgName}]
		，并同时登录Shiro示例Server</div>
	<div class="error">${error}</div>

	<form action="" method="post">
		用户名：<input type="text" name="username"
			value="<shiro:principal property="username"/>"><br /> 密码：<input
			type="password" name="password"><br /> <input type="submit"
			value="登录并授权">
	</form> --%>

</body>
</html>