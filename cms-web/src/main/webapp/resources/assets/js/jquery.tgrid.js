/**

 * jquery grid plugin v1.0.0

 *

 * author: xzjie

 *

 * e-mail: 513961835@qq.com

 */

; (function ($, window, document, undefined) {
    "use strict";

    var defaults={
        columns:[],
        className:'table table-border table-bordered table-bg',
        checkbox:false,
        url: false, //URL if using data from AJAX

        method: 'POST', //data sending method

        dataType: 'json', //type of data for AJAX, either xml or json

        params: {} //allow optional parameters to be passed around


    };

    var _grid={
        cache:{},
        init:function(){
            var options = methods.options;
            //add class name

            methods.$ele.addClass(options.className);
            var div=document.createElement('div');
            //div box add table before

            methods.$ele.before(div);
            //add to div box

            methods.$ele.appendTo(div);

            //loading table thead row cell

            this.thead();
            //loading data

            this.load();
        },
        thead:function(){
            var options = methods.options;
            var thead = document.createElement('thead');
            var tr = document.createElement('tr');

            //add checkbox

            if(options.checkbox){
                var th=document.createElement('th');
                var input =document.createElement('input');
                input.type='checkbox';

                $(th).css({width:'25px','text-align': 'center'});

                //th.innerHTML='<input type="checkbox" name="" value="">';

                $(th).append(input);
                $(tr).append(th);

                $(th).click(function(e){
                    //var table= $(e.target).closest('table');

                    $('td input:checkbox',methods.$ele).prop('checked',e.target.checked);
                });
            }
            //add cell

            for (var i = 0; i < options.columns.length; i++) {
                var cols = options.columns[i];
                var th = document.createElement('th');
                if(cols){

                    if(cols.title){
                        th.innerHTML=cols.title;
                    }
                    if(cols.field){//axis

                        th.setAttribute('field',cols.field);
                    }
                    if(cols.width){
                        $(th).css('width',cols.width);
                    }
                    if(cols.align){
                        $(th).css('text-align',cols.align);
                    }
                    if(cols.hidden){
                        $(th).css('hidden',cols.hidden);
                    }
                    if(cols.className){
                        th.className=cols.className;
                    }
                }
                $(tr).append(th);
            }
            $(thead).append(tr);

            //this.insertBefore();

            methods.$ele.prepend(thead);
        },
        tbody:function(data){
            var tbody = document.createElement('tbody');
            var $ele=$(tbody);
            //romve tbody content

            methods.$ele.find('tbody').remove();
            //add data

            for (var i = 0; i < data.length; i++) {
                var rowData=data[i];
                this.addRow($ele,rowData,i);
            }

            methods.$ele.append($ele);
        },
        clearRow:function(index){
            var $ele=methods.$ele;
            //清空列表，移除全部行的方法

            if($ele.find('tbody')[0]){
                if(index){
                    $ele.find('tbody')[0].deleteRow(index);
                }else{
                    while($ele.find('tbody')[0].rows.length>0){
                        $ele.find('tbody')[0].deleteRow(0);
                    }
                }}
        },
        addRow:function ($ele,data,index){
            var options = methods.options;
            var tr = document.createElement('tr');
            if(options.checkbox){
                var td=document.createElement('td');
                var input =document.createElement('input');
                input.type='checkbox';
                input.axis=data;
                //intput.name='';

                //intput.value='';

                $(td).css({width:'25px','text-align': 'center'});
                $(td).append(input);
                $(tr).append(td);
            }
            //add cell

            for (var i = 0; i < options.columns.length; i++) {
                var td=document.createElement('td');
                var cols = options.columns[i];
                var value=data[cols.field];

                if (!value && (typeof value === 'string' || typeof value === 'undefined')) {
                    value = '';
                }
                if(cols){

                    if(cols.title){
                        td.innerHTML=value;
                    }
                    if(cols.field){//axis

                        td.setAttribute('field',cols.field);
                    }
                    if(cols.width){
                        $(td).css('width',cols.width);
                    }
                    if(cols.align){
                        $(td).css('text-align',cols.align);
                    }
                    if(cols.hidden){
                        $(td).css('hidden',cols.hidden);
                    }
                    if(cols.className){
                        td.className=cols.className;
                    }
                    if(cols.formatter){
                        td.innerHTML=cols.formatter(value,data,index);
                    }

                }
                $(tr).append(td);
            }

            $ele.append(tr);
        },
        load:function(index){
            var options = methods.options;
            var params={};
            params=options.params;
            params.currentPage=index==undefined?1:index;
            params.pageSize=10;
            $.ajax({
                type: options.method,
                url: options.url,
                data: params,
                dataType: options.dataType,
                success: function (res) {
                    var data=res.rows;
                    var page={total:res.total,totalPage:res.totalPage,currentPage:params.currentPage};
                    _grid.cache=data;
                    _grid.addData(data,page);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    // try {

                    //	if (options.onError) options.onError(XMLHttpRequest, textStatus, errorThrown);

                    //} catch (e) {}

                }
            });
        },
        addData:function(data,page){
            this.tbody(data);
            //page setting
            this.page(page);

            // 显示总数
            $('.total').text(page.total);

        },
        page:function(page){
            if(page.currentPage==1){
                //显示分页
                laypage({
                    cont: 'pagination', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                    pages: page.totalPage, //通过后台拿到的总页数
                    curr:  page.currentPage, //当前页
                    skip: true, //是否开启跳页
                    skin: '#5eb95e',
                    groups: 3, //连续显示分页数
                    jump: function(obj, first){ //触发分页后的回调
                        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                            _grid.load(obj.curr);
                        }
                    }
                });
            }
        }

    };

    var methods = {
        init: function (options) {
            methods.$ele=$(this)
            methods.options = $.extend(defaults, options || {});
            _grid.init();


            /* return this.each(function () {

                $(window).bind('resize.tgrid', methods.reposition);

            }); */

        },
        load:function(params){
            methods.options.params=params;
            _grid.load(1);
        },
        getSelections:function(){
            var rowData=new Array();
            var checks= $('td input:checkbox',methods.$ele);
            for(var i=0;i<checks.length;i++){
                var chk=checks[i];
                if(chk.checked){
                    rowData.push(chk.axis);
                }

            }

            return rowData;
        },
        destroy: function () {
            return this.each(function () {
                $(window).unbind('.tgrid');
            })

        },
        reposition: function () {
            //...

        },
        show: function () {
            //...

        },
        hide: function () {
            //...

        },
        update: function (content) {
            _grid.load();
            //...

        }
    };

    $.fn.tgrid = function (method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {

            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jquery.tgrid');
        }
    };

})(jQuery, window, document);