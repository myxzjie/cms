<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <%@ include file="include/meta.jsp" %>
  <title>登录-${site.siteName}</title>
  <meta name="description" content="${site.keywords}">
  <meta name="keywords" content="${site.description}">
  <%@ include file="include/style.jsp" %>
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
<body>
<div class="header">
  <!-- <div class="am-g">
    <h1>Web ide</h1>
    <p>Integrated Development Environment<br/>代码编辑，代码生成，界面设计，调试，编译</p>
  </div>
  <hr />-->
</div> 
<div class="am-g" style="margin-top: 5rem;">
  <div class="am-u-lg-4 am-u-md-8 am-u-sm-centered">
    <h3 class="blog-text-center">登录</h3>
    <!-- <hr>
    <div class="am-btn-group">
      <a href="#" class="am-btn am-btn-secondary am-btn-sm"><i class="am-icon-github am-icon-sm"></i> Github</a>
      <a href="#" class="am-btn am-btn-success am-btn-sm"><i class="am-icon-google-plus-square am-icon-sm"></i> Google+</a>
      <a href="#" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-stack-overflow am-icon-sm"></i> stackOverflow</a>
    </div> -->
   <!--  <br>
    <br>
 -->
    <form method="post" class="am-form">
      <label for="email">邮箱:</label>
      <input type="email" name="" id="email" value="">
      <br>
      <label for="password">密码:</label>
      <input type="password" name="" id="password" value="">
      <br>
      <label for="remember-me">
        <input id="remember-me" type="checkbox">
        记住密码
      </label>
      <br />
      <div class="am-cf">
        <input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
        <!-- <input type="submit" name="" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr"> -->
      </div>
    </form>
    <hr>
    <p>Copyright © 2016 dev56.com All Rights Reserved 闽ICP备16032514号-1</p>
  </div>
</div>
</body>
</html>