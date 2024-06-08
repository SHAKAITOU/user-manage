//*****************************************************************************
// --ShaInput.sound--
// ShaInput.sound.playSoundClick()
// ShaInput.sound.playSoundPullDown()
// ShaInput.sound.playSoundAlert()
// ShaInput.sound.playSoundSuccess()
// --ShaInput.obj--
// ShaInput.obj.disabled(obj)
// ShaInput.obj.enabled(obj)
// ShaInput.obj.readonly(obj)
// ShaInput.obj.focusRight(obj)
// ShaInput.obj.isDisabled(obj)
// ShaInput.obj.isEnabled(obj)
// ShaInput.obj.focusRight(obj)
// ShaInput.obj.numberInit(obj)
// ShaInput.obj.changeCssByCheckBox(tr, checked)
// ShaInput.obj.changeBgCssByCheckBox(tr, orgCssAttrName, checked)
// --ShaInput.keyPress--
// ShaInput.keyPress.enterPress(obj, callFunc)
// ShaInput.keyPress.enterAltPress(obj, callFunc)
// ShaInput.keyPress.customsPressF1(callFunc)
// ShaInput.keyPress.customsPressF2(callFunc)
// ShaInput.keyPress.customsPressF3(callFunc)
// ShaInput.keyPress.customsPressF4(callFunc)
// ShaInput.keyPress.customsPressF5(callFunc)
// ShaInput.keyPress.customsPressF6(callFunc)
// ShaInput.keyPress.customsPressF7(callFunc)
// ShaInput.keyPress.customsPressF8(callFunc)
// ShaInput.keyPress.customsPressF9(callFunc)
// ShaInput.keyPress.customsPressF10(callFunc)
// ShaInput.keyPress.customsPressF11(callFunc)
// ShaInput.keyPress.customsPressF12(callFunc)
// ShaInput.keyPress.recoverDefaultPressF1ToF12()
// --ShaInput.button--
// ShaInput.button.onClick(btn_obj, callFunc)
// ShaInput.button.onChange(btn_obj, callFunc)
// --ShaInput.table--
// ShaInput.table.adjustCellWidthToFitHead(form, tableId)
// ShaInput.table.addClickEventToTr(form, tableId, clickEventFunc)
// ShaInput.table.addClickActiveToTr(form, tableId)
// ShaInput.table.doClickToTr(form, tableId, trIndex)
// ShaInput.table.length(form, tableId)
// --ShaInput.tab--
// ShaInput.tab.initTab(form, tabId)
// ShaInput.tab.hideAllTab (form, tabId)
// ShaInput.tab.activeTab (form, tabId, activeId)
// --ShaInput.img--
// ShaInput.img.preview(form, fileInputId, imgId)
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaInput;
}catch(e){
	ShaInput = {};
}

(function($shaInput) {
//*****************************************************************************
// sound define
//*****************************************************************************
	
	if($shaInput.sound) { 
		return $shaInput.sound; 
	}	
	
	$shaInput.sound = {
			
		playSoundClick : function(){
//			setTimeout(function(){
//				try{
//					document.getElementById("audio_click").play();
//				}
//				catch(e) {
//					console.log(e);
//				}
//			}, 50);
		},
		
		playSoundPullDown : function(){
//			setTimeout(function(){
//				try{
//					document.getElementById("audio_click4").play();
//				}
//				catch(e) {
//					console.log(e);
//				}
//			}, 50);
		},
		
		playSoundAlert : function(){
//			setTimeout(function(){
//				try{
//					document.getElementById("audio_click4").play();
//				}
//				catch(e) {
//					console.log(e);
//				}
//			}, 50);
		},
		
		playSoundSuccess : function(){
//			setTimeout(function(){
//				try{
//					document.getElementById("audio_click4").play();
//				}
//				catch(e) {
//					console.log(e);
//				}
//			}, 50);
		},
	}	
//*****************************************************************************
// obj define
//*****************************************************************************
	
	if($shaInput.obj) { 
		return $shaInput.obj; 
	}	
	
	$shaInput.obj = {
			
		disabled : function(obj){
			obj.prop("disabled", true);
		},
		
		enabled : function(obj){
			obj.prop("disabled", false);
		},
		
		readonly : function(obj){
			obj.prop("readonly", true);
			obj.css("background-color", "#dcd3b2");
			
		},
		
		editable : function(obj){
			obj.prop("readonly", false);
			obj.css("background-color", "");
			
		},
		
		isDisabled : function(obj){
			return obj.prop("disabled");
		},
		
		isEnabled : function(obj){
			return !obj.prop("disabled");
		},
		
		focusRight : function(obj){
			obj.focus(function(){
		        if($(this).attr('type') === 'text'){
		            var data = $(this).val();
		            $(this).val("");
		            $(this).val(data);
		        }
		    });
		},
		
		numberInit : function(obj){
			obj.keypress(function(event){
				var st = String.fromCharCode(event.which);
				if ("0123456789".indexOf(st,0) < 0) { return false; }
				return true;
			});
		},
		
		changeCssByCheckBox : function(tr, checked){
			if(checked){
				tr.addClass('table-selectAct');
			}else{
				tr.removeClass('table-selectAct');
			}
		},
		
		changeBgCssByCheckBox : function(tr, orgCssAttrName, checked){
			if(checked){
				var oldCss = tr.attr(orgCssAttrName);
				if(oldCss != ''){
					tr.removeClass('table-'+oldCss);
				}
				tr.addClass('table-selectAct');
			}else{
				var oldCss = tr.attr(orgCssAttrName);
				if(oldCss != ''){
					tr.addClass('table-'+oldCss);
				}
				tr.removeClass('table-selectAct');
			}
		},
	}	
//*****************************************************************************
// keyPress define
//*****************************************************************************
	
	if($shaInput.keyPress) { 
		return $shaInput.keyPress; 
	}	
	
	$shaInput.keyPress = {
			
		//------------------------------------------------------------------------------
		// when enter key pressed
		//------------------------------------------------------------------------------
		enterPress : function (obj, callFunc) {
			obj.keypress(
			  function(event){
			    if (event.which == '13') {
			      event.preventDefault();
			      ShaInput.sound.playSoundClick();
			      callFunc();
			      event.stopPropagation();
			      return false;
			    }
			});
		},
		
		enterAltPress : function (obj, callFunc) {
			obj.keydown(
			  function(event){
			    if (event.which == '13' && event.altKey) {
			      event.preventDefault();
			      ShaInput.sound.playSoundClick();
			      callFunc();
			      event.stopPropagation();
			      return false;
			    }
			});
		},
		
		customsPressF1 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 112)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF2 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 113)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF3 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 114)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF4 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 115)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF5 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 116)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF6 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 117)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF7 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 118)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF8 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 119)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF9 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 120)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF10 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 121)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF11 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 122)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		customsPressF12 : function(callFunc){
			$(document).keydown(function(e){
				var code = e.keyCode;
				if(!isNaN(code) && code == 123)
				{
					event.preventDefault();
					callFunc();
				}
			});
		},
		
		recoverDefaultPressF1ToF12 : function(){
			$(document).off('keydown');
		},
	}
	
//*****************************************************************************
// button define
//*****************************************************************************
	
	if($shaInput.button) { 
		return $shaInput.button; 
	}	
	
	$shaInput.button = {
	
		onClick : function(obj, callFunc){
			obj.unbind('click'); 
			obj.on('click', function(event) {
	            event.preventDefault();
	            ShaInput.sound.playSoundClick();
	            callFunc();
	        });
		},
		
		onClickKeepDefault : function(obj, callFunc){
			obj.unbind('click'); 
			obj.on('click', function(event) {
	            ShaInput.sound.playSoundClick();
	            callFunc();
	        });
		},
		
		onChange : function(obj, callFunc){
			obj.unbind('change'); 
			obj.on('change', function(event) {
	            event.preventDefault();
	            ShaInput.sound.playSoundClick();
	            callFunc();
	        });
		},
		
		onChangeKeepDefault : function(obj, callFunc){
			obj.unbind('change'); 
			obj.on('change', function(event) {
	            ShaInput.sound.playSoundClick();
	            callFunc();
	        });
		},
		
		onMouseOver : function(obj, callFunc){
			obj.unbind('mouseover'); 
			obj.on('mouseover', function(event) {
	            event.preventDefault();
	            callFunc();
	        });
		},
		
		onMouseOut : function(obj, callFunc){
			obj.unbind('mouseout'); 
			obj.on('mouseout', function(event) {
	            event.preventDefault();
	            callFunc();
	        });
		}
	}
	
//*****************************************************************************
// table define
//*****************************************************************************
	
	if($shaInput.table) { 
		return $shaInput.table; 
	}	
	
	$shaInput.table = {
		
		adjustCellWidthToFitHead : function(form, tableId){
			
			var table = form.find(ShaUtil.util.convertToJqueryId(tableId));
			var maxHeight = table.find('tbody').height();
			var totalHeight = 0;
			var rowHeight = ShaConstants.constants.TABLE_TD_HEIGHT;
			
			table.find('tbody > tr').each(function(i, elem) {
	        	totalHeight += table.find('tbody > tr > td').eq(0).outerHeight(true);
	        });
			
			//when need scroll
			if(maxHeight < totalHeight){
				var outer = $('<div>').css({
					visibility: 'hidden',
					width: 100,
					overflow: 'scroll',
				}).appendTo('body'),
				width = $('<div>').css({
					width: '100%'
				}).appendTo(outer).outerWidth(),
				result = 0;
				outer.remove();
				result = 100 - width;
				table.find('thead').css({
					paddingRight: result
				});
				
				// when IE
			    if (ShaUtil.other.isIE()){
			    	table.find('tbody').css({
						paddingRight: result
					});
			    }
			}
		},
			
		addClickEventToTr : function(form, tableId, clickEventFunc){
			$table = form.find(ShaUtil.util.convertToJqueryId(tableId));
			$table.find('tbody > tr').each(function(i, elem) {
	        	$(elem).on('click', function(event) {
	                event.preventDefault();
	                clickEventFunc($(this), i);
	            });
	        });
		},
		
		addClickActiveToTr : function(form, tableId){
			$table = form.find(ShaUtil.util.convertToJqueryId(tableId));
			$table.find('tbody > tr').each(function(i, elem) {
	        	$(elem).on('click', function(event) {
	                event.preventDefault();
	                if($(this).hasClass('table-selectAct')){
	                	return;
	                }
	                $(elem).parent().find('tr').removeClass('table-selectAct');
	                $(elem).parent().find('tr').each(function(i, subElem) {
	                	if($(subElem).hasClass('keep-danger')){
	                		$(this).addClass('table-danger');
		                	$(this).removeClass('keep-danger');
	                	} else if($(subElem).hasClass('keep-info')){
	                		$(this).addClass('table-info');
		                	$(this).removeClass('keep-info');
	                	} else if($(subElem).hasClass('keep-success')){
	                		$(this).addClass('table-success');
		                	$(this).removeClass('keep-success');
	                	} else if($(subElem).hasClass('keep-warning')){
	                		$(this).addClass('table-warning');
		                	$(this).removeClass('keep-warning');
	                	}
	                });
	                $(this).addClass('table-selectAct');
	                if($(this).hasClass('table-danger')){
	                	$(this).addClass('keep-danger');
	                	$(this).removeClass('table-danger');
	                } else if($(this).hasClass('table-info')){
	                	$(this).addClass('keep-info');
	                	$(this).removeClass('table-info');
	                } else if($(this).hasClass('table-success')){
	                	$(this).addClass('keep-success');
	                	$(this).removeClass('table-success');
	                } else if($(this).hasClass('table-warning')){
	                	$(this).addClass('keep-warning');
	                	$(this).removeClass('table-warning');
	                }
	            });
	        });
		},
		
		doClickToTr : function(form, tableId, trIndex){
			$table = form.find(ShaUtil.util.convertToJqueryId(tableId));
			$tr = $table.find('tbody > tr').eq(trIndex);
			if($tr != null){
				$tr.click();
			}
		},
		
		getTrData : function(elem, key){
			// button -> div -> div -> td --> tr
			$tr = $(elem).parent().parent().parent().parent();
			return $tr.attr(key);
		},
		
		length : function(form, tableId){
			$table = form.find(ShaUtil.util.convertToJqueryId(tableId));
			return $table.find('tbody > tr').length;
		},
		
		filter : function(form, tableId, filterKey, filterValue) {
			$table = form.find(ShaUtil.util.convertToJqueryId(tableId));
			$table.find('tbody > tr').each(function(i, elem) {
				if ($(elem).attr(filterKey).indexOf(filterValue) < 0) {
					$(elem).css('display','none');
				} else {
					$(elem).css('display','block');
				}
			});
		}
	}
	
//*****************************************************************************
// common tab define
//*****************************************************************************
	
	if($shaInput.tab) { 
		return $shaInput.tab; 
	}	
	
	$shaInput.tab = {
		initTab : function(form, tabId){
			$tab = form.find(ShaUtil.util.convertToJqueryId(tabId));
			ShaInput.tab.hideAllTab(form, tabId);
			$tab.find('label').each(function(i, elem) {
				$(elem).on('click', function(event) {
	                event.preventDefault();
	                ShaInput.tab.hideAllTab(form, tabId);
	                var contextId = $(this).attr('for');
	                form.find('#'+contextId).show();
	            });
			});
		},
		
		hideAllTab : function (form, tabId){
			$tab = form.find(ShaUtil.util.convertToJqueryId(tabId));
			$tab.find('label').each(function(i, elem) {
				var contextId = $(elem).attr('for');
				form.find('#'+contextId).hide();
				$(elem).removeClass('active');
			});
		},
		
		activeTab : function (form, tabId, activeId){
			$tab = form.find(ShaUtil.util.convertToJqueryId(tabId));
			ShaInput.tab.hideAllTab(form, tabId);
			$tab.find('label').each(function(i, elem) {
				if($(elem).attr('for') == activeId){
					$(elem).addClass('active');
					form.find('#'+activeId).show();
				}
			});
		},
		
		getTabButton : function (form, tabId, activeId){
			$tab = form.find(ShaUtil.util.convertToJqueryId(tabId));
			var btn;
			$tab.find('label').each(function(i, elem) {
				if($(elem).attr('for') == activeId){
					btn = $(elem);
				}
			});
			
			return btn;
		},
		
		getActivedTab : function (form, tabId){
			$tab = form.find(ShaUtil.util.convertToJqueryId(tabId));
			var activeId;
			$tab.find('label').each(function(i, elem) {
				if($(elem).hasClass('active')){
					activeId = $(elem).attr('for');
				}
			});
			return activeId;
		},
	}
	
//*****************************************************************************
// common img define
//*****************************************************************************
	
	if($shaInput.img) { 
		return $shaInput.img; 
	}	
	
	$shaInput.img = {
		preview : function(form, fileInputId, imgId){
			var $files = form.find(ShaUtil.util.convertToJqueryId(fileInputId));
			var $imgFile;
			$files.each(function(){
				$imgFile = $(this).prop("files")[0];
		    });
			$img = form.find(ShaUtil.util.convertToJqueryId(imgId));
		    var reader = new FileReader();
		    
		    reader.onload = function(e) {
		    	$img.attr('src', e.target.result);
		    }
		    
		    reader.readAsDataURL($imgFile);
		},
	}	
})(ShaInput);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});
