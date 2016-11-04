/**
 * 
 */

$(function(){
	resourceTree();
});

function changePassword(){
	var url= global.basePath+'/changepwd?_='+new Date().getTime();
	layer_show('修改密码',url,550,300);
};

function resourceTree(){
	var data={};
	$.ajax({
		type: "POST",
		data: data,
		async:false,
		dataType: 'json',
		url: global.basePath+'/resource/resourceuser',
		success: function(res){
			if(res.success){
				var data=res.data[0].children;
				var _html='';
				for(var i=0;i<data.length;i++){
					var node=data[i];
					_html+='<dl id="menu-article"><dt><i class="Hui-iconfont">&#xe616;</i> '+node.resourceName+'<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
					
					if(node.children!=''&&node.children.length>0){
						var children=node.children;
						_html+='<dd><ul>';
						
						for(var j=0;j<children.length;j++){
							var childrenNode=children[j];
							var _href=global.basePath+childrenNode.resourceUrl;
							_html+='<li><a _href="'+_href+'" href="javascript:void(0)">'+childrenNode.resourceName+'</a></li>';
						}
						_html+='</ul></dd>';
					}
					
					_html+='</dl>';
				}
				$('#resource').html(_html);
				
				$.Huifold(".menu_dropdown dl dt",".menu_dropdown dl dd","fast",1,"click");
			}else{
				alert("错误："+res.message);
			}
			
		}
	});
}


function traverse(node, i) {  
	if(node){
		
	
		
		$('#resource').append(_html);
	}
    /* var children = node.children;  
    if (children != null) {  
        window.alert("parent:" + node.name + ",child:" + children[i].name);  
        if (i == children.length - 1) {  
            traverse(children[0], 0);  
        } else {  
            traverse(node, i + 1);  
        }  
    }   */
}  