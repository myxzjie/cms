/**
 * 
 */

$(function(){

	$('#btn_query').click(function(e){
		tgridObj.load();
	})
	tgridObj.init();
	
});


var tgridObj={
		init:function(){
			$('#datatable').tgrid({
				columns:tgridObj.columns,
				checkbox:true,
				url:global.basePath+'/org/datapage'
			});
		},
		load:function(){
			var params={};
			params=$('#query_form').serializeJSON();
			$('#datatable').tgrid('load',params);
		},
		columns:[{
		    width : '120',
		    title : '组织名称',
		    field : 'orgName'
		},{
			width : '120',
		    title : '组织父级',
		    field : 'orgPName'
		},{
		    width : '120',
		    title : '组织全名',
		    field : 'unit'
		},{
			 width : '120',
			title : '创建时间',
			field:'createDate',
			formatter:function(value,row,index){
				return new Date(value).format("yyyy-MM-dd");
			}
		},{
			title:'备注',
			field:'orgDesc'
		}],
		add:function(){
			var url= global.basePath+'/org/edit?_='+new Date().getTime();
			layer_show('添加',url,800,500);
		},
		edit:function(){
			var obj=$('#datatable').tgrid('getSelections');
			
			if(obj.length!=1){
				layer.alert('请选择一条', {icon: 7});
				return false;
			}
			
			var url= global.basePath+'/org/edit?_='+new Date().getTime()+'&id='+obj[0].orgId;
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
				data.id=obj[0].orgId;
				$.ajax({
					type: "POST",
					data: data,
					dataType: 'json',
					url: global.basePath+'/org/delete',
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