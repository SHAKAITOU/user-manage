//*****************************************************************************
// --ShaCheck.check--
// ShaCheck.check.checkNotBlank(inputCheckItemList)
// ShaCheck.check.checkDuplicate(inputCheckItemList)
// ShaCheck.check.checkInput(inputCheckItemList, itemNameList, checkFunc, errorMsg)
// ShaCheck.check.checkMaxLength(inputCheckItemList)
// ShaCheck.check.checkMinLength(inputCheckItemList, itemNameList, lengthList)
// ShaCheck.check.checkItem(inputCheckItem, checkFLg, errorMsg)
// ShaCheck.check.watchInputChange(inputItemIdList)
// ShaCheck.check.hasInputChange()
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaCheck;
}catch(e){
	ShaCheck = {};
}

(function($shaCheck) {
	
//*****************************************************************************
// common check define
//*****************************************************************************
	
	if($shaCheck.check) { 
		return $shaCheck.check; 
	}	
	
	$shaCheck.check = {
		
		_WATCH_INPUT_LIST : [],
		
		checkNotBlank : function(inputCheckItemList){
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var inputCheckItem = inputCheckItemList[i][1];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				$("#"+errorId).remove();
			}
			
			var ngFlag = false;
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var errorItemName = inputCheckItemList[i][0];
				var inputCheckItem = inputCheckItemList[i][1];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				if(ShaCheck.check._checkNotBlank(inputCheckItem)){
					var errorMsg = errorItemName + ShaConstants.constants.NOT_BLANK_MSG;
					inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + errorMsg + '</div>'); 
					inputCheckItem.addClass('alert-input');
					inputCheckItem.addClass('is-invalid');
					ngFlag = true;
				}else{
					inputCheckItem.removeClass('alert-input');
					inputCheckItem.removeClass('is-invalid');
				}
			}
			
			return ngFlag;
		},
		
		checkDuplicate : function(inputCheckItemList, itemNameList){
			var numMap = [];
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				if(i>0 && numMap[inputCheckItemList[i].val()] != null) {
					ShaDialog.dialogs.alertFocus(itemNameList[i] + ShaConstants.constants.NOT_DUPLICATE_MSG,
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
		
		checkInput : function(inputCheckItemList, checkFunc, errorMsg){
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var inputCheckItem = inputCheckItemList[i][1];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				$("#"+errorId).remove();
			}
			var ngFlag = false;
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var errorItemName = inputCheckItemList[i][0];
				var inputCheckItem = inputCheckItemList[i][1];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				if(checkFunc(inputCheckItem)){
					var errorMsgC = errorItemName + errorMsg
					inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + errorMsgC + '</div>'); 
					inputCheckItem.addClass('alert-input');
					inputCheckItem.addClass('is-invalid');
					ngFlag = true;

				}else{
					inputCheckItem.removeClass('alert-input');
					inputCheckItem.removeClass('is-invalid');
				}
			}
			
			return ngFlag;
		},
		
		checkMaxLength : function(inputCheckItemList){
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var inputCheckItem = inputCheckItemList[i][1];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				$("#"+errorId).remove();
			}
			var ngFlag = false;
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var errorItemName = inputCheckItemList[i][0];
				var inputCheckItem = inputCheckItemList[i][1];
				var maxLength = inputCheckItemList[i][2];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				if(ShaCheck.check._checkMaxLength(inputCheckItem, maxLength)){
					var msg = ShaUtil.util.format(errorItemName + ShaConstants.constants.MAX_LENGTH_MSG, maxLength);
					inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + msg + '</div>'); 
					inputCheckItem.addClass('alert-input');
					inputCheckItem.addClass('is-invalid');
					ngFlag = true;
				}else{
					inputCheckItem.removeClass('alert-input');
					inputCheckItem.removeClass('is-invalid');
				}
			}
			
			return ngFlag;
		},
		
		checkMinLength : function(inputCheckItemList){
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var inputCheckItem = inputCheckItemList[i][1];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				$("#"+errorId).remove();
			}
			var ngFlag = false;
			for( var i = 0; i < inputCheckItemList.length; i++ ){
				var errorItemName = inputCheckItemList[i][0];
				var inputCheckItem = inputCheckItemList[i][1];
				var minLength = inputCheckItemList[i][2];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				if(ShaCheck.check._checkMinLength(inputCheckItem, minLength)){
					var msg = ShaUtil.util.format(errorItemName + ShaConstants.constants.MIN_LENGTH_MSG, minLength);
					inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + msg + '</div>'); 
					inputCheckItem.addClass('alert-input');
					inputCheckItem.addClass('is-invalid');
					ngFlag = true;
				}else{
					inputCheckItem.removeClass('alert-input');
					inputCheckItem.removeClass('is-invalid');
				}
			}
			
			return ngFlag;
		},
		
		checkItem : function(inputCheckItem, checkFLg, errorMsg){
			var id = inputCheckItem.attr('id');
			var errorId = id + "_error";
			if(checkFLg){
				inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + errorMsg + '</div>'); 
				inputCheckItem.addClass('is-invalid');
				inputCheckItem.addClass('alert-input');
				return true;
			}else{
				inputCheckItem.removeClass('alert-input');
				inputCheckItem.removeClass('is-invalid');
			}
			
			return false;
		},
		
		watchInputChange : function(form, inputItemIdList){
			var list = [];
			if(arguments.length === 1){
				list = form;
			}else{
				list = inputItemIdList;
			}
			_WATCH_INPUT_LIST = [];
			for( var i = 0; i < list.length; i++ ){
				var watchItem = ShaUtil.json.create();
				var id = ShaUtil.util.convertToJqueryId(list[i]);
				var object;
				if(arguments.length === 1){
					object = $(id);
				}else{
					object = form.find(id);
				}
				var type = object.attr('type');
				ShaUtil.json.setJsonData(watchItem, 'itemId', id);
				if(type == 'text' || type == 'password'|| type == 'number'|| object.is('textarea') ||
						type == 'file' || object.is('select')) {
					ShaUtil.json.setJsonData(watchItem, 'value', object.val());
				}else if(type == 'radio' || type == 'checkbox'){
					ShaUtil.json.setJsonData(watchItem, 'value', object.prop('checked'));
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
				if(type == 'text' || type == 'password'||type == 'number'|| object.is('textarea') ||
						type == 'file' || object.is('select')) {
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
			if(type == 'text' || type == 'password' ||type == 'number'|| inputCheckItem.is('textarea') ||
					type == 'file') {
				if(inputCheckItem.val() == ""){
					return true;
				} else {
					return false;
				}
			}
			
			return false;
		},
		
		_checkMaxLength : function(inputCheckItem, maxLength){
			var type = inputCheckItem.attr('type');
			if(type == 'text' || type == 'password' || type == 'number'|| 
					inputCheckItem.is('textarea')) {
				if(inputCheckItem.val().length > maxLength){
					return true;
				} else {
					return false;
				}
			}
			
			return false;
		},
		
		_checkMinLength : function(inputCheckItem, minLength){
			var type = inputCheckItem.attr('type');
			if(type == 'text' || type == 'password' || type == 'number'||
					inputCheckItem.is('textarea')) {
				if(inputCheckItem.val().length < minLength){
					return true;
				} else {
					return false;
				}
			}
			
			return false;
		},
		
	}
	
})(ShaCheck);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});
