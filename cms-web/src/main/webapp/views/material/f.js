/**
 * 
 */

jQuery(function() {
	var $ = jQuery,
	// 弹出上传框
	$wrap = $('#uploader'),
	// 图片缩略图容器
	$list = $('#fileList'),
	// 开始上传按钮
	$upload = $wrap.find('.uploadBtn'),
	// 缩略图压缩程度
	ratio = window.devicePixelRatio || 1,
	// 缩略图大小
	thumbnailWidth = 100 * ratio, thumbnailHeight = 100 * ratio,
	// Web Uploader实例
	uploader;
	// 初始化Web Uploader
	uploader = WebUploader.create({
		// 自动上传。
		auto : false,
		// swf文件路径
		swf : basePath + 'picture/Uploader.swf',
		// 文件接收服务端。
		server : basePath + 'uploadPicture',
		// 选择文件的按钮。
		pick : '#filePicker',
		// 单次上传最多图片数
		fileNumLimit : 12,
		// 单个文件最大为2m
		fileSingleSizeLimit : 2 * 1024 * 1024,
		// 允许选择的图片格式
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});

	// 当有文件添加进来的时候
	uploader.on('fileQueued',function(file) {
						// 图片数目限制为12张
						if ($('.file-item').length < 12) {
							// 创建新添加图片和删除条
							var $li = $('<div id="' + file.id
									+ '" class="file-item">' + '<img>'
									+ '<div class="info">删除</div>' + '</div>'), $info = $li
									.find('.info'), $img = $li.find('img');
							// 将新添加图片放入缩略图容器
							$list.append($li);
							// 为图片删除条添加单击删除事件
							$info
									.on(
											'click',
											function() {
												// 将图片移除上传序列
												uploader.removeFile(file, true);
												// 将图片从缩略图容器删除
												var $li = $('#' + file.id);
												$li.off().remove();
												$('#filePicker').children()
														.css('display', '');
												if ($('#filePicker').attr(
														'class') === 'qyfc_upload webuploader-container') {
													$('#filePicker')
															.css('background',
																	'url("images/chooseImg_qyfc.png") 0 0 no-repeat');
												} else {
													$('#filePicker')
															.css('background',
																	'url("images/chooseImg_grzp.png") 0 0 no-repeat');
												}
											});
							// 创建缩略图
							uploader.makeThumb(file, function(error, src) {
								if (error) {
									$img.replaceWith('<span>不能预览</span>');
									return;
								}
								$img.attr('src', src);
							}, thumbnailWidth, thumbnailHeight);
							// 添加图片完成后将添加按钮和上传按钮固定在弹出框底部
							$('.add_img').css({
								'left' : '0',
								'top' : '350px',
								'width' : '100%',
								'margin-top' : '0',
								'margin-left' : '0',
								'height' : '60px',
								'padding-top' : '8px'
							});
							// 为弹出框内元素更改样式和添加事件
							$('.uploadBtn').css({
								'display' : 'block'
							});
							$('#filePicker').css({
								'margin-left' : '540px'
							});
							$('.add_img p').css({
								'display' : 'none'
							});
							if ($('.file-item').length >= 12) {
								$('#filePicker').children().css('display',
										'none');
								if ($('#filePicker').attr('class') === 'qyfc_upload webuploader-container') {
									$('#filePicker')
											.css('background',
													'url("images/chooseImg_qyfcdis.png") 0 0 no-repeat');
								} else {
									$('#filePicker')
											.css('background',
													'url("images/chooseImg_grzpdis.png") 0 0 no-repeat');
								}
							}
						}
					});

	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress',
			function(file, percentage) {
				var $li = $('#' + file.id), $percent = $li
						.find('.progress span'), $info = $li.find('.info');
				// 避免重复创建
				if (!$percent.length) {
					$percent = $('<p class="progress"><span></span></p>')
							.appendTo($li).find('span');
				}
				$info.css({
					display : 'none'
				});
				$percent.css('width', percentage * 100 + '%');
			});

	// 文件上传成功，给info添加文字，标记上传成功。
	uploader.on('uploadSuccess', function(file) {
		var $li = $('#' + file.id), $info = $li.find('.info');
		$li.off();
		$info.off().text('上传成功!').css({
			color : 'green',
			display : 'block'
		});
	});

	// 文件上传失败，给info添加文字，标记上传失败。
	uploader.on('uploadError', function(file) {
		var $li = $('#' + file.id), $info = $li.find('.info');
		$info.off().text('上传失败!').css({
			color : 'red',
			display : 'block'
		});
	});

	// 上传成功或失败后删除进度条。
	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').remove();
	});

	// 单击开始上传按钮开始上传
	$upload
			.on(
					'click',
					function() {
						if ($('#fileList').children().length) {
							$('.uploadBtn')
									.css('background',
											'url("images/uploaderbtndis.png") 0 0 no-repeat');
							if ($('#filePicker').attr('class') === 'qyfc_upload webuploader-container') {
								$('#filePicker')
										.css('background',
												'url("images/chooseImg_qyfcdis.png") 0 0 no-repeat');
							} else {
								$('#filePicker')
										.css('background',
												'url("images/chooseImg_grzpdis.png") 0 0 no-repeat');
							}
							$('#filePicker').children().css('display', 'none');
							timer = setInterval(function() {
								if ($upload.html() === '重新上传'
										|| $upload.html() === '开始上传'
										|| $upload.html() === '上传中...') {
									$upload.html('上传中')
								} else {
									$upload.html($upload.html() + '.')
								}
							}, 500);
							$('.info').html('等待上传..').off();
							if ($upload.html() === '重新上传') {
								uploader.retry();
							} else {
								uploader.upload();
							}
						}
					});

	// 全部上传完成时触发关闭弹出层
	uploader.on('uploadFinished', function() {
		$('.uploadBtn').css('background',
				'url("images/uploaderbtn.png") 0 0 no-repeat');
		clearInterval(timer);
		closeBtn();
	});

	// 单击页面上的上传图片选项弹出上传框
	$('.add_resume_item').click(function() {
		$('.zpzs_gray,#uploader').css('display', 'block');
	});

	// 单击上传框上叉号按钮添加关闭上传框
	$('.closeBtn').click(closeBtn);

	// 关闭弹出窗
	function closeBtn() {
		// 获取上传出错和未上传的图片
		var allPic = $('#fileList').children().length, inited = uploader
				.getFiles('inited').length, error = uploader.getFiles('error').length, queued = uploader
				.getFiles('queued').length;
		// 判断是否可以关闭窗口
		if (error) {
			$upload.html('重新上传');
			if (window.confirm('您已上传成功' + (allPic - error) + '张，上传失败' + error
					+ '张，可能由于网络原因上传失败，是否确认关闭窗口！')) {
				closeuploader();
			}
		} else if (inited) {
			$upload.html('开始上传');
			if ($('#filePicker').attr('class') === 'qyfc_upload webuploader-container') {
				var text = '企业风采';
			} else {
				var text = '个人作品';
			}
			if (window.confirm('您还有' + text + '尚未上传!\r\r是否确认取消上传？')) {
				closeuploader();
			}
		} else if (queued) {
			if (window.confirm('您还有' + text + '等待上传!\r\r是否确认关闭窗口！')) {
				closeuploader();
			}
		} else {
			if (allPic) {
				window.location.href = window.location.href;
			}
			closeuploader();
		}
		// 关闭上传框窗口后恢复上传框初始状态
		function closeuploader() {
			// 关闭上传框
			$('.zpzs_gray,#uploader').css('display', 'none');
			// 移除所有缩略图并将图片移除上传序列
			for (var i = 0; i < uploader.getFiles().length; i++) {
				// 将图片从上传序列移除
				uploader.removeFile(uploader.getFiles()[i], true);
				delete uploader.getFiles()[i];
				// 将图片从缩略图容器移除
				var $li = $('#' + uploader.getFiles()[i].id);
				$li.off().remove();
			}
			// 恢复上传框内元素样式
			$('#filePicker,.uploadBtn,.add_img p,.add_img').removeAttr("style");
			if ($('#filePicker').attr('class') === 'qyfc_upload webuploader-container') {
				$('#filePicker').css('background',
						'url("images/chooseImg_qyfc.png") 0 0 no-repeat');
			} else {
				$('#filePicker').css('background',
						'url("images/chooseImg_grzp.png") 0 0 no-repeat');
			}
			$('#filePicker').children().removeAttr("style");
			$upload.html('开始上传');
		}
	}
});
/**
 * 显示文件上传弹层
 * @return
 */
function showUploadFrame() {
	$('.zpzs_gray,#uploader').css('display', 'block');
}