/**
 * 
 */

var DevelopMain = function() {
	"use strict";

	var autosizeHandler = function() {
		$('.autosize.area-animated').append("\n");
		autosize($('.autosize'));
		 //$('.autosize.area-animated').trigger('autosize.resize');
		//window.jQuery.fn.autosize=function(){ return autosize(this); };
	};
	
	var interdataHandler=function(){
		interdata('',function(res){
			var data=res.data;
			var html='';
			$('#interdata').html('');
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				html+='<li>\
				<a href="javascript:void(0);" >'+obj.name+'</a>\
				<input type="hidden" value="'+obj.id+'" />\
			</li>';
			}
			$('#interdata').html(html);
		});
	};

	var interdata = function(id,callback) {
		var data={};
		if(id){
			data.id=id;
		}
		$.ajax({
			type: "POST",
			data: data,
			dataType: 'json',
			url: '/develop/interface/data',
			success: callback,
			error:function(e){
				alert(e);
			}
		});
	};
	
	var interdataClick=function(){
		$('#interdata').delegate("a", "click", function(e){
			var id=$(this).next().val();
			interdata(id,function(res){
				var data=res.data[0];
				
				$('#requset_text').val(data.requset).trigger('resize');
				cursorPosition.get($('#requset_text'));
			});
			
			
		});
		
	};
	
	var testSubmitClick=function(){
		
		$('#btn_submit_test').click(function(){
			var curl=Global.basePath+$('#test_url').val();
			var data={};
			data.xml=$('#requset_text').val();
			$.ajax({
				type: "POST",
				data: data,
				dataType: 'json',
				url: curl,
				success:function(res){
					if(res.success){
						$('#respons_txt').val(JSON.stringify(res.data));
					}else{
						$('#respons_txt').val(res.message);
					}
					
				}
			});
		});
	}
	


	/**
	 * cursorPosition Object
	 *
	 * Created by Blank Zheng on 2010/11/12.
	 * Copyright (c) 2010 PlanABC.net. All rights reserved.
	 * 
	 * The copyrights embodied in the content of this file are licensed under the BSD (revised) open source license.
	 */
	 
	var cursorPosition = {
		get: function (textarea) {
			var rangeData = {text: "", start: 0, end: 0 };
		
			if (textarea.setSelectionRange) { // W3C	
				textarea.focus();
				rangeData.start= textarea.selectionStart;
				rangeData.end = textarea.selectionEnd;
				rangeData.text = (rangeData.start != rangeData.end) ? textarea.value.substring(rangeData.start, rangeData.end): "";
			} else if (document.selection) { // IE
				textarea.focus();
				var i,
					oS = document.selection.createRange(),
					// Don't: oR = textarea.createTextRange()
					oR = document.body.createTextRange();
				oR.moveToElementText(textarea);
				
				rangeData.text = oS.text;
				rangeData.bookmark = oS.getBookmark();
				
				// object.moveStart(sUnit [, iCount]) 
				// Return Value: Integer that returns the number of units moved.
				for (i = 0; oR.compareEndPoints('StartToStart', oS) < 0 && oS.moveStart("character", -1) !== 0; i ++) {
					// Why? You can alert(textarea.value.length)
					if (textarea.value.charAt(i) == '\r' ) {
						i ++;
					}
				}
				rangeData.start = i;
				rangeData.end = rangeData.text.length + rangeData.start;
			}
			
			return rangeData;
		},
		
		set: function (textarea, rangeData) {
			var oR, start, end;
			if(!rangeData) {
				alert("You must get cursor position first.")
			}
			textarea.focus();
			if (textarea.setSelectionRange) { // W3C
				textarea.setSelectionRange(rangeData.start, rangeData.end);
			} else if (textarea.createTextRange) { // IE
				oR = textarea.createTextRange();
				
				// Fixbug : ues moveToBookmark()
				// In IE, if cursor position at the end of textarea, the set function don't work
				if(textarea.value.length === rangeData.start) {
					//alert('hello')
					oR.collapse(false);
					oR.select();
				} else {
					oR.moveToBookmark(rangeData.bookmark);
					oR.select();
				}
			}
		},

		add: function (textarea, rangeData, text) {
			var oValue, nValue, oR, sR, nStart, nEnd, st;
			this.set(textarea, rangeData);
			
			if (textarea.setSelectionRange) { // W3C
				oValue = textarea.value;
				nValue = oValue.substring(0, rangeData.start) + text + oValue.substring(rangeData.end);
				nStart = nEnd = rangeData.start + text.length;
				st = textarea.scrollTop;
				textarea.value = nValue;
				// Fixbug:
				// After textarea.values = nValue, scrollTop value to 0
				if(textarea.scrollTop != st) {
					textarea.scrollTop = st;
				}
				textarea.setSelectionRange(nStart, nEnd);
			} else if (textarea.createTextRange) { // IE
				sR = document.selection.createRange();
				sR.text = text;
				sR.setEndPoint('StartToEnd', sR);
				sR.select();
			}
		}
	}


	
	 

	return {
		//main function to initiate template pages
		init : function() {
			autosizeHandler();
			interdataHandler();
			interdataClick();
			testSubmitClick();
		}
	};
}();