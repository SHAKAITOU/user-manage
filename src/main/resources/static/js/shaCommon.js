//*****************************************************************************
// --ShaCommon.constants--
// --ShaCommon.init--

// ShaCommon.util
// ShaCommon.check
// ShaCommon.json
// ShaCommon.dialogs
// ShaCommon.restful
// ShaCommon.ajax
// ShaCommon.ajaxJson
// ShaCommon.pageLink
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaCommon;
}catch(e){
	ShaCommon = {};
}

(function($common) {
	
//*****************************************************************************
// common constants define
//*****************************************************************************
	
	if($common.constants) { 
		return $common.constants; 
	}	
	
	$common.constants = {
		//root path
		ROOT_PATH : '',	
		NOT_BLANK_MSG : 'must not be blank.',
		NOT_DUPLICATE_MSG : 'is duplicated.',
	}
	
//*****************************************************************************
// common init define
//*****************************************************************************
	
	if($common.init) { 
		return $common.init; 
	}	
	
	$common.init = {

	}
	
//*****************************************************************************
// common util define
//*****************************************************************************
	
	if($common.util) { 
		return $common.util; 
	}	
	
	$common.util = {
			
		//------------------------------------------------------------------------------
		// get random int between min and max int
		//------------------------------------------------------------------------------
		getRandomInt : function (min, max) {
			return Math.floor( Math.random() * (max - min + 1) ) + min;
		},
		
		getAverageInt : function (numbers) {
			var sum = 0;
			for( var i = 0; i < numbers.length; i++ ){
				//don't forget to add the base
			    sum += parseInt( numbers[i], 10 ); //10進
			}
			var avg = Math.floor(sum/numbers.length);
			return avg;
		},
		
		isLoto7Number : function(number) {
			var numbers = [];
			for( var i = 1; i <= 37; i++ ){
				if(number == i){
					return true;
				}
			}
			
			return false;
		},
		
		isLoto6Number : function(number) {
			var numbers = [];
			for( var i = 1; i <= 43; i++ ){
				if(number == i){
					return true;
				}
			}
			
			return false;
		},
		
		isMiniLotoNumber : function(number) {
			var numbers = [];
			for( var i = 1; i <= 31; i++ ){
				if(number == i){
					return true;
				}
			}
			
			return false;
		},
		
		triggerWinResize : function(callFun) {
			$(window).resize(function() {
				callFun();
			}).trigger("resize");
		},
		
		//------------------------------------------------------------------------------
		// get the full url
		//------------------------------------------------------------------------------	
		getFullUrl : function (url) {
			if (url.match(ShaCommon.constants.ROOT_PATH)) {
				return url;
			} else {
				return ShaCommon.constants.ROOT_PATH+url;
			}
		},
		
		convertToJqueryId : function(objectId) {
			if(objectId != null && objectId != '') {
				var firstChar = objectId.substr(0, 1);
				if(firstChar != '#') {
					return '#'+objectId;
				} 
				
				return objectId;
			}
			return objectId;
		},
	}
	
//*****************************************************************************
// common check define
//*****************************************************************************
	
	if($common.check) { 
		return $common.check; 
	}	
	
	$common.check = {
		
		_WATCH_INPUT_LIST : [],
		
		checkNotBlank : function(inputCheckItemList, itemNameList){
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var inputCheckItem = inputCheckItemList[i];
				var errorItemName = itemNameList[i];
				if(ShaCommon.check._checkNotBlank(inputCheckItem)){
					ShaCommon.dialogs.alertFocus(errorItemName + ShaCommon.constants.NOT_BLANK_MSG,
							inputCheckItem);
					inputCheckItem.addClass('alert-input');
					return true;
				}else{
					inputCheckItem.removeClass('alert-input');
				}
			}
			
			return false;
		},
		
		checkDuplicate : function(inputCheckItemList, itemNameList){
			var numMap = [];
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				if(i>0 && numMap[inputCheckItemList[i].val()] != null) {
					ShaCommon.dialogs.alertFocus(itemNameList[i] + ShaCommon.constants.NOT_DUPLICATE_MSG,
							inputCheckItemList[i]);
					inputCheckItemList[i].addClass('alert-input');
					return true;
				} else{
					numMap[inputCheckItemList[i].val()] = inputCheckItemList[i].val();
					inputCheckItemList[i].removeClass('alert-input');
				}
			}
			return false;
		},
		
		checkInput : function(inputCheckItemList, itemNameList, checkFunc, errorMsg){
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var inputCheckItem = inputCheckItemList[i];
				var errorItemName = itemNameList[i];
				if(checkFunc(inputCheckItem)){
					ShaCommon.dialogs.alertFocus(errorItemName + errorMsg, inputCheckItem);
					inputCheckItem.addClass('alert-input');
					return true;
				}else{
					inputCheckItem.removeClass('alert-input');
				}
			}
			
			return false;
		},
		
		watchInputChange : function(inputItemIdList){
			_WATCH_INPUT_LIST = [];
			for( var i = 0; i < inputItemIdList.length; i++ ){
				var watchItem = ShaCommon.json.create();
				var id = ShaCommon.util.convertToJqueryId(inputItemIdList[i]);
				var object = $(id);
				var type = object.attr('type');
				ShaCommon.json.setJsonData(watchItem, 'itemId', id);
				if(type == 'text' || type == 'password'|| 
						type == 'file') {
					ShaCommon.json.setJsonData(watchItem, 'value', object.val());
				}else if(type == 'radio' || type == 'checkbox'){
					ShaCommon.json.setJsonData(watchItem, 'value', object.prop('checked'));
				}
				_WATCH_INPUT_LIST[i] = watchItem;
			}
		},
		
		hasInputChange : function(){
			for( var i = 0; i < _WATCH_INPUT_LIST.length; i++ ){
				var watchItem = _WATCH_INPUT_LIST[i];
				var oldValue = watchItem.value;
				var object = $(watchItem.itemId);
				var type = object.attr('type');
				var newValue = '';
				if(type == 'text' || type == 'password'|| 
						type == 'file') {
					newValue = object.val();
				}else if(type == 'radio' || type == 'checkbox'){
					newValue = object.prop('checked');
				}
				
				if(oldValue != newValue){
					return true;
				}
			}
			
			return false;
		},
		
		_checkNotBlank : function(inputCheckItem){
			var type = inputCheckItem.attr('type');
			if(type == 'text' || type == 'password' || 
					type == 'file') {
				if(inputCheckItem.val() == ""){
					return true;
				} else {
					return false;
				}
			}
			
			return false;
		},
		
	}
	
//*****************************************************************************
// common json define
//*****************************************************************************
	
	if($common.json) { 
		return $common.json; 
	}	
	
	$common.json = {
		create : function(){
			return {};
		},
		
		setJsonData : function (jsonObject,key,value){
		    jsonObject[key] = value;
		},
		
		getJsonData : function (jsonObject,key){
			return jsonObject[key];
		},
		
		toObject : function(jsonString){
			return JSON.parse(jsonString);
		},
		
		toString : function(jsonObject){
			return JSON.stringify(jsonObject);
		}
	}	
	
//*****************************************************************************
// common dialogs define
//*****************************************************************************
	
	if($common.dialogs) { 
		return $common.dialogs; 
	}	
	
	$common.dialogs = {
		//------------------------------------------------------------------------------
		// get the windown size
		//------------------------------------------------------------------------------	
		getWindowSize : function () {
			var w = window.innerWidth ? window.innerWidth: $(window).width();
			var h = window.innerHeight ? window.innerHeight: $(window).height();
			return {width : w, height : h};
		},
		//------------------------------------------------------------------------------
		// show component to vertical center and align center
		//------------------------------------------------------------------------------
		toAlignVerticalCenter : function (component) {
			var winSize = ShaCommon.dialogs.getWindowSize();
			var dialogTop =  winSize.height/2 - component.height()/2;
			var dialogLeft = (winSize.width/2) - component.width()/2;
			component.css('top', dialogTop);
			component.css('left', dialogLeft);
		},
		progress : function (showFlg) {
			ShaCommon.dialogs.toAlignVerticalCenter($('#progress'));
			if(showFlg){
				return $('#progress').modal('show');
			}else{
				return $('#progress').modal('hide');
			}
		},
		alert : function (context) {
			ShaCommon.dialogs.toAlignVerticalCenter($('#alert'));
			$('#alert').find('#alertBody').html(context);
			return $('#alert').modal('show');
		},
		
		alertFocus : function (context, focusObj) {
			ShaCommon.dialogs.toAlignVerticalCenter($('#alert'));
			$('#alert').find('#alertBody').html(context);
			$('#alert').find('#alertBtn').on('click', function(event) {
				event.preventDefault();
				focusObj.focus();
			});
			return $('#alert').modal('show');
		},
		
		confirm : function (title, context, callBackOk) {
			ShaCommon.dialogs.toAlignVerticalCenter($('#confirm'));
			$('#confirm').find('#confirmTitle').html(title);
			$('#confirm').find('#confirmBody').html(context);
			
			$btn_obj = $('#confirm').find('#confirmBtnOk'); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
		    	ShaCommon.dialogs.confirmClose();
				callBackOk();
			});
			
			$('#confirm').modal('show');
		},
		confirmClose : function () {
			$('#confirm').modal('hide');
		},
		success : function (context) {
			ShaCommon.dialogs.toAlignVerticalCenter($('#success'));
			$('#success').find('#successBody').html(context);
			$('#success').modal('show');
		},
		
		dialog : function (title, context, buttonList){
			ShaCommon.dialogs.toAlignVerticalCenter($('#dialog'));
			$('#dialog').find('#dialogTitle').html(title);
			$('#dialog').find('#dialogBody').html(context);
			var footer = $('#dialog').find('#dialogFooter');
			footer.empty();
			footer.append(errorDiv);
			for(i=0; i<buttonList.length; i++){
				var button = 
					$('<button/>').text(buttonList[i].text)
							.addClass(buttonList[i].class)
			    			.click(function () { buttonList[i].func });					
				footer.append(button);
			}
			$('#dialog').modal('show');
		},
		dialogClose : function () {
			$('#dialog').modal('hide');
		},
	
	}
	
//*****************************************************************************
// common restful define
//*****************************************************************************
	
	if($common.restful) { 
		return $common.restful; 
	}	
	
	$common.restful = {
		get : function(url, parameters) {
			var paras = "";
			if(parameters != null) {
				for( var i = 0; i <= parameters.length; i++ ){
					if(i == 0) {
						paras += "?" + parameters[i].name + "=" + parameters[i].value;
					} else {
						paras += "&" + parameters[i].name + "=" + parameters[i].value;
					}
				}
			}
			window.location.href = ShaCommon.util.getFullUrl(url)+paras;
			ShaCommon.dialogs.progress(true);
		},
		
		post : function(url, formObj) {
			formObj.attr('action', ShaCommon.util.getFullUrl(url));
			formObj.submit();
			ShaCommon.dialogs.progress(true);
		},
		
		refeshIndex : function() {
			ShaCommon.restful.get(ShaCommon.util.getFullUrl("/"),null);
		}
	}
	
	
//*****************************************************************************
// common ajax with page refresh define
//*****************************************************************************
	
	if($common.ajax) { 
		return $common.ajax; 
	}	
	
	$common.ajax = {
			
		//------------------------------------------------------------------------------
		// execute get ajax
		//------------------------------------------------------------------------------
		get : function (url, formData) {
			
			ShaCommon.dialogs.progress(true);
			
			var paramData = null;
			if(arguments.length === 2){
				paramData = formData;
			}
			
			$.ajax({
				   type: "GET",
				   url: ShaCommon.util.getFullUrl(url),
				   data: paramData,
				   statusCode: {
					    200: function(data){
					    	ShaCommon.ajax.callBackOk(data);
					    	ShaCommon.dialogs.progress(false);
					    },
					    404: function() {
					    	alert( "page not found" );
					    	ShaCommon.dialogs.progress(false);
					    },
					    500: function(data){
					    	ShaCommon.ajax.callBackNg(data);
					    	ShaCommon.dialogs.progress(false);
					    }
				   },
				   timeout: 100000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post ajax
		//------------------------------------------------------------------------------
		post : function (url, formData) { 
			
			ShaCommon.dialogs.progress(true);
			
			$.ajax({
				   type: "POST",
				   url: ShaCommon.util.getFullUrl(url),
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	ShaCommon.ajax.callBackOk(data);
					    	ShaCommon.dialogs.progress(false);
					    },
					    404: function() {
					    	alert( "page not found" );
					    	ShaCommon.dialogs.progress(false);
					    },
					    500: function(data){
					    	ShaCommon.ajax.callBackNg(data);
					    	ShaCommon.dialogs.progress(false);
					    }
				   },
				   timeout: 100000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post with succ ajax
		//------------------------------------------------------------------------------
		postWithSucc : function (url, formData, sucMsg) { 
			
			ShaCommon.dialogs.progress(true);
			
			$.ajax({
				   type: "POST",
				   url: ShaCommon.util.getFullUrl(url),
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	ShaCommon.ajax.callBackOk(data);
					    	ShaCommon.dialogs.progress(false);
					    	ShaCommon.dialogs.success(sucMsg);
					    },
					    404: function() {
					    	alert( "page not found" );
					    	ShaCommon.dialogs.progress(false);
					    },
					    500: function(data){
					    	ShaCommon.ajax.callBackNg(data);
					    	ShaCommon.dialogs.progress(false);
					    }
				   },
				   timeout: 100000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute ajax callBackOk
		//------------------------------------------------------------------------------
		callBackOk : function (data) {
			$("#okPanel").show();
			$("#ngPanel").hide();
			$("#okResultBody").html(data);
			$("#ngResultBody").html("");
		},
		
		//------------------------------------------------------------------------------
		// execute ajax callBackNg
		//------------------------------------------------------------------------------
		callBackNg : function (data) {
			$("#okPanel").hide();
			$("#ngPanel").show();
			$("#okResultBody").html("");
			$("#ngResultBody").html(data.responseText);
		}
	}

//*****************************************************************************
// execute ajax, return a json object class
//*****************************************************************************
	if($common.ajaxJson) { 
		return $common.ajaxJson; 
	}	
	
	$common.ajaxJson = {
		
		//------------------------------------------------------------------------------
		// execute get ajax
		//------------------------------------------------------------------------------
		get : function (url, formData, callBackOk) {
			
			ShaCommon.dialogs.progress(true);
			
			$.ajax({
				   type: "GET",
				   url: ShaCommon.util.getFullUrl(url),
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	callBackOk(data);
					    	ShaCommon.dialogs.progress(false);
					    },
					    404: function() {
					    	alert( "page not found" );
					    	ShaCommon.dialogs.progress(false);
					    },
					    500: function(data){
					    	ShaCommon.ajax.callBackNg(data);
					    	ShaCommon.dialogs.progress(false);
					    }
				   },
				   timeout: 100000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post ajax, return a json object
		//------------------------------------------------------------------------------
		post : function (url, formData, callBack) { 
			
			ShaCommon.dialogs.progress(true);
			
			$.ajax({
				   type: "POST",
				   url : ShaCommon.util.getFullUrl(url),
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	ShaCommon.dialogs.progress(false);
					    	callBack(data);
					    },
					    404: function(data) {
					    	ShaCommon.dialogs.progress(false);
					    	alert( "page not found" );
					    },
					    405: function(data) {
					    	ShaCommon.dialogs.progress(false);
					    	alert( "wrong type" );
					    },
					    500: function(data){
					    	ShaCommon.dialogs.progress(false);
					    	ShaCommon.ajax.callBackNg(data);
					    }
				   },
				   timeout: 100000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post ajax with upload files, return a json object
		//------------------------------------------------------------------------------
		postWithUploadFile : function (url, formId, callBack) { 
			
			ShaCommon.dialogs.progress(true);
			
			var formData;
			var form;
			var jqueryFormId = ShaCommon.util.convertToJqueryId(formId);
			form = $(jqueryFormId);
			formData = new FormData(form.get()[0]);
			
			var $files = form.find('input[type="file"]');
			// get file data by .prop() and set the file information to formData
		    $files.each(function(){
			    formData.append( 'file', $(this).prop("files")[0] );
		    });
		    
		    
			$.ajax({
				   type: "POST",
				   url : ShaCommon.util.getFullUrl(url),
				   data: formData,
				   enctype: 'multipart/form-data',
				   processData: false,
				   contentType: false,
				   statusCode: {
					   200: function(data){
					    	ShaCommon.dialogs.progress(false);
					    	callBack(data);
					    },
					    404: function(data) {
					    	ShaCommon.dialogs.progress(false);
					    	alert( "page not found" );
					    },
					    405: function(data) {
					    	ShaCommon.dialogs.progress(false);
					    	alert( "wrong type" );
					    },
					    500: function(data){
					    	ShaCommon.dialogs.progress(false);
					    	ShaCommon.ajax.callBackNg(data);
					    }
				   },
				   timeout: 100000
			});
		},
	}
	
//*****************************************************************************
// common pageLink define
//*****************************************************************************
	
	if($common.pageLink) { 
		return $common.pageLink; 
	}	
	
	$common.pageLink = {
		initPageLink : function(pageLinkIdPrefix, url, initSetting, formId){
			var linkObject;
			linkObject = $('[id^='+pageLinkIdPrefix+']');
			linkObject.each(function(i, elem) {
				$(elem).on('click', function(event) {
					event.preventDefault();
					var doFlag = true;
					if(initSetting != null){
						doFlag = initSetting();
					}
					
					if(doFlag){
						$('#'+pageLinkIdPrefix+'Index').val(
								ShaCommon.pageLink._setLinkedPageIndex($(elem).attr('id'), pageLinkIdPrefix));
						
						var postData = $('#'+formId).serializeArray();
						ShaCommon.ajax.post(url, postData);
					}
				});
				
			});
		},
		_setLinkedPageIndex : function(linkedPageId, pageLinkIdPrefix){
			if(linkedPageId == (pageLinkIdPrefix+'Prev')){
				return parseInt($('#'+pageLinkIdPrefix+'Index').val()) - 1;
			}else if(linkedPageId == (pageLinkIdPrefix+'Next')){
				return parseInt($('#'+pageLinkIdPrefix+'Index').val()) + 1;
			}else{
				return parseInt(linkedPageId.replace( pageLinkIdPrefix, '' ))-1;
			}
		}
	}
	
})(ShaCommon);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});