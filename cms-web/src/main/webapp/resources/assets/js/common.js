

var global={
    page:{
        showCount:10
    },
    adminPath:document.getElementsByTagName('base')[0].href+'admin',
    basePath:document.getElementsByTagName('base')[0].href,
    //frontPath:document.getElementsByTagName('base')[0].href+'/f'
};


var encodeEscape = {
    html2Escape: function (value) {
        var entities = {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;', "'": "&apos;"};
        return value.replace(/[<>&"]/g, function (all) {
            return entities[all];
        });
    },
    escape2Html: function (value) {
//            if(str.indexOf("&#34;")){
//                str = str.replace(/&#34;/ig,"&quot;");
//            }
        var entities = {
            //'&#34': '"',
            'lt': '<',
            'gt': '>',
            'nbsp': ' ',
            'amp': '&',
            'quot': '"',
            'apos': "'",
            'mdash': '—',
            'ldquo': '“',
            'rdquo': '”'
        };
        return value.replace(/&(lt|gt|nbsp|amp|quot|apos|mdash|ldquo|rdquo);/ig, function (all, t) {
            return entities[t];
        });
    }
}

//对Date的扩展，将 Date 转化为指定格式的String

//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，

//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)

//例子：

//(new Date()).format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423

//(new Date()).format("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18

Date.prototype.format = function(fmt)   {
    var o = {
        "M+" : this.getMonth()+1,                 //月份

        "d+" : this.getDate(),                    //日

        "H+" : this.getHours(),                   //小时

        "m+" : this.getMinutes(),                 //分

        "s+" : this.getSeconds(),                 //秒

        "q+" : Math.floor((this.getMonth()+3)/3), //季度

        "S"  : this.getMilliseconds()             //毫秒

    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};


/**

 * 获得表单参数，以JSON格式返回

 */
(function ($) {
    $.fn.serializeJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);
