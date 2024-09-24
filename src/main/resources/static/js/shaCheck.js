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
		
		isMoveToFirstItem : false,
		_isAlreadyMoveToFistItem : false,
		
		_WATCH_INPUT_LIST : [],
		
		setFirstItemFocus : function(isMoveToFirstItem){
			ShaCheck.check.isMoveToFirstItem = isMoveToFirstItem;
			ShaCheck.check._isAlreadyMoveToFistItem = false;
		},
		
		moveToFirstItem : function(inputCheckItem){
			if (ShaCheck.check.isMoveToFirstItem && !ShaCheck.check._isAlreadyMoveToFistItem){
				ShaCheck.check._isAlreadyMoveToFistItem = true;
				inputCheckItem.focus();
			}
		},
		
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
				var errorMsg = errorItemName + ShaConstants.constants.NOT_BLANK_MSG;
				var isError = ShaCheck.check._checkNotBlank(inputCheckItem) ;
				ngFlag = isError || ngFlag;
				/*if(ShaCheck.check._checkNotBlank(inputCheckItem)){
					inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + errorMsg + '</div>'); 
					inputCheckItem.addClass('alert-input');
					inputCheckItem.addClass('is-invalid');
					ngFlag = true;
					ShaCheck.check.moveToFirstItem(inputCheckItem);
				}else{
					inputCheckItem.removeClass('alert-input');
					inputCheckItem.removeClass('is-invalid');
				}*/
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkNotNumber : function(inputCheckItemList){
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
				if(ShaCheck.check._checkNotNumber(inputCheckItem)){
					var errorMsg = errorItemName + ShaConstants.constants.NUMBER_ONLY_MSG;
					inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + errorMsg + '</div>'); 
					inputCheckItem.addClass('alert-input');
					inputCheckItem.addClass('is-invalid');
					ngFlag = true;
					ShaCheck.check.moveToFirstItem(inputCheckItem);
				}else{
					inputCheckItem.removeClass('alert-input');
					inputCheckItem.removeClass('is-invalid');
				}
			}
			
			return ngFlag;
		},
		checkNumberRange : function(inputCheckItemList){
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
				var maxValue = parseFloat(inputCheckItemList[i][2]);
				var minValue = parseFloat(inputCheckItemList[i][3]);
				var inputValue = inputCheckItem.val();
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				if (inputValue !=''){
					inputValue = parseFloat(inputValue);
					var errorMsg = '';
					if(minValue != maxValue){
						if (inputValue < minValue){
							errorMsg = ShaUtil.util.format(errorItemName + ShaConstants.constants.MIN_VALUE_MSG, minValue);
						}else if (inputValue > maxValue){
							errorMsg = ShaUtil.util.format(errorItemName + ShaConstants.constants.MAX_VALUE_MSG, maxValue);
						}
					}else if (inputValue != minValue){
						errorMsg = ShaUtil.util.format(errorItemName + ShaConstants.constants.REQUIRED_NUMBER_VAL_MSG, minValue);
					}
					if (errorMsg != ''){
						inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + errorMsg + '</div>'); 
						inputCheckItem.addClass('alert-input');
						inputCheckItem.addClass('is-invalid');
						ngFlag = true;
						ShaCheck.check.moveToFirstItem(inputCheckItem);
					}else{
						inputCheckItem.removeClass('alert-input');
						inputCheckItem.removeClass('is-invalid');
					}
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
					ShaCheck.check.moveToFirstItem(inputCheckItem);
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
					ShaCheck.check.moveToFirstItem(inputCheckItem);
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
				var errorMsg = ShaUtil.util.format(errorItemName + ShaConstants.constants.MIN_LENGTH_MSG, minLength);
				/*if(ShaCheck.check._checkMinLength(inputCheckItem, minLength)){
					var msg = ShaUtil.util.format(errorItemName + ShaConstants.constants.MIN_LENGTH_MSG, minLength);
					inputCheckItem.after('<div id="'+ errorId +'" class="invalid-feedback">' + msg + '</div>'); 
					inputCheckItem.addClass('alert-input');
					inputCheckItem.addClass('is-invalid');
					ngFlag = true;
					ShaCheck.check.moveToFirstItem(inputCheckItem);
				}else{
					inputCheckItem.removeClass('alert-input');
					inputCheckItem.removeClass('is-invalid');
				}*/
				var isError = ShaCheck.check._checkMinLength(inputCheckItem, minLength);
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
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
		
		checkConfirmPassword : function(inputCheckItemList){
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
				var inputCheckItemConfirm = inputCheckItemList[i][2];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				var errorMsg = errorItemName + Pos.constants.setInfo.jsView.login.msg_regist_pwNotSameError;
				var isError = inputCheckItem.val() != inputCheckItemConfirm.val();
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkPhoneNumber : function(inputCheckItemList){
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
				var isError = false;
				var errorMsg = '';
				
				if (ShaCheck.check.isBlank(inputCheckItem.val())){
					errorMsg = errorItemName + ShaConstants.constants.NOT_BLANK_MSG;
					isError = true;
				}
				if(!isError && ShaCheck.check._checkPhoneNumber(inputCheckItem)){
					errorMsg = errorItemName + ShaConstants.constants.IPHONE_NUMBER_MSG;
					isError = true;
				}
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkPhoneNumberExisted : function(inputCheckItemList, isExistedCheck=true){
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
				var isExistedCheck= inputCheckItemList[i][2];
				var userId= inputCheckItemList[i][3];
				var id = inputCheckItem.attr('id');
				var errorId = id + "_error";
				var isError = false;
				var errorMsg = '';
				if (ShaCheck.check.isBlank(inputCheckItem.val())){
					continue;
				}
				
				ShaAjax.ajax.post(
					Pos.constants.setInfo.common.common.url_com_check_phone_number_exist,
					"phoneNumber="+inputCheckItem.val()+"&userId="+userId,
					function(data){
						if (data =='existed'){
							if (isExistedCheck){
								errorMsg = errorItemName + ShaConstants.constants.IPHONE_NUMBER_EXISTED_MSG;
								isError = true;
							}
						}else if (!isExistedCheck){
							errorMsg = errorItemName + ShaConstants.constants.IPHONE_NUMBER_NOT_EXISTED_MSG;
							isError = true;
						}
					},
					false,
				)
				
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkEmail : function(inputCheckItemList){
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
				var isError = false;
				var errorMsg = '';
				if(ShaCheck.check._checkEmail(inputCheckItem)){
					errorMsg = errorItemName + ShaConstants.constants.EMAIL_MSG;
					isError = true;
				}
				
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkEmailExisted : function(inputCheckItemList){
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
				var isError = false;
				var errorMsg = '';

				ShaAjax.ajax.post(
					Pos.constants.setInfo.common.common.url_com_check_email_exist,
					"email="+inputCheckItem.val(),
					function(data){
						if (data =='existed'){
							errorMsg = errorItemName + ShaConstants.constants.EMAIL_EXISTED_MSG;
							isError = true;
						}
					},
					false
				)
				
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkUserCode : function(inputCheckItemList){
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
				var isError = false;
				var errorMsg = '';

				if (!inputCheckItem.val().startsWith('M')){
					errorMsg = ShaConstants.constants.USER_CODE_MSG;
					isError = true;
				}
				
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkUserCodeExisted : function(inputCheckItemList){
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
				var isError = false;
				var errorMsg = '';

				ShaAjax.ajax.post(
					Pos.constants.setInfo.common.common.url_com_check_user_code_exist,
					"userCode="+inputCheckItem.val(),
					function(data){
						if (data =='existed'){
							errorMsg = errorItemName + ShaConstants.constants.USER_CODE_EXISTED_MSG;
							isError = true;
						}
					},
					false
				)
				
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkAuthCode : function(inputCheckItemList){
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
				var isError = false;
				var errorMsg = '';
				if(ShaCheck.check.isBlank(inputCheckItem.val()) || inputCheckItem.val().length != 6){
					errorMsg = ShaConstants.constants.AUTHCODE_ERROR_MSG;
					isError = true;
				}
				
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkCreditCode : function(inputCheckItemList){
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
				var isError = false;
				var errorMsg = '';
				if(ShaCheck.check._checkCreditCode(inputCheckItem)){
					errorMsg = errorItemName + ShaConstants.constants.CREDIT_CODE_MSG;
					isError = true;
				}
				
				ngFlag = isError || ngFlag;
				ShaCheck.check._changeClass(inputCheckItem, isError, errorId, errorMsg);
			}
			
			return ngFlag;
		},
		
		checkMultiItems : function(inputCheckItemMap){
			var ngFlag = false;
			for (let [method, inputCheckItemList] of inputCheckItemMap) {
			//inputCheckItemMap.forEach((inputCheckItemList, method) => {
				var lowerCaseMethod = method.toLowerCase();
				switch (lowerCaseMethod) {
				  case 'checknotblank':
					ngFlag = ShaCheck.check.checkNotBlank(inputCheckItemList) || ngFlag;
				    continue;
				  case 'checknotnumber':
				    ngFlag = ShaCheck.check.checkNotNumber(inputCheckItemList) || ngFlag;
				    continue;
				  case 'checknumberrange':
				    ngFlag = ShaCheck.check.checkNumberRange(inputCheckItemList) || ngFlag;
				    continue;
				  case 'checkmaxlength':
				    ngFlag = ShaCheck.check.checkMaxLength(inputCheckItemList) || ngFlag;
				    continue;
				  case 'checkminlength':
				    ngFlag = ShaCheck.check.checkMinLength(inputCheckItemList) || ngFlag;
				    continue;
				  case 'checkphonenumber':
					ngFlag = ShaCheck.check.checkPhoneNumber(inputCheckItemList) || ngFlag;
					continue;
				  case 'checkphonenumberexisted':
					ngFlag = ShaCheck.check.checkPhoneNumberExisted(inputCheckItemList) || ngFlag;
					continue;
				  case 'checkemail':
					ngFlag = ShaCheck.check.checkEmail(inputCheckItemList) || ngFlag;
					continue;
				　　case 'checkusercode':
					ngFlag = ShaCheck.check.checkUserCode(inputCheckItemList) || ngFlag;
					continue;
				  case 'checkusercodeexisted':
					ngFlag = ShaCheck.check.checkUserCodeExisted(inputCheckItemList) || ngFlag;
					continue;
				  case 'checkconfirmpassword':
					ngFlag = ShaCheck.check.checkConfirmPassword(inputCheckItemList) || ngFlag;
					continue;
				  default:
				    console.log('Unknown check method:'+method);
				}
			};
			
			return ngFlag;
		},
		
		clearErrorClass : function(inputCheckItem){
			var id = inputCheckItem.attr('id');
			var errorId = id + "_error";
			$("#"+errorId).remove();
			inputCheckItem.removeClass('alert-input');
			inputCheckItem.removeClass('is-invalid');
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
		
		_checkNotNumber : function(inputCheckItem){
			var type = inputCheckItem.attr('type');
			if(type == 'text' || type == 'password' || type == 'number'||
					inputCheckItem.is('textarea')) {
				var reg = new RegExp("^[0-9]*$");
	            if (!inputCheckItem.val().match(reg)) {
	                return true;
				} else {
					return false;
				}
			}
			
			return false;
		}, 
		
		_checkPhoneNumber : function(inputCheckItem){
			var type = inputCheckItem.attr('type');
			if(type == 'text' || type == 'password' || type == 'number'||
					inputCheckItem.is('textarea')) {
				var reg = new RegExp(/^1[3-9]\d{9}$/);
	            if (!inputCheckItem.val().match(reg)) {
	                return true;
				} else {
					return false;
				}
			}
			
			return false;
		},
		//
		_checkEmail : function(inputCheckItem){
			var type = inputCheckItem.attr('type');
			if(type == 'text' || type == 'password' || type == 'number'||
					inputCheckItem.is('textarea')) {
				var reg = new RegExp(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/);
	            if (!inputCheckItem.val().match(reg)) {
	                return true;
				} else {
					return false;
				}
			}
			
			return false;
		},
		
		isBlank : function(str){
		    if (typeof str == 'undefined' || !str || str.length === 0 || str === "" || !/[^\s]/.test(str) || /^\s*$/.test(str) || str.replace(/\s/g,"") === ""){
		        return true;
		    }
		    else{
		        return false;
		    }
		},
		
		_checkCreditCode : function(inputCheckItem){
			var type = inputCheckItem.attr('type');
			if(type == 'text' || type == 'password' || type == 'number'||
					inputCheckItem.is('textarea')) {
				var reg = new RegExp(/^[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}$/);
	            if (!inputCheckItem.val().match(reg)) {
	                return true;
				} else {
					return false;
				}
			}
			
			return false;
		},
		
		_changeClass:function(inputCheckItem, isError, errorId, errorMsg){
			if(isError){
				//var span = inputCheckItem.parent().children("span.password").last();
				var span = inputCheckItem.parent().children().last();
				var errorDiv = '<div id="'+ errorId +'" class="invalid-feedback">' + errorMsg + '</div>';
				span.length ? span.after(errorDiv):inputCheckItem.after(errorDiv);
				inputCheckItem.addClass('alert-input');
				inputCheckItem.addClass('is-invalid');
				ngFlag = true;
				ShaCheck.check.moveToFirstItem(inputCheckItem);
			}else{
				inputCheckItem.removeClass('alert-input');
				inputCheckItem.removeClass('is-invalid');
			}
		}
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
