//-------------------------------------------------------------------------------------------
//-----------------passwordChange view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
PasswordChange = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#PasswordChangeForm');
	this.i18n = JSON.parse(this.jsContext.i18n);
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(PasswordChange, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
PasswordChange.prototype.ID = {
	//btn
	BTN_CANCEL							: 'btnCancel',
	BTN_OK							    : 'btnOk',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
	ITEM_OLDPASSWORD					: 'oldPassword',
	ITEM_NEWPASSWORD  					: 'newPassword',
	ITEM_NEWPASSWORDREP  				: 'newPasswordRep',
	
	ITEM_OLDPASSWORDSHOW_SHOW_BTN  		: 'oldPasswordShow',
	ITEM_NEWPASSWORDSHOW_SHOW_BTN   	: 'newPasswordShow',
	ITEM_NEWPASSWORDREP_SHOW_BTN   	    : 'newPasswordRepShow',
};
//------------------------------------------]

//---------------method define--------------[
//init
PasswordChange.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	if(self.getObject(self.ID.ITEM_ERROR_MSG).val() != "") {
		//ShaDialog.dialogs.alert(self.getObject(self.ID.ITEM_ERROR_MSG).val());
	}
			
	self.initEvent();
	
    //set init focus item when page loaded
	self.initFocus();
};
// init event
PasswordChange.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to ITEM_OLDPASSWORDSHOW_SHOW_BTN
	ShaInput.button.onClick(self.getObject(self.ID.ITEM_OLDPASSWORDSHOW_SHOW_BTN), 
		function(event) {
			var iptObj = self.getObject(self.ID.ITEM_OLDPASSWORD);
			var btn  = self.getObject(self.ID.ITEM_OLDPASSWORDSHOW_SHOW_BTN);
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
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CANCEL), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        
			ShaDialog.dialogs.confirm(
						self.i18n["PasswordChange.confirm.edit.title"], 
						self.i18n["PasswordChange.confirm.edit.msg"], 
						function () {
		        ShaAjax.ajax.post(
					self.jsContext.jsView.passwordChange.url_edit, 
					self.form.serializeArray(), 
					function(data){
						if (data == Pos.constants.setInfo.common.executeReturnTypeOk) {
							ShaDialog.dialogs.success(self.i18n["PasswordChange.success.msg"]);
							ShaDialog.dialogs.dialogClose();
						} else {
							ShaDialog.dialogs.alert(self.i18n["PasswordChange.msg.oldPwMiss"]);
							ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_OLDPASSWORD));
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
PasswordChange.prototype.check = function(){
	//keep self instance for call back
	var self = this;
	var result = false;
    var inputCheckItemList = [
        [ self.i18n["PasswordChange.oldPassword"], 		self.getObject(self.ID.ITEM_OLDPASSWORD)], 
		[ self.i18n["PasswordChange.newPassword"], 		self.getObject(self.ID.ITEM_NEWPASSWORD)], 
		[ self.i18n["PasswordChange.newPasswordRep"],		self.getObject(self.ID.ITEM_NEWPASSWORDREP)],
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
PasswordChange.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_OLDPASSWORD));
};

//----------------------------------------------------------------------------]
