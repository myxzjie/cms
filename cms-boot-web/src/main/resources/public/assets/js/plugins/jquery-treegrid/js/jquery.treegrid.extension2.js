(function ($) {
    "use strict";

    $.fn.treegridData = function (options, param) {
        //如果是调用方法
        if (typeof options == 'string') {
            return $.fn.treegridData.methods[options](this, param);
        }

        //如果是初始化组件
        options = $.extend({}, $.fn.treegridData.defaults, options || {});
        var target = $(this);
        //得到根节点
        target.getRootNodes = function (data) {
            var result = [];
            $.each(data, function (index, item) {
                if (!item[options.parentColumn]) {
                    result.push(item);
                }
            });
            return result;
        };
        var j = 0;
        //递归获取子节点并且设置子节点
        target.getChildNodes = function (data, parentNode, parentIndex, tbody) {
            $.each(data, function (i, item) {
                if (item[options.parentColumn] == parentNode[options.id]) {
                    var tr = $('<tr></tr>');
                    var nowParentIndex = (parentIndex + (j++) + 1);
                    tr.addClass('treegrid-' + nowParentIndex);
                    tr.addClass('treegrid-parent-' + parentIndex);
                    $.each(options.columns, function (index, column) {

                        var td = $('<td></td>');
                        td.text(item[column.field]);
                        tr.append(td);
                    });
                    tbody.append(tr);
                    target.getChildNodes(data, item, nowParentIndex, tbody)

                }
            });
        };
        target.addClass('table');
        if (options.striped) {
            target.addClass('table-striped');
        }
        if (options.bordered) {
            target.addClass('table-bordered');
        }
        if (options.url) {
            $.ajax({
                type: options.type,
                url: options.url,
                data: options.ajaxParams,
                dataType: "JSON",
                success: function (res, textStatus, jqXHR) {
                    var data=res.data;
                    //构造表头
                    var thr = $('<tr></tr>');
                    $.each(options.columns, function (i, item) {
                        var th = $('<th style="padding:10px;"></th>');
                        th.text(item.title);
                        thr.append(th);
                    });
                    var thead = $('<thead></thead>');
                    thead.append(thr);
                    target.append(thead);

                    //构造表体
                    var tbody = $('<tbody></tbody>');
                    var rootNode = target.getRootNodes(data);
                    $.each(rootNode, function (i, item) {
                        var tr = $('<tr></tr>');
                        tr.addClass('treegrid-' + (j + i));
                        $.each(options.columns, function (index, column) {
                            var td = $('<td></td>');
                            td.text(item[column.field]);
                            tr.append(td);
                        });
                        tbody.append(tr);
                        target.getChildNodes(data, item, (j + i), tbody);
                    });
                    target.append(tbody);
                    target.treegrid({
                        expanderExpandedClass: options.expanderExpandedClass,
                        expanderCollapsedClass: options.expanderCollapsedClass
                    });
                    if (!options.expandAll) {
                        target.treegrid('collapseAll');
                    }
                }
            });
        }
        else {
            //也可以通过defaults里面的data属性通过传递一个数据集合进来对组件进行初始化....有兴趣可以自己实现，思路和上述类似
        }
        return target;
    };

    $.fn.treegridData.methods = {
        getAllNodes: function (target, data) {
            return target.treegrid('getAllNodes');
        },
        //组件的其他方法也可以进行类似封装........
    };

    $.fn.treegridData.defaults = {
        id: 'Id',
        parentColumn: 'ParentId',
        data: [],    //构造table的数据集合
        type: "GET", //请求数据的ajax类型
        url: null,   //请求数据的ajax的url
        ajaxParams: {}, //请求数据的ajax的data属性
        expandColumn: null,//在哪一列上面显示展开按钮
        expandAll: false,  //是否全部展开
        striped: false,   //是否各行渐变色
        bordered: false,  //是否显示边框
        columns: [],
        expanderExpandedClass: 'glyphicon glyphicon-chevron-down',//展开的按钮的图标
        expanderCollapsedClass: 'glyphicon glyphicon-chevron-right'//缩起的按钮的图标

    };
})(jQuery);

; (function ($) {
    "use strict";   //严格模式下, 全局对象无法使用默认绑定
    /*begin private functions*/
    var nodeCount = 0;  //全局结点计数
    //从数据中获取跟结点
    var getRootNodesFromData = function (data,isTreedData,parentCol,sortCol) {
        var result = [];
        $.each(data, function (index, item) {
            if (isTreedData || !item[parentCol] || item[parentCol] == 0) {
                result.push(item);
            }
        });
        if (sortCol) {
            result.sort(function (a, b) {
                return a[sortCol] - b[sortCol];
            });
        }
        return result;

    }
    //从数据中获取子节点
    var getListChildNodesFromData = function (data, parentNode,idCol,parentCol,sortCol) {
        var unsort = [];
        $.each(data, function (i, item) {
            if (item[parentCol] == parentNode[idCol]) {
                unsort.push(item);
            }
        });
        if (unsort == null || unsort.length < 1)
            return;

        if (sortCol) {
            unsort.sort(function (a, b) {
                return a[sortCol] - b[sortCol];
            });
        }
        return unsort;
    }
    var getTreeChildNodesFromdata = function (parentNode,childCol,sortCol) {
        if (childCol) {
            var childNodes = parentNode[childCol];
            if (sortCol)
                childNodes.sort(function (a, b) {
                    return a[sortCol] - b[sortCol];
                });
            return childNodes;
        }
        return null;
    }
    /*end private functions*/


    /*Main*/
    $.fn.treegridExt = function (method) {
        if (methods[method]) {
            //如果是调用方法(参数对象中含有方法名)
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            //调用初始化函数(参数不含方法名)
            return methods.initExtTree.apply(this, arguments);
        } else {
            $.error('Method with name ' + method + ' does not exists for jQuery.treegridExt');
            return this;
        }
    };

    /*public functions*/
    var methods = {
        initExtTree: function (options) {
            //获取配置信息
            var settings = $.extend({}, this.treegrid.defaults, this.treegridExt.defaults, options || {});
            var $this = $(this);
            //Code
            //设置控件容器
            $this.treegridExt('setTreeContainer', $this);
            //保存设置
            $this.treegridExt('setSettings', settings);
            if (!settings.url) {
                //载入数据
                var data = $this.treegridExt('loadData',settings);
                if (data && data.length > 0) {
                    //生成表格
                    $this.treegridExt('renderTable',data);
                }
                //初始化header
                settings.getTableHeader.apply(this,[$this]).treegridExt('initHeader',settings);
                //初始化结点
                settings.getRootNodes.apply(this, [$this]).treegridExt('initNode', settings);
                //生成树
                $this.treegridExt('getRootNodes').treegridExt('render');
                //默认展开状态
                if (!settings.expandAll) {
                    $this.treegridExt('collapseAll');
                }
            } else {
                $this.treegridExt('loadData',settings);
            }
            return $this;
        },
        dispose: function (options) {
            return $(this).each(function () {
                var $this = $(this);
                $this.treegridExt('delState');
                $this.treegridExt('delSettings');
                $this.treegridExt('delTreeContainer');
            });
        },
        val: function (options) {
            //获取选择器中第一个元素的html
            return this.eq(0).html();
        },


        //##Beigin自定义初始化方法
        /*init*/
        initHeader: function (settings) {
            return this.each(function () {
                var $this = $(this);
                $this.treegridExt('setTreeContainer', settings.getTreeGridContainer.apply(this));
                $this.treegridExt('initCheckAll').treegridExt('initDelAll');
                if ($this.treegridExt('getSetting', 'showOperation')) {
                    var cell = $('<th></th>');
                    cell.text('操作');
                    $this.append(cell);
                }
                return $this;
            });
        },
        initNode: function (settings) {
            return this.each(function () {
                var $this = $(this);
                $this.treegridExt('setTreeContainer', settings.getTreeGridContainer.apply(this));
                $this.treegridExt('initExpander').treegridExt('initIndent').treegridExt('initCheck').treegridExt('initOperation').treegridExt('initEvents').treegridExt('initState').treegridExt('initChangeEvent').treegridExt('initSettingsEvents');
                $this.treegridExt('getChildNodes').treegridExt('initNode', settings);
            });
        },
        initCheckAll: function () {
            var $this = $(this);
            if ($this.treegridExt('getSetting', 'showCheck') && $this.treegridExt('getSetting', 'showCheckAll')) {
                $this.addClass('treegridExt-checkboxAll');
                var cell = $('<th></th>');
                var tpl = $this.treegridExt('getSetting', 'checkTemplate');
                var checks = $this.treegridExt('getSetting', 'getCheckbox').apply(this);
                if (checks && checks.length > 0) {
                    checks.remove();
                }
                $this.prepend(cell.append(tpl));
                $this.treegridExt('getSetting', 'getCheckbox').apply(this).click(function () { $this.treegridExt('toggleCheckAll'); });
            }
            return $this;
        },
        initDelAll: function () {
            var $this = $(this);
            if ($this.treegridExt('getSetting', 'showCheckAll') && $this.treegridExt('getSetting', 'showDelAll')) {
                var cell = $this.find('th').get(0);
                var delTpl = $this.treegridExt('getSetting', 'delAllTemplate');
                var checks = $this.treegridExt('getSetting', 'getDelAll').apply(this);
                if (checks && checks.length > 0) {
                    checks.remove();
                }
                $(cell).append(delTpl);
                $this.treegridExt('getSetting', 'getDelAll').apply(this).click(function () { $this.treegridExt('delChecked'); });
            }
            return $this;
        },
        initCheck: function () {
            var $this = $(this);
            if ($this.treegridExt('getSetting', 'showCheck')) {
                var cell = $('<td></td>');
                var tpl = $this.treegridExt('getSetting', 'checkTemplate');
                var checks = $this.treegridExt('getSetting', 'getCheckbox');
                if (checks && checks.length > 0) {
                    checks.remove();
                }
                $this.prepend(cell.append(tpl));
                $this.treegridExt('getSetting', 'getCheckbox').apply(this).click(function () { $this.treegridExt('toggleCheck'); });
            }
            return $this;
        },
        initOperation: function () {
            var $this = $(this);
            if ($this.treegridExt('getSetting', 'showOperation')) {
                var cell = $('<td></td>');
                var tpl = $this.treegridExt('getSetting', 'operationTemplate');
                $this.append(cell.append(tpl));
            }
            return $this;
        },
        initEvents: function () {
            var $this = $(this);
            //Default behavior on check
            $this.on('check', function () {
                var $this = $(this);
                $this.addClass('treegrid-checked');
            });
            //Default behavior on other operations
            return $this.treegrid('initEvents');
        },
        initSettingsEvents: function () {
            var $this = $(this);
            //Save state on check
            $this.on('check', function () {
                var $this = $(this);
                if (typeof ($this.treegridExt('getSetting', 'onCheck')) === "function") {
                    $this.treegridExt('getSetting', 'onCheck').apply($this);
                }
            });
            //Save state on other operation
            return $this.treegrid('initSettingsEvents');
        },
        //##end自定义的初始化方法


        //##Begin原组件的方法的封装
        /*init*/
        initExpander: function () {
            return $(this).treegrid('initExpander');
        },
        initIndent: function () {
            return $(this).treegrid('initIndent');
        },
        initState: function () {
            return $(this).treegrid('initState');
        },
        initChangeEvent: function () {
            var $this = $(this);
            //Save state on chagne
            $this.on('change', function () {
                var $this = $(this);
                $this.treegridExt('render');
                if ($this.treegridExt('getSetting', 'saveState')) {
                    $this.treegridExt('saveState');
                }
            });
            return $this;
        },
        /*init-event*/
        /*base*/
        getTreeContainer: function () {
            return $(this).treegrid('getTreeContainer');
        },
        setTreeContainer: function (container) {
            //扩展插件和原插件使用相同的存储空间
            return $(this).treegrid('setTreeContainer', container);
        },
        getSetting: function (name) {
            return $(this).treegrid('getSetting',name);
        },
        setSettings: function (settings) {
            return $(this).treegrid('setSettings',settings);
        },
        restoreState: function () {
            return $(this).treegrid('restoreState');
        },
        saveState: function () {
            return $(this).treegrid('saveState');
        },
        /*node*/
        getAllNodes: function () {
            return $(this).treegrid('getAllNodes');
        },
        getParentNode: function () {
            return $(this).treegrid('getParentNode');
        },
        getChildNodes: function () {
            return $(this).treegrid('getChildNodes');
        },
        /*fake-id*/
        getNodeId: function () {
            return $(this).treegrid('getNodeId');
        },
        getParentNodeId: function () {
            return $(this).treegrid('getParentNodeId');
        },
        getDepth: function () {
            return $(this).treegrid('getDepth');
        },
        /*bool*/
        isRoot: function () {
            return $(this).treegrid('isRoot');
        },
        isNode: function () {
            return $(this).treegrid('isNode');
        },
        isLeaf: function () {
            return $(this).treegrid('isLeaf');
        },
        isFirst: function () {
            return $(this).treegrid('isFirst');
        },
        isLast: function () {
            return $(this).treegrid('isLast');
        },
        isExpanded: function () {
            return $(this).treegrid('isExpanded');
        },
        isCollapsed: function () {
            return $(this).treegrid('isCollapsed');
        },
        isFirstInit: function () {
            return $(this).treegrid('isFirstInit');
        },
        isOneOfParentsCollapsed: function () {
            return $(this).treegrid('isOneOfParentsCollapsed');
        },
        /*verb*/
        expand: function () {
            return $(this).treegrid('expand');
        },
        expandAll: function () {
            return $(this).treegrid('expandAll');
        },
        expandRecursive: function () {
            return $(this).treegrid('expandRecursive');
        },
        collapse: function () {
            return $(this).treegrid('collapse');
        },
        collapseAll: function () {
            return $(this).treegrid('collapseAll');
        },
        collapseRecursive: function () {
            return $(this).treegrid('collapseRecursive');
        },
        /*
         toggle: function () {
         return $(this).treegrid('toggle').apply(this);
         },*/
        //##End原组件方法的封装


        //自定义的方法........
        delState: function () {
            $.cookie($this.treegridExt('getSetting', 'saveStateName'), null);
        },
        delSettings: function () {
            if (!$(this).treegridExt('getTreeContainer'))
                return;
            $this.treegridExt('getTreeContainer').removeData('settings');
        },
        delTreeContainer: function () {
            if (!$(this).treegridExt('getTreeContainer'))
                return;
            return $(this).removeData('treegrid', container);
        },
        check: function (isCheck) {
            var $this = $(this);
            if (isCheck) {
                $this.treegridExt('getSetting', 'getCheckbox').apply(this).prop('checked',true);
                $this.addClass('treegridExt-checked');
                if ($this.treegrid('isCollapsed')) {
                    $this.treegrid('expand');
                }
            } else {
                $this.treegridExt('getSetting', 'getCheckbox').apply(this).prop('checked', false);
                $this.removeClass('treegridExt-checked');
            }
            return $this;
        },
        checkDownRecursive: function (isCheck) {
            //return $(this).each(function () {
            var $this = $(this);
            $this.treegridExt('check', isCheck);
            if (!$this.treegridExt('isLeaf')) {
                $this.treegridExt('getChildNodes').treegridExt('checkDownRecursive', isCheck);
            }
            return $this;
            //});
        },
        checkUpRecursive: function (isCheck) {
            var parentNode = $(this).treegridExt('getParentNode');
            if (parentNode) {
                var tag = true;
                $.each(parentNode.treegridExt('getChildNodes'), function () {
                    var $this = $(this);
                    //异或: T^T(false)、T^F(true)、F^T(true)、F^F(false), 全选或全不选(true), 否则为false.
                    if (isCheck ^ $this.treegridExt('isChecked')) {
                        tag = false;
                        return false;
                    }
                });
                //与非: !(T^T)(false)、!(T^F)(true)、!(F^T)(true)、F^F(true)
                if (!(isCheck || tag)) {
                    //选的不全
                    parentNode.treegridExt('check', true);
                    parentNode.addClass('treegridExt-partialChecked');
                } else if (isCheck) {
                    //全选
                    parentNode.treegridExt('check', true);
                    parentNode.removeClass('treegridExt-partialChecked');
                } else {
                    //全不选
                    parentNode.treegridExt('check', false);
                    parentNode.removeClass('treegridExt-partialChecked');
                }
                if (!parentNode.treegridExt('getParentNode')) {
                    parentNode.treegridExt('checkUpRecursive', isCheck);
                }
            } else {
                //rootNodes
                var $this = $(this);
                var checkCount = 0;
                var rootNodes = $this.treegridExt('getRootNodes');
                $.each(rootNodes, function (index, item) {
                    var $item = $(item);
                    if($item.treegridExt('isChecked')){
                        checkCount++;
                    }
                    if ($item.treegridExt('isPartialChecked')) {
                        checkCount--;
                    }
                });
                var chkTr = $this.treegridExt('getSetting','getTableHeader').apply(this,[$($this.treegridExt('getTreeContainer'))]);
                if (checkCount < rootNodes.length) {
                    //全不选,部分选
                    if (isCheck || checkCount > 0) {
                        //chkTr.find('input[type="checkbox"]').prop('checked',true);
                        chkTr.treegridExt('getSetting', 'getCheckbox').apply($(chkTr)).prop('checked', true);
                        chkTr.removeClass('treegridExt-checkedAll').addClass('treegridExt-partialChecked');
                    } else {
                        chkTr.treegridExt('getSetting', 'getCheckbox').apply($(chkTr)).prop('checked', false);
                        chkTr.removeClass('treegridExt-checkedAll').removeClass('treegridExt-partialChecked');
                    }
                } else {
                    //全选
                    chkTr.treegridExt('getSetting', 'getCheckbox').apply($(chkTr)).prop('checked', true);
                    chkTr.removeClass('treegridExt-partialChecked').addClass('treegridExt-checkedAll');
                }

            }
        },
        toggleCheck: function () {
            var $this = $(this);
            var isCheck = $this.treegridExt('isChecked');
            $this.treegridExt('check', !isCheck);
            $this.treegridExt('checkDownRecursive', !isCheck);
            $this.treegridExt('checkUpRecursive', !isCheck);
            return $this;
        },
        toggleCheckAll: function () {
            var $this = $(this);
            var chkAll = $this.treegridExt('getSetting', 'getCheckbox').apply(this);
            var isCheck = $this.treegridExt('isCheckedAll');
            if (isCheck) {
                chkAll.prop('checked', false);
                $this.removeClass('treegridExt-checkedAll');
            } else {
                chkAll.prop('checked', true);
                $this.addClass('treegridExt-checkedAll');
            }
            $.each($this.treegridExt('getRootNodes'), function (index, item) {
                $(item).treegridExt('checkDownRecursive', !isCheck);
            });
            return $this;
        },
        delChecked: function () {
            alert('这是删除按钮啦!');
        },
        getRootNodes: function () {
            return $(this).treegrid('getRootNodes');
        },
        /*bool*/
        isChecked: function () {
            return $(this).hasClass('treegridExt-checked');
        },
        isPartialChecked: function(){
            return $(this).hasClass('treegridExt-partialChecked');
        },
        isCheckedAll: function () {
            return $(this).hasClass('treegridExt-checkedAll');
        },
        isDelAll: function () {
            return $(this).hasClass('treegridExt-delAll');
        },
        /*loadData*/
        loadData: function (settings) {
            var $this = $(this);
            var defData = settings.data;
            var url = settings.url;
            if (defData && defData.length >0) {
                return defData;
            } else if (url) {
                $.ajax({
                    type: settings.type,
                    url: url,
                    data: settings.data,
                    dataType: "JSON",
                    success: function (res, textStatus, jqXHR) {
                       var data=res.data;
                        if (data && data.length > 0) {
                            //生成表格
                            $this.treegridExt('renderTable',data);
                        }
                        //初始化Header
                        settings.getTableHeader.apply(this, [$this]).treegridExt('initHeader', settings);
                        //初始化结点
                        settings.getRootNodes.apply(this, [$this]).treegridExt('initNode', settings);
                        //生成树
                        $this.treegridExt('getRootNodes').treegridExt('render');
                        //默认展开状态
                        if (!settings.expandAll) {
                            $this.treegridExt('collapseAll');
                        }
                    }
                });
                return $this;
            }
        },
        /*render*/
        render: function () {
            return $(this).each(function () {
                var $this = $(this);
                //if parent colapsed we hidden
                if ($this.treegrid('isOneOfParentsCollapsed')) {
                    $this.hide();
                } else {
                    $this.show();
                }
                if (!$this.treegrid('isLeaf')) {
                    $this.treegrid('renderExpander');
                    $this.treegrid('getChildNodes').treegrid('render');
                }
            });
        },
        renderExpander: function () {
            return $(this).each(function () {
                var $this = $(this);
                var expander = $this.treegrid('getSetting', 'getExpander').apply(this);
                if (expander) {

                    if (!$this.treegrid('isCollapsed')) {
                        expander.removeClass($this.treegrid('getSetting', 'expanderCollapsedClass'));
                        expander.addClass($this.treegrid('getSetting', 'expanderExpandedClass'));
                    } else {
                        expander.removeClass($this.treegrid('getSetting', 'expanderExpandedClass'));
                        expander.addClass($this.treegrid('getSetting', 'expanderCollapsedClass'));
                    }
                } else {
                    $this.treegrid('initExpander');
                    $this.treegrid('renderExpander');
                }
            });
        },
        renderTable: function (data) {
            var $this = $(this);
            //debugger;
            //设置表样式
            $this.addClass('table');
            if ($this.treegridExt('getSetting', 'striped')) {
                $this.addClass('table-striped');
            }
            if ($this.treegridExt('getSetting', 'bordered')) {
                $this.addClass('table-bordered');
            }
            //生成表头
            $this.treegridExt('renderHead');
            //生成表体
            $this.treegridExt('renderBody', data);
            return $this;
        },
        renderHead: function () {
            var $this = $(this);
            //debugger;
            var thead = $('<thead></thead>')
            var thr = $('<tr></tr>');
            thr.addClass('treegridExt-header');
            $.each($this.treegridExt('getSetting', 'columns'), function (i, item) {
                var th = $('<th></th>');
                th.text(item.title);
                thr.append(th);
            });
            thr.appendTo(thead);
            return $this.append(thead);
        },
        renderBody: function (data) {
            var $this = $(this);
            var tbody = $('<tbody></tbody>');
            var isTreedData = $this.treegridExt('getSetting', 'treedData');
            var parentCol = $this.treegridExt('getSetting', 'parentColumn');
            var sortCol = $this.treegridExt('getSetting', 'sortColumn');
            var cols = $this.treegridExt('getSetting', 'columns');
            var rootNodes = getRootNodesFromData(data, isTreedData, parentCol, sortCol);
            if (rootNodes && rootNodes.length > 0) {
                $.each(rootNodes, function (i, item) {
                    var tr = $('<tr></tr>');
                    tr.addClass('treegrid-' + (++nodeCount));
                    $.each(cols, function (index, column) {
                        var td = $('<td></td>');
                        td.text(item[column.field]);
                        tr.append(td);
                    });
                    tbody.append(tr);
                    if ($this.treegridExt('getSetting', 'treedData')) {
                        var childCol = $this.treegridExt('getSetting', 'childrenColumn');
                        $this.treegridExt('renderTreedDataTr', item, nodeCount, cols, childCol, sortCol, tbody);
                    } else {
                        var idCol = $this.treegridExt('getSetting', 'idColumn');
                        $this.treegridExt('renderListDataTr', data, item, nodeCount, cols, idCol, parentCol, sortCol, tbody);
                    }
                });
            }
            debugger
            return $this.append(tbody);
        },
        renderListDataTr: function (data, parentData, parentIndex, columns, idColumn, parentColumn, sortColumn, tbody) {
            var nodes = getListChildNodesFromData(data, parentData, idColumn, parentColumn, sortColumn);
            if (nodes && nodes.length > 0) {
                $.each(nodes, function (i, item) {
                    var tr = $('<tr></tr>');
                    var nowParentIndex = ++nodeCount;
                    tr.addClass('treegrid-' + nowParentIndex);
                    tr.addClass('treegrid-parent-' + parentIndex);
                    $.each(columns, function (index, column) {
                        var td = $('<td></td>');
                        td.text(item[column.field]);
                        tr.append(td);
                    });
                    tbody.append(tr);
                    $(this).treegridExt('renderListDataTr', data, item, nowParentIndex, columns, idColumn, parentColumn, sortColumn, tbody);
                });
            }
        },
        renderTreedDataTr: function (parentNode, parentIndex, columns, childColumn, sortColumn, tbody) {
            var nodes = getTreeChildNodesFromdata(parentNode, childColumn, sortColumn);
            if (nodes && nodes.length > 0) {
                $each(nodes, function (i, item) {
                    var tr = $('<tr></tr>');
                    var nowParentIndex = ++nodeCount;
                    tr.addClass('treegrid-' + nowParentIndex);
                    tr.addClass('treegrid-parent-' + parentIndex);
                    $each(columns, function (index, column) {
                        var td = $('<td></td>');
                        td.text(item[column.field]);
                        tr.append(td);
                    });
                    tbody.append(tr);
                    $(this).treegridExt('renderTreedDataTr', item, nowParentIndex, columns, childColumn, sortColumn, tbody);
                });
            }
        },
    };


    /*default values*/
    $.fn.treegridExt.defaults = {
        idColumn: 'Id',
        parentColumn: 'ParentId',
        treeColumn: 0,                                                  //在哪一列上显示展开按钮
        treedData: false,                                               //是否树化的数据
        childrenColumn: null,                                           //含有孩子结点的属性, 非树化结构直接值为null
        data: [],                                                       //构造table的数据集合
        type: "GET",                                                    //请求数据的ajax类型
        url: null,                                                      //请求数据的ajax的url
        ajaxParams: {},                                                 //请求数据的ajax的data属性
        sortColumn: null,                                               //按照哪一列进行排序
        striped: false,                                                 //是否各行渐变色
        bordered: false,                                                //是否显示边框
        expandAll: true,                                                //是否全部展开
        showCheck: true,                                                //是否显示选择列
        showCheckAll: true,                                             //是否选择全选列
        showDelAll: true,                                               //显示全删按钮
        showOperation: true,                                            //是否显示操作列
        columns: [],                                                    //列名列值
        checkTemplate: '<input type="checkbox" >',
        delAllTemplate: '<input reset radius2" type="reset" value="删除" onsubmit="return false"><input type="hidden" >',
        operationTemplate: '<a href class="btn btn4 btn_pencil" data-toggle="tooltip" style="margin-left:5%" data-trigger="hover" title="编辑" ></a><a href class="btn btn4 btn_trash" style="margin-left:5%;" data-toggle="tooltip" data-trigger="hover" title="删除"></a>',
        expanderExpandedClass: 'glyphicon glyphicon-chevron-down',      //展开的按钮的图标
        expanderCollapsedClass: 'glyphicon glyphicon-chevron-right',     //缩起的按钮的图标
        getCheckbox: function () {
            return $(this).find('input[type="checkbox"]');
        },
        getDelAll: function () {
            return $(this).find('input[type="reset"]');
        },
        getTableHeader: function (treegridContainer) {
            var result = $.grep(treegridContainer.find('tr'), function (element) {
                var classNames = $(element).attr('class');
                var templateClass = /treegridExt-header/;
                return templateClass.test(classNames);
            });
            return $(result);
        },
        /*Event*/
        onCollpase: null,
        onExpand: null,
        onChange: null,
        onCheck: null

    };
})(jQuery);