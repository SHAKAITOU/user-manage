//-------------------------------------------------------------------------------------------
//-----------------registStep1.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
RegistStep1 = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#registForm');
	this.i18n = JSON.parse(this.jsContext.i18n);
};
ShaUtil.other.inherits(RegistStep1, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
RegistStep1.prototype.ID = {
	//btn
	BTN_NEXT							: 'btnNext',
	BTN_OUT			     				: 'btnOut',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',	
	ITEM_STEP_STS                       : 'stepStatus',
	
	ITEM_PHONE					    	: 'user_phone',
	ITEM_MAIL						    : 'user_mail',

};
//------------------------------------------]

//---------------method define--------------[
//init
RegistStep1.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	if(self.getObject(self.ID.ITEM_ERROR_MSG).val() != "") {
		ShaDialog.dialogs.alert(self.getObject(self.ID.ITEM_ERROR_MSG).val());
	}
	
	self.initEvent();
	
    //set init focus item when page loaded
	self.initFocus();
};
// init event
RegistStep1.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_OUT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OUT), 
		function(event) {
			
			self.getObject(self.ID.ITEM_STEP_STS).val("STEP1_OUT");  //go out
			
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.loginTitle,
    			Pos.constants.setInfo.jsView.login.url_login_init, 
    			$('#index_form').serializeArray()); 
	    }
	);
	
	//init event to BTN_NEXT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_NEXT), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        
			self.getObject(self.ID.ITEM_STEP_STS).val("STEP1_COMMIT");  //go step2
	        ShaAjax.ajax.post(
				Pos.constants.setInfo.jsView.login.url_userRegist_sendAuthCode,
				self.getForm().serializeArray(),
				function(data){
					if (data === "STEP1_NG") {
						//验证码发送失败
						ShaDialog.dialogs.alert(self.i18n["login.regist.step1.authCode.send.error"]);
					}else if (data === "STEP1_NG_SEND_SMS") {
						//X分钟以内只允许发送一次
						ShaDialog.dialogs.alert(ShaUtil.util.format(self.i18n["login.regist.step1.authCode.send.prohibit"], Pos.constants.setInfo.common.user_regist_sms_send_interval));
					}else {
						// go to step2
						self.getObject(self.ID.ITEM_STEP_STS).val("STEP1_COMMIT"); //go confirm
									
			    		ShaAjax.pop.postDialogMiddleCenter(
			    			Pos.constants.setInfo.jsView.login.userRegist_confirm_title,
			    			Pos.constants.setInfo.jsView.login.url_userRegist_commit, 
			    			self.getForm().serializeArray());
					}
				}
			)
	       /* //
	        self.getObject(self.ID.ITEM_STEP_STS).val("STEP1_COMMIT"); //go confirm
			
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.jsView.login.userRegist_confirm_title,
    			Pos.constants.setInfo.jsView.login.url_userRegist_commit, 
    			self.getForm().serializeArray()); */
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
RegistStep1.prototype.check = function(){
	//keep self instance for call back
	var self = this;

	const checkMultiItemsMap = new Map();
	var inputCheckItemList = [
        [ self.i18n["m_user.phone"], 			self.getObject(self.ID.ITEM_PHONE)], 
        [ self.i18n["m_user.mail"], 			self.getObject(self.ID.ITEM_MAIL)], 
    ];
	checkMultiItemsMap.set('checkNotBlank', inputCheckItemList);

	if (!ShaCheck.check.isBlank(self.getObject(self.ID.ITEM_PHONE).val())){
		checkMultiItemsMap.set('checkPhoneNumber', [[ self.i18n["m_user.phone"], 	self.getObject(self.ID.ITEM_PHONE)]]);
	}
	if (!ShaCheck.check.isBlank(self.getObject(self.ID.ITEM_MAIL).val())){
		checkMultiItemsMap.set('checkEmail', [[ self.i18n["m_user.mail"], 			self.getObject(self.ID.ITEM_MAIL)]]);
	}
	
	ShaCheck.check.setFirstItemFocus(true);
	if (ShaCheck.check.checkMultiItems(checkMultiItemsMap)){
		return true;
	}
	
	if (ShaCheck.check.checkPhoneNumberExisted([[ self.i18n["m_user.phone"], 	self.getObject(self.ID.ITEM_PHONE)]]) ||
		ShaCheck.check.checkEmailExisted([[ self.i18n["m_user.mail"], 			self.getObject(self.ID.ITEM_MAIL)]])){
		return true;
	}
	
	return false;
};

//initFocus
RegistStep1.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_PHONE));
};

//----------------------------------------------------------------------------]
