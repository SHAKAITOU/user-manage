//-------------------------------------------------------------------------------------------
//-----------------registStep1.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
RegistStep1Confirm = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#registForm');
	this.i18n = JSON.parse(this.jsContext.i18n);
};
ShaUtil.other.inherits(RegistStep1Confirm, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
RegistStep1Confirm.prototype.ID = {
	//btn
	BTN_CANCEL							: 'btnCancel',
	BTN_NEXT							: 'btnNext',
	BTN_BACK							: 'btnBack',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
	ITEM_AUTH_CODE				    	: 'registForm_authCode',

};
//------------------------------------------]

//---------------method define--------------[
//init
RegistStep1Confirm.prototype.init = function(){
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
RegistStep1Confirm.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CANCEL), 
		function(event) {
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.loginTitle,
    			Pos.constants.setInfo.jsView.login.url_login_init, 
    			$('#index_form').serializeArray()); 
	    }
	);
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
    		ShaAjax.pop.postDialogMiddleCenter(
				Pos.constants.setInfo.jsView.login.userRegist_title,
				Pos.constants.setInfo.jsView.login.url_userRegist, 
				self.getForm().serializeArray());
	    }
	);
	
	//init event to BTN_NEXT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_NEXT), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.jsView.login.userRegist2_title,
    			Pos.constants.setInfo.jsView.login.url_userRegist_confirm, 
    			self.getForm().serializeArray()); 
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
RegistStep1Confirm.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["login.regist.step1.authCode"], 			self.getObject(self.ID.ITEM_AUTH_CODE)], 
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};

//initFocus
RegistStep1Confirm.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_USER_CODE));
};

//----------------------------------------------------------------------------]
