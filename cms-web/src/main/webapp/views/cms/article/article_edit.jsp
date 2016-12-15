<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<title>资源</title>
<meta name="keywords" content="">
<meta name="description" content="">
<%@ include file="/common/style.jsp" %>
<link href="${ctx}/resources/assets/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/assets/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/assets/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.mfs-container {
	/* min-width: 150px;max-height: 100px;  */
	/* position: relative; */
	
}

.mfs-selected-option {
	border: 1px #ccc solid;
	display: inline-block;
	width: 100%;
	font-size: 14px;
	height: 31px;
	line-height: 2.42857;
	padding-left: 4px;
}

.mfs-options {
position: absolute;
	border: 1px #ccc solid;
	/* border-top:0px; */
	width: 100%;
	margin: 0;
	padding: 0px;
	color: #333;
	overflow: auto;
	min-height: 100px;
	max-height: 300px;
	background: #ffffff;
	z-index: 9;
	 margin-top: -6px; 
}
</style>
</head>
<body>
<div class="pd-20">
	<c:set var="uploadImageWeb" value="<%=WebUtils.getUploadImageWeb()%>"/>
	<input id="uploadImageWeb" type="hidden" value="${uploadImageWeb}">
	<input type="hidden" id="siteId" value="${siteId}">
	<form action="${ctx}/article/${model.articleId==null?'save':'update'}" method="post" class="form form-horizontal" id="form_add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>文章标题：</label>
			<div class="formControls col-10">
				<input type="hidden" name="articleId" value="${model.articleId}">
				<input type="text" class="input-text" placeholder="" id="" name="title" value="${model.title}">
			</div>
		</div>
		<!-- <div class="row cl">
			<label class="form-label col-2">简略标题：</label>
			<div class="formControls col-10">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
		</div> -->
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>分类栏目：</label>
			<div class="formControls col-2"> <!-- <span class="select-box"> -->
				
				<input type="text" class="input-text" id="categoryId" name="categoryId" placeholder="" readonly="readonly"   value="${model.categoryId==null?-1:model.categoryId}">
				
			</div>
			<label class="form-label col-2"><span class="c-red">*</span>文章类型：</label>
			<input id="h_recommendType" type="hidden" value="${model.recommendType}">
			<div class="formControls col-2"> 
				<span class="select-box">
				<select id="recommendType" name="recommendType" class="select">
					<option value="">选择</option>
					<option value="1">轮播图</option>
					<option value="2">内容推荐</option>
					<!-- <option value="0">全部类型</option>
					<option value="1">帮助说明</option>
					<option value="2">新闻资讯</option> -->
				</select>
				</span> 
			</div>
			<label class="form-label col-2">排序值：</label>
			<div class="formControls col-2">
				<input type="text" class="input-text" value="${model.sort}" placeholder="" id="" name="sort">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">关键词：</label>
			<div class="formControls col-10">
				<input type="text" class="input-text" value="${model.keywords}" placeholder="" id="" name="keywords">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">文章摘要：</label>
			<div class="formControls col-10">
				<textarea name="description" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-200" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,200)">${model.description}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
		</div>
		<!-- <div class="row cl">
			<label class="form-label col-2">文章作者：</label>
			<div class="formControls col-2">
				<input type="text" class="input-text" value="0" placeholder="" id="" name="">
			</div>
			<label class="form-label col-2">文章来源：</label>
			<div class="formControls col-2">
				<input type="text" class="input-text" value="0" placeholder="" id="" name="">
			</div>
		</div> -->
		<!-- <div class="row cl">
			<label class="form-label col-2">允许评论：</label>
			<div class="formControls col-2 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-pinglun">
					<label for="checkbox-pinglun">&nbsp;</label>
				</div>
			</div>
			<label class="form-label col-2">评论开始日期：</label>
			<div class="formControls col-2">
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate">
			</div>
			<label class="form-label col-2">评论结束日期：</label>
			<div class="formControls col-2">
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'datemin\')}'})" id="datemax" class="input-text Wdate">
			</div>
		</div> -->
		<!-- <div class="row cl">
			<label class="form-label col-2">使用独立模版：</label>
			<div class="formControls col-10 skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-moban">
					<label for="checkbox-moban">&nbsp;</label>
				</div>
				<button onClick="mobanxuanze()" class="btn btn-default radius ml-10">选择模版</button>
			</div>
		</div> -->
		<div class="row cl">
			<label class="form-label col-2">缩略图：</label>
			<div class="formControls col-10">
				<div class="uploader-thum-container">
					<div id="fileList" class="uploader-list">
						<c:if test="${model.articleId != null}">
						<div id="WU_FILE_0" class="item upload-state-success">
						<div class="pic-box">
						<img width="30" src="${uploadImageWeb}${model.image}">
						</div>
						<input type="hidden" name="image" value="${model.image}"></div>
						</c:if>
					</div>
					<div id="filePicker">选择图片</div>
					<button id="btn-star" class="btn btn-default btn-uploadstar radius ml-10">开始上传</button>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2">文章内容：</label>
			<div class="formControls col-10"> 
				<script id="editor" type="text/plain" style="width:100%;height:400px;">${model.content}</script> 
			</div>
		</div>
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并发布</button>
				<!-- <button onClick="article_save();" class="btn btn-secondary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存草稿</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button> -->
			</div>
		</div>
	</form>
</div>
<%@ include file="/common/script.jsp" %>
<script type="text/javascript" src="${ctx}/resources/assets/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/Validform/5.3.2/Validform.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/lib/webuploader/0.1.5/webuploader.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/ueditor/1.4.3/ueditor.all.js"> </script> 
<script type="text/javascript" src="${ctx}/resources/assets/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>  
<script type="text/javascript" src="${ctx}/resources/assets/lib/zTree/v3/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/js/droptree.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/js/cms/article/article_edit.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	
	
});

function mobanxuanze(){
	
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
					'<div class="pic-box"><img width="30"></div>'+
					'<div class="info">' + file.name + '</div>' +
					'<p class="state">等待上传...</p>'+
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
			$li.find(".state").text("上传中");
			$percent.css( 'width', percentage * 100 + '%' );
		});
		
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on( 'uploadSuccess', function( file,response) {
			if(response.success){
				var data=response.data;
				var $img=$( '#'+file.id ).find('img');
				$( '#'+file.id ).addClass('upload-state-success').find(".state").text("已上传");
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