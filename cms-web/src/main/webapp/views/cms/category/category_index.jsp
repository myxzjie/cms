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
		<!-- 日期范围：
		<input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;"> -->
		<input type="text" class="input-text" style="width:250px" placeholder="输入名称" id="" name="name">
		<button type="button" class="btn btn-success" id="btn_query" ><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		</form>
	</div>
	<div class="cl pd-5 mt-5"> 
	<span class="l"><!-- bg-1 bk-gray mt-20 -->
	<a href="javascript:;" onclick="tgridObj.add();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
	<a href="javascript:;" onclick="tgridObj.edit();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe60c;</i> 编辑</a>
	<!-- <a href="javascript:;" onclick="tgridObj.unlocked();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe605;</i>解锁</a> 
	<a href="javascript:;" onclick="tgridObj.locked();" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe60e;</i> 锁定</a> -->
	<!-- <a href="javascript:;" onclick="tgridObj.del();" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe605;</i>删除</a>  -->
	</span>  
	<!-- <span class="r">共有数据：<strong>54</strong> 条</span>  -->
	</div>
	<table id="datatable"></table>
	<div id="pagination" class="cl pd-10 r"></div>
	
	
</div>
<%@ include file="/common/script.jsp" %>
<script type="text/javascript">

$(function(){
	
	$('#btn_query').click(function(e){
		var params={};
		params=$('#query_form').serializeJSON();
		
		$('#datatable').tgrid('load',params);
	})
	tgridObj.init();
	
});


var tgridObj={
		init:function(){
			$('#datatable').tgrid({
				columns:tgridObj.columns,
				checkbox:true,
				url:global.basePath+'/category/datapage'
			});
		},
		load:function(){
			var params={};
			params=$('#query_form').serializeJSON();
			$('#datatable').tgrid('load',params);
		},
		columns:[{
		    width : '80',
		    align : 'center',
		    title : '栏目名称',
		    field : 'categoryName'
		},{
		    width : '80',
		    title : '上级栏目',
		    align : 'center',
		    field : 'perentName'
		},{
			width : '80',
			align : 'center',
		    title : '排序',
		    field : 'categoryOrder'
		}],
		add:function(){
			var url= global.basePath+'/category/edit';
			layer_show('添加',url,800,500);
		},
		edit:function(){
			var obj=$('#datatable').tgrid('getSelections');
			
			if (obj.length != 1) {
				layer.alert('请选择一条', {
					icon : 7
				});
				return false;
			}

			var url = global.basePath + '/category/edit?id=' + obj[0].categoryId;
			layer_show('编辑', url, 800, 500);
		},
		del : function() {

			var obj = $('#datatable').tgrid('getSelections');

			if (obj.length != 1) {
				layer.alert('请选择一条', {
					icon : 7
				});
				return false;
			}

			layer.confirm('确认要删除吗？', function(index) {
				var data = {};
				data.id = obj[0].userId;
				$.ajax({
					type : "POST",
					data : data,
					dataType : 'json',
					url : global.basePath + '/account/delete',
					success : function(res) {
						if (res.success) {
							tgridObj.load();
							layer.alert('已删除!', {
								icon : 1
							});
						} else {
							layer.alert(res.message, {
								icon : 2
							});
						}
					}
				});

				/* $(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000}); */
			});
		},
		unlocked : function() {
			var obj = $('#datatable').tgrid('getSelections');

			if (obj.length != 1) {
				layer.alert('请选择一条', {
					icon : 7
				});
				return false;
			}

			if (obj[0].locked == 1) {
				layer.alert('该用户是正常状态，不许再解锁', {
					icon : 7
				});
				return false;
			}

			layer.confirm('确认要解锁吗？', function(index) {
				var data = {};
				data.id = obj[0].userId;
				$.ajax({
					type : "POST",
					data : data,
					dataType : 'json',
					url : global.basePath + '/account/unlocked',
					success : function(res) {
						if (res.success) {
							tgridObj.load();
							layer.alert('已解锁!', {
								icon : 1
							});
						} else {
							layer.alert(res.message, {
								icon : 2
							});
						}
					}
				});

			});
		},
		locked : function() {
			var obj = $('#datatable').tgrid('getSelections');

			if (obj.length != 1) {
				layer.alert('请选择一条', {
					icon : 7
				});
				return false;
			}
			if (obj[0].locked == 0) {
				layer.alert('该用户是冻结状态，不许再锁定', {
					icon : 7
				});
				return false;
			}

			layer.confirm('确认要锁定吗？', function(index) {
				var data = {};
				data.id = obj[0].userId;
				$.ajax({
					type : "POST",
					data : data,
					dataType : 'json',
					url : global.basePath + '/account/locked',
					success : function(res) {
						if (res.success) {
							tgridObj.load();
							layer.alert('已解定!', {
								icon : 1
							});
						} else {
							layer.alert(res.message, {
								icon : 2
							});
						}
					}
				});

			});
		}

	}

	function getDataTable(index) {
		var data = {};

		$.ajax({
			type : "POST",
			data : data,
			dataType : 'json',
			url : global.basePath + '/resource/data',
			success : function(res) {
				var data = res.data;
				var datatable = document.getElementById("datatable");
				var tbody = datatable.getElementsByTagName('tbody')[0];
				clearRow(tbody);
				addRow(tbody, columns, data);
				var page = res.page;

				if (!index || index == 0) {
					//显示分页
					laypage({
						cont : 'pagination', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
						pages : page.totalPage, //通过后台拿到的总页数
						curr : page.currentPage, //当前页
						skip : true, //是否开启跳页
						skin : '#5eb95e',
						groups : 3, //连续显示分页数
						jump : function(obj, first) { //触发分页后的回调
							if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
								getDataTable(obj.curr);
							}
						}
					});
				}

			}
		});
	}

	function clearRow(table, index) {
		//清空列表，移除全部行的方法
		if (index) {
			table.deleteRow(index);
		} else {
			while (table.rows.length > 0) {
				table.deleteRow(0);
			}
		}
	}

	function addRow(table, columns, data) {
		for (var i = 0; i < data.length; i++) {
			var row = table.insertRow();
			row.className = 'text-c';
			addCell(row, columns, data[i]);
		}

		//return row;
	}

	function addCell(row, columns, data) {
		for (var i = 0; i < columns.length; i++) {
			var cell = row.insertCell(i);
			if (columns[i].className) {
				cell.className = columns[i].className;
			}
			var value = data[columns[i].field]
			if (value) {
				cell.innerText = value;
			}
			if (columns[i].formatter) {
				cell.innerHTML = columns[i].formatter(value, data[i], i);
			}
		}
	}

	var columns = [/* {
	    width : '120',
	    title : '用户名',
	    field : 'checkbox',
	    formatter:function(value,row,index){
	    	var _html='<input type="checkbox" value="'+index+'" name="checkbox">';
	    	return _html;
	    }
	}, */];
</script>
</body>
</html>

