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
.mfs-container {
	/* min-width: 150px;max-height: 100px;  */
	/* position: relative; */
	
}

.mfs-selected-option {
	border: 1px #ccc solid;
	display: inline-block;
	width: 100%;
	font-size: 14px;
	height: 31px;
	line-height: 2.42857;
	padding-left: 4px;
}

.mfs-options {
position: absolute;
	border: 1px #ccc solid;
	/* border-top:0px; */
	width: 100%;
	margin: 0;
	padding: 0px;
	color: #333;
	overflow: auto;
	min-height: 100px;
	max-height: 300px;
	background: #ffffff;
	z-index: 9;
	 margin-top: -6px; 
}
</style>
</head>
<body>
<div class="pd-20">
	
	<form action="${ctx}/org/${model.orgId==null?'save':'update'}" method="post" class="form form-horizontal" id="form_add">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>上级组织系统：</label>
			<div class="formControls col-5">
				<input type="hidden" name="orgId" value="${model.orgId}">
				<input type="text" class="input-text" id="orgPId" name="orgPId" placeholder="" readonly="readonly" value="${model.orgPId}"  datatype="*" nullmsg="选择上级" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>组织系统：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="" name="orgName" datatype="*" nullmsg="组织系统" value="${model.orgName}" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>组织全名：</label>
			<div class="formControls col-5">
				<input type="text" placeholder="" autocomplete="off" class="input-text "  datatype="*" nullmsg="组织全名" name="unit" value="${model.unit}">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3">备注：</label>
			<div class="formControls col-5">
				<textarea name="orgDesc" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)">${model.orgDesc}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		  
	</form>
	
	<div id="resourceContent" class="resourceContent">
		<ul id="resourceTree" class="ztree selectZtree" style="margin-top: 0; width: 160px;"></ul>  
	</div>
</div>
<%@ include file="/common/script.jsp" %>
<script type="text/javascript" src="${ctx}/resources/assets/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/Validform/5.3.2/Validform.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/js/droptree.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/js/org/org_edit.js"></script>
<script type="text/javascript">

</script>
</body>
</html>