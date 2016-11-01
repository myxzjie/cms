/**
 * 
 */
$(function() {  
	
	orgTree.getPerentOrg();
	
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
								parent.location.href=global.basePath+'/org';
								parent.layer.close(index);
							}else{
								alert();
								layer.alert(res.message, {icon: 2});
							}
						}
					});
					return false;
				}
			});
			
		}
}

var orgTree={
	   getPerentOrg:function(){
			$.ajax({
				type: "POST",
				data: {},
				dataType: 'json',
				url: global.basePath+'/org/data',
				success: function(res){
					var org={};
					org.orgId='0';
					org.orgName='选择';
					org.orgPId=0;
					res.data.push(org);
					$("#orgPId").droptree({
						items:res.data , 
                        transition:"ztree",
                        idLabel:"orgId", 
                        textLabel:"orgName", 
                        pidLabel:"orgPId",
                        rootPId:0
                    });
				}
			});
	   }
	   
}