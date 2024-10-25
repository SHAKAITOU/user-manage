//-------------------------------------------------------------------------------------------
//-----------------registStep1.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BindPhoneChange = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#BindPhoneChangeForm');
	this.i18n = JSON.parse(this.jsContext.i18n);
};
ShaUtil.other.inherits(BindPhoneChange, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BindPhoneChange.prototype.ID = {
	//btn
	BTN_OK								: 'btnOk',
	BTN_CANCEL							: 'btnCancel',
	BTN_SEND_AUTH_CODE			     	: 'btnSendAuthCode',

	ITEM_ID								: 'id',
	ITEM_PHONE					    	: 'phone',
	ITEM_PHONE_AUTH_CODE				: 'authCode',

};
//------------------------------------------]

//---------------method define--------------[
//init
BindPhoneChange.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.initEvent();
	
    //set init focus item when page loaded
	self.initFocus();
};
// init event
BindPhoneChange.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_CANCEL
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CANCEL), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
	
	//init event to BTN_SEND_AUTH_CODE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SEND_AUTH_CODE), 
		function(event) {
			if(self.check2()) {
	           return;
	        }
	        
			ShaAjax.ajax.post(
	            self.jsContext.jsView.bindPhoneChange.url_send_auth_code, 
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
							   ShaInput.obj.disabled(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							 }
						}, 1000);
					}
	            }
	        );
	    }
	);
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        
			ShaAjax.ajax.post(
	            self.jsContext.jsView.bindPhoneChange.url_edit, 
	            self.getForm().serializeArray(), 
	            function(data){
					if (data != ''){
						ShaDialog.dialogs.alert(data);
					}else{
						ShaDialog.dialogs.success(self.i18n["BindPhoneChange.success.msg"]);
						ShaDialog.dialogs.dialogClose();
					}
	            }
	        );
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
BindPhoneChange.prototype.check = function(){
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
	
	if (ShaCheck.check.checkPhoneNumberExisted([[ self.i18n["m_user.phone"], 	self.getObject(self.ID.ITEM_PHONE), true, self.getObject(self.ID.ITEM_ID).val()]])){
		return true;
	}

	return false;
};

BindPhoneChange.prototype.check2 = function(){
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
	
	if (ShaCheck.check.checkPhoneNumberExisted([[ self.i18n["m_user.phone"], self.getObject(self.ID.ITEM_PHONE), true, self.getObject(self.ID.ITEM_ID).val()]])){
		return true;
	}

	return false;
};

//initFocus
BindPhoneChange.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_PHONE));
};

//----------------------------------------------------------------------------]
