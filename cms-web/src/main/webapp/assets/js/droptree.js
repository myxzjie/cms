/** 
������ 
*/ 
(function($){
    var defaults = {
        idLabel: "code",
        textLabel: "name",
        pidLabel: "pid",
        rootPId: 0,
        transition: "ztree",
        expand: true,
        items: []
    };
/** 
  target:input element; 
    
*/ 
function DropTree(target,options){    
    this.target = target; 
    this.value = target.value; 
    this.$target = $(target);
    this.opts = $.extend({}, defaults, options, this.$target.data()); 
    this.id = this.target.id || this.target.name;     
    if(this.$target.length >0){ 
        this._init(); 
    } 
    return this; 
} 

DropTree.prototype._init = function(){ 
    var self = this;    
    this.$target.hide(); 
    this.$wrap = this.$target.wrap('<div class="mfs-container">').parent();     
    this.$selected = $('<a class="mfs-selected-option" href="#" />').prependTo(this.$wrap); 
    
    //this.$selected.css("height",15); 
    this.$selected.html(this.value+" "); 
    this.$down = $("<span> </span>").prependTo(this.$selected);    
    this.transition = Transitions[this.opts.transition].call(this); 
    
}; 


var Transitions = { 
  mfs:function(){     
    var mfsId = this.id+"_mfs"; 
    this.$options = $('<ul class="mfs-options" id="'+mfsId+'"/>').insertAfter(this.$selected); 
    var idLabel = this.opts.idLabel; 
    var textLabel = this.opts.textLabel; 
    var $options = this.$options;    
    //var (this.id); 
    $.each(this.opts.items,function(i,n){ 
        var $op = $('<a href="#" index="'+i+'">'+n[textLabel]+'</a>').wrap('<li class="mfs-option">').parent();        
        $options.prepend($op); 
    });    
         
    //
    var enableMagic = function (theContainer){ 

        var selectElm = theContainer.find('select'); 
        var selectElmOptions = selectElm.find('option'); 
        var optionList = theContainer.find('#'+mfsId); 
        var optionListLi = optionList.find('li.mfs-option'); 
        var selectedOption = theContainer.find('a.mfs-selected-option'); 
        var optionListOptions = optionList.find('a'); 
         
        optionList.hide(); 
        optionListOptions.click(function(){                    
          optionListLi.removeClass('active').removeClass('selected'); 
          $(this).closest('li').addClass('selected'); 
          selectedOption.html($(this).text()+'<span> </span>'); 
          selectElmOptions.removeAttr('selected'); 
          selectElmOptions.eq($(this).attr('index')).prop('selected', 'selected'); 
          optionList.hide(); 
            
          // Make a refresh function that just updates the select magic (destroy and re-enable) 
          if (selectElm.selectedIndex != $(this).attr('index') && selectElm.onchange) {    
            selectElm.selectedIndex = $(this).attr('index'); 
            selectElm.onchange();    
          } 
          if (selectElm.selectedIndex != $(this).attr('index')) { 
            selectElm.selectedIndex = $(this).attr('index'); 
            selectElm.trigger('change'); 
          } 
         
          return false; 
        }); 
     
        selectedOption.click(function(){ 
                     
          var optionListAll = $('#'+mfsId); 
          if (optionList.is(':visible')) { 
            optionList.hide(); 
            mfsSelectOpen = true; 
          } 
          else { 
            optionListLi.removeClass('active'); 
            optionListAll.hide(); 
            optionList.show(); 
            var optionListSelected = optionList.find('li.mfs-option.selected'); 
            if (optionListSelected.length > 0) { 
              optionListSelected.addClass('active'); 
            } 
            else { 
              optionList.find('li.mfs-option:first-child').addClass('active'); 
            } 
            mfsSelectOpen = optionList; 
          } 
          $(this).blur(); 
          return false; 
        }); 
     
        optionListLi.mouseover(function(){ 
          optionListLi.removeClass('active'); 
          $(this).addClass('active'); 
        }); 
    }; //end enableMagic     
     
    enableMagic(this.$wrap); 
  }, 
    
  ztree:function(){ 
    var treeId = this.id+"_tree"; 
        //<ul id="treeDemo" class="ztree"></ul> 
    this.$options = $('<ul id="'+treeId+'" class="mfs-options ztree">').insertAfter(this.$selected);        
    var theContainer = this.$wrap; 
    var optionList = theContainer.find('#'+treeId); 
    var selectedOption = theContainer.find('a.mfs-selected-option'); 
    var srcElem = this.target; 
    var idLabel = this.opts.idLabel; 
    var textLabel = this.opts.textLabel;
    
    var    zTreeOnClick= function(event, treeId, treeNode) {            
      selectedOption.html(treeNode[textLabel]+'<span> </span>');
      //srcElem.value=treeNode[idLabel]; 
      $(srcElem).val(treeNode[idLabel]);
      optionList.hide(); 
    }; 
     
    var setting = { 
      data: {
    	  key : {
				 name : this.opts.textLabel
			},
	      simpleData: { 
	          enable: true, 
	          idKey: this.opts.idLabel, 
	          pIdKey: this.opts.pidLabel,
	          rootPId : this.opts.rootPId
	        } 
      }, 
      callback: { 
        onClick: zTreeOnClick         
      }        
    }; 
    this.oper = $.fn.zTree.init($("#"+treeId), setting,this.opts.items);     
    
    this.oper.expandAll(this.opts.expand);
    // nodes
    var nodes = this.oper.getNodesByParam(idLabel, this.value, null); 
    if(nodes.length>0){ 
      var nodeName = (nodes[0])[this.opts.textLabel];    
      selectedOption.html(nodeName+'<span> </span>');//span Ϊ������ͷռλ�� 
      this.oper.selectNode(nodes[0],true); 
    } 
    var enableMagic = function (theContainer){         
        var selectedOption = theContainer.find('a.mfs-selected-option');         
        optionList.hide(); 
        selectedOption.click(function(){                                
          if (optionList.is(':visible')) { 
            optionList.hide();             
          } 
          else { 
            optionList.show(); 
          } 
          $(this).blur(); 
          return false; 
        });         
    }//end enableMagic 
    enableMagic(this.$wrap); 
  } 
    
}; 

$.fn.droptree = function(options){ 
  return    this.each(function(){ 
         if(!$.data(this,'droptree')){ 
            $.data(this,'droptree',new DropTree(this,options)); 
         }                
      }); 
}; 
    
})(jQuery)
 
