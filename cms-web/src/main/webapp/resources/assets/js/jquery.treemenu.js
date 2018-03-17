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

                $(container).load(url, function(response, status, xhr) {
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
                        $this.on('click', 'a', function () {
                            var $open = $(this).attr('_open');
                            window.location.hash = $open;
                        });
                        $this.find('li').first().addClass('active');
                        $this.find('li').first().addClass('open');
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
                //var $li = $('<li><a><div class="item-content"><div class="item-media"><i class=""></i></div><div class="item-inner"><span class="title"></span><i class="icon-arrow"></i></div></div></a></li>');
                var $li = $('<dl id="menu-"'+item[idColumn]+'><dt><i class="Hui-iconfont">'+item[iconColumn]+'</i> '+item[nameColumn]+'<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt></dl>');

                $this.treemenu('childNodes', data, item, item[idColumn], $li);
                $this.append($li);
            });

            $(".Hui-aside").Huifold({
                titCell:'.menu_dropdown dl dt',
                mainCell:'.menu_dropdown dl dd',
            });

        },
        /**
         * 递归获取子节点并且设置子节点
         * @param data
         * @param parentNode
         * @param parentIndex
         * @param tbody
         */
        childNodes: function (data, parentNode, parentIndex, li) {
            var $this = (this);
            var $ul = $('<dd><ul class="sub-menu"></ul></dd>');
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
                        var $li = $('<li><a href="javascript:void(0)">'+item[nameColumn]+'</a></li>');
                        $li.find('a').attr('data-href', item[urlColumn]);
                        $li.find('a').attr('data-title',item[nameColumn]);

                        $ul.find('ul').append($li);
                        $this.treemenu('childNodes', data, item, nowParentIndex, $li)
                    }
                });
                li.append($ul);
            }else {
                li.find('.icon-arrow').remove();
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
