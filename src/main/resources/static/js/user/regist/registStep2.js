//-------------------------------------------------------------------------------------------
//-----------------registStep2.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
RegistStep2 = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#registForm');
	this.i18n = JSON.parse(this.jsContext.i18n);
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(RegistStep2, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
RegistStep2.prototype.ID = {
	//btn
	BTN_NEXT							: 'btnNext',
	BTN_BACK							: 'btnBack',
	BTN_CANCEL							: 'btnCancel',
	BTN_OK							    : 'btnOk',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
	ITEM_NAME					    	: 'user_name',
	ITEM_PW  						    : 'user_password',
	ITEM_PWREP  					    : 'user_passwordRep',
	ITEM_BIRTH  					    : 'user_birth',
	ITEM_EMPLOYER                       : "user_employer",
	
	
	ITEM_PW_SHOW_BTN  				    : 'user_passwordShow',
	ITEM_PWREP_SHOW_BTN   	    	    : 'user_passwordRepShow',

};
//------------------------------------------]

//---------------method define--------------[
//init
RegistStep2.prototype.init = function(){
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
RegistStep2.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.ITEM_PW_SHOW_BTN), 
		function(event) {
			var iptObj = self.getObject(self.ID.ITEM_PW);
			var btn  = self.getObject(self.ID.ITEM_PW_SHOW_BTN);
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
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.ITEM_PWREP_SHOW_BTN), 
		function(event) {
			var iptObj = self.getObject(self.ID.ITEM_PWREP);
			var btn  = self.getObject(self.ID.ITEM_PWREP_SHOW_BTN);
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
	
	//init event to BTN_BACK
    self.getObject(self.ID.ITEM_BIRTH).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CANCEL), 
			function(event) {
				
				//self.getObject(self.ID.ITEM_STEP_STS).val("STEP1_OUT");  //go out
	    		ShaAjax.pop.postDialogMiddleCenter(
	    			Pos.constants.setInfo.loginTitle,
	    			Pos.constants.setInfo.jsView.login.url_login_init, 
	    			$('#index_form').serializeArray()); 
		    }
		);
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        
	        //self.getObject(self.ID.ITEM_STEP_STS).val("STEP2");  //go step2
	        ShaAjax.ajax.post(
				Pos.constants.setInfo.jsView.login.url_userRegistFinal,
				self.getForm().serializeArray(),
				function(data){
					if (data == Pos.constants.setInfo.common.executeReturnTypeOk) {
						ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
						ShaAjax.pop.postDialogMiddleCenter(
				    			Pos.constants.setInfo.loginTitle,
				    			Pos.constants.setInfo.jsView.login.url_login_init, 
				    			$('#index_form').serializeArray()); 
					} else {
						ShaDialog.dialogs.alert(self.i18n["dialogs.fail.title"]);
					}
				}
			)
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
RegistStep2.prototype.check = function(){
	//keep self instance for call back
	var self = this;
	var result = false;
    var inputCheckItemList = [
        [ self.i18n["m_user.name"], 			self.getObject(self.ID.ITEM_NAME)], 
        [ self.i18n["m_user.password"], 		self.getObject(self.ID.ITEM_PW)], 
		[ self.i18n["m_user.passwordRep"], 		self.getObject(self.ID.ITEM_PWREP)], 
		[ self.i18n["m_user.birth"], 		    self.getObject(self.ID.ITEM_BIRTH)],
		[ self.i18n["m_user.employer"], 		self.getObject(self.ID.ITEM_EMPLOYER)],
    ];
    if (ShaCheck.check.checkNotBlank(inputCheckItemList)){
		return true;
	}
	
	if (ShaCheck.check.checkMinLength(			[
				       [ self.i18n["m_user.password"],self.getObject(self.ID.ITEM_PW), Pos.constants.setInfo.common.user_pw_min_l], 
					   [ self.i18n["m_user.passwordRep"],self.getObject(self.ID.ITEM_PWREP), Pos.constants.setInfo.common.user_pw_min_l], ]) || 
		ShaCheck.check.checkMaxLength(			[
				       [ self.i18n["m_user.password"],self.getObject(self.ID.ITEM_PW), Pos.constants.setInfo.common.user_pw_max_l], 
					   [ self.i18n["m_user.passwordRep"],self.getObject(self.ID.ITEM_PWREP), Pos.constants.setInfo.common.user_pw_max_l],
				     ])){
		result = true;
	}else{
		inputCheckItemList = [
			       [ "",self.getObject(self.ID.ITEM_PW), self.getObject(self.ID.ITEM_PWREP)], 
	    ];
		if (ShaCheck.check.checkConfirmPassword(inputCheckItemList)){
			result = true;
		}
	}
	
	return result;
};

//initFocus
RegistStep2.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_USER_CODE));
};

//----------------------------------------------------------------------------]
