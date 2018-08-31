/**
 * 
 */

var ue ;
$(function() {
    categoryTree.getPerentCategory();
	ue = UE.getEditor('editor');
}); 





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

