/**
 * 
 */

/*$('.skin-minimal input').iCheck({
	checkboxClass: 'icheckbox-blue',
	radioClass: 'iradio-blue',
	increaseArea: '20%'
});*/


$(function() {  
	$('#roleType').val($('#h_roleType').val());
	
	init();
	
	toolObj.submit();
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
				layer.alert(res.message, {icon: 2});
			}
		}
	});
}


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
								parent.location.href=global.basePath+'/role';
								parent.layer.close(index);
							}else{
								alert();
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

var resourceTree={
	   getPerentResource:function(){
			$.ajax({
				type: "POST",
				data: {},
				dataType: 'json',
				url: global.basePath+'/resource/data',
				success: function(res){
					//$.fn.zTree.init($("#resourceTree"), resourceTree.setting, res.data); 
					$("#perentResourceId").droptree({
						items:res.data , 
                        transition:"ztree",
                        idLabel:"resourceId", 
                        textLabel:"resourceName", 
                        pidLabel:"perentResourceId",
                        rootPId:0
                    });
				}
			});
	   }
	   
}

