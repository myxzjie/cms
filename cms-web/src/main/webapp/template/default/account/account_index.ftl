<#include "/_inc/_layout.ftl"/>

<#macro css>
<link href="${cdn}/css/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
<style type="text/css">
.query-toolbar{
	margin-bottom: 30px;
}
.fixed-table-toolbar .bs-bars, .fixed-table-toolbar .search, .fixed-table-toolbar .columns {
    position: relative;
    margin-top: 1px;
    margin-bottom: 1px;
    line-height: 34px;
}
</style>
</#macro>

<#macro script>
	<script src="${cdn}/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="${cdn}/js/plugins/bootstrap-table/bootstrap-table-mobile.js"></script>
    <script src="js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script>
    	$(function () {
		    //toolbar();
		    initTable()
		    //$('#btn-query').click(function () {
		    //    $table.bootstrapTable('destroy');
		    //    initTable();
		    //});
		});
    	function initTable() {
		
		    $table = $('#table_data').bootstrapTable({
		        url: '/account/dataPage',  //请求后台的URL（*）
		        method: 'get',   //请求方式（*）
		        toolbar: '#toolbar',  //工具按钮用哪个容器
		        striped: true,   //是否显示行间隔色
		        cache: false,   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		        pagination: true,   //是否显示分页（*）
		        sortable: false,   //是否启用排序
		        sortOrder: "desc",   //排序方式
		        queryParams: function queryParams(params) { //传递参数（*）
		            params.username = $('#usrename').val();
		            return params;
		        },
		        sidePagination: "server",  //分页方式：client客户端分页，server服务端分页（*）
		        pageNumber: 1,   //初始化加载第一页，默认第一页
		        pageSize: 10,   //每页的记录行数（*）
		        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
		        search: false,   //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		        strictSearch: true,
		        showColumns: false,   //是否显示所有的列
		        showRefresh: false,   //是否显示刷新按钮
		        minimumCountColumns: 2,  //最少允许的列数
		        clickToSelect: true,  //是否启用点击选中行
		        height: 400,   //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		        uniqueId: "ID",   //每一行的唯一标识，一般为主键列
		        showToggle: false,   //是否显示详细视图和列表视图的切换按钮
		        cardView: false,   //是否显示详细视图
		        detailView: false,   //是否显示父子表
		        columns: [
		            {
		                checkbox: true
		            },
		            {
		                field: 'username',
		                title: '用户名'
		            },
		            {
		                field: 'role_name',
		                title: '角色'
		            },
		            {
		                field: 'create_time',
		                title: '创建时间',
		                formatter: function (value, row, index) {
		                    return  (new Date(value)).format("yyyy-MM-dd HH:mm:ss");
		                }
		            },
		            {
		                field: 'desc',
		                title: '备注'
		            }
		        ]
		    });
		}
	</script>
	<script src="${ctx}/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx}/js/content.min.js"></script>
    <script src="${ctx}/js/welcome.min.js"></script>
</#macro> 

<@layout bcls='class="gray-bg"'>
<div class="wrapper wrapper-content animated fadeInRight">
        <!-- Panel Other -->
        <div class="ibox float-e-margins">
            <!--<div class="ibox-title">
                <h5>其他</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#">选项1</a>
                        </li>
                        <li><a href="#">选项2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>-->
            <div class="ibox-content">
                <div class="row row-lg">

                    <div class="col-sm-12">
                        <!-- Example Toolbar -->
                        <div class="example-wrap">
                            <h4 class="example-title">工具条</h4>
                            <div  class="example query-toolbar">
                               <form class="form-inline" role="form">
					                <div class="form-group">
					                    <label class="sr-only" for="usrename">用户名</label>
					                    <input type="text" class="form-control" id="usrename" placeholder="用户名">
					                </div>
					
					                <button id="btn-query" type="button" class="btn btn-primary">查询</button>
					
					            </form>
                            </div>
                        </div>
                        <!-- End Example Toolbar -->
                    </div>

                    <div class="col-sm-12">
                        <!-- Example Pagination -->
                        <div class="example-wrap">
                            <div class="example">
                            	<div id="toolbar">
				                    <button id="btn-add" type="button" class="btn btn-primary">添加</button>
				                    <button id="btn-update" type="button" class="btn btn-primary">修改</button>
				                    <button id="btn-updatepwd" type="button" class="btn btn-primary">修改密码</button>
				                    <button id="btn-scene" type="button" class="btn btn-primary">设置监控点</button>
				                </div>
                                <table id="table_data" >
                                    
                                </table>
                            </div>
                        </div>
                        <!-- End Example Pagination -->
                    </div>

                    <div class="col-sm-12">
                        <!-- Example Events -->
                        <div class="example-wrap">
                            <h4 class="example-title">事件</h4>
                            <div class="example">
                                <div class="alert alert-success" id="examplebtTableEventsResult" role="alert">
                                    事件结果
                                </div>
                                <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                    </button>
                                </div>
                                <table id="exampleTableEvents" data-height="400" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="id">ID</th>
                                            <th data-field="name">名称</th>
                                            <th data-field="price">价格</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <!-- End Example Events -->
                    </div>
                </div>
            </div>
        </div>
        <!-- End Panel Other -->
    </div>
    
</@layout>
