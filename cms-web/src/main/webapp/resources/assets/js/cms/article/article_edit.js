/**
 * 
 */

var ue ;
$(function() { 

	ue = UE.getEditor('editor');
	
	categoryTree.getPerentCategory();

	toolObj.submit();
}); 


var toolObj={
		submit:function(){
			this.edit();
		},
		edit:function(){
            $("#form-add").validate({
                onkeyup:false,
                focusCleanup:true,
                success:"valid",
                submitHandler:function(form){
                    form.content.value=ue.getContent();
                    $(form).ajaxSubmit({
                        type: 'post',
                        url: form.action ,
                        success: function(data){
                            if(data.success){
                                layer.msg(data.message, {icon: 1,time: 2000 }, function(){
                                    var index = parent.layer.getFrameIndex(window.name);
                                    //parent.$('.btn-refresh').click();
                                    parent.tgridObj.load();
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
						$("#categoryId").droptree({
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

