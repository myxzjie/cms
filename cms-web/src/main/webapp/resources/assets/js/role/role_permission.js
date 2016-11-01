/**
 * 
 */

$(function(){
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
					if(!resourceTree.getSelectedCheckNodes()){
						return false;
					}
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
		setting : {
				check: {  
				        enable: true,//设置zTree的节点上是否显示checkbox/radio框，默认值: false  
				        chkboxType:  { "Y" : "p", "N" : "s" } //{ "Y": "ps", "N": "ps" }  
				},  
				view: {
					dblClickExpand: false,
					showLine: false,
					selectedMulti: false
				},
				key:{  
				    checked:"checked"//zTree 节点数据中保存check状态的属性名称。默认值："checked"  
				}, 
				data: {
					simpleData: {
						enable:true,
						idKey: "resourceId",
						pIdKey: "perentResourceId",
						rootPId: "0"
					},
					key: {
						name: "resourceName"
					}
				},
				callback: {
					beforeClick: function(treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("tree");
						if (treeNode.isParent) {
							zTree.expandNode(treeNode);
							return false;
						} else {
							demoIframe.attr("src",treeNode.file + ".html");
							return true;
						}
					}
				}
		},
		getPerentResource:function(){
				$.ajax({
					type: "POST",
					data: {},
					dataType: 'json',
					url: global.basePath+'/resource/tree',
					success: function(res){
						var data=res.data;
						var treeObj=$.fn.zTree.init($("#resourceTree"), resourceTree.setting, data); 
						resourceTree.selectNode();
						treeObj.expandAll(true); 
						/*$("#perentResourceId").droptree({
							items:res.data , 
	                        transition:"ztree",
	                        idLabel:"resourceId", 
	                        textLabel:"resourceName", 
	                        pidLabel:"perentResourceId",
	                        rootPId:0
	                    });*/
					}
				});
		   },
		   getSelectedCheckNodes:function(obj){  
			    var treeObj = $.fn.zTree.getZTreeObj("resourceTree");//获取树对象  
			    var nodes = treeObj.getChangeCheckedNodes();//选定节点  
			    var checked=null;  
			    var count = 0; 
			    var menuIdList=$('#menuIdList');
			    menuIdList.html('');
			    if(nodes.length>0){ 
			        for(var i=0;i<nodes.length;i++){  
			            checked=nodes[i].checked;//获取勾选属性  
			            if(checked==true){//选中节点  
			            	var resourceId=nodes[i].resourceId;
			            	menuIdList.append('<input name="roleResources['+i+'].resourceId" type="hidden" value="'+resourceId+'" >');
			            	
			                count++;//自增1  
			            }  
			        }  
//			        alert("选中节点："+nodes.length+"个\n"+  
//			              "被勾选："+count+"个\n"+  
//			              "没被勾选："+(nodes.length-count)+"个");  
			        return true;  
			          
			    }else{  
			        alert("请先选中节点！");  
			        $(obj)[0].checked=false;//取消勾选状态  
			        return false;  
			    }  
			},
			selectNode:function(){
				var treeObj = $.fn.zTree.getZTreeObj("resourceTree");//获取树对象
				var data={};
				data.id=$('#h_roleId').val();
				$.ajax({
					type: "POST",
					data: data,
					dataType: 'json',
					url: global.basePath+'/role/roleresourcedata',
					success: function(res){
						var data=res.data;
						for(var i=0;i<data.length;i++){
							var roleresource= data[i];
							var nodes = treeObj.getNodesByParam("resourceId", roleresource.resourceId, null); 
							if(nodes.length>0){ 
								//treeObj.selectNode(nodes[0],true); 
								treeObj.checkNode(nodes[0], true, true);  
					            //treeObj.expandNode(nodeId, true, true, true);  
						    } 
						}
					}
				});
				
				
				
				
				    
			}
		   
	}