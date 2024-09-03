//-------------------------------------------------------------------------------------------
//-----------------registStep1.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
PasswordForgetStep2 = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#PasswordForgetStep2Form');
	this.i18n = JSON.parse(this.jsContext.i18n);
};
ShaUtil.other.inherits(PasswordForgetStep2, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
PasswordForgetStep2.prototype.ID = {
	//btn
	BTN_OK								: 'btnOk',
	BTN_BACK							: 'btnBack',
	BTN_CANCEL							: 'btnCancel',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',	
	ITEM_STEP_STS                       : 'stepStatus',
	
	ITEM_PHONE					    	: 'phone',
	ITEM_MAIL						    : 'mail',
	ITEM_NEWPASSWORD  					: 'newPassword',
	ITEM_NEWPASSWORDREP  				: 'newPasswordRep',
	
	ITEM_NEWPASSWORDSHOW_SHOW_BTN   	: 'newPasswordShow',
	ITEM_NEWPASSWORDREP_SHOW_BTN   	    : 'newPasswordRepShow',

};
//------------------------------------------]

//---------------method define--------------[
//init
PasswordForgetStep2.prototype.init = function(){
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
PasswordForgetStep2.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to ITEM_NEWPASSWORDSHOW_SHOW_BTN
	ShaInput.button.onClick(self.getObject(self.ID.ITEM_NEWPASSWORDSHOW_SHOW_BTN), 
		function(event) {
			var iptObj = self.getObject(self.ID.ITEM_NEWPASSWORD);
			var btn  = self.getObject(self.ID.ITEM_NEWPASSWORDSHOW_SHOW_BTN);
    		if (iptObj.attr('type') === "text") {
				iptObj.attr('type', "password");
				btn.removeClass("fa fa-eye");
				btn.addClass("fa fa-eye-slash");
			} else {
				iptObj.attr('type', "text");
				btn.removeClass("fa fa-eye-slash");
				btn.addClass("fa fa-eye");
			}
	    }
	);
	
	//init event to ITEM_NEWPASSWORDREP_SHOW_BTN
	ShaInput.button.onClick(self.getObject(self.ID.ITEM_NEWPASSWORDREP_SHOW_BTN), 
		function(event) {
			var iptObj = self.getObject(self.ID.ITEM_NEWPASSWORDREP);
			var btn  = self.getObject(self.ID.ITEM_NEWPASSWORDREP_SHOW_BTN);
    		if (iptObj.attr('type') === "text") {
				iptObj.attr('type', "password");
				btn.removeClass("fa fa-eye");
				btn.addClass("fa fa-eye-slash");
			} else {
				iptObj.attr('type', "text");
				btn.removeClass("fa fa-eye-slash");
				btn.addClass("fa fa-eye");
			}
	    }
	);
	
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
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
			//self.getObject(self.ID.ITEM_STEP_STS).val("STEP1_NG");  //go back
    		ShaAjax.pop.postDialogMiddleCenter(
				self.i18n["PasswordForget.step1.title"],
				self.jsContext.jsView.passwordForget.url_init,
				self.getForm().serializeArray());
	    }
	);
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        
			ShaDialog.dialogs.confirm(
				self.i18n["PasswordForget.confirm.edit.title"], 
				self.i18n["PasswordForget.confirm.edit.msg"], 
				function () {
		        ShaAjax.ajax.post(
					self.jsContext.jsView.passwordForget.url_edit, 
					self.form.serializeArray(), 
					function(data){
						if (data == Pos.constants.setInfo.common.executeReturnTypeOk) {
							ShaDialog.dialogs.success(self.i18n["PasswordForget.success.msg"]);
							self.getObject(self.ID.BTN_CANCEL).click();
						} else {
							ShaDialog.dialogs.alert(self.i18n["dialogs.fail.title"]);
						}
					}
				)
			});
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
PasswordForgetStep2.prototype.check = function(){
	var self = this;
	var result = false;
    var inputCheckItemList = [
		[ self.i18n["PasswordChange.newPassword"], 		self.getObject(self.ID.ITEM_NEWPASSWORD)], 
		[ self.i18n["PasswordChange.newPasswordRep"],	self.getObject(self.ID.ITEM_NEWPASSWORDREP)],
    ];
    if (ShaCheck.check.checkNotBlank(inputCheckItemList)){
		return true;
	}
	
	if (ShaCheck.check.checkMinLength(			[
				       [ self.i18n["PasswordChange.newPassword"],self.getObject(self.ID.ITEM_NEWPASSWORD), Pos.constants.setInfo.common.user_pw_min_l], 
					   [ self.i18n["PasswordChange.newPasswordRep"],self.getObject(self.ID.ITEM_NEWPASSWORDREP), Pos.constants.setInfo.common.user_pw_min_l], ]) || 
		ShaCheck.check.checkMaxLength(			[
				       [ self.i18n["PasswordChange.newPassword"],self.getObject(self.ID.ITEM_NEWPASSWORD), Pos.constants.setInfo.common.user_pw_max_l], 
					   [ self.i18n["PasswordChange.newPasswordRep"],self.getObject(self.ID.ITEM_NEWPASSWORDREP), Pos.constants.setInfo.common.user_pw_max_l],
				     ])){
		result = true;
	}else{
		inputCheckItemList = [
			       [ "",self.getObject(self.ID.ITEM_NEWPASSWORD), self.getObject(self.ID.ITEM_NEWPASSWORDREP)], 
	    ];
		if (ShaCheck.check.checkConfirmPassword(inputCheckItemList)){
			result = true;
		}
	}

	return result;
};

//initFocus
PasswordForgetStep2.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_NEWPASSWORD));
};

//----------------------------------------------------------------------------]
