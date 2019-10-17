/**
 * 
 */

$(function() {
    $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",});
	$('#showModes').val($('#h_showModes').val());
	
	categoryTree.getPerentCategory();
	
	toolObj.submit();
}); 


var toolObj={
		submit:function(){
			this.edit();
		},
		edit:function(){
            $("#form").validate({
                onkeyup:false,
                focusCleanup:true,
                success:"valid",
                submitHandler:function(form){
                    $(form).ajaxSubmit({
                        type: 'post',
                        url: form.action ,
                        success: function(data){
                            if(data.success){
                                layer.msg(data.message, {icon: 1,time: 2000 }, function(){
                                    var index = parent.layer.getFrameIndex(window.name);
                                    //parent.$('.btn-refresh').click();
                                    parent.location.reload();
                                    parent.layer.close(index);
                                });
                            }else{
                                layer.msg(data.message, {icon: 2,time: 3000 }, function(){});
                            }
                        },
                        error: function(XmlHttpRequest, textStatus, errorThrown){
                            layer.msg('error!', {icon: 2,time: 3000 }, function(){});
                        }
                    });
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
					url: global.adminPath+'/category/tree',
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

