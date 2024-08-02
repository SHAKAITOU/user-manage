//-------------------------------------------------------------------------------------------
//-----------------loginBlock.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
LoginBlock = function(dataMap){
	this.form = $('#login_form');
	this.jsContext = Pos.constants.setInfo;
	this.i18n = JSON.parse(this.jsContext.i18n);
	this.dataMap = dataMap;
	this.countDown = 60;
	this.timeId = null;
};
ShaUtil.other.inherits(LoginBlock, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
LoginBlock.prototype.ID = {
	
	TAB_ID                   : "loginTab",
	TAB_TITLE_BY_PASS_ID     : "loginByPassTab",
	TAB_TITLE_BY_AUTH_ID     : "loginByAuthCodeTab",
	TAB_BODY_BY_PASS_ID      : "loginByPassTabBody",
    TAB_BODY_BY_AUTH_ID      : "loginByAuthCodeTabBody",
	//btn
	BTN_LOGIN							: 'loginBtn',
	BTN_REGIST							: 'registBtn',
	BTN_HELP							: 'helpBtn',
	BTN_REFRESH_IMG				    	: 'refreshImgBtn',
	BTN_SEND_AUTH_CODE					: 'sendAuthCodeBtn',
	BTN_LOGIN_BY_PHONE					: 'loginByPhoneBtn',
	
	//item
	ITEM_STORE_CODE						: 'storeCode',
	ITEM_USER_CODE						: 'userCode',
	ITEM_PASSWORD						: 'password',
	ITEM_PHONE_USER_CODE			    : 'phoneUserCode',
	ITEM_PHONE_AUTH_CODE				: 'phoneAuthCode',
	ITEM_HIDDEN_LOGIN_BY				: 'loginBy',
		
	ITEM_LOGIN_FORM_RETURN_ERROR_FLAG	: 'login_form_return_error_flag',
	ITEM_LOGIN_FORM_RETURN_ERROR_MSG	: 'login_form_return_error_msg',
	ITEM_AUTH_IMG                       : 'imgPhoto',
	
	//COUNTDOWN :60,
};
//------------------------------------------]

//---------------method define--------------[
//init
LoginBlock.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.initEvent();
	
	self.initFocus();
	console.log(self.getObject(self.ID.ITEM_HIDDEN_LOGIN_BY).val());
	if (self.getObject(self.ID.ITEM_HIDDEN_LOGIN_BY).val() === self.ID.TAB_TITLE_BY_AUTH_ID){
		self.getObject(self.ID.TAB_TITLE_BY_AUTH_ID).click();
	}else{
		self.getObject(self.ID.TAB_TITLE_BY_PASS_ID).click();
	}
	
	if(self.dataMap.errorMsg != null){
		ShaDialog.dialogs.alertWithCallBack(
			self.dataMap.errorMsg,
			function() {
				ShaRestful.restful.get(self.jsContext.jsView.login.url_logout, null);
			}
        );
	}
	
};
// init event
LoginBlock.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
    ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_BY_PASS_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_BY_PASS_ID, self.ID.TAB_BODY_BY_PASS_ID);
		}
	);
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_BY_AUTH_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_BY_AUTH_ID, self.ID.TAB_BODY_BY_AUTH_ID);
		}
	);
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_LOGIN), 
		function(event) {
	        if(self.checkValue()) {
	            return;
	        }
			
			ShaRestful.restful.post(
	        	self.getJsContext().jsView.login.url_user_login,
	        	self.getForm()
	        );
	    }
	);
	
	//init event to sendAuthCodeBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SEND_AUTH_CODE), 
		function(event) {
	        if(self.checkPhone()) {
	            return;
	        }
			
			ShaAjax.ajax.post(
	            self.getJsContext().jsView.login.url_send_auth_code, 
	            self.form.serializeArray(), 
	            function(data){
					if (data != ''){
						ShaDialog.dialogs.alert(data);
					}else{
						ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_PHONE_AUTH_CODE));
						self.countDown = parseInt(Pos.constants.setInfo.common.user_regist_sms_send_interval)*60;
						self.timeId = setInterval(function(){//更新倒计时显示
							if (self.countDown === 0) {
								clearInterval(self.timeId);
							   self.getObject(self.ID.BTN_SEND_AUTH_CODE).children().first().text(self.i18n["login.panel.btn.sendAuthCode"]);
							  // ShaInput.obj.enabledBtn(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							   ShaInput.obj.enabled(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							   self.getObject(self.ID.BTN_SEND_AUTH_CODE).removeAttr('style');
							 } else {
							   self.countDown--;
							   console.log(self.getObject(self.ID.BTN_SEND_AUTH_CODE).first());
							   //login.authCode.resend.msg
							   var msg = ShaUtil.util.format(self.i18n["login.authCode.resend.msg"], self.countDown);
							   self.getObject(self.ID.BTN_SEND_AUTH_CODE).children().first().text(msg);
							   //ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							   ShaInput.obj.disabled(self.getObject(self.ID.BTN_SEND_AUTH_CODE));
							 }
						}, 1000);
					}
	            }
	        );  
	    }
	);
		
	//init event to loginByPhoneBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_LOGIN_BY_PHONE), 
		function(event) {
	        if(self.checkValue()) {
	            return;
	        }
			
	        ShaRestful.restful.post(
	        	self.getJsContext().jsView.login.url_user_login,
	        	self.getForm()
	        );  
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REGIST), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				Pos.constants.setInfo.jsView.login.userRegist_title,
				Pos.constants.setInfo.jsView.login.url_userRegist, 
				$('#index_form').serializeArray());
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_HELP), 
		function(event) {
			window.open("/help");
	    }
	);
	
	
	//add btn enter event
    ShaInput.keyPress.enterPress(self.getObject(self.ID.BTN_LOGIN), function(){
    	self.getObject(self.ID.BTN_LOGIN).click();
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.BTN_REFRESH_IMG), 
		function(event) {
	        ShaAjax.ajax.post(
                self.getJsContext().jsView.login.url_refreshImg, 
                self.form.serializeArray(), 
                function(data){
					self.getObject(self.ID.ITEM_AUTH_IMG).attr("src", data);
                }
            ); 
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
LoginBlock.prototype.checkValue = function(){
	//keep self instance for call back
	var self = this;
	var result = false;
	var inputCheckItemList = [];
	var activeId = ShaInput.tab.getActivedTab(self.getForm(), self.ID.TAB_ID);
	ShaCheck.check.setFirstItemFocus(true);
	self.getObject(self.ID.ITEM_HIDDEN_LOGIN_BY).val(activeId);
	if (activeId === self.ID.TAB_TITLE_BY_PASS_ID){
	    inputCheckItemList = [
	    	[ self.getJsContext().jsView.login.label_storeCode, 	self.getObject(self.ID.ITEM_STORE_CODE)], 
	        [ self.getJsContext().jsView.login.label_user, 			self.getObject(self.ID.ITEM_USER_CODE)], 
	        [ self.getJsContext().jsView.login.label_pw, 			self.getObject(self.ID.ITEM_PASSWORD)],
	    ];
		result = ShaCheck.check.checkNotBlank(inputCheckItemList);
	}else{
		if (ShaCheck.check.checkPhoneNumber([[ self.getJsContext().jsView.login.label_phone_code, 	self.getObject(self.ID.ITEM_PHONE_USER_CODE)]])){
			result = true;		
		}else if (ShaCheck.check.checkPhoneNumberExisted([[ self.getJsContext().jsView.login.label_phone_code, 	self.getObject(self.ID.ITEM_PHONE_USER_CODE)]], false)){
			result = true;		
		}
		if (ShaCheck.check.checkAuthCode([[ self.getJsContext().jsView.login.label_phone_auth_code, self.getObject(self.ID.ITEM_PHONE_AUTH_CODE)]])){
			result = true;		
		}
	}
    
    return result;
};

//checkValue
LoginBlock.prototype.checkPhone = function(){
	//keep self instance for call back
	var self = this;
	ShaCheck.check.setFirstItemFocus(true);
	self.getObject(self.ID.ITEM_PHONE_AUTH_CODE).val("");
	ShaCheck.check.clearErrorClass(self.getObject(self.ID.ITEM_PHONE_AUTH_CODE));
	if (ShaCheck.check.checkPhoneNumber([[ self.getJsContext().jsView.login.label_phone_code, 	self.getObject(self.ID.ITEM_PHONE_USER_CODE)]])){
		return true;		
	}
	if (ShaCheck.check.checkPhoneNumberExisted([[ self.getJsContext().jsView.login.label_phone_code, 	self.getObject(self.ID.ITEM_PHONE_USER_CODE)]], false)){
		return true;		
	}

    return false;
};

// initFocus
LoginBlock.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_STORE_CODE));
};

//----------------------------------------------------------------------------]
