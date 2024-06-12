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
	BTN_BACK							: 'btnBack',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
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
RegistStep1.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["m_user.phone"], 			self.getObject(self.ID.ITEM_PHONE)], 
        [ self.i18n["m_user.mail"], 			self.getObject(self.ID.ITEM_MAIL)], 
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};

//initFocus
RegistStep1.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_USER_CODE));
};

//----------------------------------------------------------------------------]
