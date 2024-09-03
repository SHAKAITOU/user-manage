//-------------------------------------------------------------------------------------------
//-----------------registStep1.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
PasswordForgetStep1 = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#PasswordForgetStep1Form');
	this.i18n = JSON.parse(this.jsContext.i18n);
};
ShaUtil.other.inherits(PasswordForgetStep1, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
PasswordForgetStep1.prototype.ID = {
	//btn
	BTN_NEXT							: 'btnNext',
	BTN_CANCEL			     			: 'btnCancel',
	BTN_SEND_AUTH_CODE			     	: 'btnSendAuthCode',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',	
	ITEM_STEP_STS                       : 'stepStatus',
	
	ITEM_PHONE					    	: 'phone',
	ITEM_MAIL						    : 'mail',
	ITEM_PHONE_AUTH_CODE				: 'authCode',

};
//------------------------------------------]

//---------------method define--------------[
//init
PasswordForgetStep1.prototype.init = function(){
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
PasswordForgetStep1.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_CANCEL
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CANCEL), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
			
			ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.loginTitle,
    			Pos.constants.setInfo.jsView.login.url_login_init, 
    			$('#index_form').serializeArray());
	    }
	);
	
	//init event to BTN_SEND_AUTH_CODE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SEND_AUTH_CODE), 
		function(event) {
			if(self.check2()) {
	           return;
	        }
	        
			ShaAjax.ajax.post(
	            self.jsContext.jsView.passwordForget.url_send_auth_code, 
	            self.getForm().serializeArray(), 
	            function(data){
					if (data != ''){
						ShaDialog.dialogs.alert(data);
					}else{
						ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_PHONE_AUTH_CODE));
						self.countDown = parseInt(Pos.constants.setInfo.common.auth_code_send_interval_minute)*60;
						self.buttonName = self.getObject(self.ID.BTN_SEND_AUTH_CODE).first().html();
						self.timeId = setInterval(function(){//更新倒计时显示
							if (self.countDown === 0) {
								clearInterval(self.timeId);
							   self.getObject(self.ID.BTN_SEND_AUTH_CODE).first().html(self.buttonName);
							   ShaInput.obj.enabled(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							   self.getObject(self.ID.BTN_SEND_AUTH_CODE).removeAttr('style');
							 } else {
							   self.countDown--;
							   //login.authCode.resend.msg
							   var msg = ShaUtil.util.format(self.i18n["login.authCode.resend.msg"], self.countDown);
							   self.getObject(self.ID.BTN_SEND_AUTH_CODE).first().text(msg);
							   //ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							   ShaInput.obj.disabled(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							 }
						}, 1000);
					}
	            }
	        );
	    }
	);
	
	//init event to BTN_NEXT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_NEXT), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        
			ShaAjax.pop.postDialogMiddleCenter(
    			self.i18n["PasswordForget.step2.title"],
    			self.jsContext.jsView.passwordForget.url_step2, 
    			self.getForm().serializeArray());
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
PasswordForgetStep1.prototype.check = function(){
	//keep self instance for call back
	var self = this;

	const checkMultiItemsMap = new Map();
	var inputCheckItemList = [
        [ self.i18n["m_user.phone"], 			self.getObject(self.ID.ITEM_PHONE)], 
		[ self.i18n["login.regist.step1.authCode"], 			self.getObject(self.ID.ITEM_PHONE_AUTH_CODE)]
    ];
	checkMultiItemsMap.set('checkNotBlank', inputCheckItemList);

	if (!ShaCheck.check.isBlank(self.getObject(self.ID.ITEM_PHONE).val())){
		checkMultiItemsMap.set('checkPhoneNumber', [[ self.i18n["m_user.phone"], 	self.getObject(self.ID.ITEM_PHONE)]]);
	}
	
	ShaCheck.check.setFirstItemFocus(true);
	if (ShaCheck.check.checkMultiItems(checkMultiItemsMap)){
		return true;
	}

	return false;
};

PasswordForgetStep1.prototype.check2 = function(){
	//keep self instance for call back
	var self = this;

	const checkMultiItemsMap = new Map();
	var inputCheckItemList = [
        [ self.i18n["m_user.phone"], 			self.getObject(self.ID.ITEM_PHONE)], 
    ];
	checkMultiItemsMap.set('checkNotBlank', inputCheckItemList);

	if (!ShaCheck.check.isBlank(self.getObject(self.ID.ITEM_PHONE).val())){
		checkMultiItemsMap.set('checkPhoneNumber', [[ self.i18n["m_user.phone"], 	self.getObject(self.ID.ITEM_PHONE)]]);
	}
	
	ShaCheck.check.setFirstItemFocus(true);
	if (ShaCheck.check.checkMultiItems(checkMultiItemsMap)){
		return true;
	}

	return false;
};

//initFocus
PasswordForgetStep1.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_PHONE));
};

//----------------------------------------------------------------------------]
