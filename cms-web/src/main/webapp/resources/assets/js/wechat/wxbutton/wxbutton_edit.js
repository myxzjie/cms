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
	
	wxbuttonTree.getPerentButton();
	
	
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
								parent.location.href=global.basePath+'/wxbutton/index';
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



var wxbuttonTree={
		   getPerentButton:function(){
				$.ajax({
					type: "POST",
					data: {},
					dataType: 'json',
					url: global.basePath+'/wxbutton/tree',
					success: function(res){
						//$.fn.zTree.init($("#resourceTree"), resourceTree.setting, res.data); 
						$("#pButtonId").droptree({
							items:res.data , 
	                        transition:"ztree",
	                        idLabel:"buttonId", 
	                        textLabel:"name", 
	                        pidLabel:"pButtonId",
	                        rootPId:0
	                    });
					}
				});
		   }
		   
	}

