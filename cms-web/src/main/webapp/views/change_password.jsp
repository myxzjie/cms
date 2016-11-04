<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<title>资源</title>
<meta name="keywords" content="">
<meta name="description" content="">
<%@ include file="/common/style.jsp" %>
<link href="${ctx}/resources/assets/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/assets/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/assets/lib/treeselect/jquery.treeselect.css" rel="stylesheet" type="text/css">
<style type="text/css">
.is0{background:url(images/progressImg1.png) no-repeat 0 0;width:138px;height:7px;margin:10px 0 0 104px;}
.is10{background-position:0 -7px;}
.is20{background-position:0 -14px;}
.is30{background-position:0 -21px;}
.is40{background-position:0 -28px;}
.is50{background-position:0 -35px;}
.is60{background-position:0 -42px;}
.is70{background-position:0 -49px;}
.is80{background-position:0 -56px;}
.is90{background-position:0 -63px;}
.is100{background-position:0 -70px;}
 
#autotab input { width:138px; }
</style>
</head>
<body>
<div class="pd-20">
	
	<form action="${ctx}/changepwd" method="post" class="form form-horizontal" id="form_add">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>旧密码：</label>
			<div class="formControls col-5">
				<input type="password" class="input-text" placeholder="" id="password" name="password" datatype="*" nullmsg="旧密码" value="">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>新密码：</label>
			<div class="formControls col-5">
				<input type="password" class="input-text" placeholder="" id="newPassword" name="newPassword" 
				rrormsg="密码至少6个字符,最多18个字符！" datatype="*6-18" plugin="passwordStrength" class="inputxt" nullmsg="新密码" 
				value="">
				<!-- <div id="passwordStrengthDiv" class="is0"></div> -->
				<!-- http://validform.rjboy.cn/document.html -->
				<div class="passwordStrength" style="display:none;"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></div>
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-5">
				<input type="password" class="input-text" placeholder="" id="confirmPassword" name="confirmPassword" datatype="*" nullmsg="确认密码" 
				recheck="newPassword" errormsg="您两次输入的密码不一致！" value="">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		  
	</form>
	
</div>
<%@ include file="/common/script.jsp" %>
<script type="text/javascript" src="${ctx}/resources/assets/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/Validform/5.3.2/Validform.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/lib/Validform/5.3.2/passwordStrength-min.js"></script>
<script type="text/javascript">
$(function(){
	//$('#newPassword').passwordStrength();
	formSubmit('#form_add',global.basePath+"/logout");
	
});
</script>
</body>
</html>