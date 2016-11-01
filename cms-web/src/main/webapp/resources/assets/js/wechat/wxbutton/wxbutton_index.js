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
				url:global.basePath+'/wxbutton/datapage'
			});
		},
		load:function(){
			var params={};
			params=$('#query_form').serializeJSON();
			$('#datatable').tgrid('load',params);
		},
		columns:[{
		    width : '120',
		    align : 'center',
		    title : '菜单名称',
		    field : 'name'
		},{
			width : '120',
			align : 'center',
		    title : '上级菜单',
		    field : 'perentName'
		},{
		    width : '120',
		    align : 'center',
		    title : '菜单标识',
		    field : 'buttonKey'
		},{
		    width : '120',
		    align : 'center',
		    title : '菜单类型',
		    field : 'type',
		    formatter:function(value,row,index){
		    	if(value=='click'){
		    		return '消息触发类';
		    	}else if(value=='view'){
		    		return '网页链接类';
		    	}
		    }
		},{
		    width : '120',
		    align : 'center',
		    title : 'URL',
		    field : 'url'
		},{
		    width : '120',
		    align : 'center',
		    title : '排序',
		    field : 'orders'
		},{
			width : '120',
			align : 'center',
			title : '创建时间',
			field:'createDate',
			formatter:function(value,row,index){
				return new Date(value).format("yyyy-MM-dd");
			}
		}],
		add:function(){
			var url= global.basePath+'/wxbutton/edit?_='+new Date().getTime();
			layer_show('添加',url,800,500);
		},
		edit:function(){
			var obj=$('#datatable').tgrid('getSelections');
			if(obj.length!=1){
				layer.alert('请选择一条', {icon: 7});
				return false;
			}
			
			var url= global.basePath+'/wxbutton/edit?id='+obj[0].buttonId;
			layer_show('编辑',url,800,500);
		},
		sync:function(){
			var url= global.basePath+'/wxbutton/sync';
			$.ajax({
				type: "POST",
				data: {},
				dataType: 'json',
				url:url,
				success: function(res){
					if(res.success){
						layer.alert(res.message, {icon: 1});//+" 3秒后自动刷新"
//						window.setTimeout(function(){
//							location.href=global.basePath+'/wxbutton/index';
//						}, 3000);  
						
					}else{
						layer.alert(res.message, {icon: 2});
					}
				}
			});
			
//			var obj=$('#datatable').tgrid('getSelections');
//			var buttonId='';
//			if(obj.length>1){
//				buttonId=obj[0].buttonId
//			}
//			
//			var url= global.basePath+'/wxbutton/sync?id='+buttonId;
//			layer_show('菜单同步到微信',url,800,500);
		},
		del:function(){
			
			var obj=$('#datatable').tgrid('getSelections');
			
			if(obj.length!=1){
				layer.alert('请选择一条', {icon: 7});
				return false;
			}
			
			layer.confirm('确认要删除吗？',function(index){
				var data={};
				data.id=obj[0].buttonId;
				$.ajax({
					type: "POST",
					data: data,
					dataType: 'json',
					url: global.basePath+'/wxbutton/delete',
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
		}
		
		
}