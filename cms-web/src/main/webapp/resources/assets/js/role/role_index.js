/**
 * 
 */

$(function(){
	init();

	$('#btn_query').click(function(e){
		tgridObj.load();
	})
	tgridObj.init();
	
});

function init(){
	$.ajax({
		type: "POST",
		data: {},
		dataType: 'json',
		url: global.basePath+'/org/data',
		success: function(res){
			if(res.success){
				var data=res.data;
				var orgiId=$('#orgId');
				orgiId.append('<option value="">选择</option>');
				for(var i=0;i<data.length;i++){
					var org=data[i];
					orgiId.append('<option value="'+org.orgId+'">'+org.orgName+'</option>');
				}
				//
				$('#orgId').val($('#h_orgId').val());
			
			}else{
				alert();
				layer.alert(res.message, {icon: 2});
			}
		}
	});
}


var tgridObj={
		init:function(){
			$('#datatable').tgrid({
				columns:tgridObj.columns,
				checkbox:true,
				url:global.basePath+'/role/datapage'
			});
		},
		load:function(){
			var params={};
			params=$('#query_form').serializeJSON();
			$('#datatable').tgrid('load',params);
		},
		columns:[{
		    width : '120',
		    title : '角色名称',
		    field : 'roleName'
		},{
			width : '120',
		    title : '角色编码',
		    field : 'roleCode'
		},{
		    width : '120',
		    title : '组织系统',
		    field : 'orgName'
		},{
			width : '120',
			field :'roleType',
			title :'角色类型',
			formatter:function(value,row,index){
				switch (value) {
		        case 1:
		            return '系统';
		        case 2:
		            return '';
		        }
			}
		},{
			 width : '120',
			title : '创建时间',
			field:'createDate',
			formatter:function(value,row,index){
				return new Date(value).format("yyyy-MM-dd");
			}
		},{
		    width : '120',
		    title : '级别',
		    field : 'roleLevel'
		},{
			title:'备注',
			field:'roleDesc'
		}],
		add:function(){
			var url= global.basePath+'/role/edit?_='+new Date().getTime();
			layer_show('添加',url,800,500);
		},
		edit:function(){
			var obj=$('#datatable').tgrid('getSelections');
			if(obj.length!=1){
				layer.alert('请选择一条', {icon: 7});
				return false;
			}
			
			var url= global.basePath+'/role/edit?_='+new Date().getTime()+'&id='+obj[0].roleId;
			layer_show('编辑',url,800,500);
		},
		del:function(){
			
			var obj=$('#datatable').tgrid('getSelections');
			
			if(obj.length!=1){
				layer.alert('请选择一条', {icon: 7});
				return false;
			}
			
			layer.confirm('确认要删除吗？',function(index){
				var data={};
				data.id=obj[0].roleId;
				$.ajax({
					type: "POST",
					data: data,
					dataType: 'json',
					url: global.basePath+'/role/delete',
					success: function(res){
						if(res.success){
							tgridObj.load();
							layer.alert('已删除!', {icon: 1});
						}else{
							layer.alert(res.message, {icon: 2});
						}
					}
				});
				
			});
		},
		permission:function(){
			var obj=$('#datatable').tgrid('getSelections');
			
			if(obj.length!=1){
				layer.alert('请选择一条', {icon: 7});
				return false;
			}
			
			var url= global.basePath+'/role/permission?_='+new Date().getTime()+'&id='+obj[0].roleId;
			layer_show('设置授权',url,800,500);
		}
		
		
}