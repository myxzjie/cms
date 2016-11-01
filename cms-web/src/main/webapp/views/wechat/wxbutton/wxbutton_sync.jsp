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
</head>
<body>
<div class="pd-20">
	
	<form action="${ctx}/wxbutton/sync" method="post" class="form form-horizontal" id="form_add">
		<input id="h_roleId" type="hidden" value="${roleId}">
		<div id="list">
		</div>
		<div class="row cl"  style="margin-top: 0;">
			<div class="col-10 col-offset-0">
				<button type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
			</div>
		</div>
		<ul id="buttonTree" class="ztree" style="margin-top: 0; width: 160px;"></ul> 
		
	</form>
	
	
</div>
<%@ include file="/common/script.jsp" %>
<script type="text/javascript" src="${ctx}/resources/assets/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/Validform/5.3.2/Validform.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/js/droptree.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/js/wechat/wxbutton/wxbutton_sync.js"></script>
<script type="text/javascript">


</script>
</body>
</html>