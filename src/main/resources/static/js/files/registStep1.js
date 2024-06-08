//-------------------------------------------------------------------------------------------
//-----------------registStep1.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
RegistStep1 = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#registForm');
};
ShaUtil.other.inherits(RegistStep1, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
RegistStep1.prototype.ID = {
	//btn
	BTN_REGIST							: 'btnRegist',
	BTN_BACK							: 'btnBack',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
	ITEM_USER_CODE						: 'users_mail',

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
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.loginTitle,
    			Pos.constants.setInfo.jsView.login.url_login_init, 
    			$('#index_form').serializeArray()); 
	    }
	);
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REGIST), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.jsView.login.userRegist2_title,
    			Pos.constants.setInfo.jsView.login.url_userRegist2, 
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
        [ self.getJsContext().jsView.login.label_mail, 			self.getObject(self.ID.ITEM_USER_CODE)], 
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
