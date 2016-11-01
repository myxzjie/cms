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
	
	<form action="${ctx}/resource/${model.resourceId==null?'save':'update'}" method="post" class="form form-horizontal" id="form_add">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>上级资源：</label>
			<div class="formControls col-5">
				<input type="hidden" name="resourceId" value="${model.resourceId}">
				<input type="text" class="input-text" id="perentResourceId" name="perentResourceId" placeholder="" readonly="readonly" datatype="*" nullmsg="选择上级资源"  value="${model.perentResourceId}">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>资源名称：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="" name="resourceName" datatype="*" nullmsg="资源名称" value="${model.resourceName}">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>权限：</label>
			<div class="formControls col-5">
				<input type="text" placeholder="" autocomplete="off" class="input-text "  datatype="*" name="permission" value="${model.permission}">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">资源路径：</label>
			<div class="formControls col-5">
				<input type="text" name="resourceUrl" placeholder="" autocomplete="off" class="input-text" value="${model.resourceUrl}" >
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>排序：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="" name="resourceOrder"  datatype="n" nullmsg="不能为空"  errormsg="请输入数字" value="${model.resourceOrder}">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">图标：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" placeholder="" name="resourceIcon" value="${model.resourceIcon}" >
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>资源类型：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<input type="hidden" id="h_resourceType" value="${model.resourceType}">
				<select class="select" id="resourceType" name="resourceType" size="1" datatype="*" nullmsg="不能为空" >
					<option value="1">菜单</option>
					<option value="2">按钮</option>
				</select>
				</span> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">备注：</label>
			<div class="formControls col-5">
				<textarea name="resourceDesc" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" onKeyUp="textarealength(this,100)">${model.resourceDesc}</textarea>
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
<script type="text/javascript" src="${ctx}/resources/assets/js/resources/resource_edit.js"></script>
<script type="text/javascript">


</script>
</body>
</html>