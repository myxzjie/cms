(function($){
	var browser =null
	
	if (!browser) {
		function uaMatch( ua ) {
			ua = ua.toLowerCase();

			var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
				/(webkit)[ \/]([\w.]+)/.exec( ua ) ||
				/(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
				/(msie) ([\w.]+)/.exec( ua ) ||
				ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
				[];

			return {
				browser: match[ 1 ] || "",
				version: match[ 2 ] || "0"
			};
		};

		var matched = uaMatch( navigator.userAgent );
		browser = {};

		if ( matched.browser ) {
			browser[ matched.browser ] = true;
			browser.version = matched.version;
		}

		// Chrome is Webkit, but Webkit is also Safari.
		if ( browser.chrome ) {
			browser.webkit = true;
		} else if ( browser.webkit ) {
			browser.safari = true;
		}
	}
	
	$.fn.browser=function(){
		return browser;
	}
})(jQuery);

;(function($) {
	
	var browser =$.browser;
	
	if(!browser){
		browser =$.support;
	}
	
	if (!browser) {
		function uaMatch( ua ) {
			ua = ua.toLowerCase();

			var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
				/(webkit)[ \/]([\w.]+)/.exec( ua ) ||
				/(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
				/(msie) ([\w.]+)/.exec( ua ) ||
				ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
				[];

			return {
				browser: match[ 1 ] || "",
				version: match[ 2 ] || "0"
			};
		};

		var matched = uaMatch( navigator.userAgent );
		browser = {};

		if ( matched.browser ) {
			browser[ matched.browser ] = true;
			browser.version = matched.version;
		}

		// Chrome is Webkit, but Webkit is also Safari.
		if ( browser.chrome ) {
			browser.webkit = true;
		} else if ( browser.webkit ) {
			browser.safari = true;
		}
	}
	
    $.addFlex = function(t, p) {
        if (t.grid)
            return false; //如果Grid已经存在则返回
        // 引用默认属性
        p = $.extend({
            id: '', //最外围的div的ID值
            idProperty: 'id', //主键名称
            height: 'auto', //flexigrid插件的高度，单位为px
            width: 'auto', //宽度值 auto表示根据每列的宽度自动计算
            striped: true, //是否显示斑纹效果，默认是奇偶交互的形式
            novstripe: false, //是否隐藏列之间的竖线
            minwidth: 30, //列的最小宽度
            minheight: 80, //列的最小高度
            resizable: false, //是否可伸缩
            url: false, //Ajax方式对应的url地址
            method: 'POST', //数据发送方式 GET/POST
            dataType: 'json', //数据加载的类型 xml/json
            usepager: false, //是否分页
            nowrap: true, //是否不换行
            page: 1, //默认当前页
            total: 1, //总页面数
            useRp: false, //use the results per page select box,是否可以动态设置每页显示的结果数
            rp: 25, // 每页默认的结果数
            rpOptions: [10, 15, 20, 25, 50, 100], //可选择设定的每页结果数
            title: false, //是否包含标题
            searchitems: false, //默认从colModel中取出所有的字段名称,可手动组成数组
            sortname: '', //排序字段名
            sortorder: '', //排序方式
            errormsg: '发生错误', //错误提升信息
            pagetext: '当前',
            pagestat: '显示记录从{from}到{to}，总数 {total} 条', //显示当前页和总页面的样式
            outof: '总页数',
            findtext: '快速检索',
            procmsg: '正在处理数据，请稍候 ...', //正在处理的提示信息
            nomsg: '没有符合条件的记录存在', //无结果的提示信息
            removefilter: '点击移除筛选', //移除筛选提示
            btnsubmit: '提交', //提交按钮
            btnappend: '追加', //追加按钮
            btnclear: '清空', //清空按钮
            query: '', //搜索查询的条件
            qtype: '', //搜索查询的类别
            qop: "=", //搜索的操作符
            minColToggle: 1, //隐藏列时最小保留的列数量
            showToggleBtn: true, //是否显示主体显示/隐藏切换按钮
            hideOnSubmit: true, //显示遮盖
            showTableToggleBtn: false, //显示隐藏Grid 
            autoload: true, //自动加载
            blockOpacity: 0.5, //透明度设置
            onToggleCol: false, //当在行之间转换时
            onChangeSort: false, //当改变排序时
            onSuccess: false, //成功后执行
            onError: false, //发生错误时执行
            onSubmit: false, // 当提交数据时执行
            onLayerInit: false, // 当Flexigrid的布局加载完成以后调用
            showcheckbox: true, //是否显示checkbox    
            singleselected: false, //是否单选
            rowhandler: false, //是否启用行的扩展事情功能
            selectedonclick: true, //点击行是否选中
            onrowchecked: false, //当行被选中时触发的事件，这里指checkbox选中
            showrownum: false, //是否显示行号，只有在checkbox不显示的情况下有用即showcheckbox:false
            extParam: [], //扩展参数[{name:'name',value:'value'}]
            gridClass: "flexigrid", //默认的Class
        }, p);
        $(t).show().attr({
            cellPadding: 0,
            cellSpacing: 0,
            border: 0
        }).removeAttr('width');
        var g = {
            hset: {},
            rePosDrag: function() {
                var cdleft = 0 - this.hDiv.scrollLeft;
                if (this.hDiv.scrollLeft > 0)
                    cdleft -= Math.floor(p.cgwidth / 2);
                $(g.cDrag).css({
                    top: g.hDiv.offsetTop + 1
                });
                var cdpad = this.cdpad;
                $('div', g.cDrag).hide();
                var i = 0;
                $('thead tr:first th:visible', this.hDiv).each(function() {
                    if ($(this).css("display") == "none")
                        return;
                    var n = i;
                    //var n = $('thead tr:first th:visible', g.hDiv).index(this);			 	  
                    var cdpos = parseInt($('div', this).width());
                    var ppos = cdpos;
                    if (cdleft == 0)
                        cdleft -= Math.floor(p.cgwidth / 2);
                    cdpos = cdpos + cdleft + cdpad;
                    $('div:eq(' + n + ')', g.cDrag).css({
                        'left': cdpos + 'px'
                    }).show();
                    cdleft = cdpos;
                    i++;
                });
            },
            fixHeight: function(newH) {
                newH = false;
                if (!newH)
                    newH = $(g.bDiv).height();
                var hdHeight = $(this.hDiv).height();
                $('div', this.cDrag).each(function() {
                    $(this).height(newH + hdHeight);
                });
                var nd = parseInt($(g.nDiv).height());
                if (nd > newH)
                    $(g.nDiv).height(newH).width(200);
                else
                    $(g.nDiv).height('auto').width('auto');
                $(g.block).css({
                    height: newH,
                    marginBottom: (newH * -1)
                });
                var hrH = g.bDiv.offsetTop + newH;
                if (p.height != 'auto' && p.resizable)
                    hrH = g.vDiv.offsetTop;
                $(g.rDiv).css({
                    height: hrH
                });
            },
            dragStart: function(dragtype, e, obj) { //default drag function start
                if (dragtype == 'colresize') {
                    $(g.nDiv).hide();
                    $(g.nBtn).hide();
                    var n = $('div', this.cDrag).index(obj);
                    //var ow = $('th:visible div:eq(' + n + ')', this.hDiv).width();
                    var ow = $('th:visible:eq(' + n + ') div', this.hDiv).width();
                    $(obj).addClass('dragging').siblings().hide();
                    $(obj).prev().addClass('dragging').show();
                    this.colresize = {
                        startX: e.pageX,
                        ol: parseInt(obj.style.left),
                        ow: ow,
                        n: n
                    };
                    $('body').css('cursor', 'col-resize');
                } else if (dragtype == 'vresize') {
                    var hgo = false;
                    $('body').css('cursor', 'row-resize');
                    if (obj) {
                        hgo = true;
                        $('body').css('cursor', 'col-resize');
                    }
                    this.vresize = {
                        h: p.height,
                        sy: e.pageY,
                        w: p.width,
                        sx: e.pageX,
                        hgo: hgo
                    };
                } else if (dragtype == 'colMove') {
                    $(g.nDiv).hide();
                    $(g.nBtn).hide();
                    this.hset = $(this.hDiv).offset();
                    this.hset.right = this.hset.left + $('table', this.hDiv).width();
                    this.hset.bottom = this.hset.top + $('table', this.hDiv).height();
                    this.dcol = obj;
                    this.dcoln = $('th', this.hDiv).index(obj);
                    this.colCopy = document.createElement("div");
                    this.colCopy.className = "colCopy";
                    this.colCopy.innerHTML = obj.innerHTML;
                    if (browser.msie) {
                        this.colCopy.className = "colCopy ie";
                    }
                    $(this.colCopy).css({
                        position: 'absolute',
                        float: 'left',
                        display: 'none',
                        textAlign: obj.align
                    });
                    $('body').append(this.colCopy);
                    $(this.cDrag).hide();
                }
                $('body').noSelect();
            },
            reSize: function() {
                $(this.gDiv).css("width", p.width);
                $(this.bDiv).css("height", p.height);
                this.fixHeight();
            },
            dragMove: function(e) {
                if (this.colresize) {
                    var n = this.colresize.n;
                    var diff = e.pageX - this.colresize.startX;
                    var nleft = this.colresize.ol + diff;
                    var nw = this.colresize.ow + diff;
                    if (nw > p.minwidth) {
                        $('div:eq(' + n + ')', this.cDrag).css('left', nleft);
                        this.colresize.nw = nw;
                    }
                } else if (this.vresize) {
                    var v = this.vresize;
                    var y = e.pageY;
                    var diff = y - v.sy;
                    if (!p.defwidth)
                        p.defwidth = p.width;
                    if (p.width != 'auto' && !p.nohresize && v.hgo) {
                        var x = e.pageX;
                        var xdiff = x - v.sx;
                        var newW = v.w + xdiff;
                        if (newW > p.defwidth) {
                            $(this.gDiv).css('width', newW);
                            p.width = newW;
                        }
                    }
                    var newH = v.h + diff;
                    if ((newH > p.minheight || p.height < p.minheight) && !v.hgo) {
                        this.bDiv.style.height = newH + 'px';
                        p.height = newH;
                        this.fixHeight(newH);
                    }
                    v = null;
                }
                else if (this.colCopy) {
                    $(this.dcol).addClass('thMove').removeClass('thOver');
                    if (e.pageX > this.hset.right || e.pageX < this.hset.left || e.pageY > this.hset.bottom || e.pageY < this.hset.top) {
                        //this.dragEnd();
                        $('body').css('cursor', 'move');
                    } else {
                        $('body').css('cursor', 'pointer');
                    }
                    $(this.colCopy).css({
                        top: e.pageY + 10,
                        left: e.pageX + 20,
                        display: 'block'
                    });
                }
            },
            dragEnd: function() {
                if (this.colresize) {
                    var n = this.colresize.n;
                    var nw = this.colresize.nw;
                    //$('th:visible div:eq(' + n + ')', this.hDiv).css('width', nw);
                    $('th:visible:eq(' + n + ') div', this.hDiv).css('width', nw);
                    $('tr', this.bDiv).each(function() {
                        //$('td:visible div:eq(' + n + ')', this).css('width', nw);
                        $('td:visible:eq(' + n + ') div', this).css('width', nw);
                    });
                    this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                    $('div:eq(' + n + ')', this.cDrag).siblings().show();
                    $('.dragging', this.cDrag).removeClass('dragging');
                    this.rePosDrag();
                    this.fixHeight();
                    this.colresize = false;
                } else if (this.vresize) {
                    this.vresize = false;
                }
                else if (this.colCopy) {
                    $(this.colCopy).remove();
                    if (this.dcolt != null) {
                        if (this.dcoln > this.dcolt) {
                            $('th:eq(' + this.dcolt + ')', this.hDiv).before(this.dcol);
                        } else {
                            $('th:eq(' + this.dcolt + ')', this.hDiv).after(this.dcol);
                        }
                        this.switchCol(this.dcoln, this.dcolt);
                        $(this.cdropleft).remove();
                        $(this.cdropright).remove();
                        this.rePosDrag();
                    }
                    this.dcol = null;
                    this.hset = null;
                    this.dcoln = null;
                    this.dcolt = null;
                    this.colCopy = null;
                    $('.thMove', this.hDiv).removeClass('thMove');
                    $(this.cDrag).show();
                }
                $('body').css('cursor', 'default');
                $('body').noSelect(false);
            },
            toggleCol: function(cid, visible) {
                var ncol = $("th[axis='col" + cid + "']", this.hDiv)[0];
                var n = $('thead th', g.hDiv).index(ncol);
                var cb = $('input[value=' + cid + ']', g.nDiv)[0];
                if (visible == null) {
                    visible = ncol.hide;
                }
                if ($('input:checked', g.nDiv).length < p.minColToggle && !visible)
                    return false;
                if (visible) {
                    ncol.hide = false;
                    $(ncol).show();
                    cb.checked = true;
                } else {
                    ncol.hide = true;
                    $(ncol).hide();
                    cb.checked = false;
                }
                $('tbody tr', t).each(function() {
                    if (visible)
                        $('td:eq(' + n + ')', this).show();
                    else
                        $('td:eq(' + n + ')', this).hide();
                });
                this.rePosDrag();
                if (p.onToggleCol)
                    p.onToggleCol(cid, visible);
                return visible;
            },
            switchCol: function(cdrag, cdrop) { //switch columns
                $('tbody tr', t).each
                        (function() {
                            if (cdrag > cdrop)
                                $('td:eq(' + cdrop + ')', this).before($('td:eq(' + cdrag + ')', this));
                            else
                                $('td:eq(' + cdrop + ')', this).after($('td:eq(' + cdrag + ')', this));
                        });
                //switch order in nDiv
                if (cdrag > cdrop)
                    $('tr:eq(' + cdrop + ')', this.nDiv).before($('tr:eq(' + cdrag + ')', this.nDiv));
                else
                    $('tr:eq(' + cdrop + ')', this.nDiv).after($('tr:eq(' + cdrag + ')', this.nDiv));
                if (browser.msie && browser.version < 7.0)
                    $('tr:eq(' + cdrop + ') input', this.nDiv)[0].checked = true;
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
            },
            scroll: function() {
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                this.rePosDrag();
            },
            hideLoading: function() {
                $('.pReload', this.pDiv).removeClass('loading');
                if (p.hideOnSubmit)
                    $(g.block).remove();
                $('.pPageStat', this.pDiv).html(p.errormsg);
                this.loading = false;
            },
            addData: function(data) { //parse data                
                if (p.preProcess) {
                    data = p.preProcess(data);
                }
                $('.pReload', this.pDiv).removeClass('loading');
                this.loading = false;
                if (!data) {
                    $('.pPageStat', this.pDiv).html(p.errormsg);
                    return false;
                }
                var temp = p.total;
                if (p.dataType == 'xml') {
                    p.total = +$('rows total', data).text();
                } else {
                    p.total = data.total;
                }
                if (p.total < 0) {
                    p.total = temp;
                }
                if (p.total == 0) {
                    $('tr, a, td, div', t).unbind();
                    $(t).empty();
                    p.pages = 1;
                    p.page = 1;
                    this.buildpager();
                    $('.pPageStat', this.pDiv).html(p.nomsg);
                    if (p.hideOnSubmit)
                        $(g.block).remove();
                    return false;
                }
                p.pages = Math.ceil(p.total / p.rp);
                if (p.dataType == 'xml') {
                    p.page = +$('rows page', data).text();
                } else {
                    p.page = data.page;
                }
                this.buildpager();
                var ths = $('thead tr:first th', g.hDiv);
                var thsdivs = $('thead tr:first th div', g.hDiv);
                var tbhtml = [];
                tbhtml.push("<tbody>");
                var pindex = p.rp * (p.page - 1);
                if (p.dataType == 'json') {
                    if (data.rows != null) {
                        $.each(data.rows, function(i, row) {
                            tbhtml.push("<tr id='", "row", p.id, row.id, "'");
                            if (i % 2 && p.striped) {
                                tbhtml.push(" class='erow'");
                            }
                            tbhtml.push(">");
                            var trid;
                            if (row[p.idProperty]) {
                                trid = row[p.idProperty];
                            }
                            $(ths).each(function(j) {
                                var tddata = "";
                                var tdclass = "";
                                tbhtml.push("<td align='", this.align, "'");
                                var idx = $(this).attr('axis').substr(3);
                                if (p.sortname && p.sortname == $(this).attr('abbr')) {
                                    tdclass = 'sorted';
                                }
                                if (this.hide) {
                                    tbhtml.push(" style='display:none;'");
                                }
                                var width = thsdivs[j].style.width;
                                var div = [];
                                div.push("<div style='text-align:", this.align, ";width:", width, ";");
                                if (p.nowrap == false) {
                                    div.push("white-space:normal");
                                }
                                div.push("'>");
                                if (idx == "-1" || idx == "-2") { //checkbox
                                    idx == "-1" && div.push("<input type='checkbox' id='chk_", row.id, "' class='itemchk' value='", row.id, "'/>");
                                    idx == "-2" && p.showrownum && div.push("<em>", pindex + i + 1, "</em>");
                                    if (tdclass != "") {
                                        tdclass += " chboxtd";
                                    } else {
                                        tdclass += "chboxtd";
                                    }
                                } else {
                                    var divInner = '';
                                    if (typeof row.cell[idx] != "undefined") {
                                        divInner = (row.cell[idx] !== null) ? row.cell[idx] : ''; //null-check for Opera-browser
                                    } else {
                                        divInner = row.cell[p.colModel[idx].alias];
                                    }
                                    if (this.process) {
                                        //回调的函数参数分别为：当前单元格的值,当前行主键的ID值,当前行的所有值(数组),当前单元格的字段名
                                        divInner = this.process(divInner, trid, row.cell, $(this).attr('field'));
                                    }
                                    div.push(divInner);
                                }
                                div.push("</div>");
                                if (tdclass != "") {
                                    tbhtml.push(" class='", tdclass, "'");
                                }
                                tbhtml.push(">", div.join(""), "</td>");
                            });
                            tbhtml.push("</tr>");
                        });
                    }
                } else if (p.dataType == 'xml') {
                    i = 1;
                    $("rows row", data).each(function() {
                        i++;
                        var robj = this;
                        var arrdata = new Array();
                        $("cell", robj).each(function() {
                            arrdata.push($(this).text());
                        });
                        var nid = $(this).attr('id');
                        tbhtml.push("<tr id='", "row", nid, "'");
                        if (i % 2 && p.striped) {
                            tbhtml.push(" class='erow'");
                        }
                        tbhtml.push(">");
                        var trid = nid;
                        $(ths).each(function(j) {
                            tbhtml.push("<td align='", this.align, "'");
                            if (this.hide) {
                                tbhtml.push(" style='display:none;'");
                            }
                            var tdclass = "";
                            var tddata = "";
                            var idx = $(this).attr('axis').substr(3);
                            if (p.sortname && p.sortname == $(this).attr('abbr')) {
                                tdclass = 'sorted';
                            }
                            var width = thsdivs[j].style.width;
                            var div = [];
                            div.push("<div style='text-align:", this.align, ";width:", width, ";");
                            if (p.nowrap == false) {
                                div.push("white-space:normal");
                            }
                            div.push("'>");
                            if (idx == "-1" || idx == "-2") { //checkbox
                                idx == "-1" && div.push("<input type='checkbox' id='chk_", nid, "' class='itemchk' value='", nid, "'/>");
                                idx == "-2" && p.showrownum && div.push("<em>", pindex + i + 1, "</em>");
                                if (tdclass != "") {
                                    tdclass += " chboxtd";
                                } else {
                                    tdclass += "chboxtd";
                                }
                            } else {
                                var divInner;
                                //支持XML以别名的形式
                                if ($(p.colModel[idx].alias, robj).size()) {
                                    divInner = $(p.colModel[idx].alias, robj).text();
                                } else {
                                    divInner = arrdata[idx] || "&nbsp;";
                                }
                                if (this.process) {
                                    divInner = this.process(divInner, trid, robj, $(this).attr('field'));
                                }
                                div.push(divInner);
                            }
                            div.push("</div>");
                            if (tdclass != "") {
                                tbhtml.push(" class='", tdclass, "'");
                            }
                            tbhtml.push(" axis='", tddata, "'", ">", div.join(""), "</td>");
                        });
                        tbhtml.push("</tr>");
                    });
                }
                tbhtml.push("</tbody>");
                $(t).html(tbhtml.join(""));

                $(".hDiv input[type=checkbox]").attr("checked", false);
                //this.rePosDrag();
                this.addRowProp();
                if (p.onSuccess)
                    p.onSuccess();
                if (p.hideOnSubmit)
                    $(g.block).remove(); //$(t).show();
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                if (browser.opera)
                    $(t).css('visibility', 'visible');
            },
            changeSort: function(th) { //change sortorder
                if (this.loading)
                    return true;
                $(g.nDiv).hide();
                $(g.nBtn).hide();
                if (p.sortname == $(th).attr('abbr')) {
                    if (p.sortorder == 'asc')
                        p.sortorder = 'desc';
                    else
                        p.sortorder = 'asc';
                }
                $(th).addClass('sorted').siblings().removeClass('sorted');
                $('.sdesc', this.hDiv).removeClass('sdesc');
                $('.sasc', this.hDiv).removeClass('sasc');
                $('div', th).addClass('s' + p.sortorder);
                p.sortname = $(th).attr('abbr');
                if (p.onChangeSort)
                    p.onChangeSort(p.sortname, p.sortorder);
                else
                    this.populate();
            },
            buildpager: function() { //rebuild pager based on new properties
                $('.pcontrol input', this.pDiv).val(p.page);
                $('.pcontrol span', this.pDiv).html(p.pages);
                var r1 = (p.page - 1) * p.rp + 1;
                var r2 = r1 + p.rp - 1;
                if (p.total < r2)
                    r2 = p.total;
                var stat = p.pagestat;
                stat = stat.replace(/{from}/, r1);
                stat = stat.replace(/{to}/, r2);
                stat = stat.replace(/{total}/, p.total);
                $('.pPageStat', this.pDiv).html(stat);
            },
            populate: function() { //get latest data 
                //log.trace("开始访问数据源");
                if (this.loading)
                    return true;
                if (p.onSubmit) {
                    var gh = p.onSubmit();
                    if (!gh)
                        return false;
                }
                this.loading = true;
                if (!p.url)
                    return false;
                $('.pPageStat', this.pDiv).html(p.procmsg);
                $('.pReload', this.pDiv).addClass('loading');
                $(g.block).css({
                    top: g.bDiv.offsetTop
                });
                if (p.hideOnSubmit)
                    $(this.gDiv).prepend(g.block); //$(t).hide();
                if (browser.opera)
                    $(t).css('visibility', 'hidden');
                if (!p.newp)
                    p.newp = 1;
                if (p.page > p.pages)
                    p.page = p.pages;
                //var param = {page:p.newp, rp: p.rp, sortname: p.sortname, sortorder: p.sortorder, query: p.query, qtype: p.qtype};
                var param = [
                    {
                        name: 'page',
                        value: p.newp
                    }
                    , {
                        name: 'rp',
                        value: p.rp
                    }
                    , {
                        name: 'sortname',
                        value: p.sortname
                    }
                    , {
                        name: 'sortorder',
                        value: p.sortorder
                    }
                    , {
                        name: 'query',
                        value: p.query
                    }
                    , {
                        name: 'qtype',
                        value: p.qtype
                    }
                    , {
                        name: 'qop',
                        value: p.qop
                    }
                ];
                p.colkey = '';
                var cols = [];
                var alias = [];
                var name, field, aliasname;
                var hash = {};
                for (var cindex = 0, clength = p.colModel.length; cindex < clength; cindex++) {
                    if (p.colModel[cindex] == undefined)
                        continue;
                    if (p.colModel[cindex].iskey) {
                        p.colkey = p.colModel[cindex].name;
                    }
                    name = p.colModel[cindex].name;
                    //如果设定了别名,则使用别名,否则直接使用name的值
                    if (p.colModel[cindex].alias != undefined) {
                        field = p.colModel[cindex].alias;
                    } else {
                        field = name.substr(name.indexOf(".") + 1);
                    }
                    if (hash[field] != undefined) {
                        hash[field]++;
                        aliasname = field + "" + hash[field];
                    } else {
                        hash[field] = 0;
                        aliasname = field;
                    }
                    cols.push(name);
                    alias.push(aliasname);
                }
                p.cols = cols.join(",");
                p.alias = alias.join(",");
                param.push({
                    name: "colkey",
                    value: p.colkey
                });
                param.push({
                    name: "colsinfo",
                    value: p.cols
                });
                param.push({
                    name: "aliasinfo",
                    value: p.alias
                });
                //param = jQuery.extend(param, p.extParam);             
                if (p.extParam) {
                    for (var pi = 0; pi < p.extParam.length; pi++)
                        param[param.length] = p.extParam[pi];
                }
                //读取筛选的条件
                if (p.searchParam) {
                    for (var si = 0; si < p.searchParam.length; si++)
                        param[param.length] = p.searchParam[si];
                }
                //追加搜索
                $(".sDiv3 a", g.sDiv).each(function() {
                    param.push({
                        name: "searchitems[]",
                        value: $(this).attr("name") + "$$" + $(this).attr("op") + "$$" + $("u", this).text()
                    });
                });
                var purl = p.url + (p.url.indexOf('?') > -1 ? '&' : '?') + '_=' + (new Date()).valueOf();
                $.ajax({
                    type: p.method,
                    url: purl,
                    data: param,
                    dataType: p.dataType,
                    success: function(data) {
                        if (data != null && data.error != null) {
                            if (p.onError) {
                                p.onError(data);
                                g.hideLoading();
                            }
                        } else {
                            g.addData(data);
                        }
                    },
                    error: function(data) {
                        try {
                            if (p.onError) {
                                p.onError(data);
                            } else {
                                alert("获取数据发生异常;")
                            }
                            g.hideLoading();
                        } catch (e) {
                        }
                    }
                });
            },
            doSearch: function() {
                var queryType = $('select[name=qtype]', g.sDiv).val();
                var qArrType = queryType.split("$");
                var index = -1;
                if (qArrType.length != 3) {
                    p.qop = "=";
                    p.qtype = queryType;
                }
                else {
                    p.qop = qArrType[1];
                    p.qtype = qArrType[0];
                    index = parseInt(qArrType[2]);
                }
                p.qop = $("select[name=qop]", g.sDiv).val();
                p.query = $('input[name=q]', g.sDiv).val();
                //添加验证代码
                if (p.query != "" && p.searchitems && index >= 0 && p.searchitems.length > index) {
                    if (p.searchitems[index].reg) {
                        if (!p.searchitems[index].reg.test(p.query)) {
                            alert("你的输入不符合要求!");
                            return;
                        }
                    }
                }
                p.newp = 1;
                this.populate();
            },
            changePage: function(ctype) { //change page
                if (this.loading)
                    return true;
                switch (ctype) {
                    case 'first':
                        p.newp = 1;
                        break;
                    case 'prev':
                        if (p.page > 1)
                            p.newp = parseInt(p.page) - 1;
                        break;
                    case 'next':
                        if (p.page < p.pages)
                            p.newp = parseInt(p.page) + 1;
                        break;
                    case 'last':
                        p.newp = p.pages;
                        break;
                    case 'input':
                        var nv = parseInt($('.pcontrol input', this.pDiv).val());
                        if (isNaN(nv))
                            nv = 1;
                        if (nv < 1)
                            nv = 1;
                        else if (nv > p.pages)
                            nv = p.pages;
                        $('.pcontrol input', this.pDiv).val(nv);
                        p.newp = nv;
                        break;
                }
                if (p.newp == p.page)
                    return false;
                if (p.onChangePage)
                    p.onChangePage(p.newp);
                else
                    this.populate();
            },
            cellProp: function(n, ptr, pth) {
                var tdDiv = document.createElement('div');
                if (pth != null) {
                    if (p.sortname == $(pth).attr('abbr') && p.sortname) {
                        this.className = 'sorted';
                    }
                    $(tdDiv).css({
                        textAlign: pth.align,
                        width: $('div:first', pth)[0].style.width
                    });
                    if (pth.hide)
                        $(this).css('display', 'none');
                }
                if (p.nowrap == false)
                    $(tdDiv).css('white-space', 'normal');
                if (this.innerHTML == '')
                    this.innerHTML = '&nbsp;';
                //tdDiv.value = this.innerHTML; //store preprocess value
                tdDiv.innerHTML = this.innerHTML;
                var prnt = $(this).parent()[0];
                var pid = false;
                if (prnt.id)
                    pid = prnt.id.substr(3);
                if (pth != null) {
                    if (pth.process)
                    {
                        pth.process(tdDiv, pid);
                    }
                }
                $("input.itemchk", tdDiv).each(function() {
                    $(this).click(function() {
                        if (this.checked) {
                            $(ptr).addClass("trSelected");
                        }
                        else {
                            $(ptr).removeClass("trSelected");
                        }
                        if (p.onrowchecked) {
                            p.onrowchecked.call(this);
                        }
                    });
                });
                $(this).empty().append(tdDiv).removeAttr('width'); //wrap content
                //add editable event here 'dblclick',如果需要可编辑在这里添加可编辑代码 
            },
            addCellProp: function() {
                var $gF = this.cellProp;
                $('tbody tr td', g.bDiv).each(
                        function() {
                            var n = $('td', $(this).parent()).index(this);
                            var pth = $('th:eq(' + n + ')', g.hDiv).get(0);
                            var ptr = $(this).parent();
                            $gF.call(this, n, ptr, pth);
                        });
                $gF = null;
            },
            getCheckedRows: function() {
                var ids = [];
                $("input.itemchk:checked", g.bDiv).each(function() {
                    ids.push($(this).val());
                });
                return ids;
            },
            getSelectedRows: function() {
                var items = [];
                $("tr.trSelected", g.bDiv).each(function() {
                    var haschkbox = $("td:.chboxtd", this).size();
                    var item = {};
                    $("td:not(.chboxtd)", this).each(function() {
                        item[p.colModel[$(this).index() - haschkbox].alias] = $(this).text();
                    });
                    items.push(item);
                });
                return items;
            },
            getCellDim: function(obj) {
                // get cell prop for editable event
                var ht = parseInt($(obj).height());
                var pht = parseInt($(obj).parent().height());
                var wt = parseInt(obj.style.width);
                var pwt = parseInt($(obj).parent().width());
                var top = obj.offsetParent.offsetTop;
                var left = obj.offsetParent.offsetLeft;
                var pdl = parseInt($(obj).css('paddingLeft'));
                var pdt = parseInt($(obj).css('paddingTop'));
                return {
                    ht: ht,
                    wt: wt,
                    top: top,
                    left: left,
                    pdl: pdl,
                    pdt: pdt,
                    pht: pht,
                    pwt: pwt
                };
            },
            rowProp: function() {
                if (p.rowhandler) {
                    p.rowhandler(this);
                }
                if (browser.msie && browser.version < 7.0) {
                    $(this).hover(function() {
                        $(this).addClass('trOver');
                    }, function() {
                        $(this).removeClass('trOver');
                    });
                }
            },
            checkhandler: function() {
                var $t = $(this);
                var $ck = $("input.itemchk", this);
                if (p.singleselected) {
                    $t.parent().find("tr.trSelected").each(function(e) {
                        if (this != $t[0]) {
                            $(this).removeClass("trSelected");
                        }
                        $("input.itemchk", this).each(function(e) {
                            this.checked = false;
                        });
                    });
                }
                if ($t.hasClass("trSelected")) {
                    $ck.length > 0 && ($ck[0].checked = false);
                    $t.removeClass("trSelected");
                }
                else {
                    $ck.length > 0 && ($ck[0].checked = true);
                    $t.addClass("trSelected");
                }
            },
            addRowProp: function() {
                var $gF = this.rowProp;
                var $cf = this.checkhandler;
                $('tbody tr', g.bDiv).each(
                        function() {
                            if (p.showcheckbox) {
                                $("input.itemchk", this).each(function() {
                                    $(this).click(function(e) {
                                        var ptr = $(this).parent().parent().parent();
                                        $cf.call(ptr);
                                        if (p.onrowchecked) {
                                            p.onrowchecked.call(this);
                                        }
                                        e.stopPropagation();
                                    });
                                });
                            }
                            if (p.selectedonclick) {
                                $(this).click($cf);
                            }
                            $gF.call(this);
                        });
                $gF = null;
            },
            checkAllOrNot: function(parent) {
                var ischeck = $(this).attr("checked");
                $('tbody tr', g.bDiv).each(function() {
                    if (ischeck) {
                        $(this).addClass("trSelected");
                    } else {
                        $(this).removeClass("trSelected");
                    }
                });
                $("input.itemchk", g.bDiv).each(function() {
                    this.checked = ischeck;
                    //Raise Event
                    if (p.onrowchecked) {
                        p.onrowchecked.call(this);
                    }
                });
            },
            pager: 0
        };
        //create model if any
        if (p.colModel) {
            thead = document.createElement('thead');
            tr = document.createElement('tr');
            //p.showcheckbox ==true;
            var cth = jQuery('<th/>');
            cth.addClass("cth").attr({
                width: "27",
                "isch": true
            })
            if (p.showcheckbox) {
                var cthch = jQuery('<input type="checkbox"/>');
                cthch.addClass("noborder");
                if (p.singleselected) {
                    cthch.attr("disabled", true).css("visibility", "hidden");
                }
                cth.attr('axis', "col-1");
                cth.append(cthch);
            } else {
                cth.attr('axis', "col-2");
            }
            $(tr).append(cth);
            var alias, hash = {};
            for (i = 0; i < p.colModel.length; i++) {
                var cm = p.colModel[i];
                if (p.colModel[i] == undefined)
                    continue;
                cm.align = cm.align == undefined ? 'center' : cm.align;
                cm.sortable = cm.sortable == undefined ? true : cm.sortable;

                var th = document.createElement('th');
                th.innerHTML = cm.display;
                $(th).attr('field', cm.name);
                if (cm.name && cm.sortable)
                    $(th).attr('abbr', cm.name);
                //th.idx = i;
                $(th).attr('axis', 'col' + i);
                //Append alias attr
                alias = cm.alias ? cm.alias : cm.name.substr(cm.name.indexOf(".") + 1);
                if (hash[alias] != undefined) {
                    hash[alias]++;
                    alias = alias + "" + hash[alias];
                } else {
                    hash[alias] = 0;
                }
                $(th).attr('alias', alias);
                cm.alias = alias;
                if (cm.align)
                    th.align = cm.align;
                if (cm.width)
                    $(th).attr('width', cm.width);
                if (cm.hide) {
                    th.hide = true;
                }
                if (cm.toggle != undefined) {
                    th.toggle = cm.toggle;
                }
                if (cm.process) {
                    th.process = cm.process;
                }
                if (cm.search) {
                    th.search = cm.search;
                }
                $(tr).append(th);
            }
            $(thead).append(tr);
            $(t).prepend(thead);
        } // end if p.colmodel	
        //init divs
        g.gDiv = document.createElement('div'); //create global container
        g.mDiv = document.createElement('div'); //create title container
        g.hDiv = document.createElement('div'); //create header container
        g.bDiv = document.createElement('div'); //create body container
        g.vDiv = document.createElement('div'); //create grip
        g.rDiv = document.createElement('div'); //create horizontal resizer
        g.cDrag = document.createElement('div'); //create column drag
        g.block = document.createElement('div'); //creat blocker
        g.nDiv = document.createElement('div'); //create column show/hide popup
        g.nBtn = document.createElement('div'); //create column show/hide button
        g.iDiv = document.createElement('div'); //create editable layer
        g.tDiv = document.createElement('div'); //create toolbar
        g.sDiv = document.createElement('div');
        if (p.usepager)
            g.pDiv = document.createElement('div'); //create pager container
        g.hTable = document.createElement('table');
        //set gDiv
        g.gDiv.className = p.gridClass;
        p.id = p.id ? p.id : "flexigrid_" + $(p).getRandomString(10);
        g.gDiv.id = p.id;

        if (p.width != 'auto')
            $(g.gDiv).css('width', p.width);
        //add conditional classes
        if (browser.msie)
            $(g.gDiv).addClass('ie');
        if (p.novstripe)
            $(g.gDiv).addClass('novstripe');
        $(t).before(g.gDiv);
        $(g.gDiv)
                .append(t);
        //set toolbar
        if (p.buttons) {
            g.tDiv.className = 'tDiv';
            var tDiv2 = document.createElement('div');
            tDiv2.className = 'tDiv2';
            for (i = 0; i < p.buttons.length; i++) {
                if (p.buttons[i] == undefined)
                    continue;
                var btn = p.buttons[i];
                if (!btn.separator) {
                    var btnDiv = document.createElement('div');
                    btnDiv.className = 'fbutton';
                    var title = btn.title != undefined ? btn.title : btn.displayname;
                    btnDiv.innerHTML = "<div>" + (btn.url ? "<a href='" + btn.url + "' title='" + title + "' class='navlink'>" : '') + "<span>" + btn.displayname + "</span>" + (btn.url ? "</a>" : '') + "</div>";
                    if (btn.title) {
                        btnDiv.title = btn.title;
                    }
                    if (btn.bclass)
                        $('span', btnDiv).addClass(btn.bclass);
                    btnDiv.onpress = btn.onpress;
                    btnDiv.name = btn.name;
                    if (btn.onpress) {
                        $(btnDiv).click(function() {
                            this.onpress(this.name, g.gDiv);
                        });
                    }
                    $(tDiv2).append(btnDiv);
                    if (browser.msie && browser.version < 7.0) {
                        $(btnDiv).hover(function() {
                            $(this).addClass('fbOver');
                        }, function() {
                            $(this).removeClass('fbOver');
                        });
                    }
                } else {
                    $(tDiv2).append("<div class='btnseparator'></div>");
                }
            }
            $(g.tDiv).append(tDiv2);
            $(g.tDiv).append("<div style='clear:both'></div>");
            $(g.gDiv).prepend(g.tDiv);
        }
        //set hDiv
        g.hDiv.className = 'hDiv';
        $(t).before(g.hDiv);
        //set hTable
        g.hTable.cellPadding = 0;
        g.hTable.cellSpacing = 0;
        $(g.hDiv).append('<div class="hDivBox"></div>');
        $('div', g.hDiv).append(g.hTable);
        var thead = $("thead:first", t).get(0);
        if (thead)
            $(g.hTable).append(thead);
        thead = null;
        if (!p.colmodel)
            var ci = 0;
        var totalnumwidth = 0;
        var totalpercent = 0;
        var totalwidth = $(g.hDiv).width() - 21;
        $("thead tr:first th", g.hDiv).each(function() {
            this.percent = this.width.indexOf("%") != -1 ? parseInt(this.width.replace("%", "")) : 0;
            totalpercent += this.percent;
            totalnumwidth += this.percent ? 0 : parseInt(this.width) + 12;
        });
        if (totalnumwidth < totalwidth) {
            var everywidth = (totalwidth - totalnumwidth) / totalpercent;
            $("thead tr:first th", g.hDiv).each(function() {
                if (this.percent) {
                    this.width = this.percent * everywidth - 12;
                }
            })
        }
        //setup thead 进行排序操作	
        $('thead tr:first th', g.hDiv).each(function() {
            var thdiv = document.createElement('div');
            if ($(this).attr('abbr')) {
                $(this).click(function(e) {
                    if (!$(this).hasClass('thOver'))
                        return false;
                    var obj = (e.target || e.srcElement);
                    if (obj.href || obj.type)
                        return true;
                    g.changeSort(this);
                });
                if ($(this).attr('abbr') == p.sortname) {
                    this.className = 'sorted';
                    thdiv.className = 's' + p.sortorder;
                }
            }
            if (this.hide)
                $(this).hide();
            if (!p.colmodel && !$(this).attr("isch")) {
                $(this).attr('axis', 'col' + ci++);
            }

            //this.width = this.width.indexOf("%") != -1 ? GetColumnSize(this.width.replace('%', '')) : this.width;
            $(thdiv).css({
                textAlign: this.align,
                width: this.width + 'px'
            });
            thdiv.innerHTML = this.innerHTML;
            $(this).empty().append(thdiv).removeAttr('width');
            if (!$(this).attr("isch")) {
                $(this).mousedown(function(e) {
                    g.dragStart('colMove', e, this);
                })
                        .hover(
                        function() {
                            if (!g.colresize && !$(this).hasClass('thMove') && !g.colCopy)
                                $(this).addClass('thOver');
                            if ($(this).attr('abbr') != p.sortname && !g.colCopy && !g.colresize && $(this).attr('abbr'))
                                $('div', this).addClass('s' + p.sortorder);
                            else if ($(this).attr('abbr') == p.sortname && !g.colCopy && !g.colresize && $(this).attr('abbr')) {
                                var no = '';
                                if (p.sortorder == 'asc')
                                    no = 'desc';
                                else
                                    no = 'asc';
                                $('div', this).removeClass('s' + p.sortorder).addClass('s' + no);
                            }
                            if (g.colCopy) {
                                var n = $('th', g.hDiv).index(this);
                                if (n == g.dcoln)
                                    return false;
                                if (n < g.dcoln)
                                    $(this).append(g.cdropleft);
                                else
                                    $(this).append(g.cdropright);
                                g.dcolt = n;
                            } else if (!g.colresize) {
                                var thsa = $('th:visible', g.hDiv);
                                var nv = -1;
                                for (var i = 0, j = 0, l = thsa.length; i < l; i++) {
                                    if ($(thsa[i]).css("display") != "none") {
                                        if (thsa[i] == this) {
                                            nv = j;
                                            break;
                                        }
                                        j++;
                                    }
                                }
                                // var nv = $('th:visible', g.hDiv).index(this);
                                var onl = parseInt($('div:eq(' + nv + ')', g.cDrag).css('left'));
                                var nw = parseInt($(g.nBtn).width()) + parseInt($(g.nBtn).css('borderLeftWidth'));
                                nl = onl - nw + Math.floor(p.cgwidth / 2);
                                $(g.nDiv).hide();
                                $(g.nBtn).hide();
                                $(g.nBtn).css({
                                    'left': nl,
                                    top: g.hDiv.offsetTop
                                }).show();
                                var ndw = parseInt($(g.nDiv).width());
                                $(g.nDiv).css({
                                    top: g.bDiv.offsetTop
                                });
                                if ((nl + ndw) > $(g.gDiv).width())
                                    $(g.nDiv).css('left', onl - ndw + 1);
                                else
                                    $(g.nDiv).css('left', nl);
                                if ($(this).hasClass('sorted'))
                                    $(g.nBtn).addClass('srtd');
                                else
                                    $(g.nBtn).removeClass('srtd');
                            }
                        },
                        function() {
                            $(this).removeClass('thOver');
                            if ($(this).attr('abbr') != p.sortname)
                                $('div', this).removeClass('s' + p.sortorder);
                            else if ($(this).attr('abbr') == p.sortname) {
                                var no = '';
                                if (p.sortorder == 'asc')
                                    no = 'desc';
                                else
                                    no = 'asc';
                                $('div', this).addClass('s' + p.sortorder).removeClass('s' + no);
                            }
                            if (g.colCopy) {
                                $(g.cdropleft).remove();
                                $(g.cdropright).remove();
                                g.dcolt = null;
                            }
                        }); //wrap content
            }
        }
        );
        //set bDiv
        g.bDiv.className = 'bDiv';
        $(t).before(g.bDiv);
        $(g.bDiv)
                .css({
            height: (p.height == 'auto') ? 'auto' : p.height + "px"
        })
                .scroll(function(e) {
            g.scroll();
        })
                .append(t);
        if (p.height == 'auto') {
            $('table', g.bDiv).addClass('autoht');
        }
        //add td properties
        if (p.url == false || p.url == "") {
            g.addCellProp();
            //add row properties
            g.addRowProp();
        }
        //set cDrag
        var cdcol = $('thead tr:first th:first', g.hDiv).get(0);
        if (cdcol != null) {
            g.cDrag.className = 'cDrag';
            g.cdpad = 0;
            g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderLeftWidth'))) ? 0 : parseInt($('div', cdcol).css('borderLeftWidth')));
            g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderRightWidth'))) ? 0 : parseInt($('div', cdcol).css('borderRightWidth')));
            g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingLeft'))) ? 0 : parseInt($('div', cdcol).css('paddingLeft')));
            g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingRight'))) ? 0 : parseInt($('div', cdcol).css('paddingRight')));
            g.cdpad += (isNaN(parseInt($(cdcol).css('borderLeftWidth'))) ? 0 : parseInt($(cdcol).css('borderLeftWidth')));
            g.cdpad += (isNaN(parseInt($(cdcol).css('borderRightWidth'))) ? 0 : parseInt($(cdcol).css('borderRightWidth')));
            g.cdpad += (isNaN(parseInt($(cdcol).css('paddingLeft'))) ? 0 : parseInt($(cdcol).css('paddingLeft')));
            g.cdpad += (isNaN(parseInt($(cdcol).css('paddingRight'))) ? 0 : parseInt($(cdcol).css('paddingRight')));
            $(g.bDiv).before(g.cDrag);
            var cdheight = $(g.bDiv).height();
            var hdheight = $(g.hDiv).height();
            $(g.cDrag).css({
                top: -hdheight + 'px'
            });
            $('thead tr:first th', g.hDiv).each(function() {
                var cgDiv = document.createElement('div');
                $(g.cDrag).append(cgDiv);
                if (!p.cgwidth)
                    p.cgwidth = $(cgDiv).width();
                $(cgDiv).css({
                    height: cdheight + hdheight
                })
                        .mousedown(function(e) {
                    g.dragStart('colresize', e, this);
                });
                if (browser.msie && browser.version < 7.0) {
                    g.fixHeight($(g.gDiv).height());
                    $(cgDiv).hover(
                            function() {
                                g.fixHeight();
                                $(this).addClass('dragging');
                            },
                            function() {
                                if (!g.colresize)
                                    $(this).removeClass('dragging');
                            });
                }
            });
            //g.rePosDrag();
        }
        //add strip		
        if (p.striped)
            $('tbody tr:odd', g.bDiv).addClass('erow');
        if (p.resizable && p.height != 'auto') {
            g.vDiv.className = 'vGrip';
            $(g.vDiv)
                    .mousedown(function(e) {
                g.dragStart('vresize', e);
            })
                    .html('<span></span>');
            $(g.bDiv).after(g.vDiv);
        }
        if (p.resizable && p.width != 'auto' && !p.nohresize) {
            g.rDiv.className = 'hGrip';
            $(g.rDiv)
                    .mousedown(function(e) {
                g.dragStart('vresize', e, true);
            })
                    .html('<span></span>')
                    .css('height', $(g.gDiv).height());
            if (browser.msie && browser.version < 7.0) {
                $(g.rDiv).hover(function() {
                    $(this).addClass('hgOver');
                }, function() {
                    $(this).removeClass('hgOver');
                });
            }
            $(g.gDiv).append(g.rDiv);
        }
        // add pager
        if (p.usepager) {
            g.pDiv.className = 'pDiv';
            g.pDiv.innerHTML = '<div class="pDiv2"></div>';
            $(g.bDiv).after(g.pDiv);
            var html = '<div class="pGroup"><div class="pFirst pButton" title=""><span></span></div><div class="pPrev pButton" title=""><span></span></div> </div><div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">' + p.pagetext + ' <input type="text" size="1" value="1" /> ' + p.outof + ' <span> 1 </span></span></div><div class="btnseparator"></div><div class="pGroup"> <div class="pNext pButton" title=""><span></span></div><div class="pLast pButton" title=""><span></span></div></div><div class="btnseparator"></div><div class="pGroup"> <div class="pReload pButton" title=""><span></span></div> </div> <div class="btnseparator"></div><div class="pGroup"><span class="pPageStat"></span></div>';
            $('div', g.pDiv).html(html);
            $('.pReload', g.pDiv).click(function() {
                g.populate();
            });
            $('.pFirst', g.pDiv).click(function() {
                g.changePage('first');
            });
            $('.pPrev', g.pDiv).click(function() {
                g.changePage('prev');
            });
            $('.pNext', g.pDiv).click(function() {
                g.changePage('next');
            });
            $('.pLast', g.pDiv).click(function() {
                g.changePage('last');
            });
            $('.pcontrol input', g.pDiv).keydown(function(e) {
                if (e.keyCode == 13)
                    g.changePage('input');
            });
            if (browser.msie && browser.version < 7)
                $('.pButton', g.pDiv).hover(function() {
                    $(this).addClass('pBtnOver');
                }, function() {
                    $(this).removeClass('pBtnOver');
                });
            if (p.useRp) {
                var opt = "";
                for (var nx = 0; nx < p.rpOptions.length; nx++) {
                    if (p.rp == p.rpOptions[nx])
                        sel = 'selected="selected"';
                    else
                        sel = '';
                    opt += "<option value='" + p.rpOptions[nx] + "' " + sel + " >" + p.rpOptions[nx] + "</option>";
                }
                $('.pDiv2', g.pDiv).prepend("<div class='pGroup'><select name='rp'>" + opt + "</select></div> <div class='btnseparator'></div>");
                $('select', g.pDiv).change(
                        function() {
                            if (p.onRpChange)
                                p.onRpChange(+this.value);
                            else {
                                p.newp = 1;
                                p.rp = +this.value;
                                g.populate();
                            }
                        });
            }
            //add search button
            if (p.searchitems) {
                $('.pDiv2', g.pDiv).prepend("<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
                $('.pSearch', g.pDiv).click(function() {
                    $(g.sDiv).slideToggle('fast', function() {
                        $('.sDiv:visible input:first', g.gDiv).trigger('focus');
                    });
                });
                //add search box
                g.sDiv.className = 'sDiv';

                var sitems = [];
                var sopt = "";
                var sfilter = "";
                var op = "=";
                var hash = {};
                if (typeof p.searchitems == 'boolean') {
                    $(p.colModel).each(function(i, j) {
                        if (j.name != undefined && hash[j.name] == undefined) {
                            sitems.push({display: j.display, name: j.name, isdefault: j.iskey, defaultvalue: j.defaultvalue});
                            hash[j.name] = true;
                        }
                    });
                } else {
                    sitems = p.searchitems;
                }
                for (var s = 0; s < sitems.length; s++) {
                    if (sitems[s] == undefined)
                        continue;
                    if (p.qtype == '' && sitems[s].isdefault == true) {
                        p.qtype = sitems[s].name;
                        sel = 'selected="selected"';
                    } else {
                        sel = '';
                    }
                    if (sitems[s].operater != undefined) {
                        op = sitems[s].operater;
                    } else {
                        op = "=";
                    }
                    sopt += "<option value='" + sitems[s].name + "$" + op + "$" + s + "' " + sel + " >" + sitems[s].display + "</option>";
                    if (sitems[s].defaultvalue) {
                        sfilter += "<a href='javascript:;' title='" + p.removefilter + "' op='" + op + "' name='" + sitems[s].name + "'><b>" + sitems[s].display + " " + op + "</b><u>" + sitems[s].defaultvalue + "</u><i>×</i></a>";
                    }
                }
                if (p.qtype == '' && sitems[0] != undefined)
                    p.qtype = sitems[0].name;
                var qoparr = ["like", "=", ">", "<", ">=", "<="];
                var qops = '';
                for (var s = 0; s < qoparr.length; s++) {
                    qops += "<option value='" + qoparr[s] + "'>" + qoparr[s] + "</option>";
                }
                $(g.sDiv).append("<div class='sDiv2'>" + p.findtext + "：<select name='qtype'>" + sopt + "</select><select name='qop'>" + qops + "</select>  <input type='text' size='30' name='q' class='qsbox' /> <input type='button' name='qsubmitbtn' value='" + p.btnsubmit + "' /> <input type='button' name='qappendbtn' value='" + p.btnappend + "' /> <input type='button' name='qclearbtn' value='" + p.btnclear + "' /></div>");
                //多条件匹配搜索查询
                $(g.sDiv).append("<div class='sDiv3'>" + sfilter + "</div>");
                $(".sDiv3 a", g.sDiv).live('click', function() {
                    $(this).remove();
                    //如果不存在筛选条件时,隐藏搜索框
                    if ($(".qsbox", g.sDiv).val() == '' && $(".sDiv3 a", g.sDiv).size() == 0) {
                        $(g.sDiv).hide();
                    }
                    g.doSearch();
                });
                $('input[name=q],select[name=qtype]', g.sDiv).keydown(function(e) {
                    if (e.keyCode == 13)
                        g.doSearch();
                });
                $('input[name=qsubmitbtn]', g.sDiv).click(function() {
                    g.doSearch();
                });
                //追加查询
                $('input[name=qappendbtn]', g.sDiv).click(function() {
                    var queryType = $('select[name=qtype]', g.sDiv).val();
                    var qArrType = queryType.split("$");
                    var index = -1;
                    var qop = $("select[name=qop]", g.sDiv).val();
                    var qtype = queryType;
                    if (qArrType.length == 3) {
                        //qop = qArrType[1];
                        qtype = qArrType[0];
                        index = parseInt(qArrType[2]);
                    }
                    var exist = false;
                    $("a[op='" + qop + "'][name='" + qtype + "']", g.sDiv).each(function() {
                        if ($("u", this).text() == $('input[name=q]', g.sDiv).val()) {
                            exist = true;
                            return false;
                        }
                    });
                    if (!exist) {
                        var sitem = "<a href='javascript:;' title='" + p.removefilter + "' op='" + qop + "' name='" + qtype + "'><b>" + $('select[name=qtype] option', g.sDiv).eq(index).text() + " " + qop + "</b><u>" + $('input[name=q]', g.sDiv).val() + "</u><i>×</i></a>";
                        $(".sDiv3", g.sDiv).append(sitem);
                        $('input[name=q]', g.sDiv).val('');
                        p.query = '';
                        g.populate();
                    } else {
                        alert("已经存在追加的查询字符串");
                    }
                });
                $('input[name=qclearbtn]', g.sDiv).click(function() {
                    $('input[name=q]', g.sDiv).val('');
                    p.query = '';
                    g.doSearch();
                });
                $(g.bDiv).after(g.sDiv);
                //如果有筛选条件则显示出来
                if ($('.sDiv3 a', g.sDiv).size()) {
                    $(g.sDiv).show();
                }
            }
        }
        $(g.pDiv, g.sDiv).append("<div style='clear:both'></div>");
        // add title
        if (p.title) {
            g.mDiv.className = 'mDiv';
            g.mDiv.innerHTML = '<div class="ftitle">' + p.title + '</div>';
            $(g.gDiv).prepend(g.mDiv);
            if (p.showTableToggleBtn) {
                $(g.mDiv).append('<div class="ptogtitle" title="Minimize/Maximize Table"><span></span></div>');
                $('div.ptogtitle', g.mDiv).click(function() {
                    $(g.gDiv).toggleClass('hideBody');
                    $(this).toggleClass('vsble');
                });
            }
            //g.rePosDrag();
        }
        //setup cdrops
        g.cdropleft = document.createElement('span');
        g.cdropleft.className = 'cdropleft';
        g.cdropright = document.createElement('span');
        g.cdropright.className = 'cdropright';
        //add block
        g.block.className = 'gBlock';
        var blockloading = $("<div/>");
        blockloading.addClass("loading");
        $(g.block).append(blockloading);
        var gh = $(g.bDiv).height();
        var gtop = g.bDiv.offsetTop;
        $(g.block).css({
            width: g.bDiv.style.width,
            height: gh,
            position: 'relative',
            marginBottom: (gh * -1),
            zIndex: 1,
            top: gtop,
            left: '0px'
        });
        $(g.block).fadeTo(0, p.blockOpacity);
        // add column control
        if ($('th', g.hDiv).length) {
            g.nDiv.className = 'nDiv';
            g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
            $(g.nDiv).css({
                marginBottom: (gh * -1),
                display: 'none',
                top: gtop
            }).noSelect();
            var cn = 0;
            $('th div', g.hDiv).each(function() {
                var kcol = $(this).parent("th");
                var chkall = $("input[type='checkbox']", this);
                if (chkall.length > 0) {
                    chkall[0].onclick = g.checkAllOrNot;
                    return;
                }
                if (kcol.attr("isch")) {
                    return;
                }
                if (kcol[0].toggle == false || this.innerHTML == "") {
                    cn++;
                    return;
                }
                var chk = 'checked="checked"';
                if (kcol.css("display") == 'none') {
                    chk = '';
                }
                $('tbody', g.nDiv).append('<tr><td class="ndcol1"><input type="checkbox" ' + chk + ' class="togCol noborder" value="' + cn + '" /></td><td class="ndcol2">' + this.innerHTML + '</td></tr>');
                cn++;
            });
            if (browser.msie && browser.version < 7.0)
                $('tr', g.nDiv).hover(function() {
                    $(this).addClass('ndcolover');
                },
                        function() {
                            $(this).removeClass('ndcolover');
                        });
            $('td.ndcol2', g.nDiv).click(function() {
                if ($('input:checked', g.nDiv).length <= p.minColToggle && $(this).prev().find('input')[0].checked)
                    return false;
                return g.toggleCol($(this).prev().find('input').val());
            });
            $('input.togCol', g.nDiv).click(function() {
                if ($('input:checked', g.nDiv).length < p.minColToggle && this.checked == false)
                    return false;
                $(this).parent().next().trigger('click');
                //return false;
            });
            $(g.gDiv).prepend(g.nDiv);
            $(g.nBtn).addClass('nBtn')
                    .html('<div></div>')
                    //.attr('title', 'Hide/Show Columns')
                    .click(
                    function() {
                        $(g.nDiv).toggle();
                        return true;
                    });
            if (p.showToggleBtn)
                $(g.gDiv).prepend(g.nBtn);
        }
        // add date edit layer
        $(g.iDiv)
                .addClass('iDiv')
                .css({
            display: 'none'
        });
        $(g.bDiv).append(g.iDiv);
        // add flexigrid events
        $(g.bDiv)
                .hover(function() {
            $(g.nDiv).hide();
            $(g.nBtn).hide();
        }, function() {
            if (g.multisel)
                g.multisel = false;
        });
        $(g.gDiv)
                .hover(function() {
        }, function() {
            $(g.nDiv).hide();
            $(g.nBtn).hide();
        });
        //add document events
        $(document)
                .mousemove(function(e) {
            g.dragMove(e);
        })
                .mouseup(function(e) {
            g.dragEnd();
        })
                .hover(function() {
        }, function() {
            g.dragEnd();
        });
        //browser adjustments
        if (browser.msie && browser.version < 7.0) {
            $('.hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv', g.gDiv)
                    .css({
                width: '100%'
            });
            $(g.gDiv).addClass('ie6');
            if (p.width != 'auto')
                $(g.gDiv).addClass('ie6fullwidthbug');
        }
        g.rePosDrag();
        g.fixHeight();
        //make grid functions accessible
        t.p = p;
        t.grid = g;
        //当布局加载完成后
        if (p.onLayerInit)
            p.onLayerInit(g);
        // load data
        if (p.url && p.autoload) {
            g.populate();
        }
        return t;
    };
    var docloaded = false;
    $(function() {
        docloaded = true
    });
    $.fn.flexigrid = function(p) {
        return this.each(function() {
            if (!docloaded) {
                $(this).hide();
                var t = this;
                $(function() {
                    $.addFlex(t, p);
                });
            } else {
                $.addFlex(this, p);
            }
        });
    }; //end flexigrid
    $.fn.flexReload = function(p) { // function to reload grid
        return this.each(function() {
            $.extend(this.p, p);
            if (this.grid && this.p.url)
                this.grid.populate();
        });
    }; //end flexReload
    //重新指定宽度和高度
    $.fn.flexResize = function(w, h) {
        var p = {
            width: w,
            height: h
        };
        return this.each(function() {
            if (this.grid) {
                $.extend(this.p, p);
                this.grid.reSize();
            }
        });
    };
    $.fn.ChangePage = function(type) {
        return this.each(function() {
            if (this.grid) {
                this.grid.changePage(type);
            }
        })
    };
    $.fn.flexOptions = function(p) { //function to update general options
        return this.each(function() {
            if (this.grid)
                $.extend(this.p, p);
        });
    }; //end flexOptions
    $.fn.GetOptions = function() {
        if (this[0].grid) {
            return this[0].p;
        }
        return null;
    };
    // 获取选中的行，返回选中行的主键
    $.fn.getCheckedRows = function() {
        if (this[0].grid) {
            return this[0].grid.getCheckedRows();
        }
        return [];
    };
    // 获取选中的行，返回选中行的所有数据
    $.fn.getSelectedRows = function() {
        if (this[0].grid) {
            return this[0].grid.getSelectedRows();
        }
        return [];
    };
    $.fn.flexToggleCol = function(cid, visible) { // function to reload grid
        return this.each(function() {
            if (this.grid)
                this.grid.toggleCol(cid, visible);
        });
    }; //end flexToggleCol
    $.fn.flexAddData = function(data) { // function to add data to grid
        return this.each(function() {
            if (this.grid)
                this.grid.addData(data);
        });
    };
    $.fn.exportCSV = function() {
        if (this[0].grid) {
            var csvData = $(this[0].grid.hDiv).getTableData();
            var csvH = csvData.join("\n");
            var csvData = $(this[0].grid.bDiv).getTableData(".trSelected");
            var csvB = csvData.join("\n");
            var uri = 'data:application/csv;charset=UTF-8,' + encodeURIComponent(csvH + "\n" + csvB);
            window.open(uri);
        }
    }
    $.fn.getRandomString = function(len) {
        len = len || 32;
        var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1
        var maxPos = $chars.length;
        var pwd = '';
        for (i = 0; i < len; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    }
    //读取表格的数据
    $.fn.getTableData = function(classname) {
        classname = classname == undefined ? '' : classname;
        var data = [];
        var tmpArr = [];
        var tmpStr = '';
        $(this[0]).find("tr" + classname).each(function() {
            if ($(this).find("th").length) {
                $(this).find("th").each(function(i) {
                    if ($(this).text() == '' && i == 0)
                        return true;
                    tmpStr = $(this).text().replace(/"/g, '""');
                    tmpArr.push('"' + tmpStr + '"');
                });
                data.push(tmpArr);
            } else {
                tmpArr = [];
                $(this).find("td").each(function(i) {
                    var t = $(this).text();
                    if (t == '' && i == 0)
                        return true;
                    t = t == '&nbsp;' || t == 'null' ? '' : t;
                    if (t.match(/^-{0,1}\d*\.{0,1}\d+$/)) {
                        tmpArr.push(parseFloat($(this).text()));
                    } else {
                        tmpStr = t.replace(/"/g, '""');
                        tmpArr.push('"' + tmpStr + '"');
                    }
                });
                data.push(tmpArr.join(','));
            }
        });
        return data;
    }
    $.fn.noSelect = function(p) { //no select plugin by me :-)
        prevent = p == null ? true : p;
        if (prevent) {
            return this.each(function() {
                if (browser.msie || browser.safari)
                    $(this).bind('selectstart', function() {
                        return false;
                    });
                else if (browser.mozilla) {
                    $(this).css('MozUserSelect', 'none');
                    $('body').trigger('focus');
                }
                else if (browser.opera)
                    $(this).bind('mousedown', function() {
                        return false;
                    });
                else
                    $(this).attr('unselectable', 'on');
            });
        } else {
            return this.each(function() {
                if (browser.msie || browser.safari)
                    $(this).unbind('selectstart');
                else if (browser.mozilla)
                    $(this).css('MozUserSelect', 'inherit');
                else if (browser.opera)
                    $(this).unbind('mousedown');
                else
                    $(this).removeAttr('unselectable', 'on');
            });
        }
    }; //end noSelect
})(jQuery);