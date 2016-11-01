/**
 * 
 */

/*$('.skin-minimal input').iCheck({
	checkboxClass: 'icheckbox-blue',
	radioClass: 'iradio-blue',
	increaseArea: '20%'
});*/

$(function() { 
	
	$('#sex').val($('#h_sex').val());
	
	getData.roleData();
	
	toolObj.submit();
}); 


var toolObj={
		submit:function(){
			this.edit();
		},
		edit:function(){
			$("#form_add").Validform({
				tiptype:2,
				ajaxPost:true,
				showAllError:true,
				beforeSubmit:function(curform){
					var data={};
					data=curform.serializeJSON();
					$.ajax({
						type: "POST",
						data: data,
						dataType: 'json',
						url:curform[0].action,
						success: function(res){
							if(res.success){
								layer.alert(res.message, {icon: 1});
								var index = parent.layer.getFrameIndex(window.name);
								//parent.$('.btn-refresh').click();
								parent.location.href=global.basePath+'/wxaccount/index';
								parent.layer.close(index);
							}else{
								layer.alert(res.message, {icon: 2});
							}
						}
					});
					return false;
				}
				/*callback:function(form){
				form[0].submit();
				
				}*/
			});
			
		}
}

var getData={
		roleData:function(){
			$.ajax({
				type: "POST",
				data: {},
				dataType: 'json',
				url: global.basePath+'/role/data',
				success: function(res){
					if(res.success){
						var roleId=$('#roleId');
						var data=res.data;
						roleId.append('<option value="">选择角色</option>');
						for(var i=0;i<data.length;i++){
							var role=data[i];
							roleId.append('<option value="'+role.roleId+'">'+role.roleName+'</option>');
						}
						$('#roleId').val($('#h_roleId').val());
					}
				}
			});
	   }
	   
}

