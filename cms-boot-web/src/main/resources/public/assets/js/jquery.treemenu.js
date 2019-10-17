/**
 * Created by xzjie on 2017/8/13.
 */
/**
 * Created xzjie asus on 2017/8/11.
 */

(function($) {

    var methods = {
        /**
         * Initialize tree
         *
         * @param {Object} options
         * @returns {Object[]}
         */
        init: function (options) {
            var settings = $.extend({}, this.treemenu.defaults, options);
            return this.each(function () {
                var $this = $(this);

                $this.treemenu('setTreeContainer', $(this));
                $this.treemenu('setSettings', settings);
                $this.treemenu('hashchange');
                $this.treemenu('load', settings);
            });
        },
        hashchange:function(){

            var $this = $(this);
            var container=$this.treemenu('getSetting', 'container');
            $(window).on("hashchange", function() {
                var url = window.location.hash;
                if (url && url.length > 1) {
                    url = url.substring(1);
                }else {
                    return;
                }
                $('.loader').show();
                $(container).load(url, function(response, status, xhr) {
                    $('.loader').hide();
//                $(".content").resize(function() {
//                });
                });
            });
        },
        /**
         * Method return setting by name
         *
         * @param {type} name
         * @returns {unresolved}
         */
        getSetting: function (name) {
            if (!$(this).treemenu('getTreeContainer')) {
                return null;
            }
            return $(this).treemenu('getTreeContainer').data('settings')[name];
        },
        /**
         * Add new settings
         *
         * @param {Object} settings
         */
        setSettings: function (settings) {
            $(this).treemenu('getTreeContainer').data('settings', settings);
        },
        /**
         * Return tree container
         *
         * @returns {HtmlElement}
         */
        getTreeContainer: function () {
            return $(this).data('treemenu');
        },
        /**
         * Set tree container
         *
         * @param {HtmlE;ement} container
         */
        setTreeContainer: function (container) {
            return $(this).data('treemenu', container);
        },
        load: function (settings) {
            var $this = $(this);
            $.ajax({
                type: settings.type,
                url: settings.url,
                data: settings.params,
                dataType: "JSON",
                async: true,
                success: function (res, textStatus, jqXHR) {
                    var data = res.data;
                    if (data && data.length > 0) {
                        $this.treemenu('renderBody', data);
                        $("#side-menu").metisMenu();
                        $(".J_menuItem").each(function (k) {
                            if (!$(this).attr("data-index")) {
                                $(this).attr("data-index", k)
                            }
                        });
                        $(".J_menuItem").on("click", c);
                        // $this.on('click', 'a', function () {
                        //     var $open = $(this).attr('_open');
                        //     window.location.hash = $open;
                        // });
                        // $this.find('li').first().addClass('active');
                        // $this.find('li').first().addClass('open');
                    }
                }
            });
        },
        renderBody: function (data) {
            var $this = $(this);

            var idColumn = $this.treemenu('getSetting', 'idColumn');
            var parentColumn = $this.treemenu('getSetting', 'parentColumn');
            var nameColumn = $this.treemenu('getSetting', 'nameColumn');
            var urlColumn = $this.treemenu('getSetting', 'urlColumn');
            var iconColumn = $this.treemenu('getSetting', 'iconColumn');
            var sortColumn = $this.treemenu('getSetting', 'sortColumn');
            var rootNodes = $this.treemenu('rootNodesDataSort', data, parentColumn, sortColumn);

            $.each(rootNodes, function (i, item) {
                var $li = $('<li><a href="#"><i class="fa fa-sticky-note-o"></i><span class="nav-label"></span><span class="fa arrow"></span></a></li>');

                //$li.find('a').attr('_open', item[urlColumn]);
                //$li.find('a .item-media i').addClass(item[iconColumn]);
               // $li.find('a .title').html(item[nameColumn]);
                $li.find('a > .nav-label').html(item[nameColumn]);

                $this.treemenu('childNodes', data, item, item[idColumn], $li,1);
                $this.append($li);
            });

        },
        /**
         * 递归获取子节点并且设置子节点
         * @param data
         * @param parentNode
         * @param parentIndex
         * @param tbody
         */
        childNodes: function (data, parentNode, parentIndex, li, level) {

            var $this = (this);
            var $ul = level>1?$('<ul class="nav nav-third-level"></ul>'):$('<ul class="nav nav-second-level"></ul>');
            var idColumn = $this.treemenu('getSetting', 'idColumn');
            var parentColumn = $this.treemenu('getSetting', 'parentColumn');
            var nameColumn = $this.treemenu('getSetting', 'nameColumn');
            var urlColumn = $this.treemenu('getSetting', 'urlColumn');
            var iconColumn = $this.treemenu('getSetting', 'iconColumn');
            var sortColumn = $this.treemenu('getSetting', 'sortColumn');
            var nodes = $this.treemenu('childNodesDataSort', data, parentColumn, parentIndex, sortColumn);

            if (nodes.length > 0) {
                $.each(nodes, function (i, item) {
                    if (item[parentColumn] == parentNode[idColumn]) {
                        var nowParentIndex = item[idColumn];
                        var $li = $('<li><a><span class="nav-label"></span><span class="fa arrow"></span></a></li>');
                        //$li.find('a').attr('_open', item[urlColumn]);
                        $li.find('a .nav-label').html(item[nameColumn]);

                        $ul.append($li);
                        $this.treemenu('childNodes', data, item, nowParentIndex, $li,level+1)
                    }
                });
                li.append($ul);
            }else {
                li.find('a').addClass('J_menuItem');
                li.find('a').attr('href',parentNode[urlColumn]);
                li.find('a > span.arrow').remove();
            }
        },
        childNodesDataSort: function (data, parentCol, parentIndex, sortCol) {
            var result = [];
            $.each(data, function (index, item) {
                if (item[parentCol] == parentIndex) {
                    result.push(item);
                }
            });

            if (sortCol) {
                result.sort(function (a, b) {
                    return a[sortCol] - b[sortCol];
                });
            }
            return result;
        },
        rootNodesDataSort: function (data, parentCol, sortCol) {
            var result = [];
            $.each(data, function (index, item) {
                if (!item[parentCol] || item[parentCol] == 0) {
                    result.push(item);
                }
            });
            if (sortCol) {
                result.sort(function (a, b) {
                    return a[sortCol] - b[sortCol];
                });
            }
            return result;
        },
    };

    function c() {
        var o = $(this).attr("href"), m = $(this).data("index"), l = $.trim($(this).text()), k = true;
        if (o == undefined || $.trim(o).length == 0) {
            return false
        }
        $(".J_menuTab").each(function () {
            if ($(this).data("id") == o) {
                if (!$(this).hasClass("active")) {
                    $(this).addClass("active").siblings(".J_menuTab").removeClass("active");
                    g(this);
                    $(".J_mainContent .J_iframe").each(function () {
                        if ($(this).data("id") == o) {
                            $(this).show().siblings(".J_iframe").hide();
                            return false
                        }
                    })
                }
                k = false;
                return false
            }
        });
        if (k) {
            var p = '<a href="javascript:;" class="active J_menuTab" data-id="' + o + '">' + l + ' <i class="fa fa-times-circle"></i></a>';
            $(".J_menuTab").removeClass("active");
            var n = '<iframe class="J_iframe" name="iframe' + m + '" width="100%" height="100%" src="' + o + '" frameborder="0" data-id="' + o + '" seamless></iframe>';
            $(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(n);
            $(".J_menuTabs .page-tabs-content").append(p);
            g($(".J_menuTab.active"))
        }
        return false
    }

    function f(l) {
        var k = 0;
        $(l).each(function () {
            k += $(this).outerWidth(true)
        });
        return k
    }

    function g(n) {
        var o = f($(n).prevAll()), q = f($(n).nextAll());
        var l = f($(".content-tabs").children().not(".J_menuTabs"));
        var k = $(".content-tabs").outerWidth(true) - l;
        var p = 0;
        if ($(".page-tabs-content").outerWidth() < k) {
            p = 0
        } else {
            if (q <= (k - $(n).outerWidth(true) - $(n).next().outerWidth(true))) {
                if ((k - $(n).next().outerWidth(true)) > q) {
                    p = o;
                    var m = n;
                    while ((p - $(m).outerWidth()) > ($(".page-tabs-content").outerWidth() - k)) {
                        p -= $(m).prev().outerWidth();
                        m = $(m).prev()
                    }
                }
            } else {
                if (o > (k - $(n).outerWidth(true) - $(n).prev().outerWidth(true))) {
                    p = o - $(n).prev().outerWidth(true)
                }
            }
        }
        $(".page-tabs-content").animate({marginLeft: 0 - p + "px"}, "fast")
    }

    $.fn.treemenu = function(method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method with name ' + method + ' does not exists for jQuery.treemenu');
        }
    };
    /**
     *  Plugin's default options
     */
    $.fn.treemenu.defaults = {
        container:'#container',
        idColumn: 'resourceId',
        nameColumn:'resourceName',
        urlColumn:'resourceUrl',
        iconColumn:'resourceIcon',
        parentColumn: 'perentResourceId',
        sortColumn: null,
        server:false,
        type: "GET",                                                    //请求数据的ajax类型
        url: null,                                                      //请求数据的ajax的url
        params: {}


    };
})(jQuery);
