<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!doctype html>
<html>
<head>
  <%@ include file="include/meta.jsp" %>
  <title>${model.title}-${site.siteName}</title>
  <meta name="description" content="${model.keywords}">
  <meta name="keywords" content="${model.description}">
  <%@ include file="include/style.jsp" %>
  <link href="${ctx}/resources/assets/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
  <style type="text/css">
  .my-error{
  border-color: #dd514c!important;
  border: 1px solid;
  }
  </style>
</head>
<body id="blog">

<%@include file="include/navs.jsp" %>


<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
	<ol class="am-breadcrumb" style=" margin-bottom: 0rem;">
	<li><a href="${ctx}" class="am-icon-home">首页</a></li>
	<li><a href="${ctx_front}/personal/info?cid=${site.siteId}">个人主页</a></li>
	<li class="am-active">${editType==1?'博文编辑':'发布博文'}</li>
	</ol>
	<hr style="margin: 0 0 0.5rem;">	
	
	<div class="am-u-md-8 am-u-sm-12" style="padding-left:0">
    	<div class="am-panel am-panel-default">
    		<form id="form_submit" class="am-form am-form-horizontal" action="${ctx_front}/blog/add">
  				<fieldset>
  					<div class="am-form-group">
					    <label for="doc-ipt-3" class="am-u-sm-2 am-form-label">标题</label>
					    <div class="am-u-sm-10">
					      <input id="articleId" type="hidden" name="articleId" value="${model.articleId}">
					      <input type="text" placeholder=""  name="title" required value="${model.title}">
					    </div>	    
	  				</div>
	  				
  					<div class="am-form-group">
					    <label for="doc-ipt-3" class="am-u-sm-2 am-form-label">关键词</label>
					    <div class="am-u-sm-10">
					      
					      <input type="text" value="${model.keywords}" placeholder="" name="keywords">
					    </div>
					</div>
					
					<div class="am-form-group">
					    <label for="description" class="am-u-sm-2 am-form-label">内容摘要</label>
					    <div class="am-u-sm-10">
					      <textarea id="description" name="description" cols="" rows="4" minlength="10" maxlength="100"  placeholder="说点什么...最少输入10个字符" >${model.description}</textarea>
					    </div>
					</div>
					
					<div class="am-form-group">
					    <label for="categoryId" class="am-u-sm-2 am-form-label">内容分类</label>
					    <div class="am-u-sm-10">
					    	<input id="h_categoryId" type="hidden" value="${model.categoryId}">
					      <select id="categoryId" name="categoryId" required>					        
					      </select>
					    </div>
					</div>
				    
				     <div class="am-form-group am-form-file">
      					<label for="doc-ipt-file-2" class="am-u-sm-2 am-form-label">展示图片</label>
				      <div class="am-u-sm-10">
				        <div id="filePicker" >
				          <i class="am-icon-cloud-upload"></i> 上传的图片
				         </div>
				         
				         <div id="fileList" class="uploader-list">
						<c:if test="${model.image != null}">
						<div id="WU_FILE_0" class="item upload-state-success">
						<div class="pic-box">
						<img width="30" src="${uploadImageWeb}${model.image}">
						</div>
						<input type="hidden" name="image" value="${model.image}"></div>
						</c:if>
					</div>
				       </div>
			
				    </div>
				    
				    
				    <div class="am-form-group">
	    			<script id="editor" type="text/plain" style="width:100%;height:400px;">${model.content}</script> 
		 		 	</div>
		 		 	
		 		 	<div class="am-form-group">
				      <label class="am-radio-inline">
				        <input type="radio" <c:if test='${model.showModes==1}'>checked="checked"</c:if> value="1" name="showModes"> 公开
				      </label>
				      <label class="am-radio-inline">
				        <input type="radio" <c:if test='${model.showModes==null || model.showModes==0 }'>checked="checked"</c:if> value="0" name="showModes"> 私藏
				      </label>
				     </div>
				     
		 		 	<c:if test='${model.approveStatus==null || model.approveStatus==0}'>
		 		 	 <div class="am-form-group">
				      <label class="am-radio-inline">
				        <input type="radio" checked="checked" value="0" name="approveStatus"> 草稿
				      </label>
				      <label class="am-radio-inline">
				        <input type="radio"  value="1" name="approveStatus"> 发布
				      </label>
				      
				     </div>
				     </c:if>
				     
		 		 	 <button type="submit" class="am-btn am-btn-primary">提交</button>
	 		 	</fieldset>
			</form>
		</div>
        <ul id="pagination" class="am-pagination">
		</ul>
    </div>

    <div class="am-u-md-4 am-u-sm-12 ">
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor">
    	<a href="${ctx_front}/blog/edit?cid=${site.siteId}" class="am-btn am-btn-primary am-btn-block">发布博文</a>
    	</div>
    	
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor" style="margin-top: 5px;">
    	<h2 style="border-bottom: 1px #eee solid; margin: 0 0 0.5rem; padding-left: 0.5rem">关于博主</h2>
    	<div class="blog-sidebar-widget">
    	<img style="width: 100%" alt="<shiro:principal property="username"/>" src="${uploadImageWeb}<shiro:principal property="photo"/>" >
    	<!-- onerror="javascript:this.src='/attached/avatar/default.jpg'" -->
    	<div style="text-align: left;">
    		<ul class="am-list am-list-static am-list-border">
			  <li>
			    <i class="am-icon-user-secret"></i>
			   	 博主:
			   	 <shiro:principal property="username"/>
			   	 <!-- <button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '点我管理您的博客', trigger: 'hover focus'}" onclick="javascript:location.href='/blog/myblog/'">2</button> -->
			  </li>
			  <li>
			    <i class="am-icon-mortar-board"></i>
			   	职业:java工程师
			   	<!-- <button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '点我管理您的美图', trigger: 'hover focus'}" onclick="javascript:location.href='/beauty/myBeauty/'">0</button> -->
			  </li>
			  <li>
			    <i class="am-icon-newspaper-o"></i>
			   	 简介
			   	 <!-- <button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '点我管理您的视频', trigger: 'hover focus'}" onclick="javascript:location.href='/video/myVideo/'">0</button> -->
			  </li>
			 </ul>
    	</div>
    	</div>
    	</div>
    	<div data-am-widget="list_news" class="am-list-news am-list-news-default " >
  			<!--列表标题-->
    		<div class="am-list-news-hd am-cf">
	       		<!--带更多链接-->
	        	<!-- <a href="##" class=""> -->
	          	<h2>内容推荐</h2>
	            <!-- <span class="am-list-news-more am-fr">更多 &raquo;</span> -->
	        	<!-- </a> -->
          	</div>

  			<div class="am-list-news-bd">
			  <ul class="am-list recommend-list">
			
			  </ul>
  			</div>

    	</div>
    
    	<%@include file="include/about.jsp" %>
        
    </div>
</div>
<!-- content end -->


<%@include file="include/footer.jsp" %>

<%@include file="include/script.jsp" %>
<script type="text/javascript" src="${ctx}/resources/assets/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/ueditor/1.4.3/ueditor.all.js"> </script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/webuploader/0.1.5/webuploader.js"></script>
<%-- ${param.name} --%>
<%-- ${paramValues.name}可以取得所有同名参数的值
${paramValues.hobbies[0]}可以通过指定下标来访问特定的参数的值 --%>

<script type="text/javascript">
var ue ;

$(function() { 
	
	ue = UE.getEditor('editor');
	
	/* if($('#articleId').val()!=''){
		ue.addListener("ready",function(){
	        //通过ajax请求数据
	        UE.ajax.request(global.frontPath+'/blog/content/'+$('#articleId').val(),
	            {
	                method:"post",
	                async:true,
	                data:{cid:location.search.match(/cid=(\d+)/)[1]},
	                dataType: 'json',
	                onsuccess:function(xhr){
	                	debugger
	                    var s = xhr.responseText;
	                    ue.setContent(s);
	                    //document.getElementById("show").innerHTML=s;
	                }
	            }
	        );
	    });
	} */
	
	
	
	$.ajax({
		type: "POST",
		data: {cid:location.search.match(/cid=(\d+)/)[1]},
		dataType: 'json',
		url:global.frontPath+'/blog/category',
		success: function(res){
			if(res.success){
				
				var categoryId=$('#categoryId');
				
				for(var i=0;i<res.data.length;i++){
					var row=res.data[i];
					categoryId.append('<option value="'+row.categoryId+'">'+row.categoryName+'</option>');
				}
				$('#categoryId').val($('#h_categoryId').val());
			}else{
				layer.alert(res.message, {icon: 2});
			}
		}
	});
	//
	toolObj.submit();
}); 


var toolObj={
		submit:function(){
			this.edit();
		},
		edit:function(){
			 //var $textArea = $('[name=editor');
			var $form =$("#form_submit");
			$form.validator({
		          submit: function() {
		            // 同步编辑器数据
		            //ue.sync();
		            //

		            var formValidity = this.isFormValid();
		            if (!formValidity){
		            	return false;
		            }
		            
		            var content=ue.getContent();
		            var form_group=$('#editor').parents('.am-form-group');
		            if(!content){
		            	 
		            	 form_group.addClass('my-error');
		            	//form_group.clss('border', '1px solid');
		            	 ue.focus();
		            	 return false;
		            }else{
		            	form_group.removeClass('my-error');
		            }
		            
		            var data={};
		            data=$form.serializeJSON();
					data.content=ue.getContent();
		            $.ajax({
						type: "POST",
						data: data,
						dataType: 'json',
						url:$form[0].action,
						success: function(res){
							if(res.success){
								//layer.alert(res.message, {icon: 1});
								//var index = parent.layer.getFrameIndex(window.name);
								location.href=global.basePath+'/f/personal/info?cid=1';
								//parent.layer.close(index);
							}else{
								layer.alert(res.message, {icon: 2});
							}
						}
					});
		            
		           

		            // 表单验证未成功，而且未成功的第一个元素为 UEEditor 时，focus 编辑器
		            //if (!formValidity && $form.find('.' + this.options.inValidClass).eq(0).is($textArea)) {
		            //	ue.focus();
		            //}

		            //console.warn('验证状态：', formValidity ? '通过' : '未通过');

		            return false;
		          }
		        });
		}
}

</script>

<script type="text/javascript">
	$(function(){
		$list = $("#fileList"),
		$btn = $("#btn-star"),
		state = "pending",
		uploader;

		var uploader = WebUploader.create({
			auto: true,
			swf: 'lib/webuploader/0.1.5/Uploader.swf',
		
			// 文件接收服务端。
			server: global.basePath+'/upload/image?dir=article',
		
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick: '#filePicker',
		
			// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			resize: false,
			// 只允许选择图片文件。
			accept: {
				title: 'Images',
				extensions: 'gif,jpg,jpeg,bmp,png',
				mimeTypes: 'image/*'
			}
		});
		uploader.on( 'fileQueued', function( file ) {
			var $li = $(
				'<div id="' + file.id + '" class="item">' +
					'<div class="pic-box"><img width="120"></div>'+
					'<div class="info"></div>' +
					'<span class="state"></span>'+
				'</div>'
			),
			//debugger
			$img = $li.find('img');
			$list.html('');
			$list.append( $li );
		
			// 创建缩略图
			// 如果为非图片文件，可以不用调用此方法。
			// thumbnailWidth x thumbnailHeight 为 100 x 100
			/* uploader.makeThumb( file, function( error, src ) {
				if ( error ) {
					$img.replaceWith('<span>不能预览</span>');
					return;
				}
		
				$img.attr( 'src', src );
			}, thumbnailWidth, thumbnailHeight ); */
		});
		// 文件上传过程中创建进度条实时显示。
		uploader.on( 'uploadProgress', function( file, percentage ) {
			var $li = $( '#'+file.id ),
				$percent = $li.find('.progress-box .sr-only');
		
			// 避免重复创建
			if ( !$percent.length ) {
				$percent = $('<div class="progress-box"><span class="progress-bar radius"><span class="sr-only" style="width:0%"></span></span></div>').appendTo( $li ).find('.sr-only');
			}
			//$li.find(".state").text("上传中");
			$percent.css( 'width', percentage * 100 + '%' );
		});
		
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on( 'uploadSuccess', function( file,response) {
			if(response.success){
				var data=response.data;
				var $img=$( '#'+file.id ).find('img');
				//$( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
				$( '#'+file.id ).append('<input type="hidden" name="image" value="'+data.webUrl+'">');
				$img.attr( 'src', $('#uploadImageWeb').val()+data.webUrl );
			}else{
				$( '#'+file.id ).addClass('upload-state-error').find(".state").text(response.message);
			}
			
		});
		
		// 文件上传失败，显示上传出错。
		uploader.on( 'uploadError', function( file ) {
			
			$( '#'+file.id ).addClass('upload-state-error').find(".state").text("上传出错");
		});
		
		// 完成上传完了，成功或者失败，先删除进度条。
		uploader.on( 'uploadComplete', function( file ) {
			$( '#'+file.id ).find('.progress-box').fadeOut();
		});
		uploader.on('all', function (type) {
	        if (type === 'startUpload') {
	            state = 'uploading';
	        } else if (type === 'stopUpload') {
	            state = 'paused';
	        } else if (type === 'uploadFinished') {
	            state = 'done';
	        }

	        if (state === 'uploading') {
	            $btn.text('暂停上传');
	        } else {
	            $btn.text('开始上传');
	        }
	    });

	    $btn.on('click', function () {
	        if (state === 'uploading') {
	            uploader.stop();
	        } else {
	            uploader.upload();
	        }
	    });
	});

</script>
</body>
</html>