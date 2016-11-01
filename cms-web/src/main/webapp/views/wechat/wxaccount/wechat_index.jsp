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
</head>
<body>
<nav class="breadcrumb">
<!-- <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 -->
<a class="btn btn-success btn-refresh radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="pd-20">
	<div class="text-l"> 
		<form id="query_form">
		公众帐号名称：
		<input type="text" class="input-text" style="width:150px" placeholder="公众帐号名称" id="" name="wxName">
		<button type="button" class="btn btn-success" id="btn_query" ><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	<span class="l">
	<a href="javascript:;" onclick="tgridObj.add();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
	<a href="javascript:;" onclick="tgridObj.edit();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe60c;</i> 编辑</a>
	<a href="javascript:;" onclick="tgridObj.del();" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a> 
	</span> 
	<!-- <span class="r">共有数据：<strong>54</strong> 条</span>  -->
	</div>
	<table id="datatable"></table>
	<div id="pagination" class="cl pd-10 r"></div>
	
</div>
<%@ include file="/common/script.jsp" %>
<script type="text/javascript" src="${ctx}/resources/assets/js/wechat/wxaccount/wechat_index.js"></script>
<script type="text/javascript">

</script>
</body>
</html>

