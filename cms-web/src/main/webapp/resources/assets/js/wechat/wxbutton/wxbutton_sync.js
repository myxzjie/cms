/**
 * 
 */

$(function(){
	buttonTree.getPerentButton();
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
					if(!buttonTree.getSelectedCheckNodes()){
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
								parent.location.href=global.basePath+'/wxbutton/index';
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

var buttonTree={
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
						idKey: "buttonId",
						pIdKey: "pButtonId",
						rootPId: "0"
					},
					key: {
						name: "name"
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
		getPerentButton:function(){
			$.ajax({
				type: "POST",
				data: {},
				dataType: 'json',
				url: global.basePath+'/wxbutton/tree',
				success: function(res){
					var data=res.data;
					var treeObj=$.fn.zTree.init($("#buttonTree"), buttonTree.setting, data); 
					buttonTree.selectNode();
					treeObj.expandAll(true); 
				}
			});
	   },
	   getSelectedCheckNodes:function(obj){  
		    var treeObj = $.fn.zTree.getZTreeObj("buttonTree");//获取树对象  
		    var nodes = treeObj.getChangeCheckedNodes();//选定节点  
		    var checked=null;  
		    var count = 0; 
		    var list=$('#list');
		    list.html('');
		    if(nodes.length>0){ 
		        for(var i=0;i<nodes.length;i++){  
		            checked=nodes[i].checked;//获取勾选属性  
		            if(checked==true){//选中节点  
		            	var buttonId=nodes[i].buttonId;
		            	list.append('<input name="buttonId['+i+']" type="hidden" value="'+buttonId+'" >');
		            	
		                count++;//自增1  
		            }  
		        }  
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