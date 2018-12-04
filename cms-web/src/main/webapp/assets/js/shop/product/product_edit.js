$(function () {
    $.ajax({
        type: "POST",
        data: {},
        dataType: 'json',
        url: '/prodcategory/tree',
        success: function (res) {
            $("#parentId").droptree({
                items: res.data,
                transition: "ztree",
                idLabel: "id",
                textLabel: "name",
                pidLabel: "parentId",
                rootPId: 0,
                expand: false
            });
        }
    });

    $('#upload_image').fileupload({
        //inputid:'fileupload',//我要实例化哪个标签
        url: 'upload/product',//服务端的地址
        dataType: 'json',//数据格式，ajax你懂的
        autoUpload: true,//是否选了文件就直接自动上传
        maxNumberOfFiles: 1,//最多同时上传几个文件
        maxFileSize: 5000000,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        //maxChunkSize:10000,//最大接收文件大小
        done: function (e, data) {//上传完了干嘛
            var res = data.result;
            if (res.success) {
                $('#image').val(res.url);
            }

            layer.open({
                type: 1,
                title: false,
                closeBtn: 0, //不显示关闭按钮
                shade: [0],
                area: ['300px', '200px'],
                offset: 'rb', //右下角弹出
                time: 2000, //2秒后自动关闭
                anim: 2,
                content: res.message, //iframe的url，no代表不显示滚动条
                end: function () { //此处用于演示

                }
            });

        },
        progressall: function (e, data) {//上传过程中，隔段时间就会调起的回调函数，这个东西，主要是用来返回进度的，你看里面的引用，没错，它带回来的参数值，只有total、loaded这种数字。

        },
        processalways: function (e, data) {
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');//不用猜了，就是看看是不是支持jqueryajax上传方式。就是上面说的那个frame东东。

    $('#upload_thumb').fileupload({
        //inputid:'fileupload',//我要实例化哪个标签
        url: 'upload/product_thumb',//服务端的地址
        dataType: 'json',//数据格式，ajax你懂的
        autoUpload: true,//是否选了文件就直接自动上传
        maxNumberOfFiles: 1,//最多同时上传几个文件
        maxFileSize: 5000000,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        //maxChunkSize:10000,//最大接收文件大小
        done: function (e, data) {//上传完了干嘛
            var res = data.result;
            if (res.success) {
                $('#thumb').val(res.url);
            }

            layer.open({
                type: 1,
                title: false,
                closeBtn: 0, //不显示关闭按钮
                shade: [0],
                area: ['300px', '200px'],
                offset: 'rb', //右下角弹出
                time: 2000, //2秒后自动关闭
                anim: 2,
                content: res.message, //iframe的url，no代表不显示滚动条
                end: function () { //此处用于演示

                }
            });

        },
        progressall: function (e, data) {//上传过程中，隔段时间就会调起的回调函数，这个东西，主要是用来返回进度的，你看里面的引用，没错，它带回来的参数值，只有total、loaded这种数字。

        },
        processalways: function (e, data) {
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');//不用猜了，就是看看是不是支持jqueryajax上传方式。就是上面说的那个frame东东。


    var upload_gallery_index=0;
    var et_Dropzone = new Dropzone("#upload_gallery", {
        url: "upload/product_gallery",
        addRemoveLinks: true,
        method: 'post',
        filesizeBase: 1024,
        dictDefaultMessage: '点击或拖拽上传图片',
        dictRemoveLinks: "移除",
        dictCancelUpload: "移除",
        dictRemoveFile: '移除',
        previewsContainer: null,
        //previewTemplate:document.querySelector('#preview-template').innerHTML,
        init: function(){
            var $dropzone =this;
            var data=$("#et-dz-data").find('input[type="hidden"]');
            $.each(data,function (i,e) {
                var url= $(e).val();

                var mockFile = {
                    name: url.substring(url.lastIndexOf('/')+1), //需要显示给用户的图片名
                    //size: 0, //图片尺寸
                    // height:200,
                    // width:200,
                    // type: '.jpg,.png,.gif'//图片文件类型
                };
                $dropzone.options.addedfile.call ($dropzone,mockFile);//添加mock图片到显示区域
                $dropzone.options.thumbnail.call ($dropzone,mockFile,url);//添加数据源给mock图片
            });


            //$(".dz-remove").click(function(){$dropzone.removeFile();})
        },
        sending: function (file, xhr, formData) {
            //formData.append("filesize", file.size);
        },
        success: function (file, res, e) {
            // var res = JSON.parse(response);
            // if (res.error) {
            //     $(file.previewTemplate).children('.dz-error-mark').css('opacity', '1')
            // }
            // var inp = '<input type="hidden" value="' + res.url + '" name="prodGallerys['+upload_gallery_index+'].image" >';
            // upload_gallery_index++;
            // $(file.previewTemplate).append(inp);
            $(file.previewTemplate).find('img').attr('src',res.url);
        }
    });
});

//
// (function ($) {
//
//     var _fileupload =$.fileupload;
//
//     $.fileupload = function (opt) {
//         //备份opt中error和success方法
//         var fn = {
//             //error:function(XMLHttpRequest, textStatus, errorThrown){},
//             success:function(e,data){}
//         }
//         if(opt.error){
//             fn.error=opt.error;
//         }
//         if(opt.success){
//             fn.success=opt.success;
//         }
//         var _opt= $.extend(opt,{
//             //inputid:'fileupload',//我要实例化哪个标签
//             url: 'upload/product',//服务端的地址
//             dataType: 'json',//数据格式，ajax你懂的
//             autoUpload: true,//是否选了文件就直接自动上传
//             maxNumberOfFiles: 1,//最多同时上传几个文件
//             maxFileSize: 5000000,
//             acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
//             //maxChunkSize:10000,//最大接收文件大小
//             done: function (e, data) {//上传完了干嘛
//                 var res = data.result;
//                 //成功回调方法增强处理
//                 fn.success(e, res);
//
//                 layer.open({
//                     type: 1,
//                     title: false,
//                     closeBtn: 0, //不显示关闭按钮
//                     shade: [0],
//                     area: ['300px', '200px'],
//                     offset: 'rb', //右下角弹出
//                     time: 2000, //2秒后自动关闭
//                     anim: 2,
//                     content: res.message, //iframe的url，no代表不显示滚动条
//                     end: function () { //此处用于演示
//
//                     }
//                 });
//
//             },
//             progressall: function (e, data) {//上传过程中，隔段时间就会调起的回调函数，这个东西，主要是用来返回进度的，你看里面的引用，没错，它带回来的参数值，只有total、loaded这种数字。
//
//             },
//             processalways: function (e, data) {
//             }
//         });
//         return _fileupload(_opt).prop('disabled', !$.support.fileInput)
//             .parent().addClass($.support.fileInput ? undefined : 'disabled');//不用猜了，就是看看是不是支持jqueryajax上传方式。就是上面说的那个frame东东。
//
//     };
// })(jQuery);