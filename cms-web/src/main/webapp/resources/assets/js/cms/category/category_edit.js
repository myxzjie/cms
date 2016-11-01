/**
 * 
 */

$(function() { 
	
	$('#sex').val($('#h_sex').val());
	
	categoryTree.getPerentCategory();
	
	
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
								parent.location.href=global.basePath+'/category/index';
								parent.layer.close(index);
							}else{
								layer.alert(res.message, {icon: 2});
							}
						}
					});
					return false;
				}
			
			});
			
		}
}



var categoryTree={
		   getPerentCategory:function(){
			   var data={};
			   data.siteId=$('#siteId').val();
				$.ajax({
					type: "POST",
					data: data,
					dataType: 'json',
					url: global.basePath+'/category/tree',
					success: function(res){
						$("#categoryPId").droptree({
							items:res.data , 
	                        transition:"ztree",
	                        idLabel:"categoryId", 
	                        textLabel:"categoryName", 
	                        pidLabel:"categoryPId",
	                        rootPId:0
	                    });
					}
				});
		   }
		   
	}

