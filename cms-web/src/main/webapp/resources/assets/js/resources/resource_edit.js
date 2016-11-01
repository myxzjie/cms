/**
 * 
 */

/*$('.skin-minimal input').iCheck({
	checkboxClass: 'icheckbox-blue',
	radioClass: 'iradio-blue',
	increaseArea: '20%'
});*/


$(function() {  
	
	$('#resourceType').val($('#h_resourceType').val());
	
	resourceTree.getPerentResource();
	
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
								parent.location.href=global.basePath+'/resource';
								parent.layer.close(index);
							}else{
								layer.alert(res.message, {icon: 2});
							}
						},  
					    error: function(XMLHttpRequest, textStatus, errorThrown){ 
					    	if(textStatus=='parsererror'){
					    		parent.location.href=global.basePath+'/unauthorized.jsp';
					    		parent.layer.close(index);
					    	}
					    }
					});
					return false;
				}
			});
			
		}
}

var resourceTree={
	   getPerentResource:function(){
			$.ajax({
				type: "POST",
				data: {},
				dataType: 'json',
				url: global.basePath+'/resource/tree',
				success: function(res){
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

