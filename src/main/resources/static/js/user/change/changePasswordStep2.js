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

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
	ITEM_NAME					    	: 'user_name',
	ITEM_PW  						    : 'user_password',
	ITEM_PWREP  					    : 'user_passwordRep',
	ITEM_BIRTH  					    : 'user_birth',
	
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
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
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
			
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.jsView.login.userRegist_confirm_title,
    			Pos.constants.setInfo.jsView.login.url_userRegist_commit, 
    			self.getForm().serializeArray()); 
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
RegistStep2.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["m_user.name"], 			self.getObject(self.ID.ITEM_NAME)], 
        [ self.i18n["m_user.password"], 		self.getObject(self.ID.ITEM_PW)], 
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};

//initFocus
RegistStep2.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_USER_CODE));
};

//----------------------------------------------------------------------------]
