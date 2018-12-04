$(function () {
    $('#fileupload').fileupload({
        inputid:'fileupload',//我要实例化哪个标签
        url: 'upload/article/',//服务端的地址
        dataType: 'json',//数据格式，ajax你懂的
        autoUpload: true,//是否选了文件就直接自动上传
        maxNumberOfFiles:1,//最多同时上传几个文件
        maxFileSize: 5000000,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        //maxChunkSize:10000,//最大接收文件大小
        done: function (e, data) {//上传完了干嘛
            var info=data.result;
            $('a.preview').attr('href',info.url);
            $('#image').val(info.url)
//                $.each(data.result, function (index, file) {
//                    $('#'+data.inputid).prev('span').text('重新上传');
//                    $('#'+data.inputid).attr('value',file.url);
//                });
        },
        progressall: function (e, data) {//上传过程中，隔段时间就会调起的回调函数，这个东西，主要是用来返回进度的，你看里面的引用，没错，它带回来的参数值，只有total、loaded这种数字。
//                var progress = parseInt(data.loaded / data.total * 100, 10);
//                $('#progress_'+$(this).fileupload('option', 'inputid')+' .progress-bar').css(
//                    'width',
//                    progress + '%'
//                ).next('.progress-text').children('.progress-percent-text').text('上传进度：'+progress + '%');
        },
        processalways:function (e, data) {//这货是干嘛的呢？就是上传之前调起的函数。没错，如果你选择了自动上传，那么当你点选完了文件，这个货就会被调起。
//                $('#progress_'+$(this).fileupload('option', 'inputid')+' .progress-bar').css(
//                    'width',
//                    '0'
//                ).next('.progress-text').children('.progress-filename-text').empty().next('.progress-percent-text').empty();
//
//                $('#'+$(this).fileupload('option', 'inputid')).attr('value','').prev('span').text('上传文件');
//                var index = data.index,
//                    file = data.files[index];
//                var file_name=file.name.length > 8? file.name.substr(0, 3) + "..." + file.name.substr(file.name.length-5, 5) : file.name;
//                $('#progress_'+$(this).fileupload('option', 'inputid')+' .progress-text .progress-filename-text').text(file_name);
//                if (file.error)
//                {
//                    $('#error_'+$(this).fileupload('option', 'inputid')).children('p').text(file.error).css('color','#f60941');
//                }
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');//不用猜了，就是看看是不是支持jqueryajax上传方式。就是上面说的那个frame东东。

});