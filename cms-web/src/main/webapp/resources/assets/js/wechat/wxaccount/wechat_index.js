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
				url:global.basePath+'/wxaccount/datapage'
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
		    title : '账号appid',
		    field : 'wxAccId'
		},{
		    width : '120',
		    align : 'center',
		    title : '公众帐号名称',
		    field : 'wxName'
		},{
			width : '120',
			align : 'center',
		    title : '公众微信号',
		    field : 'wxCode'
		},{
		    width : '120',
		    align : 'center',
		    title : '公众帐号TOKEN',
		    field : 'token'
		},{
			width : '120',
			align : 'center',
			field :'stype',
			title :'公众号类型',
			formatter:function(value,row,index){
				switch (value) {
		        case 1:
		            return '服务号';
		        case 2:
		            return '订阅号';
		        case 2:
		            return '企业号';
		        default:
		        	return '未知';
				}
			}
		},{
		    width : '120',
		    align : 'center',
		    title : '公众帐号邮箱',
		    field : 'email'
		},{
		    width : '120',
		    align : 'center',
		    title : '公众帐号APPID',
		    field : 'appid'
		},{
		    width : '120',
		    align : 'center',
		    title : '公众帐号APPSECRET',
		    field : 'appsecret'
		},{
			width : '120',
			align : 'center',
			title : '创建时间',
			field:'createDate',
			formatter:function(value,row,index){
				return new Date(value).format("yyyy-MM-dd");
			}
		},{
			title:'公众帐号描述',
			field:'description'
		}],
		add:function(){
			var url= global.basePath+'/wxaccount/edit?_='+new Date().getTime();
			layer_show('添加',url,800,500);
		},
		edit:function(){
			var obj=$('#datatable').tgrid('getSelections');
			if(obj.length!=1){
				layer.alert('请选择一条', {icon: 7});
				return false;
			}
			
			var url= global.basePath+'/wxaccount/edit?_='+new Date().getTime()+'&id='+obj[0].wxAccId;
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
				data.id=obj[0].wxAccId;
				$.ajax({
					type: "POST",
					data: data,
					dataType: 'json',
					url: global.basePath+'/wxaccount/delete',
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